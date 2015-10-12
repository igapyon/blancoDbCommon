/*
 * blancoDb Enterprise Edition
 * Copyright (C) 2004-2005 Tosiki Iga
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
package blanco.db.common.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * blancoDbが利用するQueryパース・ユーティリティクラス
 * 
 * SQLの解釈および変換などを目的とします。
 * 
 * @author Tosiki Iga
 */
public class BlancoDbQueryParserUtil {
    /**
     * SQL入力パラメータとして判定するための正規表現文字列。
     */
    private static final String SZ_PARAMETER_FOR_SQL_INPUT_PARAMETER = "#[a-zA-Z0-9.\\-_\\P{InBasicLatin}]*\\b|#.*$";

    /**
     * SQL入力パラメータのマップ <br>
     * TODO マップを利用していますが、これだと順序性が確保されません。
     */
    @SuppressWarnings("unchecked")
    private final Map fMapForSqlInputParameters = new Hashtable();

    /**
     * オリジナルのSQL文字列
     */
    private String fOriginalSqlQueryString = "";

    @SuppressWarnings("unchecked")
    public BlancoDbQueryParserUtil(final String sqlQueryString) {
        // パラメータを記憶します。
        fOriginalSqlQueryString = sqlQueryString;

        // 正規表現文字列インスタンスを生成します。
        // TODO 正規表現による処理において不適切な状況が発生する可能性があります。
        final Matcher matcher = Pattern.compile(
                SZ_PARAMETER_FOR_SQL_INPUT_PARAMETER).matcher(
                fOriginalSqlQueryString);

        for (int index = 1; matcher.find(); index++) {
            String name = matcher.group();
            // 先頭の＃を除去します。
            name = name.substring(1, name.length());
            if (fMapForSqlInputParameters.containsKey(name) == false) {
                fMapForSqlInputParameters.put(name, new ArrayList());
            }
            ((List) fMapForSqlInputParameters.get(name))
                    .add(new Integer(index));
        }

        for (Iterator ite = fMapForSqlInputParameters.keySet().iterator(); ite
                .hasNext();) {
            final String key = (String) ite.next();
            final List list = (List) fMapForSqlInputParameters.get(key);
            fMapForSqlInputParameters.put(key, convertListToArray(list));
        }
    }

    /**
     * 入力されたSQL入力パラメータをint配列に変換します。
     * 
     * @param sqlInputParameterFoundList
     * @return
     */
    @SuppressWarnings("unchecked")
    private int[] convertListToArray(final List sqlInputParameterFoundList) {
        final int[] convertedIntArray = new int[sqlInputParameterFoundList
                .size()];
        final Iterator ite = sqlInputParameterFoundList.iterator();
        for (int index = 0; ite.hasNext(); index++) {
            convertedIntArray[index] = ((Integer) ite.next()).intValue();
        }
        return convertedIntArray;
    }

    /**
     * 試作：キーを元に、intをゲットします。
     * 
     * @param key
     * @return
     */
    public int[] getSqlParameters(final String key) {
        // マップからIteratorを作成している点に注意。
        return (int[]) fMapForSqlInputParameters.get(key);
    }

    /**
     * JDBCに実際に発行する際に利用されるナチュラルなSQL文
     * 
     * SQL文からメタ情報を取得する際に利用されます。つまり、DotNet版など
     * Java版以外の版のソースコード自動生成の際に、このメソッドが利用されます。
     * 
     * @return
     */
    public String getNaturalSqlStringForJava() {
        return fOriginalSqlQueryString.replaceAll(
                SZ_PARAMETER_FOR_SQL_INPUT_PARAMETER, "?");
    }
}