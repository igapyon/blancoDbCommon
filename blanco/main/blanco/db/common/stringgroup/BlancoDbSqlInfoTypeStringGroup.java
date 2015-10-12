/*
 * ���̃\�[�X�R�[�h�� blanco Framework�ɂ�莩����������܂����B
 */
package blanco.db.common.stringgroup;

/**
 * SQL���̌^�B
 */
public class BlancoDbSqlInfoTypeStringGroup {
    /**
     * No.1 ����:�����^�B
     */
    public static final int ITERATOR = 1;

    /**
     * No.2 ����:�X�V�^�B
     */
    public static final int INVOKER = 2;

    /**
     * No.3 ����:�ďo�^�B
     */
    public static final int CALLER = 3;

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
        // ����:�����^�B
        if ("iterator".equals(argCheck)) {
            return true;
        }
        // No.2
        // ����:�X�V�^�B
        if ("invoker".equals(argCheck)) {
            return true;
        }
        // No.3
        // ����:�ďo�^�B
        if ("caller".equals(argCheck)) {
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
        // ����:�����^�B
        if ("iterator".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.2
        // ����:�X�V�^�B
        if ("invoker".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.3
        // ����:�ďo�^�B
        if ("caller".equalsIgnoreCase(argCheck)) {
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
        // ����:�����^�B
        if ("iterator".equals(argCheck)) {
            return ITERATOR;
        }
        // No.2
        // ����:�X�V�^�B
        if ("invoker".equals(argCheck)) {
            return INVOKER;
        }
        // No.3
        // ����:�ďo�^�B
        if ("caller".equals(argCheck)) {
            return CALLER;
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
        // ����:�����^�B
        if (argCheck == ITERATOR) {
            return "iterator";
        }
        // No.2
        // ����:�X�V�^�B
        if (argCheck == INVOKER) {
            return "invoker";
        }
        // No.3
        // ����:�ďo�^�B
        if (argCheck == CALLER) {
            return "caller";
        }
        // ����`�B
        if (argCheck == NOT_DEFINED) {
            return "";
        }

        // ������ɂ��Y�����܂���ł����B
        throw new IllegalArgumentException("�^����ꂽ�l(" + argCheck + ")�͕�����O���[�v[BlancoDbSqlInfoType]�ł͒�`����Ȃ��l�ł��B");
    }
}
