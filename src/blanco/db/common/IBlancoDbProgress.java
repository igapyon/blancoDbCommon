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
     * おのおののファイルが処理されるたびにコールバックされます。
     * 
     * @param progressCurrent
     *            現在の進捗
     * @param progressTotal
     *            トータル数
     * @param progressMessage
     *            現在処理しているファイル名・表名など
     * @return 中断したい場合にはfalse
     */
    boolean progress(final int progressCurrent, final int progressTotal,
            final String progressItem);
}
