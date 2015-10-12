/*
 * blancoDb
 * Copyright (C) 2004-2006 Yasuo Nakanishi
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
package blanco.db.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMResult;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import blanco.commons.util.BlancoStringUtil;
import blanco.commons.util.BlancoXmlUtil;
import blanco.db.common.resourcebundle.BlancoDbCommonResourceBundle;
import blanco.db.common.stringgroup.BlancoDbSqlInfoScrollStringGroup;
import blanco.db.common.stringgroup.BlancoDbSqlInfoTypeStringGroup;
import blanco.db.common.valueobject.BlancoDbSqlInfoStructure;
import blanco.dbmetadata.BlancoDbMetaDataUtil;
import blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure;

/**
 * SQL��`���̒���XML��ǂݍ����Java�I�u�W�F�N�g�����܂��B
 * 
 * @author IGA Tosiki
 */
public class BlancoDbXmlParser {
	/**
	 * C#.NET ��  SQL ���̓p�����[�^�̋����̍\���I�Ȗ��ɑ΂���Ώ����@�B
	 * 
	 * FIXME ������A���̕ϐ����O�������Ă��������B
	 */
	private static final boolean IS_FORCE_CS_DOTNET_STRING_AS_NVARCHAR = false;

	/**
     * blancoDb���\�[�X�o���h�����ւ̃A�N�Z�T�B
     */
    private final BlancoDbCommonResourceBundle fBundle = new BlancoDbCommonResourceBundle();

    /**
     * ���ʏ�񂪒~�����Ă���G�������g���B
     */
    private static final String ELEMENT_COMMON = "blancodb-common";

    /**
     * SQL���̓p�����[�^���~�����Ă���G�������g���B
     */
    private static final String ELEMENT_INPARAMETERS = "blancodb-inparameters";

    /**
     * SQL�o�̓p�����[�^���~�����Ă���G�������g���B
     */
    private static final String ELEMENT_OUTPARAMETERS = "blancodb-outparameters";

    /**
     * SQL�����~�����Ă���G�������g���B
     */
    private static final String ELEMENT_QUERY = "blancodb-query";

    /**
     * SQL��`���̒���XML����͂ɁASQL��`���������W���܂��B
     * 
     * @param conn
     *            �f�[�^�x�[�X�ڑ��ݒ���B
     * @param fileSqlForm
     *            �������s������SQL��`����XML���Ԍ`���t�@�C���B
     * @return ��͌��SQL��`���̃��X�g�B
     * @throws SQLException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public List<BlancoDbSqlInfoStructure> parse(final File fileSqlForm)
            throws SQLException, SAXException, IOException,
            ParserConfigurationException, TransformerException {
        // blancoDb��`���
        final List<BlancoDbSqlInfoStructure> resultBlancoDbDef = new ArrayList<BlancoDbSqlInfoStructure>();

        // XML�t�@�C����DOM�Ƃ��ăp�[�X���܂��B
        InputStream inStream = null;
        final DOMResult result;
        try {
            inStream = new BufferedInputStream(new FileInputStream(fileSqlForm));
            result = BlancoXmlUtil.transformStream2Dom(inStream);
        } finally {
            inStream.close();
        }

        // ���[�g�m�[�h���擾���܂��B
        final Node nodeRootNode = result.getNode();
        if (nodeRootNode == null) {
            // XML�t�@�C���ł͂���܂���B
            return resultBlancoDbDef;
        }

        final Element eleWorkbook = BlancoXmlUtil.getElement(nodeRootNode,
                "workbook");
        if (eleWorkbook == null) {
            return resultBlancoDbDef;
        }

        // �V�[�g�G�������g��W�J���܂��B
        final NodeList listSheet = eleWorkbook.getElementsByTagName("sheet");
        if (listSheet == null) {
            // �V�[�g��������܂���B
            return resultBlancoDbDef;
        }

        // �e�V�[�g�G�������g�ɂ��ď��������{�B
        final int nodeLength = listSheet.getLength();
        for (int index = 0; index < nodeLength; index++) {
            final Node nodeSheet = listSheet.item(index);
            if (nodeSheet instanceof Element == false) {
                continue;
            }
            final Element eleSheet = (Element) nodeSheet;
            expandSheet(eleSheet, resultBlancoDbDef);
        }

        return resultBlancoDbDef;
    }

    /**
     * �^����ꂽ�V�[�g�G�������g��W�J���܂��B
     * 
     * @param eleSheet
     *            �V�[�g�G�������g�B
     * @param conn
     *            blanco�f�[�^�x�[�X�ڑ��I�u�W�F�N�g�B
     * @param resultBlancoDbDef
     *            blancoDb��`���B
     */
    private void expandSheet(final Element eleSheet,
            final List<BlancoDbSqlInfoStructure> resultBlancoDbDef) {
        // �ŏ��ɋ��ʏ���W�J���܂��B
        final BlancoDbSqlInfoStructure fSqlInfo = expandCommon(eleSheet);
        if (fSqlInfo == null) {
            // SQL��`���Ƃ��Ăӂ��킵���Ȃ��̂ŁA�������X�L�b�v���܂��B
            return;
        }

        // SQL���̓p�����[�^��W�J���܂��B
        expandInParameter(eleSheet, fSqlInfo);

        // SQL�o�̓p�����[�^��W�J���܂��B
        expandOutParameter(eleSheet, fSqlInfo);

        // SQL����W�J���܂��B
        expandQuery(eleSheet, fSqlInfo);

        // �ЂƂƂ���̏������낦����ŁA���W�������̑Ó����`�F�b�N�������Ȃ��܂��B
        if (fSqlInfo.getQuery() == null || fSqlInfo.getQuery().length() == 0) {
            // SQL�����擾�ł��Ȃ����̂̓G���[�������܂��B
            throw new IllegalArgumentException(fBundle
                    .getXml2javaclassErr001(fSqlInfo.getName()));
        }

        // ��͌��SQL��`���̃��X�g�֏���ǉ��B
        resultBlancoDbDef.add(fSqlInfo);
    }

