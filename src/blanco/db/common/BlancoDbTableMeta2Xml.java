/**
 * ���̃N���X�͕ʃv���_�N�g�������\��B
 */
package blanco.db.common;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import blanco.commons.sql.format.BlancoSqlFormatter;
import blanco.commons.sql.format.BlancoSqlFormatterException;
import blanco.commons.sql.format.BlancoSqlRule;
import blanco.commons.util.BlancoNameAdjuster;
import blanco.db.common.resourcebundle.BlancoDbCommonResourceBundle;
import blanco.db.common.stringgroup.BlancoDbDriverNameStringGroup;
import blanco.db.common.stringgroup.BlancoDbSqlInfoScrollStringGroup;
import blanco.db.common.stringgroup.BlancoDbSqlInfoTypeStringGroup;
import blanco.db.common.util.BlancoDbUtil;
import blanco.db.common.util.BlancoDbXmlSerializer;
import blanco.db.common.valueobject.BlancoDbSetting;
import blanco.db.common.valueobject.BlancoDbSqlInfoStructure;
import blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure;
import blanco.dbmetadata.valueobject.BlancoDbMetaDataTableStructure;

/**
 * �����[�V���i���f�[�^�x�[�X���瓾����\�������ƂɁA�P��\�A�N�Z�T�̂��߂�XML���ԃt�@�C���𐶐����܂��B
 * 
 * �����[�V���i���f�[�^�x�[�X���烁�^�������o���������̂��̂́A���̃N���X�ł͂Ȃ��A�ʂ̃v���_�N�g blancoDbMetaData���S���܂��B
 */
public abstract class BlancoDbTableMeta2Xml implements IBlancoDbProgress {
    /**
     * �P��\�̃N���X�ɕt������v���t�B�b�N�X�B
     */
    public static final String CLASS_PREFIX = "Simple";

    /**
     * �e�탊�\�[�X�o���h���B
     */
    private final BlancoDbCommonResourceBundle fBundle = new BlancoDbCommonResourceBundle();

    /**
     * blancoDb�Ɋւ���ݒ���B
     */
    private BlancoDbSetting fDbSetting = null;

    /**
     * ������������SQL�����t�H�[�}�b�g���邩�ǂ����B
     * 
     * 2006.12.01���_�ł̓f�t�H���g�� false�Ƃ��܂��B
     */
    private boolean fFormatSql = false;

    /**
     * ������������SQL�����t�H�[�}�b�g���邩�ǂ����B
     * 
     * @param argFormatSql
     */
    public void setFormatSql(final boolean argFormatSql) {
        fFormatSql = argFormatSql;
    }

