/*
 * このソースコードは blanco Frameworkにより自動生成されました。
 */
package blanco.db.common.message;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * メッセージ定義[BlancoDbCommonPlugin]が内部的に利用するリソースバンドルクラス。
 *
 * リソースバンドル定義: [BlancoDbCommonPluginMessage]。<BR>
 * このクラスはリソースバンドル定義書から自動生成されたリソースバンドルクラスです。<BR>
 */
class BlancoDbCommonPluginMessageResourceBundle {
    /**
     * リソースバンドルオブジェクト。
     *
     * 内部的に実際に入力を行うリソースバンドルを記憶します。
     */
    private ResourceBundle fResourceBundle;

    /**
     * BlancoDbCommonPluginMessageResourceBundleクラスのコンストラクタ。
     *
     * 基底名[BlancoDbCommonPluginMessage]、デフォルトのロケール、呼び出し側のクラスローダを使用して、リソースバンドルを取得します。
     */
    public BlancoDbCommonPluginMessageResourceBundle() {
        try {
            fResourceBundle = ResourceBundle.getBundle("blanco/db/common/message/BlancoDbCommonPluginMessage");
        } catch (MissingResourceException ex) {
        }
    }

    /**
     * BlancoDbCommonPluginMessageResourceBundleクラスのコンストラクタ。
     *
     * 基底名[BlancoDbCommonPluginMessage]、指定されたロケール、呼び出し側のクラスローダを使用して、リソースバンドルを取得します。
     *
     * @param locale ロケールの指定
     */
    public BlancoDbCommonPluginMessageResourceBundle(final Locale locale) {
        try {
            fResourceBundle = ResourceBundle.getBundle("blanco/db/common/message/BlancoDbCommonPluginMessage", locale);
        } catch (MissingResourceException ex) {
        }
    }

    /**
     * BlancoDbCommonPluginMessageResourceBundleクラスのコンストラクタ。
     *
     * 基底名[BlancoDbCommonPluginMessage]、指定されたロケール、指定されたクラスローダを使用して、リソースバンドルを取得します。
     *
     * @param locale ロケールの指定
     * @param loader クラスローダの指定
     */
    public BlancoDbCommonPluginMessageResourceBundle(final Locale locale, final ClassLoader loader) {
        try {
            fResourceBundle = ResourceBundle.getBundle("blanco/db/common/message/BlancoDbCommonPluginMessage", locale, loader);
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
     * bundle[BlancoDbCommonPluginMessage], key[MBDBCMI01]
     *
     * [blancoDb プラグインは以下のように利用してください。\n  1.Java プロジェクト内で利用してください\n    blancoDb のソースコード自動生成は、Java プロジェクト内で実行してください。\n  2.「ライブラリー」に JDBC ドライバの jar ファイルを追加してください\n    プロジェクトの「プロパティー」-&gt;「Java のビルド・パス」の「ライブラリー」に\n    JDBC ドライバの jar ファイルを追加してください。\n  ※これらは、ソースコード生成をおこなう場合の制限です。\n    生成されたソースコードの利用時には、関係ありません。] (ja)<br>
     *
     * @return key[MBDBCMI01]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMbdbcmi01() {
        // 初期値として定義書の値を利用します。
        String strFormat = "blancoDb プラグインは以下のように利用してください。\n  1.Java プロジェクト内で利用してください\n    blancoDb のソースコード自動生成は、Java プロジェクト内で実行してください。\n  2.「ライブラリー」に JDBC ドライバの jar ファイルを追加してください\n    プロジェクトの「プロパティー」->「Java のビルド・パス」の「ライブラリー」に\n    JDBC ドライバの jar ファイルを追加してください。\n  ※これらは、ソースコード生成をおこなう場合の制限です。\n    生成されたソースコードの利用時には、関係ありません。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("MBDBCMI01");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoDbCommonPluginMessage], key[MBDBCMC01]
     *
     * [接続チェック失敗] (ja)<br>
     *
     * @return key[MBDBCMC01]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMbdbcmc01() {
        // 初期値として定義書の値を利用します。
        String strFormat = "接続チェック失敗";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("MBDBCMC01");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoDbCommonPluginMessage], key[MBDBCMC02]
     *
     * [指定のJDBCドライバ({0})の読み込みに失敗しました。\n\n該当の JDBC ドライバを読み込めるように設定変更が必要である可能性があります。\n以下の点を確認してください。\n 1.Java プロジェクト内で利用してください\n   blancoDb のソースコード自動生成は、Java プロジェクト内で実行して\n   ください。\n 2.「ライブラリー」に JDBC ドライバの jar ファイルを追加してください\n   プロジェクトの「プロパティー」-&gt;「Java のビルド・パス」の「ライブラリー」\n   に JDBC ドライバの jar ファイルを追加してください。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[MBDBCMC02]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMbdbcmc02(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "指定のJDBCドライバ({0})の読み込みに失敗しました。\n\n該当の JDBC ドライバを読み込めるように設定変更が必要である可能性があります。\n以下の点を確認してください。\n 1.Java プロジェクト内で利用してください\n   blancoDb のソースコード自動生成は、Java プロジェクト内で実行して\n   ください。\n 2.「ライブラリー」に JDBC ドライバの jar ファイルを追加してください\n   プロジェクトの「プロパティー」->「Java のビルド・パス」の「ライブラリー」\n   に JDBC ドライバの jar ファイルを追加してください。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("MBDBCMC02");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }
}
