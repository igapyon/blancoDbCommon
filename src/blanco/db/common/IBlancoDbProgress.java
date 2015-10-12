/*
 * blancoDb
 * Copyright (C) 2004-2006 Yasuo Nakanishi
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
package blanco.db.common;

public interface IBlancoDbProgress {
    /**
     * ���̂��̂̃t�@�C������������邽�тɃR�[���o�b�N����܂��B
     * 
     * @param progressCurrent
     *            ���݂̐i��
     * @param progressTotal
     *            �g�[�^����
     * @param progressMessage
     *            ���ݏ������Ă���t�@�C�����E�\���Ȃ�
     * @return ���f�������ꍇ�ɂ�false
     */
    boolean progress(final int progressCurrent, final int progressTotal,
            final String progressItem);
}
