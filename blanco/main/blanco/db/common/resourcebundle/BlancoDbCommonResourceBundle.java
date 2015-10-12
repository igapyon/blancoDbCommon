/*
 * このソースコードは blanco Frameworkにより自動生成されました。
 */
package blanco.db.common.resourcebundle;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * blancoDbCommonのリソースバンドル。
 *
 * リソースバンドル定義: [BlancoDbCommon]。<BR>
 * このクラスはリソースバンドル定義書から自動生成されたリソースバンドルクラスです。<BR>
 * 既知のロケール<BR>
 * <UL>
 * <LI>ja
 * </UL>
 */
public class BlancoDbCommonResourceBundle {
    /**
     * リソースバンドルオブジェクト。
     *
     * 内部的に実際に入力を行うリソースバンドルを記憶します。
     */
    private ResourceBundle fResourceBundle;

    /**
     * BlancoDbCommonResourceBundleクラスのコンストラクタ。
     *
     * 基底名[BlancoDbCommon]、デフォルトのロケール、呼び出し側のクラスローダを使用して、リソースバンドルを取得します。
     */
    public BlancoDbCommonResourceBundle() {
        try {
            fResourceBundle = ResourceBundle.getBundle("blanco/db/common/resourcebundle/BlancoDbCommon");
        } catch (MissingResourceException ex) {
        }
    }

    /**
     * BlancoDbCommonResourceBundleクラスのコンストラクタ。
     *
     * 基底名[BlancoDbCommon]、指定されたロケール、呼び出し側のクラスローダを使用して、リソースバンドルを取得します。
     *
     * @param locale ロケールの指定
     */
    public BlancoDbCommonResourceBundle(final Locale locale) {
        try {
            fResourceBundle = ResourceBundle.getBundle("blanco/db/common/resourcebundle/BlancoDbCommon", locale);
        } catch (MissingResourceException ex) {
        }
    }

    /**
     * BlancoDbCommonResourceBundleクラスのコンストラクタ。
     *
     * 基底名[BlancoDbCommon]、指定されたロケール、指定されたクラスローダを使用して、リソースバンドルを取得します。
     *
     * @param locale ロケールの指定
     * @param loader クラスローダの指定
     */
    public BlancoDbCommonResourceBundle(final Locale locale, final ClassLoader loader) {
        try {
            fResourceBundle = ResourceBundle.getBundle("blanco/db/common/resourcebundle/BlancoDbCommon", locale, loader);
        } catch (MissingResourceException ex) {
        }
    }

    /**
     * 内部的に保持しているリソースバンドルオブジェクトを取得します。
     *
     * @return 内部的に保持しているリソースバンドルオブジェクト。
     */
    public ResourceBundle getResourceBundle() {
        return fResourceBundle;
    }

