/*
 * このソースコードは blanco Frameworkにより自動生成されました。
 */
package blanco.db.common.valueobject;

import java.util.List;

/**
 * SQL情報に関する構造。SQL定義書を抽象的にあらわしたものにも相当します。オリジナル作者 Yasuo Nakanishi
 */
public class BlancoDbSqlInfoStructure {
    /**
     * SQL定義ID。
     *
     * フィールド: [name]。
     */
    private String fName;

    /**
     * 基準パッケージ。
     *
     * フィールド: [package]。
     */
    private String fPackage;

    /**
     * SQLのタイプ。xmlのquery-typeに相当。BlancoDbSqlInfoTypeStringGroupの中から、ITERATOR(検索型), INVOKER(実行型), CALLER(呼出型)のいずれかの値を選びます。
     *
     * フィールド: [type]。
     */
    private int fType;

    /**
     * このクエリの詳細。
     *
     * フィールド: [description]。
     */
    private String fDescription;

    /**
     * SQL定義のSQL文そのもの。
     *
     * フィールド: [query]。
     */
    private String fQuery;

    /**
     * SQL入力パラメータのリスト。
     *
     * フィールド: [inParameterList]。
     * デフォルト: [new java.util.ArrayList<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure>()]。
     */
    private List<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure> fInParameterList = new java.util.ArrayList<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure>();

    /**
     * (caller)呼出型SQLのSQL出力パラメータのリスト。Callerの場合にのみ利用されます。
     *
     * フィールド: [outParameterList]。
     * デフォルト: [new java.util.ArrayList<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure>()]。
     */
    private List<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure> fOutParameterList = new java.util.ArrayList<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure>();

    /**
     * (iterator)検索型SQLの検索結果の列リスト。Iteratorの場合にのみ利用されます。
     *
     * フィールド: [resultSetColumnList]。
     * デフォルト: [new java.util.ArrayList<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure>()]。
     */
    private List<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure> fResultSetColumnList = new java.util.ArrayList<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure>();

    /**
     * 一行制約があるかどうか。xmlのsingleに相当。
     *
     * フィールド: [single]。
     * デフォルト: [false]。
     */
    private boolean fSingle = false;

    /**
     * (iterator)検索型SQLのカーソルスクロールの属性。xmlのscrollに相当。forward_onlyなどが格納される。
     *
     * フィールド: [scroll]。
     */
    private int fScroll;

    /**
     * (iterator)検索型SQLの更新可能属性。xmlのupdatableに相当。
     *
     * フィールド: [updatable]。
     * デフォルト: [false]。
     */
    private boolean fUpdatable = false;

    /**
     * 動的SQLかどうか。
     *
     * フィールド: [dynamic-sql]。
     * デフォルト: [false]。
     */
    private boolean fDynamicSql = false;

    /**
     * パラメータにBeanを利用するかどうか。
     *
     * フィールド: [use-bean-parameter]。
     * デフォルト: [false]。
     */
    private boolean fUseBeanParameter = false;

    /**
     * ステートメント・タイムアウト (秒)
     *
     * フィールド: [statementTimeout]。
     * デフォルト: [-1]。
     */
    private int fStatementTimeout = -1;

    /**
     * フィールド [name] の値を設定します。
     *
     * フィールドの説明: [SQL定義ID。]。
     *
     * @param argName フィールド[name]に設定する値。
     */
    public void setName(final String argName) {
        fName = argName;
    }

    /**
     * フィールド [name] の値を取得します。
     *
     * フィールドの説明: [SQL定義ID。]。
     *
     * @return フィールド[name]から取得した値。
     */
    public String getName() {
        return fName;
    }

    /**
     * フィールド [package] の値を設定します。
     *
     * フィールドの説明: [基準パッケージ。]。
     *
     * @param argPackage フィールド[package]に設定する値。
     */
    public void setPackage(final String argPackage) {
        fPackage = argPackage;
    }

    /**
     * フィールド [package] の値を取得します。
     *
     * フィールドの説明: [基準パッケージ。]。
     *
     * @return フィールド[package]から取得した値。
     */
    public String getPackage() {
        return fPackage;
    }

    /**
     * フィールド [type] の値を設定します。
     *
     * フィールドの説明: [SQLのタイプ。xmlのquery-typeに相当。BlancoDbSqlInfoTypeStringGroupの中から、ITERATOR(検索型), INVOKER(実行型), CALLER(呼出型)のいずれかの値を選びます。]。
     *
     * @param argType フィールド[type]に設定する値。
     */
    public void setType(final int argType) {
        fType = argType;
    }

