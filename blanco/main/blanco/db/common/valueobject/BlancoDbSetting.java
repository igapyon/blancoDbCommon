/*
 * ���̃\�[�X�R�[�h�� blanco Framework�ɂ�莩����������܂����B
 */
package blanco.db.common.valueobject;

/**
 * blancoDb���N�����邽�߂̐ݒ���B
 */
public class BlancoDbSetting {
    /**
     * �o�͐�f�B���N�g���B
     *
     * �t�B�[���h: [TargetDir]�B
     */
    private String fTargetDir;

    /**
     * ��ƂȂ�p�b�P�[�W���B
     *
     * �t�B�[���h: [BasePackage]�B
     */
    private String fBasePackage;

    /**
     * SQL ��`���̏������ɃG���[�����������ꍇ�ɏ������f���邩�ǂ����B2012.01 ���_�ł� Java �̂ݑΉ��ς݁B
     *
     * �t�B�[���h: [failonerror]�B
     * �f�t�H���g: [false]�B
     */
    private boolean fFailonerror = false;

    /**
     * ���M���O���s�����ǂ����B
     *
     * �t�B�[���h: [logging]�B
     */
    private boolean fLogging;

    /**
     * ���M���O�̃��[�h�B0:�m�[�}����debug���O��񍐁B1.�p�t�H�[�}���X�񍐕t���񍐁B2:SQLID�̂ݕ񍐁B
     *
     * �t�B�[���h: [loggingMode]�B
     * �f�t�H���g: [0]�B
     */
    private int fLoggingMode = 0;

    /**
     * SQL �̃��M���O���s�����ǂ����Blogging �ƈقȂ�Aloggingsql �ł� ���ǐ��̍������O���o�͂��܂��B
     *
     * �t�B�[���h: [loggingsql]�B
     * �f�t�H���g: [false]�B
     */
    private boolean fLoggingsql = false;

    /**
     * JDBC�ڑ��̍ۂɗ��p����JDBC�h���C�o�E�N���X��
     *
     * �t�B�[���h: [jdbcdriver]�B
     */
    private String fJdbcdriver;

    /**
     * JDBC�ڑ��̍ۂɗ��p����JDBC URI��
     *
     * �t�B�[���h: [jdbcurl]�B
     */
    private String fJdbcurl;

    /**
     * JDBC�ڑ��̍ۂɗ��p���郆�[�U��
     *
     * �t�B�[���h: [jdbcuser]�B
     */
    private String fJdbcuser;

    /**
     * JDBC�ڑ��̍ۂɗ��p����p�X���[�h
     *
     * �t�B�[���h: [jdbcpassword]�B
     */
    private String fJdbcpassword;

    /**
     * JDBC�h���C�o�� jar �t�@�C�����B�w�肵���ꍇ�ɂ͊Y���t�@�C���𗘗p���Đڑ������݂�B�����t�@�C������΂����ɂ́u;�v�ŋ�؂�B
     *
     * �t�B�[���h: [jdbcdriverfile]�B
     */
    private String fJdbcdriverfile;

    /**
     * JDBC�ڑ��̍ۂɗ��p����X�L�[�}�� ���Oracle�̍ۂɗ��p����܂��B
     *
     * �t�B�[���h: [schema]�B
     */
    private String fSchema;

    /**
     * �ڑ��m����ɓ�������ۂ�JDBC�h���C�o�̖��́B
     *
     * �t�B�[���h: [DriverName]�B
     * �f�t�H���g: [-1]�B
     */
    private int fDriverName = -1;

    /**
     * �����^�C���p�b�P�[�W���Bnull�ȊO�̏ꍇ�ɂ́A���̎w�肳�ꂽ�p�b�P�[�W�ւƃ����^�C���N���X�𐶐����܂��B
     *
     * �t�B�[���h: [RuntimePackage]�B
     */
    private String fRuntimePackage;

    /**
     * �X�e�[�g�����g�^�C���A�E�g�l�B-1�͖��w��������B�f�t�H���g�l��-1�B
     *
     * �t�B�[���h: [StatementTimeout]�B
     * �f�t�H���g: [-1]�B
     */
    private int fStatementTimeout = -1;

