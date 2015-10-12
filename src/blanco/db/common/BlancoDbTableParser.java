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
 * データベース・テーブルから情報を取得します。
 * 
 * @author Toshiki IGA
 */
public class BlancoDbTableParser {
    /**
     * 表の一覧情報を取得します。
     * 
     * @param conn
     * @param schema
     *            nullの場合にはスキーマ指定なし。
     * @return
     * @throws SQLException
     */
    public List<BlancoDbMetaDataTableStructure> parse(final Connection conn, final String schema) throws SQLException {
        final DatabaseMetaData metadata = conn.getMetaData();

        final List<BlancoDbMetaDataTableStructure> listTables = BlancoDbMetaDataTable.getTables(metadata, schema, null,
                new String[] { "TABLE" });

        // 表にまつわる必要最低限のメタ情報を取得します。
        for (int indexTable = 0; indexTable < listTables.size(); indexTable++) {
            final BlancoDbMetaDataTableStructure tableStructure = listTables.get(indexTable);

            tableStructure.setColumns(BlancoDbMetaDataTable.getColumns(metadata, tableStructure.getCatalog(), schema,
                    tableStructure.getName()));

            tableStructure.setPrimaryKeys(BlancoDbMetaDataTable.getPrimaryKeys(metadata, tableStructure.getCatalog(),
                    schema, tableStructure.getName()));

            // 外部キー関連のメタ情報については、速度アップを目的として取得を省略しています。
        }

        return listTables;
    }
}
