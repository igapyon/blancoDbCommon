/*
 * ���̃\�[�X�R�[�h�� blanco Framework�ɂ�莩����������܂����B
 */
package blanco.db.common.stringgroup;

/**
 * ���������̍ۂ�SQL�����s�������Ȃ����ǂ������w�肷�邽�߂̃t���O�B
 */
public class BlancoDbExecuteSqlStringGroup {
    /**
     * No.1 ����:SQL���͎��s���܂���B
     */
    public static final int NONE = 1;

    /**
     * No.2 ����:�����^(iterator)�̏ꍇ�ɁASQL�������ۂɎ��s���܂��B
     */
    public static final int ITERATOR = 2;

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
        // ����:SQL���͎��s���܂���B
        if ("none".equals(argCheck)) {
            return true;
        }
        // No.2
        // ����:�����^(iterator)�̏ꍇ�ɁASQL�������ۂɎ��s���܂��B
        if ("iterator".equals(argCheck)) {
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
        // ����:SQL���͎��s���܂���B
        if ("none".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.2
        // ����:�����^(iterator)�̏ꍇ�ɁASQL�������ۂɎ��s���܂��B
        if ("iterator".equalsIgnoreCase(argCheck)) {
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
        // ����:SQL���͎��s���܂���B
        if ("none".equals(argCheck)) {
            return NONE;
        }
        // No.2
        // ����:�����^(iterator)�̏ꍇ�ɁASQL�������ۂɎ��s���܂��B
        if ("iterator".equals(argCheck)) {
            return ITERATOR;
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
        // ����:SQL���͎��s���܂���B
        if (argCheck == NONE) {
            return "none";
        }
        // No.2
        // ����:�����^(iterator)�̏ꍇ�ɁASQL�������ۂɎ��s���܂��B
        if (argCheck == ITERATOR) {
            return "iterator";
        }
        // ����`�B
        if (argCheck == NOT_DEFINED) {
            return "";
        }

        // ������ɂ��Y�����܂���ł����B
        throw new IllegalArgumentException("�^����ꂽ�l(" + argCheck + ")�͕�����O���[�v[BlancoDbExecuteSql]�ł͒�`����Ȃ��l�ł��B");
    }
}
