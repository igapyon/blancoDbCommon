/*
 * ���̃\�[�X�R�[�h�� blanco Framework�ɂ�莩����������܂����B
 */
package blanco.db.common.stringgroup;

/**
 * JDBC�h���C�o���̈ꗗ�B
 */
public class BlancoDbDriverNameStringGroup {
    /**
     * No.1 ����:SQL Server 2000�p��JDBC�h���C�o
     */
    public static final int SQLSERVER_2000 = 1;

    /**
     * No.2 ����:SQL Server 2005�p��JDBC�h���C�o�BSQL Server 2000�ɂ��ڑ��\�B
     */
    public static final int SQLSERVER_2005 = 2;

    /**
     * No.3 ����:SQL Server 2008�p��JDBC�h���C�o�B
     */
    public static final int SQLSERVER_2_0 = 3;

    /**
     * No.4 ����:SQL Server 2008�p��JDBC�h���C�o�B
     */
    public static final int SQLSERVER_3_0 = 4;

    /**
     * No.5 ����:Oracle�p��JDBC�h���C�o�B
     */
    public static final int ORACLE = 5;

    /**
     * No.6 ����:PostgreSQL�p��JDBC�h���C�o�B
     */
    public static final int POSTGRESQL = 6;

    /**
     * No.7 ����:MySQL�p��JDBC�h���C�o�B
     */
    public static final int MYSQL = 7;

    /**
     * No.8 ����:SQLite �p�� JDBC �h���C�o�B
     */
    public static final int SQLITE = 8;

    /**
     * ����`�B������O���[�v�ȊO�̕�����܂��͒萔������`�̂��́B
     */
    public static final int NOT_DEFINED = -1;

    /**
     * ������O���[�v�Ɋ܂܂�镶����ł��邩�ǂ����𔻒肵�܂��B
     *
     * @param argCheck �`�F�b�N���s������������B
     * @return ������O���[�v�Ɋ܂܂�Ă����ture�B�O���[�v�Ɋ܂܂�Ȃ�������ł����false�B
     */
    public boolean match(final String argCheck) {
        // No.1
        // ����:SQL Server 2000�p��JDBC�h���C�o
        if ("SQLServer".equals(argCheck)) {
            return true;
        }
        // No.2
        // ����:SQL Server 2005�p��JDBC�h���C�o�BSQL Server 2000�ɂ��ڑ��\�B
        if ("Microsoft SQL Server 2005 JDBC Driver".equals(argCheck)) {
            return true;
        }
        // No.3
        // ����:SQL Server 2008�p��JDBC�h���C�o�B
        if ("Microsoft SQL Server JDBC Driver 2.0".equals(argCheck)) {
            return true;
        }
        // No.4
        // ����:SQL Server 2008�p��JDBC�h���C�o�B
        if ("Microsoft SQL Server JDBC Driver 3.0".equals(argCheck)) {
            return true;
        }
        // No.5
        // ����:Oracle�p��JDBC�h���C�o�B
        if ("Oracle JDBC driver".equals(argCheck)) {
            return true;
        }
        // No.6
        // ����:PostgreSQL�p��JDBC�h���C�o�B
        if ("PostgreSQL Native Driver".equals(argCheck)) {
            return true;
        }
        // No.7
        // ����:MySQL�p��JDBC�h���C�o�B
        if ("MySQL-AB JDBC Driver".equals(argCheck)) {
            return true;
        }
        // No.8
        // ����:SQLite �p�� JDBC �h���C�o�B
        if ("SQLiteJDBC".equals(argCheck)) {
            return true;
        }
        return false;
    }

