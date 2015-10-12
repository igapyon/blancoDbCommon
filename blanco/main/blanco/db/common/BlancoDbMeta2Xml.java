/*
 * このソースコードは blanco Frameworkによって自動生成されています。
 * blancoDbのための変換処理を定義します。
 */
package blanco.db.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.TransformerException;

import blanco.commons.calc.parser.BlancoCalcParser;

/**
 * blancoDbのための変換処理を定義します。
 */
public class BlancoDbMeta2Xml {
    /**
     * 定義書メタファイルから中間XMLファイルへの変換をキャッシュで済ますかどうかのフラグ。
     */
    protected boolean fCacheMeta2Xml = false;

    /**
     * 定義書メタファイルから中間XMLファイルへの変換をキャッシュで済ませた回数。
     */
    protected int fCacheMeta2XmlCount = 0;

    /**
     * クラスローダからの定義書構造XMLファイルの読込回数を減らすためのキャッシュ。
     */
    protected byte[] fCacheMetaDefXml = null;

    /**
     * 定義書メタファイルから中間XMLファイルへの変換をキャッシュで済ますかどうかのフラグを指定します。
     *
     * @param argCacheMeta2Xml 定義書メタファイルから中間XMLファイルへの変換をキャッシュで済ますかどうか。
     */
    public void setCacheMeta2Xml(final boolean argCacheMeta2Xml) {
        fCacheMeta2Xml = argCacheMeta2Xml;
    }

    /**
     * ExcelファイルのストリームをXMLファイルのストリームに変換します。
     *
     * 定義ファイルは内部的にパスを保持しています。
     *
     * @param inStreamMetaSource メタファイルの入力ストリーム。
     * @param outStreamTarget XMLファイルの出力ストリーム。
     * @throws IOException 入出力例外が発生した場合。
     * @throws TransformerException XML変換例外が発生した場合。
     */
    public void process(final InputStream inStreamMetaSource, final OutputStream outStreamTarget) throws IOException, TransformerException {
        if (inStreamMetaSource == null) {
            throw new IllegalArgumentException("BlancoDbMeta2Xml: Invalid argument: inStreamMetaSource is null.");
        }
        if (outStreamTarget == null) {
            throw new IllegalArgumentException("BlancoDbMeta2Xml: Invalid argument: outStreamTarget is null.");
        }

        if (fCacheMetaDefXml == null) {
            // このクラス自身とおなじクラスローダからXML設定ファイルのロードをおこないます。
            final InputStream meta2xmlStream = getClass().getClassLoader().getResourceAsStream("blanco/db/common/BlancoDbMeta2Xml.xml");
            if (meta2xmlStream == null) {
                throw new IllegalArgumentException("BlancoDbMeta2Xml: リソース[blanco/db/common/BlancoDbMeta2Xml.xml]の取得に失敗しました.");
            }
            final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            final byte[] bufWrk = new byte[8192];
            for (;;) {
                final int readLength = meta2xmlStream.read(bufWrk);
                if (readLength <= 0) {
                    break;
                }
                outStream.write(bufWrk, 0, readLength);
            }
            outStream.flush();
            meta2xmlStream.close();
            fCacheMetaDefXml = outStream.toByteArray();
        }

        InputStream inStreamDef = new ByteArrayInputStream(fCacheMetaDefXml);
        try {
            new BlancoCalcParser().process(inStreamDef, inStreamMetaSource, outStreamTarget);
        } finally {
            if (inStreamDef != null) {
                inStreamDef.close();
            }
        }
    }