    /**
     * フィールド [type] の値を取得します。
     *
     * フィールドの説明: [SQLのタイプ。xmlのquery-typeに相当。BlancoDbSqlInfoTypeStringGroupの中から、ITERATOR(検索型), INVOKER(実行型), CALLER(呼出型)のいずれかの値を選びます。]。
     *
     * @return フィールド[type]から取得した値。
     */
    public int getType() {
        return fType;
    }

    /**
     * フィールド [description] の値を設定します。
     *
     * フィールドの説明: [このクエリの詳細。]。
     *
     * @param argDescription フィールド[description]に設定する値。
     */
    public void setDescription(final String argDescription) {
        fDescription = argDescription;
    }

    /**
     * フィールド [description] の値を取得します。
     *
     * フィールドの説明: [このクエリの詳細。]。
     *
     * @return フィールド[description]から取得した値。
     */
    public String getDescription() {
        return fDescription;
    }

    /**
     * フィールド [query] の値を設定します。
     *
     * フィールドの説明: [SQL定義のSQL文そのもの。]。
     *
     * @param argQuery フィールド[query]に設定する値。
     */
    public void setQuery(final String argQuery) {
        fQuery = argQuery;
    }

    /**
     * フィールド [query] の値を取得します。
     *
     * フィールドの説明: [SQL定義のSQL文そのもの。]。
     *
     * @return フィールド[query]から取得した値。
     */
    public String getQuery() {
        return fQuery;
    }

    /**
     * フィールド [inParameterList] の値を設定します。
     *
     * フィールドの説明: [SQL入力パラメータのリスト。]。
     *
     * @param argInParameterList フィールド[inParameterList]に設定する値。
     */
    public void setInParameterList(final List<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure> argInParameterList) {
        fInParameterList = argInParameterList;
    }

    /**
     * フィールド [inParameterList] の値を取得します。
     *
     * フィールドの説明: [SQL入力パラメータのリスト。]。
     * デフォルト: [new java.util.ArrayList<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure>()]。
     *
     * @return フィールド[inParameterList]から取得した値。
     */
    public List<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure> getInParameterList() {
        return fInParameterList;
    }

    /**
     * フィールド [outParameterList] の値を設定します。
     *
     * フィールドの説明: [(caller)呼出型SQLのSQL出力パラメータのリスト。Callerの場合にのみ利用されます。]。
     *
     * @param argOutParameterList フィールド[outParameterList]に設定する値。
     */
    public void setOutParameterList(final List<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure> argOutParameterList) {
        fOutParameterList = argOutParameterList;
    }

    /**
     * フィールド [outParameterList] の値を取得します。
     *
     * フィールドの説明: [(caller)呼出型SQLのSQL出力パラメータのリスト。Callerの場合にのみ利用されます。]。
     * デフォルト: [new java.util.ArrayList<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure>()]。
     *
     * @return フィールド[outParameterList]から取得した値。
     */
    public List<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure> getOutParameterList() {
        return fOutParameterList;
    }

    /**
     * フィールド [resultSetColumnList] の値を設定します。
     *
     * フィールドの説明: [(iterator)検索型SQLの検索結果の列リスト。Iteratorの場合にのみ利用されます。]。
     *
     * @param argResultSetColumnList フィールド[resultSetColumnList]に設定する値。
     */
    public void setResultSetColumnList(final List<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure> argResultSetColumnList) {
        fResultSetColumnList = argResultSetColumnList;
    }

    /**
     * フィールド [resultSetColumnList] の値を取得します。
     *
     * フィールドの説明: [(iterator)検索型SQLの検索結果の列リスト。Iteratorの場合にのみ利用されます。]。
     * デフォルト: [new java.util.ArrayList<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure>()]。
     *
     * @return フィールド[resultSetColumnList]から取得した値。
     */
    public List<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure> getResultSetColumnList() {
        return fResultSetColumnList;
    }

    /**
     * フィールド [single] の値を設定します。
     *
     * フィールドの説明: [一行制約があるかどうか。xmlのsingleに相当。]。
     *
     * @param argSingle フィールド[single]に設定する値。
     */
    public void setSingle(final boolean argSingle) {
        fSingle = argSingle;
    }

    /**
     * フィールド [single] の値を取得します。
     *
     * フィールドの説明: [一行制約があるかどうか。xmlのsingleに相当。]。
     * デフォルト: [false]。
     *
     * @return フィールド[single]から取得した値。
     */
    public boolean getSingle() {
        return fSingle;
    }