    /**
     * �^����ꂽ���ʃG�������g����͂��ď���W�J���܂��B
     * 
     * @param elementSheet
     *            �V�[�g�I�u�W�F�N�g�B
     * @return ���ۃN�G���I�u�W�F�N�g�B
     */
    private BlancoDbSqlInfoStructure expandCommon(final Element eleSheet) {
        final Element elementCommon = BlancoXmlUtil.getElement(eleSheet,
                ELEMENT_COMMON);
        if (elementCommon == null) {
            // ELEMENT_COMMON��������Ȃ��ꍇ�ɂ́A���̃V�[�g���X�L�b�v���܂��B
            return null;
        }

        final BlancoDbSqlInfoStructure fSqlInfo = new BlancoDbSqlInfoStructure();

        fSqlInfo.setName(BlancoStringUtil.null2Blank(BlancoXmlUtil
                .getTextContent(elementCommon, "name")));
        if (fSqlInfo.getName().length() == 0) {
            // ���̒�`���͏����̕K�v������܂���B�������Ȃ����ƂƂ��܂��B
            // �]����query-type�����w��̏ꍇ�ɂ��������X�L�b�v���Ă��܂������A���݂̓G���[�����Ƃ��܂��B
            // name��������܂���B
            return null;
        }

        fSqlInfo.setPackage(BlancoXmlUtil.getTextContent(elementCommon,
                "package"));

        final String queryType = BlancoStringUtil.null2Blank(BlancoXmlUtil
                .getTextContent(elementCommon, "query-type"));
        fSqlInfo.setType(new BlancoDbSqlInfoTypeStringGroup()
                .convertToInt(queryType));
        if (fSqlInfo.getType() == BlancoDbSqlInfoTypeStringGroup.NOT_DEFINED) {
            // �����ł��܂���B���f���܂��B
            throw new IllegalArgumentException("�T�|�[�g���Ȃ��N�G���^�C�v["
                    + fSqlInfo.getType() + "]���^�����܂����B�������f���܂��B");
        }

        fSqlInfo.setDescription(BlancoStringUtil.null2Blank(BlancoXmlUtil
                .getTextContent(elementCommon, "description")));
        fSqlInfo.setSingle(BlancoStringUtil.null2Blank(
                BlancoXmlUtil.getTextContent(elementCommon, "single")).equals(
                "true"));

        if (fSqlInfo.getType() == BlancoDbSqlInfoTypeStringGroup.ITERATOR) {
            // �����^�̏ꍇ�ɂ̂݃X�N���[����������эX�V�\������ǂݍ��݂܂��B
            fSqlInfo.setScroll(new BlancoDbSqlInfoScrollStringGroup()
                    .convertToInt(BlancoStringUtil.null2Blank(BlancoXmlUtil
                            .getTextContent(elementCommon, "scroll"))));
            fSqlInfo.setUpdatable(BlancoStringUtil.null2Blank(
                    BlancoXmlUtil.getTextContent(elementCommon, "updatable"))
                    .equals("true"));
        } else {
            fSqlInfo.setScroll(BlancoDbSqlInfoScrollStringGroup.NOT_DEFINED);
            fSqlInfo.setUpdatable(false);
        }

        fSqlInfo.setDynamicSql(BlancoStringUtil.null2Blank(
                BlancoXmlUtil.getTextContent(elementCommon, "dynamic-sql"))
                .equals("true"));
        fSqlInfo.setUseBeanParameter(BlancoStringUtil.null2Blank(
                BlancoXmlUtil.getTextContent(elementCommon,
                        "use-bean-parameter")).equals("true"));

        {
            // �X�e�[�g�����g�E�^�C���A�E�g
            final String statementTimeout = BlancoXmlUtil.getTextContent(
                    elementCommon, "statementTimeout");
            if (BlancoStringUtil.null2Blank(statementTimeout).length() > 0) {
                try {
                    fSqlInfo.setStatementTimeout(Integer
                            .parseInt(statementTimeout));
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException(fBundle
                            .getXml2javaclassErr009(fSqlInfo.getName(),
                                    statementTimeout));
                }
            }
        }

        return fSqlInfo;
    }