    /**
     * ExcelファイルをXMLファイルに変換します。
     *
     * @param fileMeta メタファイルの入力ファイル。
     * @param fileOutput XMLファイルの出力。
     * @throws IOException 入出力例外が発生した場合。
     * @throws TransformerException XML変換例外が発生した場合。
     */
    public void process(final File fileMeta, final File fileOutput) throws IOException, TransformerException {
        if (fileMeta == null) {
            throw new IllegalArgumentException("BlancoDbMeta2Xml: Invalid argument: fileMeta is null.");
        }
        if (fileOutput == null) {
            throw new IllegalArgumentException("BlancoDbMeta2Xml: Invalid argument: fileOutput is null.");
        }
        if (fileMeta.exists() == false) {
            throw new IllegalArgumentException("BlancoDbMeta2Xml: Invalid argument: file file [" + fileMeta.getAbsolutePath() + "] not found.");
        }

        if (fCacheMeta2Xml && fileMeta.lastModified() < fileOutput.lastModified()) {
            // キャッシュを利用して、処理をスキップします。
            fCacheMeta2XmlCount++;
            return;
        }

        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            inStream = new BufferedInputStream(new FileInputStream(fileMeta), 8192);
            outStream = new BufferedOutputStream(new FileOutputStream(fileOutput), 8192);

            // ストリームの準備ができたので、実際の処理をおこないます。
            process(inStream, outStream);

            outStream.flush();
        } finally {
            if (inStream != null) {
                inStream.close();
            }
            if (outStream != null) {
                outStream.close();
            }
        }
    }

    /**
     * 指定ディレクトリ内のExcelファイルをXMLファイルに変換します。
     *
     * 指定されたフォルダ内の拡張子[.xls]のファイルを処理します。<br>
     * 処理したデータは もとのファイル名に拡張子[.xml]を付与したファイルへ保存します。
     *
     * @param fileMetadir メタファイルが格納されている入力ディレクトリ。
     * @param targetDirectory 出力ディレクトリ。
     * @throws IOException 入出力例外が発生した場合。
     * @throws TransformerException XML変換例外が発生した場合。
     */
    public void processDirectory(final File fileMetadir, final String targetDirectory) throws IOException, TransformerException {
        System.out.println("m2x: begin.");
        final long startMills = System.currentTimeMillis();
        long totalFileCount = 0;
        long totalFileBytes = 0;

        if (fileMetadir == null) {
            throw new IllegalArgumentException("BlancoDbMeta2Xml: Invalid argument: fileMetadir is null.");
        }
        if (targetDirectory == null) {
            throw new IllegalArgumentException("BlancoDbMeta2Xml: Invalid argument: targetDirectory is null.");
        }
        if (fileMetadir.exists() == false) {
            throw new IllegalArgumentException("BlancoDbMeta2Xml: Invalid argument: file [" + fileMetadir.getAbsolutePath() + "] not found.");
        }
        final File fileTargetDirectory = new File(targetDirectory);
        if (fileTargetDirectory.exists() == false) {
            // 出力先ディレクトリが存在しないので、事前に作成します。
            fileTargetDirectory.mkdirs();
        }

        // 指定されたディレクトリのファイル一覧を取得します。
        final File[] fileMeta = fileMetadir.listFiles();
        if (fileMeta == null) {
            throw new IllegalArgumentException("BlancoMeta2XmlProcessMeta2Xml: list directory [" + fileMetadir.getAbsolutePath() + "] is failed.");
        }
        for (int index = 0; index < fileMeta.length; index++) {
            if (fileMeta[index].getName().endsWith(".xls") == false) {
                // ファイルの拡張子が処理すべきものとは異なるため処理をスキップします。。
                continue;
            }

            if (progress(index + 1, fileMeta.length, fileMeta[index].getName()) == false) {
                // 進捗表示から処理中断の指示が来たので、処理中断します。
                break;
            }

            try {
                totalFileCount++;
                totalFileBytes += fileMeta[index].length();
                process(fileMeta[index], new File(targetDirectory + "/" + fileMeta[index].getName() + ".xml"));
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new IllegalArgumentException("BlancoDbMeta2Xml: Exception occurs during processing the file [" + fileMeta[index].getAbsolutePath() + "]. " + ex.toString());
            }
        }

        if (fCacheMeta2Xml) {
            System.out.println("m2x: cache: " + fCacheMeta2XmlCount + " file skipped.");
        }
        final long costMills = System.currentTimeMillis() - startMills + 1;
        System.out.println("m2x: end: " + (costMills / 1000) + " sec, " + totalFileCount + " file, " + totalFileBytes + " byte (" + (totalFileBytes * 1000 / costMills) + " byte/sec).");
    }

    /**
     * 処理の進捗を示します。
     *
     * 進捗表示をさせたい場合には継承して処理を作りこみます。
     *
     * @param progressCurrent 現在処理している件数の番号。
     * @param progressTotal 総処理件数。
     * @param progressItem 処理しているアイテム名。
     * @return 処理を続行してよいかどうか。falseなら処理中断。
     */
    protected boolean progress(final int progressCurrent, final int progressTotal, final String progressItem) {
        // 常に処理続行を示す true を戻します。
        return true;
    }
}