    /**
     * bundle[BlancoDbCommon], key[SELECT.SCROLL]
     *
     * [forward_only] (ja)<br>
     *
     * @return key[SELECT.SCROLL]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getSelectScroll() {
        // 初期値として定義書の値を利用します。
        String strFormat = "forward_only";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("SELECT.SCROLL");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoDbCommon], key[SELECT_ALL.SCROLL]
     *
     * [insensitive] (ja)<br>
     *
     * @return key[SELECT_ALL.SCROLL]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getSelectAllScroll() {
        // 初期値として定義書の値を利用します。
        String strFormat = "insensitive";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("SELECT_ALL.SCROLL");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoDbCommon], key[SELECT_UPDATABLE.SCROLL]
     *
     * [insensitive] (ja)<br>
     *
     * @return key[SELECT_UPDATABLE.SCROLL]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getSelectUpdatableScroll() {
        // 初期値として定義書の値を利用します。
        String strFormat = "insensitive";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("SELECT_UPDATABLE.SCROLL");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoDbCommon], key[TYPE_MAPPING.FAIL_ON_ERROR]
     *
     * [true] (ja)<br>
     *
     * @return key[TYPE_MAPPING.FAIL_ON_ERROR]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getTypeMappingFailOnError() {
        // 初期値として定義書の値を利用します。
        String strFormat = "true";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("TYPE_MAPPING.FAIL_ON_ERROR");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoDbCommon], key[TYPE_MAPPING.ERR001]
     *
     * [型マッピングとしてサポートしないJDBC型({0,number}/{1})が見つかりました。処理中断します。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.math.BigDecimal型を与えてください。
     * @param arg1 置換文字列{1}を置換する値。java.lang.String型を与えてください。
     * @return key[TYPE_MAPPING.ERR001]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getTypeMappingErr001(final BigDecimal arg0, final String arg1) {
        // 初期値として定義書の値を利用します。
        String strFormat = "型マッピングとしてサポートしないJDBC型({0,number}/{1})が見つかりました。処理中断します。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("TYPE_MAPPING.ERR001");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0, arg1}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoDbCommon], key[SIMPLE.COL.BINARY_ASCII.SELECT.SINGLEROW]
     *
     * [false] (ja)<br>
     *
     * @return key[SIMPLE.COL.BINARY_ASCII.SELECT.SINGLEROW]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getSimpleColBinaryAsciiSelectSinglerow() {
        // 初期値として定義書の値を利用します。
        String strFormat = "false";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("SIMPLE.COL.BINARY_ASCII.SELECT.SINGLEROW");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoDbCommon], key[XML2JAVACLASS.ERR001]
     *
     * [SQL定義ID[{0}]の「SQL文」が取得できませんでした。SQL文が適切に記載されていることを確認してください。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2JAVACLASS.ERR001]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2javaclassErr001(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "SQL定義ID[{0}]の「SQL文」が取得できませんでした。SQL文が適切に記載されていることを確認してください。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.ERR001");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoDbCommon], key[XML2JAVACLASS.ERR002]
     *
     * [SQL定義ID[{0}]においてSQL例外が発生しました。SQL定義ID[{0}]のSQL文やSQL入力パラメータに誤りが含まれていないか調べてください。SQLState[{1}] エラーコード[{2,number}] メッセージ[{3}]] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @param arg1 置換文字列{1}を置換する値。java.lang.String型を与えてください。
     * @param arg2 置換文字列{2}を置換する値。java.math.BigDecimal型を与えてください。
     * @param arg3 置換文字列{3}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2JAVACLASS.ERR002]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2javaclassErr002(final String arg0, final String arg1, final BigDecimal arg2, final String arg3) {
        // 初期値として定義書の値を利用します。
        String strFormat = "SQL定義ID[{0}]においてSQL例外が発生しました。SQL定義ID[{0}]のSQL文やSQL入力パラメータに誤りが含まれていないか調べてください。SQLState[{1}] エラーコード[{2,number}] メッセージ[{3}]";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.ERR002");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0, arg1, arg2, arg3}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoDbCommon], key[XML2JAVACLASS.ERR004]
     *
     * [SQL定義ID[{0}]のSQL入力パラメータ{1}において、パラメータIDが指定されない型[{2}]が検出されました。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @param arg1 置換文字列{1}を置換する値。java.lang.String型を与えてください。
     * @param arg2 置換文字列{2}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2JAVACLASS.ERR004]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2javaclassErr004(final String arg0, final String arg1, final String arg2) {
        // 初期値として定義書の値を利用します。
        String strFormat = "SQL定義ID[{0}]のSQL入力パラメータ{1}において、パラメータIDが指定されない型[{2}]が検出されました。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.ERR004");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0, arg1, arg2}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoDbCommon], key[XML2JAVACLASS.ERR005]
     *
     * [SQL定義ID[{0}]のSQL入力パラメータ{1}において、パラメータID[{2}]の型が指定されていません。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @param arg1 置換文字列{1}を置換する値。java.lang.String型を与えてください。
     * @param arg2 置換文字列{2}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2JAVACLASS.ERR005]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2javaclassErr005(final String arg0, final String arg1, final String arg2) {
        // 初期値として定義書の値を利用します。
        String strFormat = "SQL定義ID[{0}]のSQL入力パラメータ{1}において、パラメータID[{2}]の型が指定されていません。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.ERR005");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0, arg1, arg2}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoDbCommon], key[XML2JAVACLASS.ERR006]
     *
     * [SQL定義ID[{0}]のSQL出力パラメータ{1}において、パラメータIDが指定されない型[{2}]が検出されました。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @param arg1 置換文字列{1}を置換する値。java.lang.String型を与えてください。
     * @param arg2 置換文字列{2}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2JAVACLASS.ERR006]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2javaclassErr006(final String arg0, final String arg1, final String arg2) {
        // 初期値として定義書の値を利用します。
        String strFormat = "SQL定義ID[{0}]のSQL出力パラメータ{1}において、パラメータIDが指定されない型[{2}]が検出されました。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.ERR006");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0, arg1, arg2}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoDbCommon], key[XML2JAVACLASS.ERR007]
     *
     * [SQL定義ID[{0}]のSQL出力パラメータ{1}において、パラメータID[{2}]の型が指定されていません。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @param arg1 置換文字列{1}を置換する値。java.lang.String型を与えてください。
     * @param arg2 置換文字列{2}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2JAVACLASS.ERR007]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2javaclassErr007(final String arg0, final String arg1, final String arg2) {
        // 初期値として定義書の値を利用します。
        String strFormat = "SQL定義ID[{0}]のSQL出力パラメータ{1}において、パラメータID[{2}]の型が指定されていません。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.ERR007");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0, arg1, arg2}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoDbCommon], key[XML2JAVACLASS.ERR008]
     *
     * [SQL定義ID[{0}]は「呼出型」でないのにSQL出力パラメータ{1}、パラメータID[{2}]が指定されています。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @param arg1 置換文字列{1}を置換する値。java.lang.String型を与えてください。
     * @param arg2 置換文字列{2}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2JAVACLASS.ERR008]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2javaclassErr008(final String arg0, final String arg1, final String arg2) {
        // 初期値として定義書の値を利用します。
        String strFormat = "SQL定義ID[{0}]は「呼出型」でないのにSQL出力パラメータ{1}、パラメータID[{2}]が指定されています。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.ERR008");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0, arg1, arg2}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoDbCommon], key[XML2JAVACLASS.ERR009]
     *
     * [SQL定義ID[{0}]のタイムアウト[{1}]は数値として認識できませんでした。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @param arg1 置換文字列{1}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2JAVACLASS.ERR009]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2javaclassErr009(final String arg0, final String arg1) {
        // 初期値として定義書の値を利用します。
        String strFormat = "SQL定義ID[{0}]のタイムアウト[{1}]は数値として認識できませんでした。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.ERR009");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0, arg1}, strbuf, null);
        return strbuf.toString();
    }
}
