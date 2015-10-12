/*
 * blancoDb Enterprise Edition
 * Copyright (C) 2004-2005 Tosiki Iga
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
package blanco.db.common.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * blancoDb�����p����Query�p�[�X�E���[�e�B���e�B�N���X
 * 
 * SQL�̉��߂���ѕϊ��Ȃǂ�ړI�Ƃ��܂��B
 * 
 * @author Tosiki Iga
 */
public class BlancoDbQueryParserUtil {
    /**
     * SQL���̓p�����[�^�Ƃ��Ĕ��肷�邽�߂̐��K�\��������B
     */
    private static final String SZ_PARAMETER_FOR_SQL_INPUT_PARAMETER = "#[a-zA-Z0-9.\\-_\\P{InBasicLatin}]*\\b|#.*$";

    /**
     * SQL���̓p�����[�^�̃}�b�v <br>
     * TODO �}�b�v�𗘗p���Ă��܂����A���ꂾ�Ə��������m�ۂ���܂���B
     */
    @SuppressWarnings("unchecked")
    private final Map fMapForSqlInputParameters = new Hashtable();

    /**
     * �I���W�i����SQL������
     */
    private String fOriginalSqlQueryString = "";

    @SuppressWarnings("unchecked")
    public BlancoDbQueryParserUtil(final String sqlQueryString) {
        // �p�����[�^���L�����܂��B
        fOriginalSqlQueryString = sqlQueryString;

        // ���K�\��������C���X�^���X�𐶐����܂��B
        // TODO ���K�\���ɂ�鏈���ɂ����ĕs�K�؂ȏ󋵂���������\��������܂��B
        final Matcher matcher = Pattern.compile(
                SZ_PARAMETER_FOR_SQL_INPUT_PARAMETER).matcher(
                fOriginalSqlQueryString);

        for (int index = 1; matcher.find(); index++) {
            String name = matcher.group();
            // �擪�́����������܂��B
            name = name.substring(1, name.length());
            if (fMapForSqlInputParameters.containsKey(name) == false) {
                fMapForSqlInputParameters.put(name, new ArrayList());
            }
            ((List) fMapForSqlInputParameters.get(name))
                    .add(new Integer(index));
        }

        for (Iterator ite = fMapForSqlInputParameters.keySet().iterator(); ite
                .hasNext();) {
            final String key = (String) ite.next();
            final List list = (List) fMapForSqlInputParameters.get(key);
            fMapForSqlInputParameters.put(key, convertListToArray(list));
        }
    }

    /**
     * ���͂��ꂽSQL���̓p�����[�^��int�z��ɕϊ����܂��B
     * 
     * @param sqlInputParameterFoundList
     * @return
     */
    @SuppressWarnings("unchecked")
    private int[] convertListToArray(final List sqlInputParameterFoundList) {
        final int[] convertedIntArray = new int[sqlInputParameterFoundList
                .size()];
        final Iterator ite = sqlInputParameterFoundList.iterator();
        for (int index = 0; ite.hasNext(); index++) {
            convertedIntArray[index] = ((Integer) ite.next()).intValue();
        }
        return convertedIntArray;
    }

    /**
     * ����F�L�[�����ɁAint���Q�b�g���܂��B
     * 
     * @param key
     * @return
     */
    public int[] getSqlParameters(final String key) {
        // �}�b�v����Iterator���쐬���Ă���_�ɒ��ӁB
        return (int[]) fMapForSqlInputParameters.get(key);
    }

    /**
     * JDBC�Ɏ��ۂɔ��s����ۂɗ��p�����i�`��������SQL��
     * 
     * SQL�����烁�^�����擾����ۂɗ��p����܂��B�܂�ADotNet�łȂ�
     * Java�ňȊO�̔ł̃\�[�X�R�[�h���������̍ۂɁA���̃��\�b�h�����p����܂��B
     * 
     * @return
     */
    public String getNaturalSqlStringForJava() {
        return fOriginalSqlQueryString.replaceAll(
                SZ_PARAMETER_FOR_SQL_INPUT_PARAMETER, "?");
    }
}