    /**
     * �^����ꂽ�V�[�g����͂���SQL���̓p�����[�^����W�J���܂��B
     * 
     * @param elementSheet
     *            �V�[�g�I�u�W�F�N�g�B
     * @param sqlInfo
     *            ���ۃN�G���I�u�W�F�N�g�B
     */
    private void expandInParameter(final Element elementSheet,
            final BlancoDbSqlInfoStructure sqlInfo) {
        final Element elementBlancoDbInparameters = BlancoXmlUtil.getElement(
                elementSheet, ELEMENT_INPARAMETERS);
        if (elementBlancoDbInparameters == null) {
            return;
        }

        final NodeList nodeList = elementBlancoDbInparameters
                .getElementsByTagName("inparameter");
        if (nodeList == null) {
            // SQL���̓p�����[�^�͂���܂���B
            return;
        }
        final int nodeLength = nodeList.getLength();
        for (int index = 0; index < nodeLength; index++) {
            final Node nodeLook = nodeList.item(index);
            if (nodeLook instanceof Element == false) {
                continue;
            }
            final String no = BlancoXmlUtil.getTextContent((Element) nodeLook,
                    "no");
            final String name = BlancoXmlUtil.getTextContent(
                    (Element) nodeLook, "name");
            final String type = BlancoXmlUtil.getTextContent(
                    (Element) nodeLook, "type");
            final String nullable = BlancoStringUtil.null2Blank(BlancoXmlUtil
                    .getTextContent((Element) nodeLook, "nullable"));

            // ���݁Adescription�͊i�[�悪����܂���B
            // final String description = BlancoXmlUtil.getTextContent(
            // (Element) nodeLook, "description");

            final String paramNoString = (no == null ? "" : " No.[" + no + "] ");
            if (name == null || name.length() == 0) {
                throw new IllegalArgumentException(fBundle
                        .getXml2javaclassErr004(sqlInfo.getName(),
                                paramNoString, type));
            }
            if (type == null || type.length() == 0) {
                throw new IllegalArgumentException(fBundle
                        .getXml2javaclassErr005(sqlInfo.getName(),
                                paramNoString, name));
            }

            final BlancoDbMetaDataColumnStructure columnStructure = new BlancoDbMetaDataColumnStructure();
            columnStructure.setName(name);

            if (nullable.equals("Nullable")) {
                columnStructure.setNullable(ResultSetMetaData.columnNullable);
            } else if (nullable.equals("NoNulls")) {
                columnStructure.setNullable(ResultSetMetaData.columnNoNulls);
            }

            // �ŏ��ɐV�l���̃f�[�^�^�ł��邱�ƂƉ��肵�ēǂݍ��݂܂��B
            columnStructure.setDataType(BlancoDbMetaDataUtil
                    .convertJdbcDataType2Int(type));
            if (columnStructure.getDataType() == Integer.MIN_VALUE) {
                // �V�l���̃f�[�^�^�ɍ��v���Ȃ��ꍇ�ɂ́A���l���Ƃ��ēǂݍ��݂��s���܂��B
                // �����ł́AdataType�����nullable �ɂ��� Java�^���瓱�o���܂��B
                convertOldSqlInputTypeToJdbc(type, columnStructure);
            }

            sqlInfo.getInParameterList().add(columnStructure);
        }
    }

