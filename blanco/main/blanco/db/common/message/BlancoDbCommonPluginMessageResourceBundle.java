/*
 * ���̃\�[�X�R�[�h�� blanco Framework�ɂ�莩����������܂����B
 */
package blanco.db.common.message;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * ���b�Z�[�W��`[BlancoDbCommonPlugin]�������I�ɗ��p���郊�\�[�X�o���h���N���X�B
 *
 * ���\�[�X�o���h����`: [BlancoDbCommonPluginMessage]�B<BR>
 * ���̃N���X�̓��\�[�X�o���h����`�����玩���������ꂽ���\�[�X�o���h���N���X�ł��B<BR>
 */
class BlancoDbCommonPluginMessageResourceBundle {
    /**
     * ���\�[�X�o���h���I�u�W�F�N�g�B
     *
     * �����I�Ɏ��ۂɓ��͂��s�����\�[�X�o���h�����L�����܂��B
     */
    private ResourceBundle fResourceBundle;

    /**
     * BlancoDbCommonPluginMessageResourceBundle�N���X�̃R���X�g���N�^�B
     *
     * ��ꖼ[BlancoDbCommonPluginMessage]�A�f�t�H���g�̃��P�[���A�Ăяo�����̃N���X���[�_���g�p���āA���\�[�X�o���h�����擾���܂��B
     */
    public BlancoDbCommonPluginMessageResourceBundle() {
        try {
            fResourceBundle = ResourceBundle.getBundle("blanco/db/common/message/BlancoDbCommonPluginMessage");
        } catch (MissingResourceException ex) {
        }
    }

    /**
     * BlancoDbCommonPluginMessageResourceBundle�N���X�̃R���X�g���N�^�B
     *
     * ��ꖼ[BlancoDbCommonPluginMessage]�A�w�肳�ꂽ���P�[���A�Ăяo�����̃N���X���[�_���g�p���āA���\�[�X�o���h�����擾���܂��B
     *
     * @param locale ���P�[���̎w��
     */
    public BlancoDbCommonPluginMessageResourceBundle(final Locale locale) {
        try {
            fResourceBundle = ResourceBundle.getBundle("blanco/db/common/message/BlancoDbCommonPluginMessage", locale);
        } catch (MissingResourceException ex) {
        }
    }

    /**
     * BlancoDbCommonPluginMessageResourceBundle�N���X�̃R���X�g���N�^�B
     *
     * ��ꖼ[BlancoDbCommonPluginMessage]�A�w�肳�ꂽ���P�[���A�w�肳�ꂽ�N���X���[�_���g�p���āA���\�[�X�o���h�����擾���܂��B
     *
     * @param locale ���P�[���̎w��
     * @param loader �N���X���[�_�̎w��
     */
    public BlancoDbCommonPluginMessageResourceBundle(final Locale locale, final ClassLoader loader) {
        try {
            fResourceBundle = ResourceBundle.getBundle("blanco/db/common/message/BlancoDbCommonPluginMessage", locale, loader);
        } catch (MissingResourceException ex) {
        }
    }

    /**
     * �����I�ɕێ����Ă��郊�\�[�X�o���h���I�u�W�F�N�g���擾���܂��B
     *
     * @return �����I�ɕێ����Ă��郊�\�[�X�o���h���I�u�W�F�N�g�B
     */
    public ResourceBundle getResourceBundle() {
        return fResourceBundle;
    }

    /**
     * bundle[BlancoDbCommonPluginMessage], key[MBDBCMI01]
     *
     * [blancoDb �v���O�C���͈ȉ��̂悤�ɗ��p���Ă��������B\n  1.Java �v���W�F�N�g���ŗ��p���Ă�������\n    blancoDb �̃\�[�X�R�[�h���������́AJava �v���W�F�N�g���Ŏ��s���Ă��������B\n  2.�u���C�u�����[�v�� JDBC �h���C�o�� jar �t�@�C����ǉ����Ă�������\n    �v���W�F�N�g�́u�v���p�e�B�[�v-&gt;�uJava �̃r���h�E�p�X�v�́u���C�u�����[�v��\n    JDBC �h���C�o�� jar �t�@�C����ǉ����Ă��������B\n  �������́A�\�[�X�R�[�h�����������Ȃ��ꍇ�̐����ł��B\n    �������ꂽ�\�[�X�R�[�h�̗��p���ɂ́A�֌W����܂���B] (ja)<br>
     *
     * @return key[MBDBCMI01]�ɑΉ�����l�B�O������ǂݍ��݂��ł��Ȃ��ꍇ�ɂ́A��`���̒l��߂��܂��B�K��null�ȊO�̒l���߂�܂��B
     */
    public String getMbdbcmi01() {
        // �����l�Ƃ��Ē�`���̒l�𗘗p���܂��B
        String strFormat = "blancoDb �v���O�C���͈ȉ��̂悤�ɗ��p���Ă��������B\n  1.Java �v���W�F�N�g���ŗ��p���Ă�������\n    blancoDb �̃\�[�X�R�[�h���������́AJava �v���W�F�N�g���Ŏ��s���Ă��������B\n  2.�u���C�u�����[�v�� JDBC �h���C�o�� jar �t�@�C����ǉ����Ă�������\n    �v���W�F�N�g�́u�v���p�e�B�[�v->�uJava �̃r���h�E�p�X�v�́u���C�u�����[�v��\n    JDBC �h���C�o�� jar �t�@�C����ǉ����Ă��������B\n  �������́A�\�[�X�R�[�h�����������Ȃ��ꍇ�̐����ł��B\n    �������ꂽ�\�[�X�R�[�h�̗��p���ɂ́A�֌W����܂���B";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("MBDBCMI01");
            }
        } catch (MissingResourceException ex) {
        }
        // �u��������͂ЂƂ�����܂���B
        return strFormat;
    }

    /**
     * bundle[BlancoDbCommonPluginMessage], key[MBDBCMC01]
     *
     * [�ڑ��`�F�b�N���s] (ja)<br>
     *
     * @return key[MBDBCMC01]�ɑΉ�����l�B�O������ǂݍ��݂��ł��Ȃ��ꍇ�ɂ́A��`���̒l��߂��܂��B�K��null�ȊO�̒l���߂�܂��B
     */
    public String getMbdbcmc01() {
        // �����l�Ƃ��Ē�`���̒l�𗘗p���܂��B
        String strFormat = "�ڑ��`�F�b�N���s";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("MBDBCMC01");
            }
        } catch (MissingResourceException ex) {
        }
        // �u��������͂ЂƂ�����܂���B
        return strFormat;
    }

    /**
     * bundle[BlancoDbCommonPluginMessage], key[MBDBCMC02]
     *
     * [�w���JDBC�h���C�o({0})�̓ǂݍ��݂Ɏ��s���܂����B\n\n�Y���� JDBC �h���C�o��ǂݍ��߂�悤�ɐݒ�ύX���K�v�ł���\��������܂��B\n�ȉ��̓_���m�F���Ă��������B\n 1.Java �v���W�F�N�g���ŗ��p���Ă�������\n   blancoDb �̃\�[�X�R�[�h���������́AJava �v���W�F�N�g���Ŏ��s����\n   ���������B\n 2.�u���C�u�����[�v�� JDBC �h���C�o�� jar �t�@�C����ǉ����Ă�������\n   �v���W�F�N�g�́u�v���p�e�B�[�v-&gt;�uJava �̃r���h�E�p�X�v�́u���C�u�����[�v\n   �� JDBC �h���C�o�� jar �t�@�C����ǉ����Ă��������B] (ja)<br>
     *
     * @param arg0 �u��������{0}��u������l�Bjava.lang.String�^��^���Ă��������B
     * @return key[MBDBCMC02]�ɑΉ�����l�B�O������ǂݍ��݂��ł��Ȃ��ꍇ�ɂ́A��`���̒l��߂��܂��B�K��null�ȊO�̒l���߂�܂��B
     */
    public String getMbdbcmc02(final String arg0) {
        // �����l�Ƃ��Ē�`���̒l�𗘗p���܂��B
        String strFormat = "�w���JDBC�h���C�o({0})�̓ǂݍ��݂Ɏ��s���܂����B\n\n�Y���� JDBC �h���C�o��ǂݍ��߂�悤�ɐݒ�ύX���K�v�ł���\��������܂��B\n�ȉ��̓_���m�F���Ă��������B\n 1.Java �v���W�F�N�g���ŗ��p���Ă�������\n   blancoDb �̃\�[�X�R�[�h���������́AJava �v���W�F�N�g���Ŏ��s����\n   ���������B\n 2.�u���C�u�����[�v�� JDBC �h���C�o�� jar �t�@�C����ǉ����Ă�������\n   �v���W�F�N�g�́u�v���p�e�B�[�v->�uJava �̃r���h�E�p�X�v�́u���C�u�����[�v\n   �� JDBC �h���C�o�� jar �t�@�C����ǉ����Ă��������B";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("MBDBCMC02");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // �^����ꂽ���������ɒu���������u�������܂��B
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }
}
