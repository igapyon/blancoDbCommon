/**
 * このクラスは別プロダクト化される予定。
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
 * リレーショナルデータベースから得られる表情報をもとに、単一表アクセサのためのXML中間ファイルを生成します。
 * 
 * リレーショナルデータベースからメタ情報を取り出す処理そのものは、このクラスではなく、別のプロダクト blancoDbMetaDataが担います。
 */
public abstract class BlancoDbTableMeta2Xml implements IBlancoDbProgress {
    /**
     * 単一表のクラスに付けられるプレフィックス。
     */
    public static final String CLASS_PREFIX = "Simple";

    /**
     * 各種リソースバンドル。
     */
    private final BlancoDbCommonResourceBundle fBundle = new BlancoDbCommonResourceBundle();

    /**
     * blancoDbに関する設定情報。
     */
    private BlancoDbSetting fDbSetting = null;

    /**
     * 自動生成したSQL文をフォーマットするかどうか。
     * 
     * 2006.12.01時点ではデフォルトは falseとします。
     */
    private boolean fFormatSql = false;

    /**
     * 自動生成したSQL文をフォーマットするかどうか。
     * 
     * @param argFormatSql
     */
    public void setFormatSql(final boolean argFormatSql) {
        fFormatSql = argFormatSql;
    }

