/*
 * このソースコードは blanco Frameworkにより自動生成されました。
 */
package blanco.db.common.stringgroup;

/**
 * SQL情報の型。
 */
public class BlancoDbSqlInfoTypeStringGroup {
    /**
     * No.1 説明:検索型。
     */
    public static final int ITERATOR = 1;

    /**
     * No.2 説明:更新型。
     */
    public static final int INVOKER = 2;

    /**
     * No.3 説明:呼出型。
     */
    public static final int CALLER = 3;

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
        // 説明:検索型。
        if ("iterator".equals(argCheck)) {
            return true;
        }
        // No.2
        // 説明:更新型。
        if ("invoker".equals(argCheck)) {
            return true;
        }
        // No.3
        // 説明:呼出型。
        if ("caller".equals(argCheck)) {
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
        // 説明:検索型。
        if ("iterator".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.2
        // 説明:更新型。
        if ("invoker".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.3
        // 説明:呼出型。
        if ("caller".equalsIgnoreCase(argCheck)) {
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
        // 説明:検索型。
        if ("iterator".equals(argCheck)) {
            return ITERATOR;
        }
        // No.2
        // 説明:更新型。
        if ("invoker".equals(argCheck)) {
            return INVOKER;
        }
        // No.3
        // 説明:呼出型。
        if ("caller".equals(argCheck)) {
            return CALLER;
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
        // 説明:検索型。
        if (argCheck == ITERATOR) {
            return "iterator";
        }
        // No.2
        // 説明:更新型。
        if (argCheck == INVOKER) {
            return "invoker";
        }
        // No.3
        // 説明:呼出型。
        if (argCheck == CALLER) {
            return "caller";
        }
        // 未定義。
        if (argCheck == NOT_DEFINED) {
            return "";
        }

        // いずれにも該当しませんでした。
        throw new IllegalArgumentException("与えられた値(" + argCheck + ")は文字列グループ[BlancoDbSqlInfoType]では定義されない値です。");
    }
}