    /**
     * �\�[�X�R�[�h������������SQL��`��SQL�������s���邩�ǂ�����ݒ肷��t���O�B�f�t�H���g�� iterator�Biterator:�����^�̂�SQL�������s���Č��؂���Bnone:SQL���͎��s���Ȃ��B
     *
     * �t�B�[���h: [executeSql]�B
     * �f�t�H���g: [2]�B
     */
    private int fExecuteSql = 2;

    /**
     * ������������\�[�X�t�@�C���̕����G���R�[�f�B���O���w�肵�܂��B
     *
     * �t�B�[���h: [encoding]�B
     */
    private String fEncoding;

    /**
     * ������ɂ��āAMicrosoft Windows 3.1���{��ł̃��j�R�[�h�ւƕϊ����邩�ǂ���.
     *
     * �t�B�[���h: [convertStringToMsWindows31jUnicode]�B
     * �f�t�H���g: [false]�B
     */
    private boolean fConvertStringToMsWindows31jUnicode = false;

    /**
     * �t�B�[���h [TargetDir] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [�o�͐�f�B���N�g���B]�B
     *
     * @param argTargetDir �t�B�[���h[TargetDir]�ɐݒ肷��l�B
     */
    public void setTargetDir(final String argTargetDir) {
        fTargetDir = argTargetDir;
    }

    /**
     * �t�B�[���h [TargetDir] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [�o�͐�f�B���N�g���B]�B
     *
     * @return �t�B�[���h[TargetDir]����擾�����l�B
     */
    public String getTargetDir() {
        return fTargetDir;
    }

    /**
     * �t�B�[���h [BasePackage] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [��ƂȂ�p�b�P�[�W���B]�B
     *
     * @param argBasePackage �t�B�[���h[BasePackage]�ɐݒ肷��l�B
     */
    public void setBasePackage(final String argBasePackage) {
        fBasePackage = argBasePackage;
    }

    /**
     * �t�B�[���h [BasePackage] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [��ƂȂ�p�b�P�[�W���B]�B
     *
     * @return �t�B�[���h[BasePackage]����擾�����l�B
     */
    public String getBasePackage() {
        return fBasePackage;
    }

    /**
     * �t�B�[���h [failonerror] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [SQL ��`���̏������ɃG���[�����������ꍇ�ɏ������f���邩�ǂ����B2012.01 ���_�ł� Java �̂ݑΉ��ς݁B]�B
     *
     * @param argFailonerror �t�B�[���h[failonerror]�ɐݒ肷��l�B
     */
    public void setFailonerror(final boolean argFailonerror) {
        fFailonerror = argFailonerror;
    }

    /**
     * �t�B�[���h [failonerror] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [SQL ��`���̏������ɃG���[�����������ꍇ�ɏ������f���邩�ǂ����B2012.01 ���_�ł� Java �̂ݑΉ��ς݁B]�B
     * �f�t�H���g: [false]�B
     *
     * @return �t�B�[���h[failonerror]����擾�����l�B
     */
    public boolean getFailonerror() {
        return fFailonerror;
    }

    /**
     * �t�B�[���h [logging] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [���M���O���s�����ǂ����B]�B
     *
     * @param argLogging �t�B�[���h[logging]�ɐݒ肷��l�B
     */
    public void setLogging(final boolean argLogging) {
        fLogging = argLogging;
    }

    /**
     * �t�B�[���h [logging] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [���M���O���s�����ǂ����B]�B
     *
     * @return �t�B�[���h[logging]����擾�����l�B
     */
    public boolean getLogging() {
        return fLogging;
    }

    /**
     * �t�B�[���h [loggingMode] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [���M���O�̃��[�h�B0:�m�[�}����debug���O��񍐁B1.�p�t�H�[�}���X�񍐕t���񍐁B2:SQLID�̂ݕ񍐁B]�B
     *
     * @param argLoggingMode �t�B�[���h[loggingMode]�ɐݒ肷��l�B
     */
    public void setLoggingMode(final int argLoggingMode) {
        fLoggingMode = argLoggingMode;
    }

    /**
     * �t�B�[���h [loggingMode] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [���M���O�̃��[�h�B0:�m�[�}����debug���O��񍐁B1.�p�t�H�[�}���X�񍐕t���񍐁B2:SQLID�̂ݕ񍐁B]�B
     * �f�t�H���g: [0]�B
     *
     * @return �t�B�[���h[loggingMode]����擾�����l�B
     */
    public int getLoggingMode() {
        return fLoggingMode;
    }

