/*
 * このソースコードは blanco Frameworkにより自動生成されました。
 */
package blanco.db.common.stringgroup;

/**
 * SQL情報のスクロール方向。
 */
public class BlancoDbSqlInfoScrollStringGroup {
    /**
     * No.1 説明:前方のみ。
     */
    public static final int TYPE_FORWARD_ONLY = 1;

    /**
     * No.2 説明:インセンシティブ。
     */
    public static final int TYPE_SCROLL_INSENSITIVE = 2;

    /**
     * No.3 説明:センシティブ。
     */
    public static final int TYPE_SCROLL_SENSITIVE = 3;

    /**
     * 未定義。文字列グループ以外の文字列または定数が未定義のもの。
     */
    public static final int NOT_DEFINED = -1;

    /**
     * 文字列グループに含まれる文字列であるかどうかを判定します。
     *
     * @param argCheck チェックを行いたい文字列。
     * @return 文字列グループに含まれていればture。グループに含まれない文字列であればfalse。
     */
    public boolean match(final String argCheck) {
        // No.1
        // 説明:前方のみ。
        if ("forward_only".equals(argCheck)) {
            return true;
        }
        // No.2
        // 説明:インセンシティブ。
        if ("insensitive".equals(argCheck)) {
            return true;
        }
        // No.3
        // 説明:センシティブ。
        if ("sensitive".equals(argCheck)) {
            return true;
        }
        return false;
    }

    /**
     * 文字列グループに含まれる文字列であるかどうかを、大文字小文字を区別せず判定します。
     *
     * @param argCheck チェックを行いたい文字列。
     * @return 文字列グループに含まれていればture。グループに含まれない文字列であればfalse。
     */
    public boolean matchIgnoreCase(final String argCheck) {
        // No.1
        // 説明:前方のみ。
        if ("forward_only".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.2
        // 説明:インセンシティブ。
        if ("insensitive".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.3
        // 説明:センシティブ。
        if ("sensitive".equalsIgnoreCase(argCheck)) {
            return true;
        }
        return false;
    }

    /**
     * 文字列から定数に変換します。
     *
     * 定数が未定義の場合や 与えられた文字列が文字列グループ外の場合には NOT_DEFINED を戻します。
     *
     * @param argCheck 変換を行いたい文字列。
     * @return 定数に変換後の値。
     */
    public int convertToInt(final String argCheck) {
        // No.1
        // 説明:前方のみ。
        if ("forward_only".equals(argCheck)) {
            return TYPE_FORWARD_ONLY;
        }
        // No.2
        // 説明:インセンシティブ。
        if ("insensitive".equals(argCheck)) {
            return TYPE_SCROLL_INSENSITIVE;
        }
        // No.3
        // 説明:センシティブ。
        if ("sensitive".equals(argCheck)) {
            return TYPE_SCROLL_SENSITIVE;
        }

        // 該当する定数が見つかりませんでした。
        return NOT_DEFINED;
    }

    /**
     * 定数から文字列に変換します。
     *
     * 定数と対応づく文字列に変換します。
     *
     * @param argCheck 変換を行いたい文字定数。
     * @return 文字列に変換後の値。NOT_DEFINEDの場合には長さ0の文字列。
     */
    public String convertToString(final int argCheck) {
        // No.1
        // 説明:前方のみ。
        if (argCheck == TYPE_FORWARD_ONLY) {
            return "forward_only";
        }
        // No.2
        // 説明:インセンシティブ。
        if (argCheck == TYPE_SCROLL_INSENSITIVE) {
            return "insensitive";
        }
        // No.3
        // 説明:センシティブ。
        if (argCheck == TYPE_SCROLL_SENSITIVE) {
            return "sensitive";
        }
        // 未定義。
        if (argCheck == NOT_DEFINED) {
            return "";
        }

        // いずれにも該当しませんでした。
        throw new IllegalArgumentException("与えられた値(" + argCheck + ")は文字列グループ[BlancoDbSqlInfoScroll]では定義されない値です。");
    }
}
