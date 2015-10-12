/*
 * このソースコードは blanco Frameworkにより自動生成されました。
 */
package blanco.db.common.stringgroup;

/**
 * JDBCドライバ名の一覧。
 */
public class BlancoDbDriverNameStringGroup {
    /**
     * No.1 説明:SQL Server 2000用のJDBCドライバ
     */
    public static final int SQLSERVER_2000 = 1;

    /**
     * No.2 説明:SQL Server 2005用のJDBCドライバ。SQL Server 2000にも接続可能。
     */
    public static final int SQLSERVER_2005 = 2;

    /**
     * No.3 説明:SQL Server 2008用のJDBCドライバ。
     */
    public static final int SQLSERVER_2_0 = 3;

    /**
     * No.4 説明:SQL Server 2008用のJDBCドライバ。
     */
    public static final int SQLSERVER_3_0 = 4;

    /**
     * No.5 説明:Oracle用のJDBCドライバ。
     */
    public static final int ORACLE = 5;

    /**
     * No.6 説明:PostgreSQL用のJDBCドライバ。
     */
    public static final int POSTGRESQL = 6;

    /**
     * No.7 説明:MySQL用のJDBCドライバ。
     */
    public static final int MYSQL = 7;

    /**
     * No.8 説明:SQLite 用の JDBC ドライバ。
     */
    public static final int SQLITE = 8;

    /**
     * 未定義。文字列グループ以外の文字列または定数が未定義のもの。
     */
    public static final int NOT_DEFINED = -1;

    /**
     * 文字列グループに含まれる文字列であるかどうかを判定します。
     *
     * @param argCheck チェックを行いたい文字列。
     * @return 文字列グループに含まれていればture。グループに含まれない文字列であればfalse。
     */
    public boolean match(final String argCheck) {
        // No.1
        // 説明:SQL Server 2000用のJDBCドライバ
        if ("SQLServer".equals(argCheck)) {
            return true;
        }
        // No.2
        // 説明:SQL Server 2005用のJDBCドライバ。SQL Server 2000にも接続可能。
        if ("Microsoft SQL Server 2005 JDBC Driver".equals(argCheck)) {
            return true;
        }
        // No.3
        // 説明:SQL Server 2008用のJDBCドライバ。
        if ("Microsoft SQL Server JDBC Driver 2.0".equals(argCheck)) {
            return true;
        }
        // No.4
        // 説明:SQL Server 2008用のJDBCドライバ。
        if ("Microsoft SQL Server JDBC Driver 3.0".equals(argCheck)) {
            return true;
        }
        // No.5
        // 説明:Oracle用のJDBCドライバ。
        if ("Oracle JDBC driver".equals(argCheck)) {
            return true;
        }
        // No.6
        // 説明:PostgreSQL用のJDBCドライバ。
        if ("PostgreSQL Native Driver".equals(argCheck)) {
            return true;
        }
        // No.7
        // 説明:MySQL用のJDBCドライバ。
        if ("MySQL-AB JDBC Driver".equals(argCheck)) {
            return true;
        }
        // No.8
        // 説明:SQLite 用の JDBC ドライバ。
        if ("SQLiteJDBC".equals(argCheck)) {
            return true;
        }
        return false;
    }

