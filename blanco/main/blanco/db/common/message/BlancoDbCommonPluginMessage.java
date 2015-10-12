/*
 * このソースコードは blanco Frameworkによって自動生成されています。
 */
package blanco.db.common.message;

/**
 * blancoDb の共通的なプラグインメッセージ。
 */
public class BlancoDbCommonPluginMessage {
    /**
     * メッセージをプロパティファイル対応させるための内部的に利用するリソースバンドルクラス。
     */
    protected final BlancoDbCommonPluginMessageResourceBundle fBundle = new BlancoDbCommonPluginMessageResourceBundle();

    /**
     * メッセージ定義ID[BlancoDbCommonPlugin]、キー[MBDBCMI01]の文字列を取得します。
     *
     * No.1:
     * 文字列[blancoDb プラグインは以下のように利用してください。\n  1.Java プロジェクト内で利用してください\n    blancoDb のソースコード自動生成は、Java プロジェクト内で実行してください。\n  2.「ライブラリー」に JDBC ドライバの jar ファイルを追加してください\n    プロジェクトの「プロパティー」-&gt;「Java のビルド・パス」の「ライブラリー」に\n    JDBC ドライバの jar ファイルを追加してください。\n  ※これらは、ソースコード生成をおこなう場合の制限です。\n    生成されたソースコードの利用時には、関係ありません。]
     *
     * @return メッセージ文字列。
     */
    public String getMbdbcmi01() {
        return fBundle.getMbdbcmi01();
    }

    /**
     * メッセージ定義ID[BlancoDbCommonPlugin]、キー[MBDBCMC01]の文字列を取得します。
     *
     * No.3:
     * 文字列[接続チェック失敗]
     *
     * @return メッセージ文字列。
     */
    public String getMbdbcmc01() {
        return fBundle.getMbdbcmc01();
    }

    /**
     * メッセージ定義ID[BlancoDbCommonPlugin]、キー[MBDBCMC02]の文字列を取得します。
     *
     * No.4:
     * 文字列[指定のJDBCドライバ({0})の読み込みに失敗しました。\n\n該当の JDBC ドライバを読み込めるように設定変更が必要である可能性があります。\n以下の点を確認してください。\n 1.Java プロジェクト内で利用してください\n   blancoDb のソースコード自動生成は、Java プロジェクト内で実行して\n   ください。\n 2.「ライブラリー」に JDBC ドライバの jar ファイルを追加してください\n   プロジェクトの「プロパティー」-&gt;「Java のビルド・パス」の「ライブラリー」\n   に JDBC ドライバの jar ファイルを追加してください。]
     *
     * @param arg0 置換文字列{0}の値。
     * @return メッセージ文字列。
     */
    public String getMbdbcmc02(final String arg0) {
        if (arg0 == null) {
            throw new IllegalArgumentException("メソッド[getMbdbcmc02]のパラメータ[arg0]にnullが与えられました。しかし、このパラメータにnullを与えることはできません。");
        }

        return fBundle.getMbdbcmc02(arg0);
    }
}
