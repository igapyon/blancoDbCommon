/*
 * ���̃\�[�X�R�[�h�� blanco Framework�ɂ���Ď�����������Ă��܂��B
 * blancoDb�̂��߂̕ϊ��������`���܂��B
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
 * blancoDb�̂��߂̕ϊ��������`���܂��B
 */
public class BlancoDbMeta2Xml {
    /**
     * ��`�����^�t�@�C�����璆��XML�t�@�C���ւ̕ϊ����L���b�V���ōς܂����ǂ����̃t���O�B
     */
    protected boolean fCacheMeta2Xml = false;

    /**
     * ��`�����^�t�@�C�����璆��XML�t�@�C���ւ̕ϊ����L���b�V���ōς܂����񐔁B
     */
    protected int fCacheMeta2XmlCount = 0;

    /**
     * �N���X���[�_����̒�`���\��XML�t�@�C���̓Ǎ��񐔂����炷���߂̃L���b�V���B
     */
    protected byte[] fCacheMetaDefXml = null;

    /**
     * ��`�����^�t�@�C�����璆��XML�t�@�C���ւ̕ϊ����L���b�V���ōς܂����ǂ����̃t���O���w�肵�܂��B
     *
     * @param argCacheMeta2Xml ��`�����^�t�@�C�����璆��XML�t�@�C���ւ̕ϊ����L���b�V���ōς܂����ǂ����B
     */
    public void setCacheMeta2Xml(final boolean argCacheMeta2Xml) {
        fCacheMeta2Xml = argCacheMeta2Xml;
    }

    /**
     * Excel�t�@�C���̃X�g���[����XML�t�@�C���̃X�g���[���ɕϊ����܂��B
     *
     * ��`�t�@�C���͓����I�Ƀp�X��ێ����Ă��܂��B
     *
     * @param inStreamMetaSource ���^�t�@�C���̓��̓X�g���[���B
     * @param outStreamTarget XML�t�@�C���̏o�̓X�g���[���B
     * @throws IOException ���o�͗�O�����������ꍇ�B
     * @throws TransformerException XML�ϊ���O�����������ꍇ�B
     */
    public void process(final InputStream inStreamMetaSource, final OutputStream outStreamTarget) throws IOException, TransformerException {
        if (inStreamMetaSource == null) {
            throw new IllegalArgumentException("BlancoDbMeta2Xml: Invalid argument: inStreamMetaSource is null.");
        }
        if (outStreamTarget == null) {
            throw new IllegalArgumentException("BlancoDbMeta2Xml: Invalid argument: outStreamTarget is null.");
        }

        if (fCacheMetaDefXml == null) {
            // ���̃N���X���g�Ƃ��Ȃ��N���X���[�_����XML�ݒ�t�@�C���̃��[�h�������Ȃ��܂��B
            final InputStream meta2xmlStream = getClass().getClassLoader().getResourceAsStream("blanco/db/common/BlancoDbMeta2Xml.xml");
            if (meta2xmlStream == null) {
                throw new IllegalArgumentException("BlancoDbMeta2Xml: ���\�[�X[blanco/db/common/BlancoDbMeta2Xml.xml]�̎擾�Ɏ��s���܂���.");
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
     * Excel�t�@�C����XML�t�@�C���ɕϊ����܂��B
     *
     * @param fileMeta ���^�t�@�C���̓��̓t�@�C���B
     * @param fileOutput XML�t�@�C���̏o�́B
     * @throws IOException ���o�͗�O�����������ꍇ�B
     * @throws TransformerException XML�ϊ���O�����������ꍇ�B
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
            // �L���b�V���𗘗p���āA�������X�L�b�v���܂��B
            fCacheMeta2XmlCount++;
            return;
        }

        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            inStream = new BufferedInputStream(new FileInputStream(fileMeta), 8192);
            outStream = new BufferedOutputStream(new FileOutputStream(fileOutput), 8192);

            // �X�g���[���̏������ł����̂ŁA���ۂ̏����������Ȃ��܂��B
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
     * �w��f�B���N�g������Excel�t�@�C����XML�t�@�C���ɕϊ����܂��B
     *
     * �w�肳�ꂽ�t�H���_���̊g���q[.xls]�̃t�@�C�����������܂��B<br>
     * ���������f�[�^�� ���Ƃ̃t�@�C�����Ɋg���q[.xml]��t�^�����t�@�C���֕ۑ����܂��B
     *
     * @param fileMetadir ���^�t�@�C�����i�[����Ă�����̓f�B���N�g���B
     * @param targetDirectory �o�̓f�B���N�g���B
     * @throws IOException ���o�͗�O�����������ꍇ�B
     * @throws TransformerException XML�ϊ���O�����������ꍇ�B
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
            // �o�͐�f�B���N�g�������݂��Ȃ��̂ŁA���O�ɍ쐬���܂��B
            fileTargetDirectory.mkdirs();
        }

        // �w�肳�ꂽ�f�B���N�g���̃t�@�C���ꗗ���擾���܂��B
        final File[] fileMeta = fileMetadir.listFiles();
        if (fileMeta == null) {
            throw new IllegalArgumentException("BlancoMeta2XmlProcessMeta2Xml: list directory [" + fileMetadir.getAbsolutePath() + "] is failed.");
        }
        for (int index = 0; index < fileMeta.length; index++) {
            if (fileMeta[index].getName().endsWith(".xls") == false) {
                // �t�@�C���̊g���q���������ׂ����̂Ƃ͈قȂ邽�ߏ������X�L�b�v���܂��B�B
                continue;
            }

            if (progress(index + 1, fileMeta.length, fileMeta[index].getName()) == false) {
                // �i���\�����珈�����f�̎w���������̂ŁA�������f���܂��B
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
     * �����̐i���������܂��B
     *
     * �i���\�������������ꍇ�ɂ͌p�����ď�������肱�݂܂��B
     *
     * @param progressCurrent ���ݏ������Ă��錏���̔ԍ��B
     * @param progressTotal �����������B
     * @param progressItem �������Ă���A�C�e�����B
     * @return �����𑱍s���Ă悢���ǂ����Bfalse�Ȃ珈�����f�B
     */
    protected boolean progress(final int progressCurrent, final int progressTotal, final String progressItem) {
        // ��ɏ������s������ true ��߂��܂��B
        return true;
    }
}
