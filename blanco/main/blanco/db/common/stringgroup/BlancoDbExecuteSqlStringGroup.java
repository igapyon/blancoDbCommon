/*
 * このソースコードは blanco Frameworkにより自動生成されました。
 */
package blanco.db.common.stringgroup;

/**
 * 自動生成の際にSQL文実行をおこなうかどうかを指定するためのフラグ。
 */
public class BlancoDbExecuteSqlStringGroup {
    /**
     * No.1 説明:SQL文は実行しません。
     */
    public static final int NONE = 1;

    /**
     * No.2 説明:検索型(iterator)の場合に、SQL文を実際に実行します。
     */
    public static final int ITERATOR = 2;

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
        // 説明:SQL文は実行しません。
        if ("none".equals(argCheck)) {
            return true;
        }
        // No.2
        // 説明:検索型(iterator)の場合に、SQL文を実際に実行します。
        if ("iterator".equals(argCheck)) {
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
        // 説明:SQL文は実行しません。
        if ("none".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.2
        // 説明:検索型(iterator)の場合に、SQL文を実際に実行します。
        if ("iterator".equalsIgnoreCase(argCheck)) {
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
        // 説明:SQL文は実行しません。
        if ("none".equals(argCheck)) {
            return NONE;
        }
        // No.2
        // 説明:検索型(iterator)の場合に、SQL文を実際に実行します。
        if ("iterator".equals(argCheck)) {
            return ITERATOR;
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
        // 説明:SQL文は実行しません。
        if (argCheck == NONE) {
            return "none";
        }
        // No.2
        // 説明:検索型(iterator)の場合に、SQL文を実際に実行します。
        if (argCheck == ITERATOR) {
            return "iterator";
        }
        // 未定義。
        if (argCheck == NOT_DEFINED) {
            return "";
        }

        // いずれにも該当しませんでした。
        throw new IllegalArgumentException("与えられた値(" + argCheck + ")は文字列グループ[BlancoDbExecuteSql]では定義されない値です。");
    }
}
