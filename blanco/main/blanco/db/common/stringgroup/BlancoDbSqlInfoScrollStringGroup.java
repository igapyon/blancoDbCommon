/*
 * ���̃\�[�X�R�[�h�� blanco Framework�ɂ�莩����������܂����B
 */
package blanco.db.common.stringgroup;

/**
 * SQL���̃X�N���[�������B
 */
public class BlancoDbSqlInfoScrollStringGroup {
    /**
     * No.1 ����:�O���̂݁B
     */
    public static final int TYPE_FORWARD_ONLY = 1;

    /**
     * No.2 ����:�C���Z���V�e�B�u�B
     */
    public static final int TYPE_SCROLL_INSENSITIVE = 2;

    /**
     * No.3 ����:�Z���V�e�B�u�B
     */
    public static final int TYPE_SCROLL_SENSITIVE = 3;

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
        // ����:�O���̂݁B
        if ("forward_only".equals(argCheck)) {
            return true;
        }
        // No.2
        // ����:�C���Z���V�e�B�u�B
        if ("insensitive".equals(argCheck)) {
            return true;
        }
        // No.3
        // ����:�Z���V�e�B�u�B
        if ("sensitive".equals(argCheck)) {
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
        // ����:�O���̂݁B
        if ("forward_only".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.2
        // ����:�C���Z���V�e�B�u�B
        if ("insensitive".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.3
        // ����:�Z���V�e�B�u�B
        if ("sensitive".equalsIgnoreCase(argCheck)) {
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
        // ����:�O���̂݁B
        if ("forward_only".equals(argCheck)) {
            return TYPE_FORWARD_ONLY;
        }
        // No.2
        // ����:�C���Z���V�e�B�u�B
        if ("insensitive".equals(argCheck)) {
            return TYPE_SCROLL_INSENSITIVE;
        }
        // No.3
        // ����:�Z���V�e�B�u�B
        if ("sensitive".equals(argCheck)) {
            return TYPE_SCROLL_SENSITIVE;
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
        // ����:�O���̂݁B
        if (argCheck == TYPE_FORWARD_ONLY) {
            return "forward_only";
        }
        // No.2
        // ����:�C���Z���V�e�B�u�B
        if (argCheck == TYPE_SCROLL_INSENSITIVE) {
            return "insensitive";
        }
        // No.3
        // ����:�Z���V�e�B�u�B
        if (argCheck == TYPE_SCROLL_SENSITIVE) {
            return "sensitive";
        }
        // ����`�B
        if (argCheck == NOT_DEFINED) {
            return "";
        }

        // ������ɂ��Y�����܂���ł����B
        throw new IllegalArgumentException("�^����ꂽ�l(" + argCheck + ")�͕�����O���[�v[BlancoDbSqlInfoScroll]�ł͒�`����Ȃ��l�ł��B");
    }
}
