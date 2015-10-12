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

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import blanco.commons.util.BlancoStringUtil;
import blanco.db.common.stringgroup.BlancoDbDriverNameStringGroup;
import blanco.db.common.valueobject.BlancoDbSetting;
import blanco.db.common.valueobject.BlancoDbSqlInfoStructure;
import blanco.dbmetadata.BlancoDbMetaDataUtil;
import blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure;
import blanco.dbmetadata.valueobject.BlancoDbMetaDataKeyStructure;
import blanco.dbmetadata.valueobject.BlancoDbMetaDataTableStructure;
import blanco.xml.bind.BlancoXmlBindingUtil;
import blanco.xml.bind.BlancoXmlUnmarshaller;
import blanco.xml.bind.valueobject.BlancoXmlDocument;
import blanco.xml.bind.valueobject.BlancoXmlElement;

/**
 * blancoDb の共通ユーティリティ・クラス。
 */
public class BlancoDbUtil {
    /**
     * プライマリキーであるかどうかをチェックするメソッド。
     * 
     * @param table
     * @param columnStructure
     * @return
     */
    public static boolean isPrimaryKey(
            final BlancoDbMetaDataTableStructure table,
            final BlancoDbMetaDataColumnStructure columnStructure) {
        for (int index = 0; index < table.getPrimaryKeys().size(); index++) {
            final BlancoDbMetaDataKeyStructure columnLook = (BlancoDbMetaDataKeyStructure) table
                    .getPrimaryKeys().get(index);
            if (columnLook.getPkcolumnName().equals(columnStructure.getName())) {
                // プライマリキーです。
                return true;
            }
        }
        return false;
    }

    /**
     * ランタイムパッケージのルートフォルダを取得します。
     * 
     * 指定がない場合には基準ディレクトリが利用されます。
     * 
     * @param dbSetting
     * @return
     */
    public static String getRuntimePackage(final BlancoDbSetting dbSetting) {
        String runtimePackage = dbSetting.getBasePackage();
        if (dbSetting.getRuntimePackage() != null) {
            runtimePackage = dbSetting.getRuntimePackage();
        }
        return runtimePackage;
    }

