/*
 * ���̃\�[�X�R�[�h�� blanco Framework�ɂ�莩����������܂����B
 */
package blanco.db.common.stringgroup;

/**
 * BlancoDbSetting�ŗ��p����郍�M���O���[�h�̈ꗗ�B
 */
public class BlancoDbLoggingModeStringGroup {
    /**
     * No.1 ����:�m�[�}����debug���O��񍐁B
     */
    public static final int DEBUG = 1;

    /**
     * No.2 ����:�p�t�H�[�}���X�񍐕t���񍐁B�������g�p�ʂȂǂ��񍐂���܂��B
     */
    public static final int PERFORMANCE = 2;

    /**
     * No.3 ����:SQLID�݂̂�񍐁B
     */
    public static final int SQLID = 3;

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
        // ����:�m�[�}����debug���O��񍐁B
        if ("debug".equals(argCheck)) {
            return true;
        }
        // No.2
        // ����:�p�t�H�[�}���X�񍐕t���񍐁B�������g�p�ʂȂǂ��񍐂���܂��B
        if ("performance".equals(argCheck)) {
            return true;
        }
        // No.3
        // ����:SQLID�݂̂�񍐁B
        if ("sqlid".equals(argCheck)) {
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
        // ����:�m�[�}����debug���O��񍐁B
        if ("debug".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.2
        // ����:�p�t�H�[�}���X�񍐕t���񍐁B�������g�p�ʂȂǂ��񍐂���܂��B
        if ("performance".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.3
        // ����:SQLID�݂̂�񍐁B
        if ("sqlid".equalsIgnoreCase(argCheck)) {
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
        // ����:�m�[�}����debug���O��񍐁B
        if ("debug".equals(argCheck)) {
            return DEBUG;
        }
        // No.2
        // ����:�p�t�H�[�}���X�񍐕t���񍐁B�������g�p�ʂȂǂ��񍐂���܂��B
        if ("performance".equals(argCheck)) {
            return PERFORMANCE;
        }
        // No.3
        // ����:SQLID�݂̂�񍐁B
        if ("sqlid".equals(argCheck)) {
            return SQLID;
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
        // ����:�m�[�}����debug���O��񍐁B
        if (argCheck == DEBUG) {
            return "debug";
        }
        // No.2
        // ����:�p�t�H�[�}���X�񍐕t���񍐁B�������g�p�ʂȂǂ��񍐂���܂��B
        if (argCheck == PERFORMANCE) {
            return "performance";
        }
        // No.3
        // ����:SQLID�݂̂�񍐁B
        if (argCheck == SQLID) {
            return "sqlid";
        }
        // ����`�B
        if (argCheck == NOT_DEFINED) {
            return "";
        }

        // ������ɂ��Y�����܂���ł����B
        throw new IllegalArgumentException("�^����ꂽ�l(" + argCheck + ")�͕�����O���[�v[BlancoDbLoggingMode]�ł͒�`����Ȃ��l�ł��B");
    }
}