    /**
     * �t�B�[���h [loggingsql] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [SQL �̃��M���O���s�����ǂ����Blogging �ƈقȂ�Aloggingsql �ł� ���ǐ��̍������O���o�͂��܂��B]�B
     *
     * @param argLoggingsql �t�B�[���h[loggingsql]�ɐݒ肷��l�B
     */
    public void setLoggingsql(final boolean argLoggingsql) {
        fLoggingsql = argLoggingsql;
    }

    /**
     * �t�B�[���h [loggingsql] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [SQL �̃��M���O���s�����ǂ����Blogging �ƈقȂ�Aloggingsql �ł� ���ǐ��̍������O���o�͂��܂��B]�B
     * �f�t�H���g: [false]�B
     *
     * @return �t�B�[���h[loggingsql]����擾�����l�B
     */
    public boolean getLoggingsql() {
        return fLoggingsql;
    }

    /**
     * �t�B�[���h [jdbcdriver] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [JDBC�ڑ��̍ۂɗ��p����JDBC�h���C�o�E�N���X��]�B
     *
     * @param argJdbcdriver �t�B�[���h[jdbcdriver]�ɐݒ肷��l�B
     */
    public void setJdbcdriver(final String argJdbcdriver) {
        fJdbcdriver = argJdbcdriver;
    }

    /**
     * �t�B�[���h [jdbcdriver] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [JDBC�ڑ��̍ۂɗ��p����JDBC�h���C�o�E�N���X��]�B
     *
     * @return �t�B�[���h[jdbcdriver]����擾�����l�B
     */
    public String getJdbcdriver() {
        return fJdbcdriver;
    }

    /**
     * �t�B�[���h [jdbcurl] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [JDBC�ڑ��̍ۂɗ��p����JDBC URI��]�B
     *
     * @param argJdbcurl �t�B�[���h[jdbcurl]�ɐݒ肷��l�B
     */
    public void setJdbcurl(final String argJdbcurl) {
        fJdbcurl = argJdbcurl;
    }

    /**
     * �t�B�[���h [jdbcurl] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [JDBC�ڑ��̍ۂɗ��p����JDBC URI��]�B
     *
     * @return �t�B�[���h[jdbcurl]����擾�����l�B
     */
    public String getJdbcurl() {
        return fJdbcurl;
    }

    /**
     * �t�B�[���h [jdbcuser] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [JDBC�ڑ��̍ۂɗ��p���郆�[�U��]�B
     *
     * @param argJdbcuser �t�B�[���h[jdbcuser]�ɐݒ肷��l�B
     */
    public void setJdbcuser(final String argJdbcuser) {
        fJdbcuser = argJdbcuser;
    }

    /**
     * �t�B�[���h [jdbcuser] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [JDBC�ڑ��̍ۂɗ��p���郆�[�U��]�B
     *
     * @return �t�B�[���h[jdbcuser]����擾�����l�B
     */
    public String getJdbcuser() {
        return fJdbcuser;
    }

    /**
     * �t�B�[���h [jdbcpassword] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [JDBC�ڑ��̍ۂɗ��p����p�X���[�h]�B
     *
     * @param argJdbcpassword �t�B�[���h[jdbcpassword]�ɐݒ肷��l�B
     */
    public void setJdbcpassword(final String argJdbcpassword) {
        fJdbcpassword = argJdbcpassword;
    }

    /**
     * �t�B�[���h [jdbcpassword] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [JDBC�ڑ��̍ۂɗ��p����p�X���[�h]�B
     *
     * @return �t�B�[���h[jdbcpassword]����擾�����l�B
     */
    public String getJdbcpassword() {
        return fJdbcpassword;
    }

    /**
     * �t�B�[���h [jdbcdriverfile] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [JDBC�h���C�o�� jar �t�@�C�����B�w�肵���ꍇ�ɂ͊Y���t�@�C���𗘗p���Đڑ������݂�B�����t�@�C������΂����ɂ́u;�v�ŋ�؂�B]�B
     *
     * @param argJdbcdriverfile �t�B�[���h[jdbcdriverfile]�ɐݒ肷��l�B
     */
    public void setJdbcdriverfile(final String argJdbcdriverfile) {
        fJdbcdriverfile = argJdbcdriverfile;
    }