    /**
     * データベース接続の確立を試みます。
     * 
     * @param connDef
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("deprecation")
    public static Connection connect(final BlancoDbSetting dbSetting)
            throws SQLException, ClassNotFoundException {
        System.out.println("データベース接続をオープンします.");

        try {
            ClassLoader loader = null;
            if (BlancoStringUtil.null2Blank(dbSetting.getJdbcdriverfile())
                    .length() > 0) {
                List<String> urlList = new ArrayList<String>();
                final StringTokenizer token = new StringTokenizer(dbSetting
                        .getJdbcdriverfile(), ";");
                for (; token.hasMoreTokens();) {
                    final String file = token.nextToken();
                    if (BlancoStringUtil.null2Blank(file).trim().length() > 0) {
                        urlList.add(file);
                    }
                }

                final URL[] urlArray = new URL[urlList.size()];
                int index = 0;
                for (String look : urlList) {
                    try {
                        urlArray[index++] = new File(look).toURL();
                    } catch (MalformedURLException e) {
                        throw new IllegalArgumentException(
                                "JDBC 接続確立: jar ファイルの URL 取得に失敗しました。", e);
                    }
                }
                loader = BlancoDbMetaDataUtil.loadDriverClass(urlArray,
                        dbSetting.getJdbcdriver());
            }

            System.out.println("  Driver:" + dbSetting.getJdbcdriver());
            System.out.println("  URL:" + dbSetting.getJdbcurl());
            System.out.println("  User:" + dbSetting.getJdbcuser());
            Connection conn = null;
            if (loader == null) {
                conn = BlancoDbMetaDataUtil.connect(dbSetting.getJdbcdriver(),
                        dbSetting.getJdbcurl(), dbSetting.getJdbcuser(),
                        dbSetting.getJdbcpassword());
            } else {
                conn = BlancoDbMetaDataUtil.connect(dbSetting.getJdbcdriver(),
                        dbSetting.getJdbcurl(), dbSetting.getJdbcuser(),
                        dbSetting.getJdbcpassword(), loader);
            }

            // 自動コミットをOFFに設定します。
            conn.setAutoCommit(false);
            return conn;
        } catch (SQLException ex) {
            System.out.println("JDBC接続の確立に失敗しました: " + ex.toString());
            throw ex;
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC接続の確立に失敗しました: " + ex.toString());
            throw ex;
        }
    }

    public static void close(final Connection conn) {
        if (conn != null) {
            try {
                System.out.println("データベース接続をクローズします. (rollbackしてcloseします)");
                try {
                    conn.rollback();
                } finally {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("JDBC接続の開放に失敗しました: " + e.toString());
                e.printStackTrace();
            }
        }
    }

    /**
     * データベースのバージョン情報などを取得します。
     */
    public static void getDatabaseVersionInfo(final Connection conn,
            final BlancoDbSetting dbSetting) {
        System.out.println("JDBCドライバの基礎情報を取得します。");
        try {
            final DatabaseMetaData databaseMetaData = conn.getMetaData();

            String driverName = databaseMetaData.getDriverName();
            final String _driverVersion = databaseMetaData.getDriverVersion();
            System.out.println("  DriverName:" + driverName);
            System.out.println("  DriverVersion:" + _driverVersion);

            try {
                System.out.println("  DatabaseMajorVersion:"
                        + databaseMetaData.getDatabaseMajorVersion());
                System.out.println("  DatabaseMinorVersion:"
                        + databaseMetaData.getDatabaseMinorVersion());
            } catch (java.lang.AbstractMethodError er) {
                // SQL Server 2000 JDBC Driverは このメソッドをサポートしません。
                // System.out.println(er.toString());
            } catch (SQLException ex) {
            }
            try {
                System.out.println("  JDBCMajorVersion:"
                        + databaseMetaData.getJDBCMajorVersion());
                System.out.println("  JDBCMinorVersion:"
                        + databaseMetaData.getJDBCMinorVersion());
            } catch (java.lang.AbstractMethodError er) {
                // SQL Server 2000 JDBC Driverは このメソッドをサポートしません。
                // System.out.println(er.toString());
            } catch (SQLException ex) {
                // Oracle 9i JDBC Driverは このメソッドをサポートしません。
            }

            dbSetting.setDriverName(new BlancoDbDriverNameStringGroup()
                    .convertToInt(driverName));
            if (BlancoDbDriverNameStringGroup.NOT_DEFINED == dbSetting
                    .getDriverName()) {
                System.out.println("未知のJDBCドライバです: " + driverName);
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
            return;
        }
    }

    /**
     * Eclipse Java プロジェクトからクラスパス情報を読み取り。
     * 
     * 「.classpath」ファイルの記載内容を入力とします。
     * 
     * @param fileClasspath
     *            入力となる「.classpath」ファイル。
     * @param dbSetting
     *            出力となる DB 情報。
     */
    public static void readClasspathEntryFromEclipseJavaProject(
            final File fileClasspath, final BlancoDbSetting dbSetting) {
        if (fileClasspath == null) {
            return;
        }
        if (fileClasspath.exists() == false) {
            return;
        }

        final BlancoXmlUnmarshaller unmarshaller = new BlancoXmlUnmarshaller();
        final BlancoXmlDocument doc = unmarshaller.unmarshal(fileClasspath);
        final List<BlancoXmlElement> classpathEntryList = BlancoXmlBindingUtil
                .getElementsByTagName(BlancoXmlBindingUtil
                        .getDocumentElement(doc), "classpathentry");

        for (BlancoXmlElement entry : classpathEntryList) {
            String kind = BlancoXmlBindingUtil.getAttribute(entry, "kind");
            if ("lib".equals(kind) == false) {
                continue;
            }
            if (dbSetting.getJdbcdriverfile() == null) {
                dbSetting.setJdbcdriverfile("");
            }
            if (dbSetting.getJdbcdriverfile().length() > 0) {
                dbSetting
                        .setJdbcdriverfile(dbSetting.getJdbcdriverfile() + ";");
            }
            dbSetting.setJdbcdriverfile(dbSetting.getJdbcdriverfile()
                    + fileClasspath.getParentFile().getAbsolutePath() + "/"
                    + BlancoXmlBindingUtil.getAttribute(entry, "path"));
        }
    }

    /**
     * 基準パッケージを取得します。BlancoDbSqlInfoStructure を優先し、無い場合には BlancoDbSetting
     * を利用して確定させます。
     * 
     * @param sqlInfo
     * @param dbSetting
     * @return
     */
    public static String getBasePackage(final BlancoDbSqlInfoStructure sqlInfo,
            final BlancoDbSetting dbSetting) {
        String basePackage = sqlInfo.getPackage();
        if (BlancoStringUtil.null2Blank(basePackage).length() == 0) {
            basePackage = dbSetting.getBasePackage();
        }

        return basePackage;
    }
}
