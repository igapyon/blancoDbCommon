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
 * blancoDb �̋��ʃ��[�e�B���e�B�E�N���X�B
 */
public class BlancoDbUtil {
    /**
     * �v���C�}���L�[�ł��邩�ǂ������`�F�b�N���郁�\�b�h�B
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
                // �v���C�}���L�[�ł��B
                return true;
            }
        }
        return false;
    }

    /**
     * �����^�C���p�b�P�[�W�̃��[�g�t�H���_���擾���܂��B
     * 
     * �w�肪�Ȃ��ꍇ�ɂ͊�f�B���N�g�������p����܂��B
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
     * �f�[�^�x�[�X�ڑ��̊m�������݂܂��B
     * 
     * @param connDef
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("deprecation")
    public static Connection connect(final BlancoDbSetting dbSetting)
            throws SQLException, ClassNotFoundException {
        System.out.println("�f�[�^�x�[�X�ڑ����I�[�v�����܂�.");

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
                                "JDBC �ڑ��m��: jar �t�@�C���� URL �擾�Ɏ��s���܂����B", e);
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

            // �����R�~�b�g��OFF�ɐݒ肵�܂��B
            conn.setAutoCommit(false);
            return conn;
        } catch (SQLException ex) {
            System.out.println("JDBC�ڑ��̊m���Ɏ��s���܂���: " + ex.toString());
            throw ex;
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC�ڑ��̊m���Ɏ��s���܂���: " + ex.toString());
            throw ex;
        }
    }

    public static void close(final Connection conn) {
        if (conn != null) {
            try {
                System.out.println("�f�[�^�x�[�X�ڑ����N���[�Y���܂�. (rollback����close���܂�)");
                try {
                    conn.rollback();
                } finally {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("JDBC�ڑ��̊J���Ɏ��s���܂���: " + e.toString());
                e.printStackTrace();
            }
        }
    }

    /**
     * �f�[�^�x�[�X�̃o�[�W�������Ȃǂ��擾���܂��B
     */
    public static void getDatabaseVersionInfo(final Connection conn,
            final BlancoDbSetting dbSetting) {
        System.out.println("JDBC�h���C�o�̊�b�����擾���܂��B");
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
                // SQL Server 2000 JDBC Driver�� ���̃��\�b�h���T�|�[�g���܂���B
                // System.out.println(er.toString());
            } catch (SQLException ex) {
            }
            try {
                System.out.println("  JDBCMajorVersion:"
                        + databaseMetaData.getJDBCMajorVersion());
                System.out.println("  JDBCMinorVersion:"
                        + databaseMetaData.getJDBCMinorVersion());
            } catch (java.lang.AbstractMethodError er) {
                // SQL Server 2000 JDBC Driver�� ���̃��\�b�h���T�|�[�g���܂���B
                // System.out.println(er.toString());
            } catch (SQLException ex) {
                // Oracle 9i JDBC Driver�� ���̃��\�b�h���T�|�[�g���܂���B
            }

            dbSetting.setDriverName(new BlancoDbDriverNameStringGroup()
                    .convertToInt(driverName));
            if (BlancoDbDriverNameStringGroup.NOT_DEFINED == dbSetting
                    .getDriverName()) {
                System.out.println("���m��JDBC�h���C�o�ł�: " + driverName);
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
            return;
        }
    }

    /**
     * Eclipse Java �v���W�F�N�g����N���X�p�X����ǂݎ��B
     * 
     * �u.classpath�v�t�@�C���̋L�ړ��e����͂Ƃ��܂��B
     * 
     * @param fileClasspath
     *            ���͂ƂȂ�u.classpath�v�t�@�C���B
     * @param dbSetting
     *            �o�͂ƂȂ� DB ���B
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
     * ��p�b�P�[�W���擾���܂��BBlancoDbSqlInfoStructure ��D�悵�A�����ꍇ�ɂ� BlancoDbSetting
     * �𗘗p���Ċm�肳���܂��B
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