    /**
     * �t�B�[���h [jdbcdriverfile] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [JDBC�h���C�o�� jar �t�@�C�����B�w�肵���ꍇ�ɂ͊Y���t�@�C���𗘗p���Đڑ������݂�B�����t�@�C������΂����ɂ́u;�v�ŋ�؂�B]�B
     *
     * @return �t�B�[���h[jdbcdriverfile]����擾�����l�B
     */
    public String getJdbcdriverfile() {
        return fJdbcdriverfile;
    }

    /**
     * �t�B�[���h [schema] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [JDBC�ڑ��̍ۂɗ��p����X�L�[�}�� ���Oracle�̍ۂɗ��p����܂��B]�B
     *
     * @param argSchema �t�B�[���h[schema]�ɐݒ肷��l�B
     */
    public void setSchema(final String argSchema) {
        fSchema = argSchema;
    }

    /**
     * �t�B�[���h [schema] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [JDBC�ڑ��̍ۂɗ��p����X�L�[�}�� ���Oracle�̍ۂɗ��p����܂��B]�B
     *
     * @return �t�B�[���h[schema]����擾�����l�B
     */
    public String getSchema() {
        return fSchema;
    }

    /**
     * �t�B�[���h [DriverName] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [�ڑ��m����ɓ�������ۂ�JDBC�h���C�o�̖��́B]�B
     *
     * @param argDriverName �t�B�[���h[DriverName]�ɐݒ肷��l�B
     */
    public void setDriverName(final int argDriverName) {
        fDriverName = argDriverName;
    }

    /**
     * �t�B�[���h [DriverName] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [�ڑ��m����ɓ�������ۂ�JDBC�h���C�o�̖��́B]�B
     * �f�t�H���g: [-1]�B
     *
     * @return �t�B�[���h[DriverName]����擾�����l�B
     */
    public int getDriverName() {
        return fDriverName;
    }

    /**
     * �t�B�[���h [RuntimePackage] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [�����^�C���p�b�P�[�W���Bnull�ȊO�̏ꍇ�ɂ́A���̎w�肳�ꂽ�p�b�P�[�W�ւƃ����^�C���N���X�𐶐����܂��B]�B
     *
     * @param argRuntimePackage �t�B�[���h[RuntimePackage]�ɐݒ肷��l�B
     */
    public void setRuntimePackage(final String argRuntimePackage) {
        fRuntimePackage = argRuntimePackage;
    }

    /**
     * �t�B�[���h [RuntimePackage] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [�����^�C���p�b�P�[�W���Bnull�ȊO�̏ꍇ�ɂ́A���̎w�肳�ꂽ�p�b�P�[�W�ւƃ����^�C���N���X�𐶐����܂��B]�B
     *
     * @return �t�B�[���h[RuntimePackage]����擾�����l�B
     */
    public String getRuntimePackage() {
        return fRuntimePackage;
    }

    /**
     * �t�B�[���h [StatementTimeout] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [�X�e�[�g�����g�^�C���A�E�g�l�B-1�͖��w��������B�f�t�H���g�l��-1�B]�B
     *
     * @param argStatementTimeout �t�B�[���h[StatementTimeout]�ɐݒ肷��l�B
     */
    public void setStatementTimeout(final int argStatementTimeout) {
        fStatementTimeout = argStatementTimeout;
    }

    /**
     * �t�B�[���h [StatementTimeout] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [�X�e�[�g�����g�^�C���A�E�g�l�B-1�͖��w��������B�f�t�H���g�l��-1�B]�B
     * �f�t�H���g: [-1]�B
     *
     * @return �t�B�[���h[StatementTimeout]����擾�����l�B
     */
    public int getStatementTimeout() {
        return fStatementTimeout;
    }

    /**
     * �t�B�[���h [executeSql] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [�\�[�X�R�[�h������������SQL��`��SQL�������s���邩�ǂ�����ݒ肷��t���O�B�f�t�H���g�� iterator�Biterator:�����^�̂�SQL�������s���Č��؂���Bnone:SQL���͎��s���Ȃ��B]�B
     *
     * @param argExecuteSql �t�B�[���h[executeSql]�ɐݒ肷��l�B
     */
    public void setExecuteSql(final int argExecuteSql) {
        fExecuteSql = argExecuteSql;
    }