    /**
     * フィールド [scroll] の値を設定します。
     *
     * フィールドの説明: [(iterator)検索型SQLのカーソルスクロールの属性。xmlのscrollに相当。forward_onlyなどが格納される。]。
     *
     * @param argScroll フィールド[scroll]に設定する値。
     */
    public void setScroll(final int argScroll) {
        fScroll = argScroll;
    }

    /**
     * フィールド [scroll] の値を取得します。
     *
     * フィールドの説明: [(iterator)検索型SQLのカーソルスクロールの属性。xmlのscrollに相当。forward_onlyなどが格納される。]。
     *
     * @return フィールド[scroll]から取得した値。
     */
    public int getScroll() {
        return fScroll;
    }

    /**
     * フィールド [updatable] の値を設定します。
     *
     * フィールドの説明: [(iterator)検索型SQLの更新可能属性。xmlのupdatableに相当。]。
     *
     * @param argUpdatable フィールド[updatable]に設定する値。
     */
    public void setUpdatable(final boolean argUpdatable) {
        fUpdatable = argUpdatable;
    }

    /**
     * フィールド [updatable] の値を取得します。
     *
     * フィールドの説明: [(iterator)検索型SQLの更新可能属性。xmlのupdatableに相当。]。
     * デフォルト: [false]。
     *
     * @return フィールド[updatable]から取得した値。
     */
    public boolean getUpdatable() {
        return fUpdatable;
    }

    /**
     * フィールド [dynamic-sql] の値を設定します。
     *
     * フィールドの説明: [動的SQLかどうか。]。
     *
     * @param argDynamicSql フィールド[dynamic-sql]に設定する値。
     */
    public void setDynamicSql(final boolean argDynamicSql) {
        fDynamicSql = argDynamicSql;
    }

    /**
     * フィールド [dynamic-sql] の値を取得します。
     *
     * フィールドの説明: [動的SQLかどうか。]。
     * デフォルト: [false]。
     *
     * @return フィールド[dynamic-sql]から取得した値。
     */
    public boolean getDynamicSql() {
        return fDynamicSql;
    }

    /**
     * フィールド [use-bean-parameter] の値を設定します。
     *
     * フィールドの説明: [パラメータにBeanを利用するかどうか。]。
     *
     * @param argUseBeanParameter フィールド[use-bean-parameter]に設定する値。
     */
    public void setUseBeanParameter(final boolean argUseBeanParameter) {
        fUseBeanParameter = argUseBeanParameter;
    }

    /**
     * フィールド [use-bean-parameter] の値を取得します。
     *
     * フィールドの説明: [パラメータにBeanを利用するかどうか。]。
     * デフォルト: [false]。
     *
     * @return フィールド[use-bean-parameter]から取得した値。
     */
    public boolean getUseBeanParameter() {
        return fUseBeanParameter;
    }

    /**
     * フィールド [statementTimeout] の値を設定します。
     *
     * フィールドの説明: [ステートメント・タイムアウト (秒)]。
     *
     * @param argStatementTimeout フィールド[statementTimeout]に設定する値。
     */
    public void setStatementTimeout(final int argStatementTimeout) {
        fStatementTimeout = argStatementTimeout;
    }

    /**
     * フィールド [statementTimeout] の値を取得します。
     *
     * フィールドの説明: [ステートメント・タイムアウト (秒)]。
     * デフォルト: [-1]。
     *
     * @return フィールド[statementTimeout]から取得した値。
     */
    public int getStatementTimeout() {
        return fStatementTimeout;
    }

    /**
     * このバリューオブジェクトの文字列表現を取得します。
     *
     * <P>使用上の注意</P>
     * <UL>
     * <LI>オブジェクトのシャロー範囲のみ文字列化の処理対象となります。
     * <LI>オブジェクトが循環参照している場合には、このメソッドは使わないでください。
     * </UL>
     *
     * @return バリューオブジェクトの文字列表現。
     */
    @Override
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("blanco.db.common.valueobject.BlancoDbSqlInfoStructure[");
        buf.append("name=" + fName);
        buf.append(",package=" + fPackage);
        buf.append(",type=" + fType);
        buf.append(",description=" + fDescription);
        buf.append(",query=" + fQuery);
        buf.append(",inParameterList=" + fInParameterList);
        buf.append(",outParameterList=" + fOutParameterList);
        buf.append(",resultSetColumnList=" + fResultSetColumnList);
        buf.append(",single=" + fSingle);
        buf.append(",scroll=" + fScroll);
        buf.append(",updatable=" + fUpdatable);
        buf.append(",dynamic-sql=" + fDynamicSql);
        buf.append(",use-bean-parameter=" + fUseBeanParameter);
        buf.append(",statementTimeout=" + fStatementTimeout);
        buf.append("]");
        return buf.toString();
    }
}