    /**
     * 文字列グループに含まれる文字列であるかどうかを、大文字小文字を区別せず判定します。
     *
     * @param argCheck チェックを行いたい文字列。
     * @return 文字列グループに含まれていればture。グループに含まれない文字列であればfalse。
     */
    public boolean matchIgnoreCase(final String argCheck) {
        // No.1
        // 説明:SQL Server 2000用のJDBCドライバ
        if ("SQLServer".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.2
        // 説明:SQL Server 2005用のJDBCドライバ。SQL Server 2000にも接続可能。
        if ("Microsoft SQL Server 2005 JDBC Driver".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.3
        // 説明:SQL Server 2008用のJDBCドライバ。
        if ("Microsoft SQL Server JDBC Driver 2.0".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.4
        // 説明:SQL Server 2008用のJDBCドライバ。
        if ("Microsoft SQL Server JDBC Driver 3.0".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.5
        // 説明:Oracle用のJDBCドライバ。
        if ("Oracle JDBC driver".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.6
        // 説明:PostgreSQL用のJDBCドライバ。
        if ("PostgreSQL Native Driver".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.7
        // 説明:MySQL用のJDBCドライバ。
        if ("MySQL-AB JDBC Driver".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.8
        // 説明:SQLite 用の JDBC ドライバ。
        if ("SQLiteJDBC".equalsIgnoreCase(argCheck)) {
            return true;
        }
        return false;
    }

    /**
     * 文字列から定数に変換します。
     *
     * 定数が未定義の場合や 与えられた文字列が文字列グループ外の場合には NOT_DEFINED を戻します。
     *
     * @param argCheck 変換を行いたい文字列。
     * @return 定数に変換後の値。
     */
    public int convertToInt(final String argCheck) {
        // No.1
        // 説明:SQL Server 2000用のJDBCドライバ
        if ("SQLServer".equals(argCheck)) {
            return SQLSERVER_2000;
        }
        // No.2
        // 説明:SQL Server 2005用のJDBCドライバ。SQL Server 2000にも接続可能。
        if ("Microsoft SQL Server 2005 JDBC Driver".equals(argCheck)) {
            return SQLSERVER_2005;
        }
        // No.3
        // 説明:SQL Server 2008用のJDBCドライバ。
        if ("Microsoft SQL Server JDBC Driver 2.0".equals(argCheck)) {
            return SQLSERVER_2_0;
        }
        // No.4
        // 説明:SQL Server 2008用のJDBCドライバ。
        if ("Microsoft SQL Server JDBC Driver 3.0".equals(argCheck)) {
            return SQLSERVER_3_0;
        }
        // No.5
        // 説明:Oracle用のJDBCドライバ。
        if ("Oracle JDBC driver".equals(argCheck)) {
            return ORACLE;
        }
        // No.6
        // 説明:PostgreSQL用のJDBCドライバ。
        if ("PostgreSQL Native Driver".equals(argCheck)) {
            return POSTGRESQL;
        }
        // No.7
        // 説明:MySQL用のJDBCドライバ。
        if ("MySQL-AB JDBC Driver".equals(argCheck)) {
            return MYSQL;
        }
        // No.8
        // 説明:SQLite 用の JDBC ドライバ。
        if ("SQLiteJDBC".equals(argCheck)) {
            return SQLITE;
        }

        // 該当する定数が見つかりませんでした。
        return NOT_DEFINED;
    }

    /**
     * 定数から文字列に変換します。
     *
     * 定数と対応づく文字列に変換します。
     *
     * @param argCheck 変換を行いたい文字定数。
     * @return 文字列に変換後の値。NOT_DEFINEDの場合には長さ0の文字列。
     */
    public String convertToString(final int argCheck) {
        // No.1
        // 説明:SQL Server 2000用のJDBCドライバ
        if (argCheck == SQLSERVER_2000) {
            return "SQLServer";
        }
        // No.2
        // 説明:SQL Server 2005用のJDBCドライバ。SQL Server 2000にも接続可能。
        if (argCheck == SQLSERVER_2005) {
            return "Microsoft SQL Server 2005 JDBC Driver";
        }
        // No.3
        // 説明:SQL Server 2008用のJDBCドライバ。
        if (argCheck == SQLSERVER_2_0) {
            return "Microsoft SQL Server JDBC Driver 2.0";
        }
        // No.4
        // 説明:SQL Server 2008用のJDBCドライバ。
        if (argCheck == SQLSERVER_3_0) {
            return "Microsoft SQL Server JDBC Driver 3.0";
        }
        // No.5
        // 説明:Oracle用のJDBCドライバ。
        if (argCheck == ORACLE) {
            return "Oracle JDBC driver";
        }
        // No.6
        // 説明:PostgreSQL用のJDBCドライバ。
        if (argCheck == POSTGRESQL) {
            return "PostgreSQL Native Driver";
        }
        // No.7
        // 説明:MySQL用のJDBCドライバ。
        if (argCheck == MYSQL) {
            return "MySQL-AB JDBC Driver";
        }
        // No.8
        // 説明:SQLite 用の JDBC ドライバ。
        if (argCheck == SQLITE) {
            return "SQLiteJDBC";
        }
        // 未定義。
        if (argCheck == NOT_DEFINED) {
            return "";
        }

        // いずれにも該当しませんでした。
        throw new IllegalArgumentException("与えられた値(" + argCheck + ")は文字列グループ[BlancoDbDriverName]では定義されない値です。");
    }
}