    /**
     * ������O���[�v�Ɋ܂܂�镶����ł��邩�ǂ������A�啶������������ʂ������肵�܂��B
     *
     * @param argCheck �`�F�b�N���s������������B
     * @return ������O���[�v�Ɋ܂܂�Ă����ture�B�O���[�v�Ɋ܂܂�Ȃ�������ł����false�B
     */
    public boolean matchIgnoreCase(final String argCheck) {
        // No.1
        // ����:SQL Server 2000�p��JDBC�h���C�o
        if ("SQLServer".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.2
        // ����:SQL Server 2005�p��JDBC�h���C�o�BSQL Server 2000�ɂ��ڑ��\�B
        if ("Microsoft SQL Server 2005 JDBC Driver".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.3
        // ����:SQL Server 2008�p��JDBC�h���C�o�B
        if ("Microsoft SQL Server JDBC Driver 2.0".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.4
        // ����:SQL Server 2008�p��JDBC�h���C�o�B
        if ("Microsoft SQL Server JDBC Driver 3.0".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.5
        // ����:Oracle�p��JDBC�h���C�o�B
        if ("Oracle JDBC driver".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.6
        // ����:PostgreSQL�p��JDBC�h���C�o�B
        if ("PostgreSQL Native Driver".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.7
        // ����:MySQL�p��JDBC�h���C�o�B
        if ("MySQL-AB JDBC Driver".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.8
        // ����:SQLite �p�� JDBC �h���C�o�B
        if ("SQLiteJDBC".equalsIgnoreCase(argCheck)) {
            return true;
        }
        return false;
    }

    /**
     * �����񂩂�萔�ɕϊ����܂��B
     *
     * �萔������`�̏ꍇ�� �^����ꂽ�����񂪕�����O���[�v�O�̏ꍇ�ɂ� NOT_DEFINED ��߂��܂��B
     *
     * @param argCheck �ϊ����s������������B
     * @return �萔�ɕϊ���̒l�B
     */
    public int convertToInt(final String argCheck) {
        // No.1
        // ����:SQL Server 2000�p��JDBC�h���C�o
        if ("SQLServer".equals(argCheck)) {
            return SQLSERVER_2000;
        }
        // No.2
        // ����:SQL Server 2005�p��JDBC�h���C�o�BSQL Server 2000�ɂ��ڑ��\�B
        if ("Microsoft SQL Server 2005 JDBC Driver".equals(argCheck)) {
            return SQLSERVER_2005;
        }
        // No.3
        // ����:SQL Server 2008�p��JDBC�h���C�o�B
        if ("Microsoft SQL Server JDBC Driver 2.0".equals(argCheck)) {
            return SQLSERVER_2_0;
        }
        // No.4
        // ����:SQL Server 2008�p��JDBC�h���C�o�B
        if ("Microsoft SQL Server JDBC Driver 3.0".equals(argCheck)) {
            return SQLSERVER_3_0;
        }
        // No.5
        // ����:Oracle�p��JDBC�h���C�o�B
        if ("Oracle JDBC driver".equals(argCheck)) {
            return ORACLE;
        }
        // No.6
        // ����:PostgreSQL�p��JDBC�h���C�o�B
        if ("PostgreSQL Native Driver".equals(argCheck)) {
            return POSTGRESQL;
        }
        // No.7
        // ����:MySQL�p��JDBC�h���C�o�B
        if ("MySQL-AB JDBC Driver".equals(argCheck)) {
            return MYSQL;
        }
        // No.8
        // ����:SQLite �p�� JDBC �h���C�o�B
        if ("SQLiteJDBC".equals(argCheck)) {
            return SQLITE;
        }

        // �Y������萔��������܂���ł����B
        return NOT_DEFINED;
    }

    /**
     * �萔���當����ɕϊ����܂��B
     *
     * �萔�ƑΉ��Â�������ɕϊ����܂��B
     *
     * @param argCheck �ϊ����s�����������萔�B
     * @return ������ɕϊ���̒l�BNOT_DEFINED�̏ꍇ�ɂ͒���0�̕�����B
     */
    public String convertToString(final int argCheck) {
        // No.1
        // ����:SQL Server 2000�p��JDBC�h���C�o
        if (argCheck == SQLSERVER_2000) {
            return "SQLServer";
        }
        // No.2
        // ����:SQL Server 2005�p��JDBC�h���C�o�BSQL Server 2000�ɂ��ڑ��\�B
        if (argCheck == SQLSERVER_2005) {
            return "Microsoft SQL Server 2005 JDBC Driver";
        }
        // No.3
        // ����:SQL Server 2008�p��JDBC�h���C�o�B
        if (argCheck == SQLSERVER_2_0) {
            return "Microsoft SQL Server JDBC Driver 2.0";
        }
        // No.4
        // ����:SQL Server 2008�p��JDBC�h���C�o�B
        if (argCheck == SQLSERVER_3_0) {
            return "Microsoft SQL Server JDBC Driver 3.0";
        }
        // No.5
        // ����:Oracle�p��JDBC�h���C�o�B
        if (argCheck == ORACLE) {
            return "Oracle JDBC driver";
        }
        // No.6
        // ����:PostgreSQL�p��JDBC�h���C�o�B
        if (argCheck == POSTGRESQL) {
            return "PostgreSQL Native Driver";
        }
        // No.7
        // ����:MySQL�p��JDBC�h���C�o�B
        if (argCheck == MYSQL) {
            return "MySQL-AB JDBC Driver";
        }
        // No.8
        // ����:SQLite �p�� JDBC �h���C�o�B
        if (argCheck == SQLITE) {
            return "SQLiteJDBC";
        }
        // ����`�B
        if (argCheck == NOT_DEFINED) {
            return "";
        }

        // ������ɂ��Y�����܂���ł����B
        throw new IllegalArgumentException("�^����ꂽ�l(" + argCheck + ")�͕�����O���[�v[BlancoDbDriverName]�ł͒�`����Ȃ��l�ł��B");
    }
}
