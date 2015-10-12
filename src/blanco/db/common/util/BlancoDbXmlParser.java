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
 * SQL定義書の中間XMLを読み込んでJavaオブジェクト化します。
 * 
 * @author IGA Tosiki
 */
public class BlancoDbXmlParser {
	/**
	 * C#.NET の  SQL 入力パラメータの挙動の構造的な問題に対する対処方法。
	 * 
	 * FIXME いずれ、この変数を外部化してください。
	 */
	private static final boolean IS_FORCE_CS_DOTNET_STRING_AS_NVARCHAR = false;

	/**
     * blancoDbリソースバンドル情報へのアクセサ。
     */
    private final BlancoDbCommonResourceBundle fBundle = new BlancoDbCommonResourceBundle();

    /**
     * 共通情報が蓄えられているエレメント名。
     */
    private static final String ELEMENT_COMMON = "blancodb-common";

    /**
     * SQL入力パラメータが蓄えられているエレメント名。
     */
    private static final String ELEMENT_INPARAMETERS = "blancodb-inparameters";

    /**
     * SQL出力パラメータが蓄えられているエレメント名。
     */
    private static final String ELEMENT_OUTPARAMETERS = "blancodb-outparameters";

    /**
     * SQL文が蓄えられているエレメント名。
     */
    private static final String ELEMENT_QUERY = "blancodb-query";

    /**
     * SQL定義書の中間XMLを入力に、SQL定義書情報を収集します。
     * 
     * @param conn
     *            データベース接続設定情報。
     * @param fileSqlForm
     *            処理を行いたいSQL定義書のXML中間形式ファイル。
     * @return 解析後のSQL定義書のリスト。
     * @throws SQLException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public List<BlancoDbSqlInfoStructure> parse(final File fileSqlForm)
            throws SQLException, SAXException, IOException,
            ParserConfigurationException, TransformerException {
        // blancoDb定義情報
        final List<BlancoDbSqlInfoStructure> resultBlancoDbDef = new ArrayList<BlancoDbSqlInfoStructure>();

        // XMLファイルをDOMとしてパースします。
        InputStream inStream = null;
        final DOMResult result;
        try {
            inStream = new BufferedInputStream(new FileInputStream(fileSqlForm));
            result = BlancoXmlUtil.transformStream2Dom(inStream);
        } finally {
            inStream.close();
        }

        // ルートノードを取得します。
        final Node nodeRootNode = result.getNode();
        if (nodeRootNode == null) {
            // XMLファイルではありません。
            return resultBlancoDbDef;
        }

        final Element eleWorkbook = BlancoXmlUtil.getElement(nodeRootNode,
                "workbook");
        if (eleWorkbook == null) {
            return resultBlancoDbDef;
        }

        // シートエレメントを展開します。
        final NodeList listSheet = eleWorkbook.getElementsByTagName("sheet");
        if (listSheet == null) {
            // シートが見つかりません。
            return resultBlancoDbDef;
        }

        // 各シートエレメントについて処理を実施。
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
     * 与えられたシートエレメントを展開します。
     * 
     * @param eleSheet
     *            シートエレメント。
     * @param conn
     *            blancoデータベース接続オブジェクト。
     * @param resultBlancoDbDef
     *            blancoDb定義情報。
     */
    private void expandSheet(final Element eleSheet,
            final List<BlancoDbSqlInfoStructure> resultBlancoDbDef) {
        // 最初に共通情報を展開します。
        final BlancoDbSqlInfoStructure fSqlInfo = expandCommon(eleSheet);
        if (fSqlInfo == null) {
            // SQL定義書としてふさわしくないので、処理をスキップします。
            return;
        }

        // SQL入力パラメータを展開します。
        expandInParameter(eleSheet, fSqlInfo);

        // SQL出力パラメータを展開します。
        expandOutParameter(eleSheet, fSqlInfo);

        // SQL文を展開します。
        expandQuery(eleSheet, fSqlInfo);

        // ひととおりの情報をそろえた上で、収集した情報の妥当性チェックをおこないます。
        if (fSqlInfo.getQuery() == null || fSqlInfo.getQuery().length() == 0) {
            // SQL文が取得できないものはエラー扱いします。
            throw new IllegalArgumentException(fBundle
                    .getXml2javaclassErr001(fSqlInfo.getName()));
        }

        // 解析後のSQL定義書のリストへ情報を追加。
        resultBlancoDbDef.add(fSqlInfo);
    }