    /**
     * リレーショナルデータベースから得られる表情報をもとに、単一表アクセサのためのXML中間ファイルを生成します。
     * 
     * リレーショナルデータベースからメタ情報を取り出す処理そのものは、このクラスではなく、別のプロダクト blancoDbMetaDataが担います。
     * 
     * @param connDef
     *            データベース接続情報。
     * @param blancoSqlDirectory
     *            出力先ディレクトリ。
     * @throws SQLException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws ClassNotFoundException
     */
    public void process(final BlancoDbSetting dbSetting,
            final File blancoSqlDirectory) throws SQLException {
        System.out.println(BlancoDbCommonConstants.PRODUCT_NAME + " ("
                + BlancoDbCommonConstants.VERSION + ") 単一表アクセサSQL自動生成: 開始.");
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
            System.out.println("単一表アクセサSQL自動生成: 終了.");
        }
    }

    /**
     * 表の単位で収集された情報をXMLファイルに書き出します。
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
                System.out.println("表[" + table.getName() + "]を処理します");
                processEveryTable(listTables, table, resultSqlInfo);
            } catch (StringIndexOutOfBoundsException ex) {
                System.out.println("表[" + table.getName()
                        + "]の処理の過程で例外が発生しました: " + ex.toString());
                ex.printStackTrace();

                // 例外発生時には、仕方が無いので 次の表を処理します。
                conn.rollback();
                continue;
            }

            // 表の単位でSQL情報をXMLファイルに出力します。
            BlancoDbXmlSerializer.serialize(resultSqlInfo,
                    new File(outputDirectoryName + "/SimpleTable"
                            + BlancoNameAdjuster.toClassName(table.getName())
                            + ".xml"));
        }
    }

    /**
     * おのおのの表を出力処理します。
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

        // 更新可能カーソルが利用可能かどうかはメソッド内で判断します。
        generateSelectUpdatable(listTables, table, resultSqlInfo);

        generateSelectColumn(listTables, table, resultSqlInfo);

        // 2005.11.11 SelectAllメソッドは復活しました。
        generateSelectAll(listTables, table, resultSqlInfo);

        generateInsert(listTables, table, resultSqlInfo, false);
        generateInsert(listTables, table, resultSqlInfo, true);

        generateUpdate(listTables, table, resultSqlInfo);

        generateDelete(listTables, table, resultSqlInfo);
    }

    /**
     * 表名のみについて、まずはクラス名に変換します。
     * 
     * @param table
     * @return
     */
    private String getBaseClassName(final BlancoDbMetaDataTableStructure table) {
        return BlancoNameAdjuster.toClassName(table.getName());
    }

    /**
     * 一行を検索するアクセサを生成します。
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
                // 単一表としてはスキップすべき型です。
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
            // ひとつも列が処理されませんでした。処理中断します。
            return;
        }

        sql.append("\n  FROM " + escapeSqlName(table.getName()));

        boolean isFirstPrimaryKey = true;
        for (int indexCol = 0; indexCol < table.getColumns().size(); indexCol++) {
            final BlancoDbMetaDataColumnStructure columnStructure = table
                    .getColumns().get(indexCol);
            if (BlancoDbUtil.isPrimaryKey(table, columnStructure)) {
                if (isSkipTypeForSimpleTable(columnStructure)) {
                    // 単一表としてはスキップすべき型です。
                    // バイナリやリーダはキーとしては利用できません。
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
            // プライマリーキーが一件も処理されていない際には、
            // WHEREが作成されていません。
            // 処理続行は危険と判断し、処理中断します。
            return;
        }

        sqlInfo.setQuery(sql.toString());
        if (fFormatSql) {
            try {
                sqlInfo.setQuery(getSqlFormatter().format(sqlInfo.getQuery()));
            } catch (BlancoSqlFormatterException e) {
                // しかたがないので、そのまま進みます。
                e.printStackTrace();
            }
        }

        // 最後の最後でルートノードに追加します。
        resultSqlInfo.add(sqlInfo);
    }

    /**
     * 更新可能な検索を生成します。
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
            // blancoDbとして更新可能な検索に対応しているデータベースです。処理可能です。
            break;
        default:
            // 処理できません。
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
            // 更新可能で動かすために、全列を取得しています。

            if (isSkipTypeForSimpleTable(columnStructure)) {
                // 単一表としてはスキップすべき型です。
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
            // ひとつも列が処理されませんでした。処理中断します。
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
                    // 単一表としてはスキップすべき型です。
                    // バイナリは検索キーに利用できません。
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
            // プライマリーキーが一件も処理されていない際には、
            // WHEREが作成されていません。
            // 処理続行は危険と判断し、処理中断します。
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
                // しかたがないので、そのまま進みます。
                e.printStackTrace();
            }
        }

        // 最後の最後でルートノードに追加します。
        resultSqlInfo.add(sqlInfo);
    }

    /**
     * InputStreamおよびReaderにマップされる型について、個別にIteratorを生成します。
     * 
     * このIterator以外では InputStreamおよびReaderにマップされる型は項目や条件としては生成がスキップされます。
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
                // ここではバイナリおよびリーダ「のみ」を処理します。
                // ※他の箇所と判定条件が反転している点に注目してください。
                continue;
            }

            final String name = CLASS_PREFIX + getBaseClassName(table)
                    + "Column"
                    + BlancoNameAdjuster.toClassName(columnStructure.getName());

            final BlancoDbSqlInfoStructure sqlInfo = new BlancoDbSqlInfoStructure();
            sqlInfo.setName(name);
            sqlInfo.setType(BlancoDbSqlInfoTypeStringGroup.ITERATOR);

            // BINARYおよびASCIISTREAMの列へのアクセサを１行制約付で出力するかどうか。
            // SQL Server 2000においては、１行制約付で生成を行うと、１行制約のgetSingleRowメソッド内の
            // next() ＋ next() の2度呼び出しを行った時点で、一度目の検索結果の
            // ストリームが閉じてしまうことが知られています。
            // このような背景から、デフォルトは false である 非１行制約としたいです。
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
                    // 条件としてはスキップすべき型です。
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
                // プライマリーキーが一件も処理されていない際には、
                // WHEREが作成されていません。
                // 処理続行は危険と判断し、処理中断します。
                return;
            }

            sqlInfo.setQuery(sql.toString());
            if (fFormatSql) {
                try {
                    sqlInfo.setQuery(getSqlFormatter().format(
                            sqlInfo.getQuery()));
                } catch (BlancoSqlFormatterException e) {
                    // しかたがないので、そのまま進みます。
                    e.printStackTrace();
                }
            }

            // 最後の最後でルートノードに追加します。
            resultSqlInfo.add(sqlInfo);
        }
    }

    /**
     * 全項目を検索するIteratorを生成します。
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
                // 単一表としてはスキップすべき型です。
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
            // ひとつも列が処理されませんでした。処理中断します。
            return;
        }

        sql.append("\n  FROM " + escapeSqlName(table.getName()));

        boolean isFirstPrimaryKey = true;
        for (int indexCol = 0; indexCol < table.getColumns().size(); indexCol++) {
            final BlancoDbMetaDataColumnStructure columnStructure = table
                    .getColumns().get(indexCol);

            if (isSkipTypeForSimpleTable(columnStructure)) {
                // 単一表としてはスキップすべき型です。
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
            // プライマリーキーが一件も処理されていない際には、
            // WHEREが作成されていません。
            // 処理続行は危険と判断し、処理中断します。
            return;
        }

        sqlInfo.setQuery(sql.toString());
        if (fFormatSql) {
            try {
                sqlInfo.setQuery(getSqlFormatter().format(sqlInfo.getQuery()));
            } catch (BlancoSqlFormatterException e) {
                // しかたがないので、そのまま進みます。
                e.printStackTrace();
            }
        }

        // 最後の最後でルートノードに追加します。
        resultSqlInfo.add(sqlInfo);
    }

    /**
     * 挿入を行うInvokerを生成します。
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
                // 単一表としてはスキップすべき型です。
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
            // ひとつも列が処理されていません。
            // この組み合わせは生成をスキップします。
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
                // 単一表としてはスキップすべき型です。
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
            // プライマリーキーが一件も処理されていない際には、
            // WHEREが作成されていません。
            // 処理続行は危険と判断し、処理中断します。
            return;
        }

        sqlInfo.setQuery(sql.toString());
        if (fFormatSql) {
            try {
                sqlInfo.setQuery(getSqlFormatter().format(sqlInfo.getQuery()));
            } catch (BlancoSqlFormatterException e) {
                // しかたがないので、そのまま進みます。
                e.printStackTrace();
            }
        }

        if (isIgnoreNullable == false || isNullableColumnExist) {
            // NULL許容列が処理された場合にのみXMLに追加します。
            resultSqlInfo.add(sqlInfo);
        }
    }

    /**
     * 更新を行うInvokerを生成します。
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
                // 単一表としてはスキップすべき型です。
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
            // ひとつも列が処理されていません。
            // この組み合わせは生成をスキップします。
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
                // 単一表としてはスキップすべき型です。
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
            // プライマリーキーが一件も処理されていない際には、
            // WHEREが作成されていません。
            // 処理続行は危険と判断し、処理中断します。
            return;
        }

        sqlInfo.setQuery(sql.toString());
        if (fFormatSql) {
            try {
                sqlInfo.setQuery(getSqlFormatter().format(sqlInfo.getQuery()));
            } catch (BlancoSqlFormatterException e) {
                // しかたがないので、そのまま進みます。
                e.printStackTrace();
            }
        }

        // 最後の最後でルートノードに追加します。
        resultSqlInfo.add(sqlInfo);
    }

    /**
     * 削除を行うInvokerを生成します。
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
                // 単一表としてはスキップすべき型です。
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
            // ひとつも列が処理されていません。
            // この組み合わせは生成をスキップします。
            return;
        }

        sqlInfo.setQuery(sql.toString());
        if (fFormatSql) {
            try {
                sqlInfo.setQuery(getSqlFormatter().format(sqlInfo.getQuery()));
            } catch (BlancoSqlFormatterException e) {
                // しかたがないので、そのまま進みます。
                e.printStackTrace();
            }
        }

        // 最後の最後でルートノードに追加します。
        resultSqlInfo.add(sqlInfo);
    }

    /**
     * 与えられたSQL上の名称(表名または列名)にエスケープするべき文字(スペース)が含まれている場合に、表名そのものをダブルクオートでエスケープします
     * 。
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
     * SQL整形フォーマッタを取得します。
     * 
     * @return SQL整形フォーマッタ。
     */
    private static BlancoSqlFormatter getSqlFormatter() {
        return new BlancoSqlFormatter(new BlancoSqlRule());
    }

    /**
     * 単一表の処理としてスキップすべき型であるかどうか判定します。
     * 
     * ※このメソッドは、リレーショナルデータベース、あるいはデータベースAPIにより可変となる箇所です。
     * 
     * TODO Java言語以外においてこのクラスを利用する際には、このメソッドをオーバーライドする必要があります。
     * 
     * @param argTypeName
     *            型名。パッケージ名を除く。
     * @return スキップすべき型の場合にはtrue。
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
        case Types.ROWID:// さしあたりサポート範囲外にマップします。
            return true;
        default:
            return false;
        }
    }
}