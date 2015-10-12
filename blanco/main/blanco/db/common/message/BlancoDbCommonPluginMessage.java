/*
 * ���̃\�[�X�R�[�h�� blanco Framework�ɂ���Ď�����������Ă��܂��B
 */
package blanco.db.common.message;

/**
 * blancoDb �̋��ʓI�ȃv���O�C�����b�Z�[�W�B
 */
public class BlancoDbCommonPluginMessage {
    /**
     * ���b�Z�[�W���v���p�e�B�t�@�C���Ή������邽�߂̓����I�ɗ��p���郊�\�[�X�o���h���N���X�B
     */
    protected final BlancoDbCommonPluginMessageResourceBundle fBundle = new BlancoDbCommonPluginMessageResourceBundle();

    /**
     * ���b�Z�[�W��`ID[BlancoDbCommonPlugin]�A�L�[[MBDBCMI01]�̕�������擾���܂��B
     *
     * No.1:
     * ������[blancoDb �v���O�C���͈ȉ��̂悤�ɗ��p���Ă��������B\n  1.Java �v���W�F�N�g���ŗ��p���Ă�������\n    blancoDb �̃\�[�X�R�[�h���������́AJava �v���W�F�N�g���Ŏ��s���Ă��������B\n  2.�u���C�u�����[�v�� JDBC �h���C�o�� jar �t�@�C����ǉ����Ă�������\n    �v���W�F�N�g�́u�v���p�e�B�[�v-&gt;�uJava �̃r���h�E�p�X�v�́u���C�u�����[�v��\n    JDBC �h���C�o�� jar �t�@�C����ǉ����Ă��������B\n  �������́A�\�[�X�R�[�h�����������Ȃ��ꍇ�̐����ł��B\n    �������ꂽ�\�[�X�R�[�h�̗��p���ɂ́A�֌W����܂���B]
     *
     * @return ���b�Z�[�W������B
     */
    public String getMbdbcmi01() {
        return fBundle.getMbdbcmi01();
    }

    /**
     * ���b�Z�[�W��`ID[BlancoDbCommonPlugin]�A�L�[[MBDBCMC01]�̕�������擾���܂��B
     *
     * No.3:
     * ������[�ڑ��`�F�b�N���s]
     *
     * @return ���b�Z�[�W������B
     */
    public String getMbdbcmc01() {
        return fBundle.getMbdbcmc01();
    }

    /**
     * ���b�Z�[�W��`ID[BlancoDbCommonPlugin]�A�L�[[MBDBCMC02]�̕�������擾���܂��B
     *
     * No.4:
     * ������[�w���JDBC�h���C�o({0})�̓ǂݍ��݂Ɏ��s���܂����B\n\n�Y���� JDBC �h���C�o��ǂݍ��߂�悤�ɐݒ�ύX���K�v�ł���\��������܂��B\n�ȉ��̓_���m�F���Ă��������B\n 1.Java �v���W�F�N�g���ŗ��p���Ă�������\n   blancoDb �̃\�[�X�R�[�h���������́AJava �v���W�F�N�g���Ŏ��s����\n   ���������B\n 2.�u���C�u�����[�v�� JDBC �h���C�o�� jar �t�@�C����ǉ����Ă�������\n   �v���W�F�N�g�́u�v���p�e�B�[�v-&gt;�uJava �̃r���h�E�p�X�v�́u���C�u�����[�v\n   �� JDBC �h���C�o�� jar �t�@�C����ǉ����Ă��������B]
     *
     * @param arg0 �u��������{0}�̒l�B
     * @return ���b�Z�[�W������B
     */
    public String getMbdbcmc02(final String arg0) {
        if (arg0 == null) {
            throw new IllegalArgumentException("���\�b�h[getMbdbcmc02]�̃p�����[�^[arg0]��null���^�����܂����B�������A���̃p�����[�^��null��^���邱�Ƃ͂ł��܂���B");
        }

        return fBundle.getMbdbcmc02(arg0);
    }
}
