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
 * blancoDb �� BlancoCgField�W�J�����ɂ����� ��ʓI�ȏ����W�߂����́B
 * 
 * @author IGA Tosiki
 */
public abstract class BlancoDbAbstractField {
    /**
     * blancoDb�Ɋւ���ݒ���B
     */
    protected BlancoDbSetting fDbSetting = null;

    /**
     * ���̃��\�b�h�������ΏۂƂ��Ă���SQL���̍\���́B
     */
    protected BlancoDbSqlInfoStructure fSqlInfo = null;

    /**
     * �o�͐� blancoCg �I�u�W�F�N�g�t�@�N�g���B
     */
    protected BlancoCgObjectFactory fCgFactory = null;

    /**
     * �o�͐�\�[�X�R�[�h�I�u�W�F�N�g�Bimport��ǉ��������ꍇ�ɗ��p����܂��B
     */
    protected BlancoCgSourceFile fCgSourceFile = null;

    /**
     * �o�͐�� blancoCgClass�B
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
