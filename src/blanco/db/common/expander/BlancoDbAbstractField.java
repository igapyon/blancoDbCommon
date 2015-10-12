/*
 * blancoDb
 * Copyright (C) 2004-2006 Yasuo Nakanishi
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
package blanco.db.common.expander;

import blanco.cg.BlancoCgObjectFactory;
import blanco.cg.valueobject.BlancoCgClass;
import blanco.cg.valueobject.BlancoCgSourceFile;
import blanco.db.common.valueobject.BlancoDbSetting;
import blanco.db.common.valueobject.BlancoDbSqlInfoStructure;

/**
 * blancoDb の BlancoCgField展開処理における 一般的な情報を集めたもの。
 * 
 * @author IGA Tosiki
 */
public abstract class BlancoDbAbstractField {
    /**
     * blancoDbに関する設定情報。
     */
    protected BlancoDbSetting fDbSetting = null;

    /**
     * このメソッドが処理対象としているSQL情報の構造体。
     */
    protected BlancoDbSqlInfoStructure fSqlInfo = null;

    /**
     * 出力先 blancoCg オブジェクトファクトリ。
     */
    protected BlancoCgObjectFactory fCgFactory = null;

    /**
     * 出力先ソースコードオブジェクト。importを追加したい場合に利用されます。
     */
    protected BlancoCgSourceFile fCgSourceFile = null;

    /**
     * 出力先の blancoCgClass。
     */
    protected BlancoCgClass fCgClass = null;

    protected BlancoDbAbstractField(final BlancoDbSetting argDbSetting,
            final BlancoDbSqlInfoStructure argSqlInfo,
            final BlancoCgObjectFactory argCgFactory,
            final BlancoCgSourceFile argCgSourceFile,
            final BlancoCgClass argCgClass) {
        fDbSetting = argDbSetting;
        fSqlInfo = argSqlInfo;
        fCgFactory = argCgFactory;
        fCgSourceFile = argCgSourceFile;
        fCgClass = argCgClass;
    }

    public abstract void expand();
}
