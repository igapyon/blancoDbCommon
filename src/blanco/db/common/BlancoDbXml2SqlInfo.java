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
 * SQL定義書の中間XMLファイルを入力として、SQL定義書に関する各種情報構造体を構築します。
 * 
 * このクラスの中では、検索型のSQL文は実際にSQL文を実行します。SQL文を実行することにより、検索結果の列一覧を取得し、
 * SQL入力パラメータの妥当性のチェックを行ったり、あるいはSQL文が そもそも実行できるかどうかを確認することができます。
 * 
 * @author Yasuo Nakanishi
 */
public class BlancoDbXml2SqlInfo {
    /**
     * blancoDbリソースバンドル情報へのアクセサ。
     */
    private final BlancoDbCommonResourceBundle fBundle = new BlancoDbCommonResourceBundle();

    /**
     * SQL定義書の中間XMLファイルを入力として、SQL定義書に関する各種情報構造体を構築します。
     * 
     * @param conn
     *            データベース接続。
     * @param dbSetting
     *            blancoDb設定。
     * @param fileSqlForm
     *            入力とする中間XMLファイル。
     * @return SQL定義情報構造のリスト。
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
                // 収集されたもののうち、検索型については、データベース接続して試し打ちが行われます。
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

            // SQL文を実行して結果セットを取得し、ここから検索結果の列一覧を取得します。
            // JDBCの機能を利用してSQL文を試し打ちし、検索結果の列一覧を取得するという、blancoDbの核ともいえる機能の実装箇所です。

            List<BlancoDbMetaDataColumnStructure> nativeParam = convertSqlInParameter2NativeParameter(
                    sqlInfo, parserUtil);
            if (dbSetting.getExecuteSql() == BlancoDbExecuteSqlStringGroup.NONE) {
                // noneの場合には paramにnullをセットします。
                nativeParam = null;
            }

            // paramに値がセットされている場合にはSQLを実行します。
            final List<BlancoDbMetaDataColumnStructure> listResult = BlancoDbMetaDataSql
                    .getResultSetMetaData(conn,
                            parserUtil.getNaturalSqlStringForJava(),
                            nativeParam);

            // 検索結果セットの列の一覧について、収集後の情報として記憶します。
            sqlInfo.setResultSetColumnList(listResult);

        } catch (SQLException e) {
            throw new IllegalArgumentException(fBundle.getXml2javaclassErr002(
                    sqlInfo.getName(), e.getSQLState(),
                    BlancoBigDecimalUtil.toBigDecimal(e.getErrorCode()),
                    e.toString()));
        }
    }

    /**
     * SQL定義書のSQL入力パラメータを、実際のJDBC上で実行されるSQL文のSQL入力パラメータへと変換します。
     * 
     * @param sqlInfo
     * @param parserUtil
     * @return
     */
    private List<BlancoDbMetaDataColumnStructure> convertSqlInParameter2NativeParameter(
            final BlancoDbSqlInfoStructure sqlInfo,
            final BlancoDbQueryParserUtil parserUtil) {
        // 同一名称を実際のSQL上の ? に対応するために複数に展開します。
        int maxNativeCol = 0;
        final Map<String, BlancoDbMetaDataColumnStructure> hashCol = new HashMap<String, BlancoDbMetaDataColumnStructure>();

        for (int indexCol = 0; indexCol < sqlInfo.getInParameterList().size(); indexCol++) {
            final BlancoDbMetaDataColumnStructure columnStructure = sqlInfo
                    .getInParameterList().get(indexCol);
            final int[] listNativeCol = parserUtil
                    .getSqlParameters(columnStructure.getName());
            if (listNativeCol == null) {
                throw new IllegalArgumentException("SQL定義ID["
                        + sqlInfo.getName() + "]の SQL入力パラメータ["
                        + columnStructure.getName() + "]が結びついていません.");
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
                throw new IllegalArgumentException("SQL定義ID["
                        + sqlInfo.getName() + "]の SQL入力パラメータ展開時に予期せぬ例外が発生. ("
                        + indexNativeCol + ")番目の入力パラメータが取得できません。");
            }
            nativeParam.add(objLook);
        }

        return nativeParam;
    }
}