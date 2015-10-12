/*
 * blancoDb
 * Copyright (C) 2004-2006 Yasuo Nakanishi
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
package blanco.db.common;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import blanco.commons.util.BlancoBigDecimalUtil;
import blanco.db.common.resourcebundle.BlancoDbCommonResourceBundle;
import blanco.db.common.stringgroup.BlancoDbExecuteSqlStringGroup;
import blanco.db.common.stringgroup.BlancoDbSqlInfoTypeStringGroup;
import blanco.db.common.util.BlancoDbQueryParserUtil;
import blanco.db.common.util.BlancoDbXmlParser;
import blanco.db.common.valueobject.BlancoDbSetting;
import blanco.db.common.valueobject.BlancoDbSqlInfoStructure;
import blanco.dbmetadata.BlancoDbMetaDataSql;
import blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure;

/**
 * SQL��`���̒���XML�t�@�C������͂Ƃ��āASQL��`���Ɋւ���e����\���̂��\�z���܂��B
 * 
 * ���̃N���X�̒��ł́A�����^��SQL���͎��ۂ�SQL�������s���܂��BSQL�������s���邱�Ƃɂ��A�������ʂ̗�ꗗ���擾���A
 * SQL���̓p�����[�^�̑Ó����̃`�F�b�N���s������A���邢��SQL���� �����������s�ł��邩�ǂ������m�F���邱�Ƃ��ł��܂��B
 * 
 * @author Yasuo Nakanishi
 */
public class BlancoDbXml2SqlInfo {
    /**
     * blancoDb���\�[�X�o���h�����ւ̃A�N�Z�T�B
     */
    private final BlancoDbCommonResourceBundle fBundle = new BlancoDbCommonResourceBundle();

    /**
     * SQL��`���̒���XML�t�@�C������͂Ƃ��āASQL��`���Ɋւ���e����\���̂��\�z���܂��B
     * 
     * @param conn
     *            �f�[�^�x�[�X�ڑ��B
     * @param dbSetting
     *            blancoDb�ݒ�B
     * @param fileSqlForm
     *            ���͂Ƃ��钆��XML�t�@�C���B
     * @return SQL��`���\���̃��X�g�B
     * @throws SQLException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public List<BlancoDbSqlInfoStructure> process(final Connection conn,
            final BlancoDbSetting dbSetting, final File fileSqlForm)
            throws SQLException, SAXException, IOException,
            ParserConfigurationException, TransformerException {

        final List<BlancoDbSqlInfoStructure> blancoDbDef = new BlancoDbXmlParser()
                .parse(fileSqlForm);

        for (int index = 0; index < blancoDbDef.size(); index++) {
            final BlancoDbSqlInfoStructure sqlInfo = blancoDbDef.get(index);

            if (sqlInfo.getType() == BlancoDbSqlInfoTypeStringGroup.ITERATOR) {
                // ���W���ꂽ���̂̂����A�����^�ɂ��ẮA�f�[�^�x�[�X�ڑ����Ď����ł����s���܂��B
                processIterator(conn, sqlInfo, dbSetting);
            }
        }

        return blancoDbDef;
    }

    public void processIterator(final Connection conn,
            final BlancoDbSqlInfoStructure sqlInfo,
            final BlancoDbSetting dbSetting) {
        try {
            final BlancoDbQueryParserUtil parserUtil = new BlancoDbQueryParserUtil(
                    sqlInfo.getQuery());

            // SQL�������s���Č��ʃZ�b�g���擾���A�������猟�����ʂ̗�ꗗ���擾���܂��B
            // JDBC�̋@�\�𗘗p����SQL���������ł����A�������ʂ̗�ꗗ���擾����Ƃ����AblancoDb�̊j�Ƃ�������@�\�̎����ӏ��ł��B

            List<BlancoDbMetaDataColumnStructure> nativeParam = convertSqlInParameter2NativeParameter(
                    sqlInfo, parserUtil);
            if (dbSetting.getExecuteSql() == BlancoDbExecuteSqlStringGroup.NONE) {
                // none�̏ꍇ�ɂ� param��null���Z�b�g���܂��B
                nativeParam = null;
            }

            // param�ɒl���Z�b�g����Ă���ꍇ�ɂ�SQL�����s���܂��B
            final List<BlancoDbMetaDataColumnStructure> listResult = BlancoDbMetaDataSql
                    .getResultSetMetaData(conn,
                            parserUtil.getNaturalSqlStringForJava(),
                            nativeParam);

            // �������ʃZ�b�g�̗�̈ꗗ�ɂ��āA���W��̏��Ƃ��ċL�����܂��B
            sqlInfo.setResultSetColumnList(listResult);

        } catch (SQLException e) {
            throw new IllegalArgumentException(fBundle.getXml2javaclassErr002(
                    sqlInfo.getName(), e.getSQLState(),
                    BlancoBigDecimalUtil.toBigDecimal(e.getErrorCode()),
                    e.toString()));
        }
    }

    /**
     * SQL��`����SQL���̓p�����[�^���A���ۂ�JDBC��Ŏ��s�����SQL����SQL���̓p�����[�^�ւƕϊ����܂��B
     * 
     * @param sqlInfo
     * @param parserUtil
     * @return
     */
    private List<BlancoDbMetaDataColumnStructure> convertSqlInParameter2NativeParameter(
            final BlancoDbSqlInfoStructure sqlInfo,
            final BlancoDbQueryParserUtil parserUtil) {
        // ���ꖼ�̂����ۂ�SQL��� ? �ɑΉ����邽�߂ɕ����ɓW�J���܂��B
        int maxNativeCol = 0;
        final Map<String, BlancoDbMetaDataColumnStructure> hashCol = new HashMap<String, BlancoDbMetaDataColumnStructure>();

        for (int indexCol = 0; indexCol < sqlInfo.getInParameterList().size(); indexCol++) {
            final BlancoDbMetaDataColumnStructure columnStructure = sqlInfo
                    .getInParameterList().get(indexCol);
            final int[] listNativeCol = parserUtil
                    .getSqlParameters(columnStructure.getName());
            if (listNativeCol == null) {
                throw new IllegalArgumentException("SQL��`ID["
                        + sqlInfo.getName() + "]�� SQL���̓p�����[�^["
                        + columnStructure.getName() + "]�����т��Ă��܂���.");
            }

            for (int indexSearch = 0; indexSearch < listNativeCol.length; indexSearch++) {
                maxNativeCol = Math.max(listNativeCol[indexSearch],
                        maxNativeCol);
                hashCol.put(String.valueOf(listNativeCol[indexSearch]),
                        columnStructure);
            }
        }

        final List<BlancoDbMetaDataColumnStructure> nativeParam = new ArrayList<BlancoDbMetaDataColumnStructure>();
        for (int indexNativeCol = 1; indexNativeCol <= maxNativeCol; indexNativeCol++) {
            final BlancoDbMetaDataColumnStructure objLook = hashCol.get(String
                    .valueOf(indexNativeCol));
            if (objLook == null) {
                throw new IllegalArgumentException("SQL��`ID["
                        + sqlInfo.getName() + "]�� SQL���̓p�����[�^�W�J���ɗ\�����ʗ�O������. ("
                        + indexNativeCol + ")�Ԗڂ̓��̓p�����[�^���擾�ł��܂���B");
            }
            nativeParam.add(objLook);
        }

        return nativeParam;
    }
}