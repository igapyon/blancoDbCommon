/*
 * ���̃\�[�X�R�[�h�� blanco Framework�ɂ�莩����������܂����B
 */
package blanco.db.common.resourcebundle;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * blancoDbCommon�̃��\�[�X�o���h���B
 *
 * ���\�[�X�o���h����`: [BlancoDbCommon]�B<BR>
 * ���̃N���X�̓��\�[�X�o���h����`�����玩���������ꂽ���\�[�X�o���h���N���X�ł��B<BR>
 * ���m�̃��P�[��<BR>
 * <UL>
 * <LI>ja
 * </UL>
 */
public class BlancoDbCommonResourceBundle {
    /**
     * ���\�[�X�o���h���I�u�W�F�N�g�B
     *
     * �����I�Ɏ��ۂɓ��͂��s�����\�[�X�o���h�����L�����܂��B
     */
    private ResourceBundle fResourceBundle;

    /**
     * BlancoDbCommonResourceBundle�N���X�̃R���X�g���N�^�B
     *
     * ��ꖼ[BlancoDbCommon]�A�f�t�H���g�̃��P�[���A�Ăяo�����̃N���X���[�_���g�p���āA���\�[�X�o���h�����擾���܂��B
     */
    public BlancoDbCommonResourceBundle() {
        try {
            fResourceBundle = ResourceBundle.getBundle("blanco/db/common/resourcebundle/BlancoDbCommon");
        } catch (MissingResourceException ex) {
        }
    }

    /**
     * BlancoDbCommonResourceBundle�N���X�̃R���X�g���N�^�B
     *
     * ��ꖼ[BlancoDbCommon]�A�w�肳�ꂽ���P�[���A�Ăяo�����̃N���X���[�_���g�p���āA���\�[�X�o���h�����擾���܂��B
     *
     * @param locale ���P�[���̎w��
     */
    public BlancoDbCommonResourceBundle(final Locale locale) {
        try {
            fResourceBundle = ResourceBundle.getBundle("blanco/db/common/resourcebundle/BlancoDbCommon", locale);
        } catch (MissingResourceException ex) {
        }
    }

    /**
     * BlancoDbCommonResourceBundle�N���X�̃R���X�g���N�^�B
     *
     * ��ꖼ[BlancoDbCommon]�A�w�肳�ꂽ���P�[���A�w�肳�ꂽ�N���X���[�_���g�p���āA���\�[�X�o���h�����擾���܂��B
     *
     * @param locale ���P�[���̎w��
     * @param loader �N���X���[�_�̎w��
     */
    public BlancoDbCommonResourceBundle(final Locale locale, final ClassLoader loader) {
        try {
            fResourceBundle = ResourceBundle.getBundle("blanco/db/common/resourcebundle/BlancoDbCommon", locale, loader);
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
     * bundle[BlancoDbCommon], key[SELECT.SCROLL]
     *
     * [forward_only] (ja)<br>
     *
     * @return key[SELECT.SCROLL]�ɑΉ�����l�B�O������ǂݍ��݂��ł��Ȃ��ꍇ�ɂ́A��`���̒l��߂��܂��B�K��null�ȊO�̒l���߂�܂��B
     */
    public String getSelectScroll() {
        // �����l�Ƃ��Ē�`���̒l�𗘗p���܂��B
        String strFormat = "forward_only";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("SELECT.SCROLL");
            }
        } catch (MissingResourceException ex) {
        }
        // �u��������͂ЂƂ�����܂���B
        return strFormat;
    }

    /**
     * bundle[BlancoDbCommon], key[SELECT_ALL.SCROLL]
     *
     * [insensitive] (ja)<br>
     *
     * @return key[SELECT_ALL.SCROLL]�ɑΉ�����l�B�O������ǂݍ��݂��ł��Ȃ��ꍇ�ɂ́A��`���̒l��߂��܂��B�K��null�ȊO�̒l���߂�܂��B
     */
    public String getSelectAllScroll() {
        // �����l�Ƃ��Ē�`���̒l�𗘗p���܂��B
        String strFormat = "insensitive";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("SELECT_ALL.SCROLL");
            }
        } catch (MissingResourceException ex) {
        }
        // �u��������͂ЂƂ�����܂���B
        return strFormat;
    }

    /**
     * bundle[BlancoDbCommon], key[SELECT_UPDATABLE.SCROLL]
     *
     * [insensitive] (ja)<br>
     *
     * @return key[SELECT_UPDATABLE.SCROLL]�ɑΉ�����l�B�O������ǂݍ��݂��ł��Ȃ��ꍇ�ɂ́A��`���̒l��߂��܂��B�K��null�ȊO�̒l���߂�܂��B
     */
    public String getSelectUpdatableScroll() {
        // �����l�Ƃ��Ē�`���̒l�𗘗p���܂��B
        String strFormat = "insensitive";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("SELECT_UPDATABLE.SCROLL");
            }
        } catch (MissingResourceException ex) {
        }
        // �u��������͂ЂƂ�����܂���B
        return strFormat;
    }

    /**
     * bundle[BlancoDbCommon], key[TYPE_MAPPING.FAIL_ON_ERROR]
     *
     * [true] (ja)<br>
     *
     * @return key[TYPE_MAPPING.FAIL_ON_ERROR]�ɑΉ�����l�B�O������ǂݍ��݂��ł��Ȃ��ꍇ�ɂ́A��`���̒l��߂��܂��B�K��null�ȊO�̒l���߂�܂��B
     */
    public String getTypeMappingFailOnError() {
        // �����l�Ƃ��Ē�`���̒l�𗘗p���܂��B
        String strFormat = "true";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("TYPE_MAPPING.FAIL_ON_ERROR");
            }
        } catch (MissingResourceException ex) {
        }
        // �u��������͂ЂƂ�����܂���B
        return strFormat;
    }

    /**
     * bundle[BlancoDbCommon], key[TYPE_MAPPING.ERR001]
     *
     * [�^�}�b�s���O�Ƃ��ăT�|�[�g���Ȃ�JDBC�^({0,number}/{1})��������܂����B�������f���܂��B] (ja)<br>
     *
     * @param arg0 �u��������{0}��u������l�Bjava.math.BigDecimal�^��^���Ă��������B
     * @param arg1 �u��������{1}��u������l�Bjava.lang.String�^��^���Ă��������B
     * @return key[TYPE_MAPPING.ERR001]�ɑΉ�����l�B�O������ǂݍ��݂��ł��Ȃ��ꍇ�ɂ́A��`���̒l��߂��܂��B�K��null�ȊO�̒l���߂�܂��B
     */
    public String getTypeMappingErr001(final BigDecimal arg0, final String arg1) {
        // �����l�Ƃ��Ē�`���̒l�𗘗p���܂��B
        String strFormat = "�^�}�b�s���O�Ƃ��ăT�|�[�g���Ȃ�JDBC�^({0,number}/{1})��������܂����B�������f���܂��B";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("TYPE_MAPPING.ERR001");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // �^����ꂽ���������ɒu���������u�������܂��B
        messageFormat.format(new Object[] {arg0, arg1}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoDbCommon], key[SIMPLE.COL.BINARY_ASCII.SELECT.SINGLEROW]
     *
     * [false] (ja)<br>
     *
     * @return key[SIMPLE.COL.BINARY_ASCII.SELECT.SINGLEROW]�ɑΉ�����l�B�O������ǂݍ��݂��ł��Ȃ��ꍇ�ɂ́A��`���̒l��߂��܂��B�K��null�ȊO�̒l���߂�܂��B
     */
    public String getSimpleColBinaryAsciiSelectSinglerow() {
        // �����l�Ƃ��Ē�`���̒l�𗘗p���܂��B
        String strFormat = "false";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("SIMPLE.COL.BINARY_ASCII.SELECT.SINGLEROW");
            }
        } catch (MissingResourceException ex) {
        }
        // �u��������͂ЂƂ�����܂���B
        return strFormat;
    }

    /**
     * bundle[BlancoDbCommon], key[XML2JAVACLASS.ERR001]
     *
     * [SQL��`ID[{0}]�́uSQL���v���擾�ł��܂���ł����BSQL�����K�؂ɋL�ڂ���Ă��邱�Ƃ��m�F���Ă��������B] (ja)<br>
     *
     * @param arg0 �u��������{0}��u������l�Bjava.lang.String�^��^���Ă��������B
     * @return key[XML2JAVACLASS.ERR001]�ɑΉ�����l�B�O������ǂݍ��݂��ł��Ȃ��ꍇ�ɂ́A��`���̒l��߂��܂��B�K��null�ȊO�̒l���߂�܂��B
     */
    public String getXml2javaclassErr001(final String arg0) {
        // �����l�Ƃ��Ē�`���̒l�𗘗p���܂��B
        String strFormat = "SQL��`ID[{0}]�́uSQL���v���擾�ł��܂���ł����BSQL�����K�؂ɋL�ڂ���Ă��邱�Ƃ��m�F���Ă��������B";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.ERR001");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // �^����ꂽ���������ɒu���������u�������܂��B
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoDbCommon], key[XML2JAVACLASS.ERR002]
     *
     * [SQL��`ID[{0}]�ɂ�����SQL��O���������܂����BSQL��`ID[{0}]��SQL����SQL���̓p�����[�^�Ɍ�肪�܂܂�Ă��Ȃ������ׂĂ��������BSQLState[{1}] �G���[�R�[�h[{2,number}] ���b�Z�[�W[{3}]] (ja)<br>
     *
     * @param arg0 �u��������{0}��u������l�Bjava.lang.String�^��^���Ă��������B
     * @param arg1 �u��������{1}��u������l�Bjava.lang.String�^��^���Ă��������B
     * @param arg2 �u��������{2}��u������l�Bjava.math.BigDecimal�^��^���Ă��������B
     * @param arg3 �u��������{3}��u������l�Bjava.lang.String�^��^���Ă��������B
     * @return key[XML2JAVACLASS.ERR002]�ɑΉ�����l�B�O������ǂݍ��݂��ł��Ȃ��ꍇ�ɂ́A��`���̒l��߂��܂��B�K��null�ȊO�̒l���߂�܂��B
     */
    public String getXml2javaclassErr002(final String arg0, final String arg1, final BigDecimal arg2, final String arg3) {
        // �����l�Ƃ��Ē�`���̒l�𗘗p���܂��B
        String strFormat = "SQL��`ID[{0}]�ɂ�����SQL��O���������܂����BSQL��`ID[{0}]��SQL����SQL���̓p�����[�^�Ɍ�肪�܂܂�Ă��Ȃ������ׂĂ��������BSQLState[{1}] �G���[�R�[�h[{2,number}] ���b�Z�[�W[{3}]";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.ERR002");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // �^����ꂽ���������ɒu���������u�������܂��B
        messageFormat.format(new Object[] {arg0, arg1, arg2, arg3}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoDbCommon], key[XML2JAVACLASS.ERR004]
     *
     * [SQL��`ID[{0}]��SQL���̓p�����[�^{1}�ɂ����āA�p�����[�^ID���w�肳��Ȃ��^[{2}]�����o����܂����B] (ja)<br>
     *
     * @param arg0 �u��������{0}��u������l�Bjava.lang.String�^��^���Ă��������B
     * @param arg1 �u��������{1}��u������l�Bjava.lang.String�^��^���Ă��������B
     * @param arg2 �u��������{2}��u������l�Bjava.lang.String�^��^���Ă��������B
     * @return key[XML2JAVACLASS.ERR004]�ɑΉ�����l�B�O������ǂݍ��݂��ł��Ȃ��ꍇ�ɂ́A��`���̒l��߂��܂��B�K��null�ȊO�̒l���߂�܂��B
     */
    public String getXml2javaclassErr004(final String arg0, final String arg1, final String arg2) {
        // �����l�Ƃ��Ē�`���̒l�𗘗p���܂��B
        String strFormat = "SQL��`ID[{0}]��SQL���̓p�����[�^{1}�ɂ����āA�p�����[�^ID���w�肳��Ȃ��^[{2}]�����o����܂����B";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.ERR004");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // �^����ꂽ���������ɒu���������u�������܂��B
        messageFormat.format(new Object[] {arg0, arg1, arg2}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoDbCommon], key[XML2JAVACLASS.ERR005]
     *
     * [SQL��`ID[{0}]��SQL���̓p�����[�^{1}�ɂ����āA�p�����[�^ID[{2}]�̌^���w�肳��Ă��܂���B] (ja)<br>
     *
     * @param arg0 �u��������{0}��u������l�Bjava.lang.String�^��^���Ă��������B
     * @param arg1 �u��������{1}��u������l�Bjava.lang.String�^��^���Ă��������B
     * @param arg2 �u��������{2}��u������l�Bjava.lang.String�^��^���Ă��������B
     * @return key[XML2JAVACLASS.ERR005]�ɑΉ�����l�B�O������ǂݍ��݂��ł��Ȃ��ꍇ�ɂ́A��`���̒l��߂��܂��B�K��null�ȊO�̒l���߂�܂��B
     */
    public String getXml2javaclassErr005(final String arg0, final String arg1, final String arg2) {
        // �����l�Ƃ��Ē�`���̒l�𗘗p���܂��B
        String strFormat = "SQL��`ID[{0}]��SQL���̓p�����[�^{1}�ɂ����āA�p�����[�^ID[{2}]�̌^���w�肳��Ă��܂���B";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.ERR005");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // �^����ꂽ���������ɒu���������u�������܂��B
        messageFormat.format(new Object[] {arg0, arg1, arg2}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoDbCommon], key[XML2JAVACLASS.ERR006]
     *
     * [SQL��`ID[{0}]��SQL�o�̓p�����[�^{1}�ɂ����āA�p�����[�^ID���w�肳��Ȃ��^[{2}]�����o����܂����B] (ja)<br>
     *
     * @param arg0 �u��������{0}��u������l�Bjava.lang.String�^��^���Ă��������B
     * @param arg1 �u��������{1}��u������l�Bjava.lang.String�^��^���Ă��������B
     * @param arg2 �u��������{2}��u������l�Bjava.lang.String�^��^���Ă��������B
     * @return key[XML2JAVACLASS.ERR006]�ɑΉ�����l�B�O������ǂݍ��݂��ł��Ȃ��ꍇ�ɂ́A��`���̒l��߂��܂��B�K��null�ȊO�̒l���߂�܂��B
     */
    public String getXml2javaclassErr006(final String arg0, final String arg1, final String arg2) {
        // �����l�Ƃ��Ē�`���̒l�𗘗p���܂��B
        String strFormat = "SQL��`ID[{0}]��SQL�o�̓p�����[�^{1}�ɂ����āA�p�����[�^ID���w�肳��Ȃ��^[{2}]�����o����܂����B";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.ERR006");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // �^����ꂽ���������ɒu���������u�������܂��B
        messageFormat.format(new Object[] {arg0, arg1, arg2}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoDbCommon], key[XML2JAVACLASS.ERR007]
     *
     * [SQL��`ID[{0}]��SQL�o�̓p�����[�^{1}�ɂ����āA�p�����[�^ID[{2}]�̌^���w�肳��Ă��܂���B] (ja)<br>
     *
     * @param arg0 �u��������{0}��u������l�Bjava.lang.String�^��^���Ă��������B
     * @param arg1 �u��������{1}��u������l�Bjava.lang.String�^��^���Ă��������B
     * @param arg2 �u��������{2}��u������l�Bjava.lang.String�^��^���Ă��������B
     * @return key[XML2JAVACLASS.ERR007]�ɑΉ�����l�B�O������ǂݍ��݂��ł��Ȃ��ꍇ�ɂ́A��`���̒l��߂��܂��B�K��null�ȊO�̒l���߂�܂��B
     */
    public String getXml2javaclassErr007(final String arg0, final String arg1, final String arg2) {
        // �����l�Ƃ��Ē�`���̒l�𗘗p���܂��B
        String strFormat = "SQL��`ID[{0}]��SQL�o�̓p�����[�^{1}�ɂ����āA�p�����[�^ID[{2}]�̌^���w�肳��Ă��܂���B";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.ERR007");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // �^����ꂽ���������ɒu���������u�������܂��B
        messageFormat.format(new Object[] {arg0, arg1, arg2}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoDbCommon], key[XML2JAVACLASS.ERR008]
     *
     * [SQL��`ID[{0}]�́u�ďo�^�v�łȂ��̂�SQL�o�̓p�����[�^{1}�A�p�����[�^ID[{2}]���w�肳��Ă��܂��B] (ja)<br>
     *
     * @param arg0 �u��������{0}��u������l�Bjava.lang.String�^��^���Ă��������B
     * @param arg1 �u��������{1}��u������l�Bjava.lang.String�^��^���Ă��������B
     * @param arg2 �u��������{2}��u������l�Bjava.lang.String�^��^���Ă��������B
     * @return key[XML2JAVACLASS.ERR008]�ɑΉ�����l�B�O������ǂݍ��݂��ł��Ȃ��ꍇ�ɂ́A��`���̒l��߂��܂��B�K��null�ȊO�̒l���߂�܂��B
     */
    public String getXml2javaclassErr008(final String arg0, final String arg1, final String arg2) {
        // �����l�Ƃ��Ē�`���̒l�𗘗p���܂��B
        String strFormat = "SQL��`ID[{0}]�́u�ďo�^�v�łȂ��̂�SQL�o�̓p�����[�^{1}�A�p�����[�^ID[{2}]���w�肳��Ă��܂��B";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.ERR008");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // �^����ꂽ���������ɒu���������u�������܂��B
        messageFormat.format(new Object[] {arg0, arg1, arg2}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoDbCommon], key[XML2JAVACLASS.ERR009]
     *
     * [SQL��`ID[{0}]�̃^�C���A�E�g[{1}]�͐��l�Ƃ��ĔF���ł��܂���ł����B] (ja)<br>
     *
     * @param arg0 �u��������{0}��u������l�Bjava.lang.String�^��^���Ă��������B
     * @param arg1 �u��������{1}��u������l�Bjava.lang.String�^��^���Ă��������B
     * @return key[XML2JAVACLASS.ERR009]�ɑΉ�����l�B�O������ǂݍ��݂��ł��Ȃ��ꍇ�ɂ́A��`���̒l��߂��܂��B�K��null�ȊO�̒l���߂�܂��B
     */
    public String getXml2javaclassErr009(final String arg0, final String arg1) {
        // �����l�Ƃ��Ē�`���̒l�𗘗p���܂��B
        String strFormat = "SQL��`ID[{0}]�̃^�C���A�E�g[{1}]�͐��l�Ƃ��ĔF���ł��܂���ł����B";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.ERR009");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // �^����ꂽ���������ɒu���������u�������܂��B
        messageFormat.format(new Object[] {arg0, arg1}, strbuf, null);
        return strbuf.toString();
    }
}
