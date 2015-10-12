/*
 * blancoDb
 * Copyright (C) 2004-2012 Yasuo Nakanishi
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
package blanco.db.common;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

import blanco.dbmetadata.BlancoDbMetaDataTable;
import blanco.dbmetadata.valueobject.BlancoDbMetaDataTableStructure;

/**
 * �f�[�^�x�[�X�E�e�[�u����������擾���܂��B
 * 
 * @author Toshiki IGA
 */
public class BlancoDbTableParser {
    /**
     * �\�̈ꗗ�����擾���܂��B
     * 
     * @param conn
     * @param schema
     *            null�̏ꍇ�ɂ̓X�L�[�}�w��Ȃ��B
     * @return
     * @throws SQLException
     */
    public List<BlancoDbMetaDataTableStructure> parse(final Connection conn, final String schema) throws SQLException {
        final DatabaseMetaData metadata = conn.getMetaData();

        final List<BlancoDbMetaDataTableStructure> listTables = BlancoDbMetaDataTable.getTables(metadata, schema, null,
                new String[] { "TABLE" });

        // �\�ɂ܂��K�v�Œ���̃��^�����擾���܂��B
        for (int indexTable = 0; indexTable < listTables.size(); indexTable++) {
            final BlancoDbMetaDataTableStructure tableStructure = listTables.get(indexTable);

            tableStructure.setColumns(BlancoDbMetaDataTable.getColumns(metadata, tableStructure.getCatalog(), schema,
                    tableStructure.getName()));

            tableStructure.setPrimaryKeys(BlancoDbMetaDataTable.getPrimaryKeys(metadata, tableStructure.getCatalog(),
                    schema, tableStructure.getName()));

            // �O���L�[�֘A�̃��^���ɂ��ẮA���x�A�b�v��ړI�Ƃ��Ď擾���ȗ����Ă��܂��B
        }

        return listTables;
    }
}