    /**
     * �^����ꂽ�V�[�g����͂���SQL�o�̓p�����[�^����W�J���܂��B
     * 
     * @param elementSheet
     *            �V�[�g�I�u�W�F�N�g�B
     * @param sqlInfo
     *            ���ۃN�G���I�u�W�F�N�g�B
     */
    private void expandOutParameter(final Element elementSheet,
            final BlancoDbSqlInfoStructure sqlInfo) {
        final Element elementBlancoDbOutparameters = BlancoXmlUtil.getElement(
                elementSheet, ELEMENT_OUTPARAMETERS);
        if (elementBlancoDbOutparameters == null) {
            // SQL�o�̓p�����[�^�͂���܂���B
            return;
        }

        final NodeList nodeList = elementBlancoDbOutparameters
                .getElementsByTagName("outparameter");
        if (nodeList == null) {
            return;
        }
        final int nodeLength = nodeList.getLength();
        for (int index = 0; index < nodeLength; index++) {
            final Node nodeLook = nodeList.item(index);
            if (nodeLook instanceof Element == false) {
                continue;
            }

            final String no = BlancoXmlUtil.getTextContent((Element) nodeLook,
                    "no");
            final String name = BlancoXmlUtil.getTextContent(
                    (Element) nodeLook, "name");
            final String type = BlancoXmlUtil.getTextContent(
                    (Element) nodeLook, "type");
            final String nullable = BlancoStringUtil.null2Blank(BlancoXmlUtil
                    .getTextContent((Element) nodeLook, "nullable"));

            // ���݁Adescription�͊i�[�悪����܂���B

            final String paramNoString = (no == null ? "" : " No.[" + no + "] ");
            if (name == null || name.length() == 0) {
                throw new IllegalArgumentException(fBundle
                        .getXml2javaclassErr006(sqlInfo.getName(),
                                paramNoString, type));
            }
            if (type == null || type.length() == 0) {
                throw new IllegalArgumentException(fBundle
                        .getXml2javaclassErr007(sqlInfo.getName(),
                                paramNoString, name));
            }
            if (sqlInfo.getType() != BlancoDbSqlInfoTypeStringGroup.CALLER) {
                // caller�ȊO�̏ꍇ�ɂ�SQL�o�̓p�����[�^�̓Z�b�g�ł��܂���B
                throw new IllegalArgumentException(fBundle
                        .getXml2javaclassErr008(sqlInfo.getName(),
                                paramNoString, name));
            }

            final BlancoDbMetaDataColumnStructure columnStructure = new BlancoDbMetaDataColumnStructure();
            columnStructure.setName(name);

            if (nullable.equals("Nullable")) {
                columnStructure.setNullable(ResultSetMetaData.columnNullable);
            } else if (nullable.equals("NoNulls")) {
                columnStructure.setNullable(ResultSetMetaData.columnNoNulls);
            }

            // �ŏ��ɐV�l���̃f�[�^�^�ł��邱�ƂƉ��肵�ēǂݍ��݂܂��B
            columnStructure.setDataType(BlancoDbMetaDataUtil
                    .convertJdbcDataType2Int(type));
            if (columnStructure.getDataType() == Integer.MIN_VALUE) {
                // �V�l���̃f�[�^�^�ɍ��v���Ȃ��ꍇ�ɂ́A���l���Ƃ��ēǂݍ��݂��s���܂��B
                // �����ł́AdataType�����nullable �ɂ��� Java�^���瓱�o���܂��B
                convertOldSqlInputTypeToJdbc(type, columnStructure);
            }

            sqlInfo.getOutParameterList().add(columnStructure);
        }
    }

    /**
     * �^����ꂽ�V�[�g����͂���SQL���Ɋւ������W�J���܂��B
     * 
     * @param elementSheet
     *            �V�[�g�I�u�W�F�N�g�B
     * @param sqlInfo
     *            ���ۃN�G���I�u�W�F�N�g�B
     */
    private void expandQuery(final Element elementSheet,
            final BlancoDbSqlInfoStructure sqlInfo) {
        final Element elementBlancoDbInparameters = BlancoXmlUtil.getElement(
                elementSheet, ELEMENT_QUERY);
        if (elementBlancoDbInparameters == null) {
            return;
        }

        final NodeList nodeList = elementBlancoDbInparameters
                .getElementsByTagName("query-line");
        if (nodeList == null) {
            return;
        }
        final int nodeLength = nodeList.getLength();
        for (int index = 0; index < nodeLength; index++) {
            final String queryLine = BlancoStringUtil.null2Blank(BlancoXmlUtil
                    .getTextContent(nodeList.item(index)));

            String query = sqlInfo.getQuery();
            if (query == null || query.length() == 0) {
                query = "";
            } else {
                // �����񂪂��łɑ��݂��Ă���ꍇ�ɂ̂݉��s��t�^���܂��B
                query = query + "\n";
            }
            sqlInfo.setQuery(query + queryLine);
        }
    }