    /**
     * 与えられた共通エレメントを解析して情報を展開します。
     * 
     * @param elementSheet
     *            シートオブジェクト。
     * @return 抽象クエリオブジェクト。
     */
    private BlancoDbSqlInfoStructure expandCommon(final Element eleSheet) {
        final Element elementCommon = BlancoXmlUtil.getElement(eleSheet,
                ELEMENT_COMMON);
        if (elementCommon == null) {
            // ELEMENT_COMMONが見つからない場合には、このシートをスキップします。
            return null;
        }

        final BlancoDbSqlInfoStructure fSqlInfo = new BlancoDbSqlInfoStructure();

        fSqlInfo.setName(BlancoStringUtil.null2Blank(BlancoXmlUtil
                .getTextContent(elementCommon, "name")));
        if (fSqlInfo.getName().length() == 0) {
            // この定義書は処理の必要がありません。処理しないこととします。
            // 従来はquery-typeが無指定の場合にも処理をスキップしていましたが、現在はエラー扱いとします。
            // nameが見つかりません。
            return null;
        }

        fSqlInfo.setPackage(BlancoXmlUtil.getTextContent(elementCommon,
                "package"));

        final String queryType = BlancoStringUtil.null2Blank(BlancoXmlUtil
                .getTextContent(elementCommon, "query-type"));
        fSqlInfo.setType(new BlancoDbSqlInfoTypeStringGroup()
                .convertToInt(queryType));
        if (fSqlInfo.getType() == BlancoDbSqlInfoTypeStringGroup.NOT_DEFINED) {
            // 処理できません。中断します。
            throw new IllegalArgumentException("サポートしないクエリタイプ["
                    + fSqlInfo.getType() + "]が与えられました。処理中断します。");
        }

        fSqlInfo.setDescription(BlancoStringUtil.null2Blank(BlancoXmlUtil
                .getTextContent(elementCommon, "description")));
        fSqlInfo.setSingle(BlancoStringUtil.null2Blank(
                BlancoXmlUtil.getTextContent(elementCommon, "single")).equals(
                "true"));

        if (fSqlInfo.getType() == BlancoDbSqlInfoTypeStringGroup.ITERATOR) {
            // 検索型の場合にのみスクロール属性および更新可能属性を読み込みます。
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
            // ステートメント・タイムアウト
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
     * 与えられたシートを解析してSQL入力パラメータ情報を展開します。
     * 
     * @param elementSheet
     *            シートオブジェクト。
     * @param sqlInfo
     *            抽象クエリオブジェクト。
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
            // SQL入力パラメータはありません。
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

            // 現在、descriptionは格納先がありません。
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

            // 最初に新様式のデータ型であることと仮定して読み込みます。
            columnStructure.setDataType(BlancoDbMetaDataUtil
                    .convertJdbcDataType2Int(type));
            if (columnStructure.getDataType() == Integer.MIN_VALUE) {
                // 新様式のデータ型に合致しない場合には、旧様式として読み込みを行います。
                // ここでは、dataTypeおよびnullable について Java型から導出します。
                convertOldSqlInputTypeToJdbc(type, columnStructure);
            }

            sqlInfo.getInParameterList().add(columnStructure);
        }
    }

    /**
     * 与えられたシートを解析してSQL出力パラメータ情報を展開します。
     * 
     * @param elementSheet
     *            シートオブジェクト。
     * @param sqlInfo
     *            抽象クエリオブジェクト。
     */
    private void expandOutParameter(final Element elementSheet,
            final BlancoDbSqlInfoStructure sqlInfo) {
        final Element elementBlancoDbOutparameters = BlancoXmlUtil.getElement(
                elementSheet, ELEMENT_OUTPARAMETERS);
        if (elementBlancoDbOutparameters == null) {
            // SQL出力パラメータはありません。
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

            // 現在、descriptionは格納先がありません。

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
                // caller以外の場合にはSQL出力パラメータはセットできません。
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

            // 最初に新様式のデータ型であることと仮定して読み込みます。
            columnStructure.setDataType(BlancoDbMetaDataUtil
                    .convertJdbcDataType2Int(type));
            if (columnStructure.getDataType() == Integer.MIN_VALUE) {
                // 新様式のデータ型に合致しない場合には、旧様式として読み込みを行います。
                // ここでは、dataTypeおよびnullable について Java型から導出します。
                convertOldSqlInputTypeToJdbc(type, columnStructure);
            }

            sqlInfo.getOutParameterList().add(columnStructure);
        }
    }

    /**
     * 与えられたシートを解析してSQL文に関する情報を展開します。
     * 
     * @param elementSheet
     *            シートオブジェクト。
     * @param sqlInfo
     *            抽象クエリオブジェクト。
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
                // 文字列がすでに存在している場合にのみ改行を付与します。
                query = query + "\n";
            }
            sqlInfo.setQuery(query + queryLine);
        }
    }

    /**
     * 旧バージョンのSQL定義書において、SQL入力／出力パラメータの型名にJava/C#.NETの型名が与えられる。この型名を
     * java.sql.Typesの型名に読み替えるためのルーチン。
     * 
     * このメソッドは旧バージョンの定義書の読込処理の目的で存在します。
     * 
     * TODO Java言語およびC#.NET言語に依存した記述があります。
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
            // C#.NETの旧版定義書上の型。
            columnStructure.setDataType(Types.VARCHAR);
        	if (IS_FORCE_CS_DOTNET_STRING_AS_NVARCHAR) {
        		// FIXME 2012.07.09 いずれ、この値は外部から設定できるように変更したい。
                columnStructure.setDataType(Types.NVARCHAR);
        	}
        } else if (type.equals("string(Unicode)")) {
            // C#.NETの旧版定義書上の型。
            columnStructure.setDataType(Types.NVARCHAR);
        } else if (type.equals("bool")) {
            // C#.NETの旧版定義書上の型。
            columnStructure.setDataType(Types.BIT);
        } else if (type.equals("decimal")) {
            // C#.NETの旧版定義書上の型。
            columnStructure.setDataType(Types.DECIMAL);
        } else if (type.equals("System.DateTime")) {
            // C#.NETの旧版定義書上の型。
            columnStructure.setDataType(Types.TIMESTAMP);
        } else if (type.equals("byte[]")) {
            // C#.NETの旧版定義書上の型。
            columnStructure.setDataType(Types.LONGVARBINARY);
        } else {
            throw new IllegalArgumentException("blancoDbでサポートしない型[" + type
                    + "]が与えられました。");
        }
    }
}