    /**
     * �����[�V���i���f�[�^�x�[�X���瓾����\�������ƂɁA�P��\�A�N�Z�T�̂��߂�XML���ԃt�@�C���𐶐����܂��B
     * 
     * �����[�V���i���f�[�^�x�[�X���烁�^�������o���������̂��̂́A���̃N���X�ł͂Ȃ��A�ʂ̃v���_�N�g blancoDbMetaData���S���܂��B
     * 
     * @param connDef
     *            �f�[�^�x�[�X�ڑ����B
     * @param blancoSqlDirectory
     *            �o�͐�f�B���N�g���B
     * @throws SQLException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws ClassNotFoundException
     */
    public void process(final BlancoDbSetting dbSetting,
            final File blancoSqlDirectory) throws SQLException {
        System.out.println(BlancoDbCommonConstants.PRODUCT_NAME + " ("
                + BlancoDbCommonConstants.VERSION + ") �P��\�A�N�Z�TSQL��������: �J�n.");
        fDbSetting = dbSetting;
        Connection conn = null;
        try {
            conn = BlancoDbUtil.connect(dbSetting);
            BlancoDbUtil.getDatabaseVersionInfo(conn, dbSetting);

            final List<BlancoDbMetaDataTableStructure> listTables = new BlancoDbTableParser()
                    .parse(conn, dbSetting.getSchema());

            serializeTable(conn, listTables,
                    blancoSqlDirectory.getAbsolutePath());

        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e.toString(), e);
        } finally {
            BlancoDbUtil.close(conn);
            conn = null;
            System.out.println("�P��\�A�N�Z�TSQL��������: �I��.");
        }
    }

    /**
     * �\�̒P�ʂŎ��W���ꂽ����XML�t�@�C���ɏ����o���܂��B
     * 
     * @param dbInfoCollector
     * @param listTables
     * @param outputDirectoryName
     * @throws SQLException
     */
    private void serializeTable(final Connection conn,
            final List<BlancoDbMetaDataTableStructure> listTables,
            final String outputDirectoryName) throws SQLException {

        for (int index = 0; index < listTables.size(); index++) {
            final List<BlancoDbSqlInfoStructure> resultSqlInfo = new ArrayList<BlancoDbSqlInfoStructure>();

            final BlancoDbMetaDataTableStructure table = listTables.get(index);
            if (progress(index + 1, listTables.size(), table.getName()) == false) {
                break;
            }

            try {
                System.out.println("�\[" + table.getName() + "]���������܂�");
                processEveryTable(listTables, table, resultSqlInfo);
            } catch (StringIndexOutOfBoundsException ex) {
                System.out.println("�\[" + table.getName()
                        + "]�̏����̉ߒ��ŗ�O���������܂���: " + ex.toString());
                ex.printStackTrace();

                // ��O�������ɂ́A�d���������̂� ���̕\���������܂��B
                conn.rollback();
                continue;
            }

            // �\�̒P�ʂ�SQL����XML�t�@�C���ɏo�͂��܂��B
            BlancoDbXmlSerializer.serialize(resultSqlInfo,
                    new File(outputDirectoryName + "/SimpleTable"
                            + BlancoNameAdjuster.toClassName(table.getName())
                            + ".xml"));
        }
    }

    /**
     * ���̂��̂̕\���o�͏������܂��B
     * 
     * @param service
     * @param collector
     * @param metadata
     * @param document
     * @param eleRoot
     * @param table
     * @throws SQLException
     */
    private void processEveryTable(
            final List<BlancoDbMetaDataTableStructure> listTables,
            final BlancoDbMetaDataTableStructure table,
            final List<BlancoDbSqlInfoStructure> resultSqlInfo)
            throws SQLException {
        generateSelect(listTables, table, resultSqlInfo);

        // �X�V�\�J�[�\�������p�\���ǂ����̓��\�b�h���Ŕ��f���܂��B
        generateSelectUpdatable(listTables, table, resultSqlInfo);

        generateSelectColumn(listTables, table, resultSqlInfo);

        // 2005.11.11 SelectAll���\�b�h�͕������܂����B
        generateSelectAll(listTables, table, resultSqlInfo);

        generateInsert(listTables, table, resultSqlInfo, false);
        generateInsert(listTables, table, resultSqlInfo, true);

        generateUpdate(listTables, table, resultSqlInfo);

        generateDelete(listTables, table, resultSqlInfo);
    }

    /**
     * �\���݂̂ɂ��āA�܂��̓N���X���ɕϊ����܂��B
     * 
     * @param table
     * @return
     */
    private String getBaseClassName(final BlancoDbMetaDataTableStructure table) {
        return BlancoNameAdjuster.toClassName(table.getName());
    }

    /**
     * ��s����������A�N�Z�T�𐶐����܂��B
     * 
     * @param collector
     * @param metadata
     * @param table
     * @param document
     * @param eleRoot
     * @throws SQLException
     */
    private void generateSelect(
            final List<BlancoDbMetaDataTableStructure> listTables,
            final BlancoDbMetaDataTableStructure table,
            final List<BlancoDbSqlInfoStructure> resultSqlInfo)
            throws SQLException {

        final String name = CLASS_PREFIX + getBaseClassName(table) + "Select";

        final BlancoDbSqlInfoStructure sqlInfo = new BlancoDbSqlInfoStructure();
        sqlInfo.setName(name);
        sqlInfo.setType(BlancoDbSqlInfoTypeStringGroup.ITERATOR);
        sqlInfo.setSingle(true);
        sqlInfo.setScroll(new BlancoDbSqlInfoScrollStringGroup()
                .convertToInt(fBundle.getSelectScroll()));
        sqlInfo.setUpdatable(false);

        final StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");

        boolean isFirstColumn = true;
        for (int indexCol = 0; indexCol < table.getColumns().size(); indexCol++) {
            final BlancoDbMetaDataColumnStructure columnStructure = table
                    .getColumns().get(indexCol);

            if (isSkipTypeForSimpleTable(columnStructure)) {
                // �P��\�Ƃ��Ă̓X�L�b�v���ׂ��^�ł��B
                continue;
            }

            if (isFirstColumn) {
                isFirstColumn = false;
            } else {
                sql.append(", ");
            }
            sql.append(columnStructure.getName());
        }

        if (isFirstColumn) {
            // �ЂƂ��񂪏�������܂���ł����B�������f���܂��B
            return;
        }

        sql.append("\n  FROM " + escapeSqlName(table.getName()));

        boolean isFirstPrimaryKey = true;
        for (int indexCol = 0; indexCol < table.getColumns().size(); indexCol++) {
            final BlancoDbMetaDataColumnStructure columnStructure = table
                    .getColumns().get(indexCol);
            if (BlancoDbUtil.isPrimaryKey(table, columnStructure)) {
                if (isSkipTypeForSimpleTable(columnStructure)) {
                    // �P��\�Ƃ��Ă̓X�L�b�v���ׂ��^�ł��B
                    // �o�C�i���⃊�[�_�̓L�[�Ƃ��Ă͗��p�ł��܂���B
                    continue;
                }

                if (isFirstPrimaryKey) {
                    isFirstPrimaryKey = false;
                    sql.append("\n WHERE ");
                } else {
                    sql.append("\n   AND ");
                }

                final BlancoDbMetaDataColumnStructure wrkStructure = cloneColumnStructure(columnStructure);
                if (fFormatSql) {
                    wrkStructure.setName("inParam"
                            + BlancoNameAdjuster.toClassName(wrkStructure
                                    .getName()));
                } else {
                    wrkStructure.setName(BlancoNameAdjuster
                            .toParameterName(wrkStructure.getName()));
                }

                sql.append(columnStructure.getName() + " = #"
                        + wrkStructure.getName());

                sqlInfo.getInParameterList().add(wrkStructure);
            }
        }

        if (isFirstPrimaryKey) {
            // �v���C�}���[�L�[���ꌏ����������Ă��Ȃ��ۂɂ́A
            // WHERE���쐬����Ă��܂���B
            // �������s�͊댯�Ɣ��f���A�������f���܂��B
            return;
        }

        sqlInfo.setQuery(sql.toString());
        if (fFormatSql) {
            try {
                sqlInfo.setQuery(getSqlFormatter().format(sqlInfo.getQuery()));
            } catch (BlancoSqlFormatterException e) {
                // ���������Ȃ��̂ŁA���̂܂ܐi�݂܂��B
                e.printStackTrace();
            }
        }

        // �Ō�̍Ō�Ń��[�g�m�[�h�ɒǉ����܂��B
        resultSqlInfo.add(sqlInfo);
    }

    /**
     * �X�V�\�Ȍ����𐶐����܂��B
     * 
     * @param collector
     * @param metadata
     * @param table
     * @param document
     * @param eleRoot
     * @throws SQLException
     */
    private void generateSelectUpdatable(
            final List<BlancoDbMetaDataTableStructure> listTables,
            final BlancoDbMetaDataTableStructure table,
            final List<BlancoDbSqlInfoStructure> resultSqlInfo)
            throws SQLException {

        switch (fDbSetting.getDriverName()) {
        case BlancoDbDriverNameStringGroup.SQLSERVER_2000:
        case BlancoDbDriverNameStringGroup.SQLSERVER_2005:
        case BlancoDbDriverNameStringGroup.ORACLE:
        case BlancoDbDriverNameStringGroup.POSTGRESQL:
            // blancoDb�Ƃ��čX�V�\�Ȍ����ɑΉ����Ă���f�[�^�x�[�X�ł��B�����\�ł��B
            break;
        default:
            // �����ł��܂���B
            return;
        }

        final String name = CLASS_PREFIX + getBaseClassName(table)
                + "SelectUpdatable";

        final BlancoDbSqlInfoStructure sqlInfo = new BlancoDbSqlInfoStructure();
        sqlInfo.setName(name);
        sqlInfo.setType(BlancoDbSqlInfoTypeStringGroup.ITERATOR);
        sqlInfo.setSingle(false);
        sqlInfo.setScroll(new BlancoDbSqlInfoScrollStringGroup()
                .convertToInt(fBundle.getSelectUpdatableScroll()));
        sqlInfo.setUpdatable(true);

        final StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");

        boolean isFirstColumn = true;
        for (int indexCol = 0; indexCol < table.getColumns().size(); indexCol++) {
            final BlancoDbMetaDataColumnStructure columnStructure = table
                    .getColumns().get(indexCol);
            // �X�V�\�œ��������߂ɁA�S����擾���Ă��܂��B

            if (isSkipTypeForSimpleTable(columnStructure)) {
                // �P��\�Ƃ��Ă̓X�L�b�v���ׂ��^�ł��B
                continue;
            }

            if (isFirstColumn) {
                isFirstColumn = false;
            } else {
                sql.append(", ");
            }
            sql.append(columnStructure.getName());
        }

        if (isFirstColumn) {
            // �ЂƂ��񂪏�������܂���ł����B�������f���܂��B
            return;
        }

        sql.append("\n  FROM " + escapeSqlName(table.getName()));

        switch (fDbSetting.getDriverName()) {
        case BlancoDbDriverNameStringGroup.SQLSERVER_2000:
        case BlancoDbDriverNameStringGroup.SQLSERVER_2005:
            sql.append(" WITH (UPDLOCK)");
            break;
        }

        boolean isFirstPrimaryKey = true;
        for (int indexCol = 0; indexCol < table.getColumns().size(); indexCol++) {
            final BlancoDbMetaDataColumnStructure columnStructure = table
                    .getColumns().get(indexCol);
            if (BlancoDbUtil.isPrimaryKey(table, columnStructure)) {
                if (isSkipTypeForSimpleTable(columnStructure)) {
                    // �P��\�Ƃ��Ă̓X�L�b�v���ׂ��^�ł��B
                    // �o�C�i���͌����L�[�ɗ��p�ł��܂���B
                    continue;
                }

                if (isFirstPrimaryKey) {
                    isFirstPrimaryKey = false;
                    sql.append("\n WHERE ");
                } else {
                    sql.append("\n   AND ");
                }

                final BlancoDbMetaDataColumnStructure wrkStructure = cloneColumnStructure(columnStructure);
                if (fFormatSql) {
                    wrkStructure.setName("inParam"
                            + BlancoNameAdjuster.toClassName(wrkStructure
                                    .getName()));
                } else {
                    wrkStructure.setName(BlancoNameAdjuster
                            .toParameterName(wrkStructure.getName()));
                }

                sql.append(columnStructure.getName() + " = #"
                        + wrkStructure.getName());

                sqlInfo.getInParameterList().add(wrkStructure);
            }
        }

        if (isFirstPrimaryKey) {
            // �v���C�}���[�L�[���ꌏ����������Ă��Ȃ��ۂɂ́A
            // WHERE���쐬����Ă��܂���B
            // �������s�͊댯�Ɣ��f���A�������f���܂��B
            return;
        }

        switch (fDbSetting.getDriverName()) {
        case BlancoDbDriverNameStringGroup.ORACLE:
        case BlancoDbDriverNameStringGroup.POSTGRESQL:
            sql.append(" FOR UPDATE");
            break;
        }

        sqlInfo.setQuery(sql.toString());
        if (fFormatSql) {
            try {
                sqlInfo.setQuery(getSqlFormatter().format(sqlInfo.getQuery()));
            } catch (BlancoSqlFormatterException e) {
                // ���������Ȃ��̂ŁA���̂܂ܐi�݂܂��B
                e.printStackTrace();
            }
        }

        // �Ō�̍Ō�Ń��[�g�m�[�h�ɒǉ����܂��B
        resultSqlInfo.add(sqlInfo);
    }

    /**
     * InputStream�����Reader�Ƀ}�b�v�����^�ɂ��āA�ʂ�Iterator�𐶐����܂��B
     * 
     * ����Iterator�ȊO�ł� InputStream�����Reader�Ƀ}�b�v�����^�͍��ڂ�����Ƃ��Ă͐������X�L�b�v����܂��B
     * 
     * @param collector
     * @param metadata
     * @param table
     * @param document
     * @param eleRoot
     * @throws SQLException
     */
    private void generateSelectColumn(
            final List<BlancoDbMetaDataTableStructure> listTables,
            final BlancoDbMetaDataTableStructure table,
            final List<BlancoDbSqlInfoStructure> resultSqlInfo)
            throws SQLException {

        for (int indexCol = 0; indexCol < table.getColumns().size(); indexCol++) {
            final BlancoDbMetaDataColumnStructure columnStructure = table
                    .getColumns().get(indexCol);

            if (isSkipTypeForSimpleTable(columnStructure) == false) {
                // �����ł̓o�C�i������у��[�_�u�̂݁v���������܂��B
                // �����̉ӏ��Ɣ�����������]���Ă���_�ɒ��ڂ��Ă��������B
                continue;
            }

            final String name = CLASS_PREFIX + getBaseClassName(table)
                    + "Column"
                    + BlancoNameAdjuster.toClassName(columnStructure.getName());

            final BlancoDbSqlInfoStructure sqlInfo = new BlancoDbSqlInfoStructure();
            sqlInfo.setName(name);
            sqlInfo.setType(BlancoDbSqlInfoTypeStringGroup.ITERATOR);

            // BINARY�����ASCIISTREAM�̗�ւ̃A�N�Z�T���P�s����t�ŏo�͂��邩�ǂ����B
            // SQL Server 2000�ɂ����ẮA�P�s����t�Ő������s���ƁA�P�s�����getSingleRow���\�b�h����
            // next() �{ next() ��2�x�Ăяo�����s�������_�ŁA��x�ڂ̌������ʂ�
            // �X�g���[�������Ă��܂����Ƃ��m���Ă��܂��B
            // ���̂悤�Ȕw�i����A�f�t�H���g�� false �ł��� ��P�s����Ƃ������ł��B
            sqlInfo.setSingle(fBundle.getSimpleColBinaryAsciiSelectSinglerow()
                    .equals("true"));
            sqlInfo.setScroll(new BlancoDbSqlInfoScrollStringGroup()
                    .convertToInt(fBundle.getSelectScroll()));
            sqlInfo.setUpdatable(false);

            final StringBuffer sql = new StringBuffer();
            sql.append("SELECT ");
            sql.append(columnStructure.getName());
            sql.append("\n FROM " + escapeSqlName(table.getName()));

            boolean isFirstPrimaryKey = true;
            for (int indexPrimaryKey = 0; indexPrimaryKey < table.getColumns()
                    .size(); indexPrimaryKey++) {
                final BlancoDbMetaDataColumnStructure columnPrimaryKey = table
                        .getColumns().get(indexPrimaryKey);

                if (isSkipTypeForSimpleTable(columnPrimaryKey)) {
                    // �����Ƃ��Ă̓X�L�b�v���ׂ��^�ł��B
                    continue;
                }

                if (BlancoDbUtil.isPrimaryKey(table, columnPrimaryKey) == false) {
                    continue;
                }

                if (isFirstPrimaryKey) {
                    isFirstPrimaryKey = false;
                    sql.append("\n WHERE ");
                } else {
                    sql.append("\n   AND ");
                }

                final BlancoDbMetaDataColumnStructure wrkStructure = cloneColumnStructure(columnPrimaryKey);
                if (fFormatSql) {
                    wrkStructure.setName("inParam"
                            + BlancoNameAdjuster.toClassName(wrkStructure
                                    .getName()));
                } else {
                    wrkStructure.setName(BlancoNameAdjuster
                            .toParameterName(wrkStructure.getName()));
                }

                sql.append(columnPrimaryKey.getName() + " = #"
                        + wrkStructure.getName());

                sqlInfo.getInParameterList().add(wrkStructure);
            }

            if (isFirstPrimaryKey) {
                // �v���C�}���[�L�[���ꌏ����������Ă��Ȃ��ۂɂ́A
                // WHERE���쐬����Ă��܂���B
                // �������s�͊댯�Ɣ��f���A�������f���܂��B
                return;
            }

            sqlInfo.setQuery(sql.toString());
            if (fFormatSql) {
                try {
                    sqlInfo.setQuery(getSqlFormatter().format(
                            sqlInfo.getQuery()));
                } catch (BlancoSqlFormatterException e) {
                    // ���������Ȃ��̂ŁA���̂܂ܐi�݂܂��B
                    e.printStackTrace();
                }
            }

            // �Ō�̍Ō�Ń��[�g�m�[�h�ɒǉ����܂��B
            resultSqlInfo.add(sqlInfo);
        }
    }

    /**
     * �S���ڂ���������Iterator�𐶐����܂��B
     * 
     * @param collector
     * @param metadata
     * @param table
     * @param document
     * @param eleRoot
     * @throws SQLException
     */
    private void generateSelectAll(
            final List<BlancoDbMetaDataTableStructure> listTables,
            final BlancoDbMetaDataTableStructure table,
            final List<BlancoDbSqlInfoStructure> resultSqlInfo)
            throws SQLException {

        final String name = CLASS_PREFIX + getBaseClassName(table)
                + "SelectAll";

        final BlancoDbSqlInfoStructure sqlInfo = new BlancoDbSqlInfoStructure();
        sqlInfo.setName(name);
        sqlInfo.setType(BlancoDbSqlInfoTypeStringGroup.ITERATOR);
        sqlInfo.setSingle(false);
        sqlInfo.setScroll(new BlancoDbSqlInfoScrollStringGroup()
                .convertToInt(fBundle.getSelectAllScroll()));
        sqlInfo.setUpdatable(false);

        final StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");

        boolean isFirstColumn = true;
        for (int indexCol = 0; indexCol < table.getColumns().size(); indexCol++) {
            final BlancoDbMetaDataColumnStructure columnStructure = table
                    .getColumns().get(indexCol);

            if (isSkipTypeForSimpleTable(columnStructure)) {
                // �P��\�Ƃ��Ă̓X�L�b�v���ׂ��^�ł��B
                continue;
            }

            if (isFirstColumn) {
                isFirstColumn = false;
            } else {
                sql.append(", ");
            }
            sql.append(columnStructure.getName());

        }

        if (isFirstColumn) {
            // �ЂƂ��񂪏�������܂���ł����B�������f���܂��B
            return;
        }

        sql.append("\n  FROM " + escapeSqlName(table.getName()));

        boolean isFirstPrimaryKey = true;
        for (int indexCol = 0; indexCol < table.getColumns().size(); indexCol++) {
            final BlancoDbMetaDataColumnStructure columnStructure = table
                    .getColumns().get(indexCol);

            if (isSkipTypeForSimpleTable(columnStructure)) {
                // �P��\�Ƃ��Ă̓X�L�b�v���ׂ��^�ł��B
                continue;
            }

            if (BlancoDbUtil.isPrimaryKey(table, columnStructure)) {
                if (isFirstPrimaryKey) {
                    isFirstPrimaryKey = false;
                    sql.append("\n ORDER BY ");
                } else {
                    sql.append(", ");
                }
                sql.append(columnStructure.getName());
            }
        }

        if (isFirstPrimaryKey) {
            // �v���C�}���[�L�[���ꌏ����������Ă��Ȃ��ۂɂ́A
            // WHERE���쐬����Ă��܂���B
            // �������s�͊댯�Ɣ��f���A�������f���܂��B
            return;
        }

        sqlInfo.setQuery(sql.toString());
        if (fFormatSql) {
            try {
                sqlInfo.setQuery(getSqlFormatter().format(sqlInfo.getQuery()));
            } catch (BlancoSqlFormatterException e) {
                // ���������Ȃ��̂ŁA���̂܂ܐi�݂܂��B
                e.printStackTrace();
            }
        }

        // �Ō�̍Ō�Ń��[�g�m�[�h�ɒǉ����܂��B
        resultSqlInfo.add(sqlInfo);
    }

    /**
     * �}�����s��Invoker�𐶐����܂��B
     * 
     * @param collector
     * @param metadata
     * @param table
     * @param document
     * @param eleRoot
     * @param isIgnoreNullable
     * @throws SQLException
     */
    private void generateInsert(
            final List<BlancoDbMetaDataTableStructure> listTables,
            final BlancoDbMetaDataTableStructure table,
            final List<BlancoDbSqlInfoStructure> resultSqlInfo,
            final boolean isIgnoreNullable) throws SQLException {

        final String name = CLASS_PREFIX + getBaseClassName(table) + "Insert"
                + (isIgnoreNullable ? "NoNulls" : "");

        final BlancoDbSqlInfoStructure sqlInfo = new BlancoDbSqlInfoStructure();
        sqlInfo.setName(name);
        sqlInfo.setType(BlancoDbSqlInfoTypeStringGroup.INVOKER);
        sqlInfo.setSingle(true);

        final StringBuffer sql = new StringBuffer();
        sql.append("INSERT");
        sql.append("\n  INTO " + escapeSqlName(table.getName()));
        sql.append("\n       (");

        boolean isNullableColumnExist = false;
        boolean isFirstColumn = true;
        for (int indexCol = 0; indexCol < table.getColumns().size(); indexCol++) {
            final BlancoDbMetaDataColumnStructure columnStructure = table
                    .getColumns().get(indexCol);
            if (isIgnoreNullable
                    && columnStructure.getNullable() == ResultSetMetaData.columnNullable) {
                isNullableColumnExist = true;
                continue;
            }

            if (isSkipTypeForSimpleTable(columnStructure)) {
                // �P��\�Ƃ��Ă̓X�L�b�v���ׂ��^�ł��B
                continue;
            }

            if (isFirstColumn) {
                isFirstColumn = false;
            } else {
                sql.append(", ");
            }
            sql.append(columnStructure.getName());
        }

        if (isFirstColumn) {
            // �ЂƂ��񂪏�������Ă��܂���B
            // ���̑g�ݍ��킹�͐������X�L�b�v���܂��B
            return;
        }

        sql.append(")");
        sql.append("\nVALUES");
        sql.append("\n       (");

        boolean isFirstPrimaryKey = true;
        for (int indexCol = 0; indexCol < table.getColumns().size(); indexCol++) {
            final BlancoDbMetaDataColumnStructure columnStructure = table
                    .getColumns().get(indexCol);
            if (isIgnoreNullable
                    && columnStructure.getNullable() == ResultSetMetaData.columnNullable) {
                continue;
            }

            if (isSkipTypeForSimpleTable(columnStructure)) {
                // �P��\�Ƃ��Ă̓X�L�b�v���ׂ��^�ł��B
                continue;
            }

            if (isFirstPrimaryKey) {
                isFirstPrimaryKey = false;
            } else {
                sql.append(", ");
            }

            final BlancoDbMetaDataColumnStructure wrkStructure = cloneColumnStructure(columnStructure);
            if (fFormatSql) {
                wrkStructure
                        .setName("inParam"
                                + BlancoNameAdjuster.toClassName(wrkStructure
                                        .getName()));
            } else {
                wrkStructure.setName(BlancoNameAdjuster
                        .toParameterName(wrkStructure.getName()));
            }

            sql.append("#" + wrkStructure.getName());

            sqlInfo.getInParameterList().add(wrkStructure);
        }
        sql.append(")");

        if (isFirstPrimaryKey) {
            // �v���C�}���[�L�[���ꌏ����������Ă��Ȃ��ۂɂ́A
            // WHERE���쐬����Ă��܂���B
            // �������s�͊댯�Ɣ��f���A�������f���܂��B
            return;
        }

        sqlInfo.setQuery(sql.toString());
        if (fFormatSql) {
            try {
                sqlInfo.setQuery(getSqlFormatter().format(sqlInfo.getQuery()));
            } catch (BlancoSqlFormatterException e) {
                // ���������Ȃ��̂ŁA���̂܂ܐi�݂܂��B
                e.printStackTrace();
            }
        }

        if (isIgnoreNullable == false || isNullableColumnExist) {
            // NULL���e�񂪏������ꂽ�ꍇ�ɂ̂�XML�ɒǉ����܂��B
            resultSqlInfo.add(sqlInfo);
        }
    }

    /**
     * �X�V���s��Invoker�𐶐����܂��B
     * 
     * @param collector
     * @param metadata
     * @param table
     * @param document
     * @param eleRoot
     * @throws SQLException
     */
    private void generateUpdate(
            final List<BlancoDbMetaDataTableStructure> listTables,
            final BlancoDbMetaDataTableStructure table,
            final List<BlancoDbSqlInfoStructure> resultSqlInfo)
            throws SQLException {

        final String name = CLASS_PREFIX + getBaseClassName(table) + "Update";

        final BlancoDbSqlInfoStructure sqlInfo = new BlancoDbSqlInfoStructure();
        sqlInfo.setName(name);
        sqlInfo.setType(BlancoDbSqlInfoTypeStringGroup.INVOKER);
        sqlInfo.setSingle(true);

        final StringBuffer sql = new StringBuffer();
        sql.append("UPDATE " + escapeSqlName(table.getName()));
        sql.append("\n   SET ");

        boolean isFirstColumn = true;
        for (int indexCol = 0; indexCol < table.getColumns().size(); indexCol++) {
            final BlancoDbMetaDataColumnStructure columnStructure = table
                    .getColumns().get(indexCol);
            if (BlancoDbUtil.isPrimaryKey(table, columnStructure)) {
                continue;
            }

            if (isSkipTypeForSimpleTable(columnStructure)) {
                // �P��\�Ƃ��Ă̓X�L�b�v���ׂ��^�ł��B
                continue;
            }

            if (isFirstColumn) {
                isFirstColumn = false;
            } else {
                sql.append(", ");
            }

            final BlancoDbMetaDataColumnStructure wrkStructure = cloneColumnStructure(columnStructure);
            if (fFormatSql) {
                wrkStructure
                        .setName("inParam"
                                + BlancoNameAdjuster.toClassName(wrkStructure
                                        .getName()));
            } else {
                wrkStructure.setName(BlancoNameAdjuster
                        .toParameterName(wrkStructure.getName()));
            }

            sql.append(columnStructure.getName() + " = #"
                    + wrkStructure.getName());

            sqlInfo.getInParameterList().add(wrkStructure);
        }

        if (isFirstColumn) {
            // �ЂƂ��񂪏�������Ă��܂���B
            // ���̑g�ݍ��킹�͐������X�L�b�v���܂��B
            return;
        }

        sql.append("\n WHERE ");

        boolean isFirstPrimaryKey = true;
        for (int indexCol = 0; indexCol < table.getColumns().size(); indexCol++) {
            final BlancoDbMetaDataColumnStructure columnStructure = table
                    .getColumns().get(indexCol);
            if (BlancoDbUtil.isPrimaryKey(table, columnStructure) == false) {
                continue;
            }

            if (isSkipTypeForSimpleTable(columnStructure)) {
                // �P��\�Ƃ��Ă̓X�L�b�v���ׂ��^�ł��B
                continue;
            }

            if (isFirstPrimaryKey) {
                isFirstPrimaryKey = false;
            } else {
                sql.append("\n   AND ");
            }

            final BlancoDbMetaDataColumnStructure wrkStructure = cloneColumnStructure(columnStructure);
            wrkStructure.setName("where"
                    + BlancoNameAdjuster.toClassName(wrkStructure.getName()));

            sql.append(columnStructure.getName() + " = #"
                    + wrkStructure.getName());

            sqlInfo.getInParameterList().add(wrkStructure);
        }

        if (isFirstPrimaryKey) {
            // �v���C�}���[�L�[���ꌏ����������Ă��Ȃ��ۂɂ́A
            // WHERE���쐬����Ă��܂���B
            // �������s�͊댯�Ɣ��f���A�������f���܂��B
            return;
        }

        sqlInfo.setQuery(sql.toString());
        if (fFormatSql) {
            try {
                sqlInfo.setQuery(getSqlFormatter().format(sqlInfo.getQuery()));
            } catch (BlancoSqlFormatterException e) {
                // ���������Ȃ��̂ŁA���̂܂ܐi�݂܂��B
                e.printStackTrace();
            }
        }

        // �Ō�̍Ō�Ń��[�g�m�[�h�ɒǉ����܂��B
        resultSqlInfo.add(sqlInfo);
    }

    /**
     * �폜���s��Invoker�𐶐����܂��B
     * 
     * @param collector
     * @param metadata
     * @param table
     * @param document
     * @param eleRoot
     * @throws SQLException
     */
    private void generateDelete(
            final List<BlancoDbMetaDataTableStructure> listTables,
            final BlancoDbMetaDataTableStructure table,
            final List<BlancoDbSqlInfoStructure> resultSqlInfo)
            throws SQLException {

        final String name = CLASS_PREFIX + getBaseClassName(table) + "Delete";

        final BlancoDbSqlInfoStructure sqlInfo = new BlancoDbSqlInfoStructure();
        sqlInfo.setName(name);
        sqlInfo.setType(BlancoDbSqlInfoTypeStringGroup.INVOKER);
        sqlInfo.setSingle(true);

        final StringBuffer sql = new StringBuffer();
        sql.append("DELETE FROM " + escapeSqlName(table.getName()));
        sql.append("\n WHERE ");

        boolean isFirstColumn = true;
        for (int indexCol = 0; indexCol < table.getColumns().size(); indexCol++) {
            final BlancoDbMetaDataColumnStructure columnStructure = table
                    .getColumns().get(indexCol);
            if (BlancoDbUtil.isPrimaryKey(table, columnStructure) == false) {
                continue;
            }

            if (isSkipTypeForSimpleTable(columnStructure)) {
                // �P��\�Ƃ��Ă̓X�L�b�v���ׂ��^�ł��B
                continue;
            }

            if (isFirstColumn) {
                isFirstColumn = false;
            } else {
                sql.append("\n   AND ");
            }

            final BlancoDbMetaDataColumnStructure wrkStructure = cloneColumnStructure(columnStructure);
            if (fFormatSql) {
                wrkStructure
                        .setName("inParam"
                                + BlancoNameAdjuster.toClassName(wrkStructure
                                        .getName()));
            } else {
                wrkStructure.setName(BlancoNameAdjuster
                        .toParameterName(wrkStructure.getName()));
            }

            sql.append(columnStructure.getName() + " = #"
                    + wrkStructure.getName());

            sqlInfo.getInParameterList().add(wrkStructure);
        }

        if (isFirstColumn) {
            // �ЂƂ��񂪏�������Ă��܂���B
            // ���̑g�ݍ��킹�͐������X�L�b�v���܂��B
            return;
        }

        sqlInfo.setQuery(sql.toString());
        if (fFormatSql) {
            try {
                sqlInfo.setQuery(getSqlFormatter().format(sqlInfo.getQuery()));
            } catch (BlancoSqlFormatterException e) {
                // ���������Ȃ��̂ŁA���̂܂ܐi�݂܂��B
                e.printStackTrace();
            }
        }

        // �Ō�̍Ō�Ń��[�g�m�[�h�ɒǉ����܂��B
        resultSqlInfo.add(sqlInfo);
    }

    /**
     * �^����ꂽSQL��̖���(�\���܂��͗�)�ɃG�X�P�[�v����ׂ�����(�X�y�[�X)���܂܂�Ă���ꍇ�ɁA�\�����̂��̂��_�u���N�I�[�g�ŃG�X�P�[�v���܂�
     * �B
     * 
     * @param tableName
     * @return
     */
    public String escapeSqlName(final String tableName) {
        if (tableName.indexOf(" ") >= 0) {
            return "\"" + tableName + "\"";
        }
        return tableName;
    }

    private static BlancoDbMetaDataColumnStructure cloneColumnStructure(
            final BlancoDbMetaDataColumnStructure argColumnStructure) {
        final BlancoDbMetaDataColumnStructure columnStructureWrk = new BlancoDbMetaDataColumnStructure();
        columnStructureWrk.setName(argColumnStructure.getName());
        columnStructureWrk.setDataType(argColumnStructure.getDataType());
        columnStructureWrk.setNullable(argColumnStructure.getNullable());
        return columnStructureWrk;
    }

    /**
     * SQL���`�t�H�[�}�b�^���擾���܂��B
     * 
     * @return SQL���`�t�H�[�}�b�^�B
     */
    private static BlancoSqlFormatter getSqlFormatter() {
        return new BlancoSqlFormatter(new BlancoSqlRule());
    }

    /**
     * �P��\�̏����Ƃ��ăX�L�b�v���ׂ��^�ł��邩�ǂ������肵�܂��B
     * 
     * �����̃��\�b�h�́A�����[�V���i���f�[�^�x�[�X�A���邢�̓f�[�^�x�[�XAPI�ɂ��ςƂȂ�ӏ��ł��B
     * 
     * TODO Java����ȊO�ɂ����Ă��̃N���X�𗘗p����ۂɂ́A���̃��\�b�h���I�[�o�[���C�h����K�v������܂��B
     * 
     * @param argTypeName
     *            �^���B�p�b�P�[�W���������B
     * @return �X�L�b�v���ׂ��^�̏ꍇ�ɂ�true�B
     */
    protected boolean isSkipTypeForSimpleTable(
            final BlancoDbMetaDataColumnStructure columnStructure) {
        switch (columnStructure.getDataType()) {
        case Types.BINARY:
        case Types.VARBINARY:
        case Types.LONGVARBINARY:
        case Types.BLOB:
            return true;
        case Types.LONGVARCHAR:
        case Types.CLOB:
        case Types.NCHAR:
        case Types.NVARCHAR:
        case Types.LONGNVARCHAR:
            return true;
        case Types.JAVA_OBJECT:
        case Types.DISTINCT:
        case Types.STRUCT:
        case Types.ARRAY:
        case Types.NULL:
        case Types.OTHER:
        case Types.REF:
        case Types.DATALINK:
        case Types.ROWID:// ����������T�|�[�g�͈͊O�Ƀ}�b�v���܂��B
            return true;
        default:
            return false;
        }
    }
}