    /**
     * ���o�[�W������SQL��`���ɂ����āASQL���́^�o�̓p�����[�^�̌^����Java/C#.NET�̌^�����^������B���̌^����
     * java.sql.Types�̌^���ɓǂݑւ��邽�߂̃��[�`���B
     * 
     * ���̃��\�b�h�͋��o�[�W�����̒�`���̓Ǎ������̖ړI�ő��݂��܂��B
     * 
     * TODO Java���ꂨ���C#.NET����Ɉˑ������L�q������܂��B
     * 
     * @param type
     * @param columnStructure
     */
    protected void convertOldSqlInputTypeToJdbc(final String type,
            final BlancoDbMetaDataColumnStructure columnStructure) {
        if (type.equals("boolean")) {
            columnStructure.setDataType(Types.BIT);
            columnStructure.setNullable(ResultSetMetaData.columnNoNulls);
        } else if (type.equals("java.lang.Boolean")) {
            columnStructure.setDataType(Types.BIT);
        } else if (type.equals("byte")) {
            columnStructure.setDataType(Types.TINYINT);
            columnStructure.setNullable(ResultSetMetaData.columnNoNulls);
        } else if (type.equals("java.lang.Byte")) {
            columnStructure.setDataType(Types.TINYINT);
        } else if (type.equals("short")) {
            columnStructure.setDataType(Types.SMALLINT);
            columnStructure.setNullable(ResultSetMetaData.columnNoNulls);
        } else if (type.equals("java.lang.Short")) {
            columnStructure.setDataType(Types.SMALLINT);
        } else if (type.equals("int")) {
            columnStructure.setDataType(Types.INTEGER);
            columnStructure.setNullable(ResultSetMetaData.columnNoNulls);
        } else if (type.equals("java.lang.Integer")) {
            columnStructure.setDataType(Types.INTEGER);
        } else if (type.equals("long")) {
            columnStructure.setDataType(Types.BIGINT);
            columnStructure.setNullable(ResultSetMetaData.columnNoNulls);
        } else if (type.equals("java.lang.Long")) {
            columnStructure.setDataType(Types.BIGINT);
        } else if (type.equals("float")) {
            columnStructure.setDataType(Types.REAL);
            columnStructure.setNullable(ResultSetMetaData.columnNoNulls);
        } else if (type.equals("java.lang.Float")) {
            columnStructure.setDataType(Types.REAL);
        } else if (type.equals("double")) {
            columnStructure.setDataType(Types.FLOAT);
            columnStructure.setNullable(ResultSetMetaData.columnNoNulls);
        } else if (type.equals("java.lang.Double")) {
            columnStructure.setDataType(Types.FLOAT);
        } else if (type.equals("java.math.BigDecimal")) {
            columnStructure.setDataType(Types.DECIMAL);
        } else if (type.equals("java.lang.String")) {
            columnStructure.setDataType(Types.VARCHAR);
        } else if (type.equals("java.util.Date")) {
            columnStructure.setDataType(Types.TIMESTAMP);
        } else if (type.equals("java.io.InputStream")) {
            columnStructure.setDataType(Types.LONGVARBINARY);
        } else if (type.equals("java.io.Reader")) {
            columnStructure.setDataType(Types.LONGVARCHAR);
        } else if (type.equals("string")) {
            // C#.NET�̋��Œ�`����̌^�B
            columnStructure.setDataType(Types.VARCHAR);
        	if (IS_FORCE_CS_DOTNET_STRING_AS_NVARCHAR) {
        		// FIXME 2012.07.09 ������A���̒l�͊O������ݒ�ł���悤�ɕύX�������B
                columnStructure.setDataType(Types.NVARCHAR);
        	}
        } else if (type.equals("string(Unicode)")) {
            // C#.NET�̋��Œ�`����̌^�B
            columnStructure.setDataType(Types.NVARCHAR);
        } else if (type.equals("bool")) {
            // C#.NET�̋��Œ�`����̌^�B
            columnStructure.setDataType(Types.BIT);
        } else if (type.equals("decimal")) {
            // C#.NET�̋��Œ�`����̌^�B
            columnStructure.setDataType(Types.DECIMAL);
        } else if (type.equals("System.DateTime")) {
            // C#.NET�̋��Œ�`����̌^�B
            columnStructure.setDataType(Types.TIMESTAMP);
        } else if (type.equals("byte[]")) {
            // C#.NET�̋��Œ�`����̌^�B
            columnStructure.setDataType(Types.LONGVARBINARY);
        } else {
            throw new IllegalArgumentException("blancoDb�ŃT�|�[�g���Ȃ��^[" + type
                    + "]���^�����܂����B");
        }
    }
}