    /**
     * �t�B�[���h [executeSql] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [�\�[�X�R�[�h������������SQL��`��SQL�������s���邩�ǂ�����ݒ肷��t���O�B�f�t�H���g�� iterator�Biterator:�����^�̂�SQL�������s���Č��؂���Bnone:SQL���͎��s���Ȃ��B]�B
     * �f�t�H���g: [2]�B
     *
     * @return �t�B�[���h[executeSql]����擾�����l�B
     */
    public int getExecuteSql() {
        return fExecuteSql;
    }

    /**
     * �t�B�[���h [encoding] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [������������\�[�X�t�@�C���̕����G���R�[�f�B���O���w�肵�܂��B]�B
     *
     * @param argEncoding �t�B�[���h[encoding]�ɐݒ肷��l�B
     */
    public void setEncoding(final String argEncoding) {
        fEncoding = argEncoding;
    }

    /**
     * �t�B�[���h [encoding] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [������������\�[�X�t�@�C���̕����G���R�[�f�B���O���w�肵�܂��B]�B
     *
     * @return �t�B�[���h[encoding]����擾�����l�B
     */
    public String getEncoding() {
        return fEncoding;
    }

    /**
     * �t�B�[���h [convertStringToMsWindows31jUnicode] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [������ɂ��āAMicrosoft Windows 3.1���{��ł̃��j�R�[�h�ւƕϊ����邩�ǂ���.]�B
     *
     * @param argConvertStringToMsWindows31jUnicode �t�B�[���h[convertStringToMsWindows31jUnicode]�ɐݒ肷��l�B
     */
    public void setConvertStringToMsWindows31jUnicode(final boolean argConvertStringToMsWindows31jUnicode) {
        fConvertStringToMsWindows31jUnicode = argConvertStringToMsWindows31jUnicode;
    }

    /**
     * �t�B�[���h [convertStringToMsWindows31jUnicode] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [������ɂ��āAMicrosoft Windows 3.1���{��ł̃��j�R�[�h�ւƕϊ����邩�ǂ���.]�B
     * �f�t�H���g: [false]�B
     *
     * @return �t�B�[���h[convertStringToMsWindows31jUnicode]����擾�����l�B
     */
    public boolean getConvertStringToMsWindows31jUnicode() {
        return fConvertStringToMsWindows31jUnicode;
    }

    /**
     * ���̃o�����[�I�u�W�F�N�g�̕�����\�����擾���܂��B
     *
     * <P>�g�p��̒���</P>
     * <UL>
     * <LI>�I�u�W�F�N�g�̃V�����[�͈͂̂ݕ����񉻂̏����ΏۂƂȂ�܂��B
     * <LI>�I�u�W�F�N�g���z�Q�Ƃ��Ă���ꍇ�ɂ́A���̃��\�b�h�͎g��Ȃ��ł��������B
     * </UL>
     *
     * @return �o�����[�I�u�W�F�N�g�̕�����\���B
     */
    @Override
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("blanco.db.common.valueobject.BlancoDbSetting[");
        buf.append("TargetDir=" + fTargetDir);
        buf.append(",BasePackage=" + fBasePackage);
        buf.append(",failonerror=" + fFailonerror);
        buf.append(",logging=" + fLogging);
        buf.append(",loggingMode=" + fLoggingMode);
        buf.append(",loggingsql=" + fLoggingsql);
        buf.append(",jdbcdriver=" + fJdbcdriver);
        buf.append(",jdbcurl=" + fJdbcurl);
        buf.append(",jdbcuser=" + fJdbcuser);
        buf.append(",jdbcpassword=" + fJdbcpassword);
        buf.append(",jdbcdriverfile=" + fJdbcdriverfile);
        buf.append(",schema=" + fSchema);
        buf.append(",DriverName=" + fDriverName);
        buf.append(",RuntimePackage=" + fRuntimePackage);
        buf.append(",StatementTimeout=" + fStatementTimeout);
        buf.append(",executeSql=" + fExecuteSql);
        buf.append(",encoding=" + fEncoding);
        buf.append(",convertStringToMsWindows31jUnicode=" + fConvertStringToMsWindows31jUnicode);
        buf.append("]");
        return buf.toString();
    }
}
