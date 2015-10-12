/*
 * ���̃\�[�X�R�[�h�� blanco Framework�ɂ�莩����������܂����B
 */
package blanco.db.common.valueobject;

import java.util.List;

/**
 * SQL���Ɋւ���\���BSQL��`���𒊏ۓI�ɂ���킵�����̂ɂ��������܂��B�I���W�i����� Yasuo Nakanishi
 */
public class BlancoDbSqlInfoStructure {
    /**
     * SQL��`ID�B
     *
     * �t�B�[���h: [name]�B
     */
    private String fName;

    /**
     * ��p�b�P�[�W�B
     *
     * �t�B�[���h: [package]�B
     */
    private String fPackage;

    /**
     * SQL�̃^�C�v�Bxml��query-type�ɑ����BBlancoDbSqlInfoTypeStringGroup�̒�����AITERATOR(�����^), INVOKER(���s�^), CALLER(�ďo�^)�̂����ꂩ�̒l��I�т܂��B
     *
     * �t�B�[���h: [type]�B
     */
    private int fType;

    /**
     * ���̃N�G���̏ڍׁB
     *
     * �t�B�[���h: [description]�B
     */
    private String fDescription;

    /**
     * SQL��`��SQL�����̂��́B
     *
     * �t�B�[���h: [query]�B
     */
    private String fQuery;

    /**
     * SQL���̓p�����[�^�̃��X�g�B
     *
     * �t�B�[���h: [inParameterList]�B
     * �f�t�H���g: [new java.util.ArrayList<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure>()]�B
     */
    private List<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure> fInParameterList = new java.util.ArrayList<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure>();

    /**
     * (caller)�ďo�^SQL��SQL�o�̓p�����[�^�̃��X�g�BCaller�̏ꍇ�ɂ̂ݗ��p����܂��B
     *
     * �t�B�[���h: [outParameterList]�B
     * �f�t�H���g: [new java.util.ArrayList<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure>()]�B
     */
    private List<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure> fOutParameterList = new java.util.ArrayList<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure>();

    /**
     * (iterator)�����^SQL�̌������ʂ̗񃊃X�g�BIterator�̏ꍇ�ɂ̂ݗ��p����܂��B
     *
     * �t�B�[���h: [resultSetColumnList]�B
     * �f�t�H���g: [new java.util.ArrayList<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure>()]�B
     */
    private List<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure> fResultSetColumnList = new java.util.ArrayList<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure>();

    /**
     * ��s���񂪂��邩�ǂ����Bxml��single�ɑ����B
     *
     * �t�B�[���h: [single]�B
     * �f�t�H���g: [false]�B
     */
    private boolean fSingle = false;

    /**
     * (iterator)�����^SQL�̃J�[�\���X�N���[���̑����Bxml��scroll�ɑ����Bforward_only�Ȃǂ��i�[�����B
     *
     * �t�B�[���h: [scroll]�B
     */
    private int fScroll;

    /**
     * (iterator)�����^SQL�̍X�V�\�����Bxml��updatable�ɑ����B
     *
     * �t�B�[���h: [updatable]�B
     * �f�t�H���g: [false]�B
     */
    private boolean fUpdatable = false;

    /**
     * ���ISQL���ǂ����B
     *
     * �t�B�[���h: [dynamic-sql]�B
     * �f�t�H���g: [false]�B
     */
    private boolean fDynamicSql = false;

    /**
     * �p�����[�^��Bean�𗘗p���邩�ǂ����B
     *
     * �t�B�[���h: [use-bean-parameter]�B
     * �f�t�H���g: [false]�B
     */
    private boolean fUseBeanParameter = false;

    /**
     * �X�e�[�g�����g�E�^�C���A�E�g (�b)
     *
     * �t�B�[���h: [statementTimeout]�B
     * �f�t�H���g: [-1]�B
     */
    private int fStatementTimeout = -1;

    /**
     * �t�B�[���h [name] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [SQL��`ID�B]�B
     *
     * @param argName �t�B�[���h[name]�ɐݒ肷��l�B
     */
    public void setName(final String argName) {
        fName = argName;
    }

    /**
     * �t�B�[���h [name] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [SQL��`ID�B]�B
     *
     * @return �t�B�[���h[name]����擾�����l�B
     */
    public String getName() {
        return fName;
    }

    /**
     * �t�B�[���h [package] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [��p�b�P�[�W�B]�B
     *
     * @param argPackage �t�B�[���h[package]�ɐݒ肷��l�B
     */
    public void setPackage(final String argPackage) {
        fPackage = argPackage;
    }

    /**
     * �t�B�[���h [package] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [��p�b�P�[�W�B]�B
     *
     * @return �t�B�[���h[package]����擾�����l�B
     */
    public String getPackage() {
        return fPackage;
    }

    /**
     * �t�B�[���h [type] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [SQL�̃^�C�v�Bxml��query-type�ɑ����BBlancoDbSqlInfoTypeStringGroup�̒�����AITERATOR(�����^), INVOKER(���s�^), CALLER(�ďo�^)�̂����ꂩ�̒l��I�т܂��B]�B
     *
     * @param argType �t�B�[���h[type]�ɐݒ肷��l�B
     */
    public void setType(final int argType) {
        fType = argType;
    }

    /**
     * �t�B�[���h [type] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [SQL�̃^�C�v�Bxml��query-type�ɑ����BBlancoDbSqlInfoTypeStringGroup�̒�����AITERATOR(�����^), INVOKER(���s�^), CALLER(�ďo�^)�̂����ꂩ�̒l��I�т܂��B]�B
     *
     * @return �t�B�[���h[type]����擾�����l�B
     */
    public int getType() {
        return fType;
    }

    /**
     * �t�B�[���h [description] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [���̃N�G���̏ڍׁB]�B
     *
     * @param argDescription �t�B�[���h[description]�ɐݒ肷��l�B
     */
    public void setDescription(final String argDescription) {
        fDescription = argDescription;
    }

    /**
     * �t�B�[���h [description] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [���̃N�G���̏ڍׁB]�B
     *
     * @return �t�B�[���h[description]����擾�����l�B
     */
    public String getDescription() {
        return fDescription;
    }

    /**
     * �t�B�[���h [query] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [SQL��`��SQL�����̂��́B]�B
     *
     * @param argQuery �t�B�[���h[query]�ɐݒ肷��l�B
     */
    public void setQuery(final String argQuery) {
        fQuery = argQuery;
    }

    /**
     * �t�B�[���h [query] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [SQL��`��SQL�����̂��́B]�B
     *
     * @return �t�B�[���h[query]����擾�����l�B
     */
    public String getQuery() {
        return fQuery;
    }

    /**
     * �t�B�[���h [inParameterList] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [SQL���̓p�����[�^�̃��X�g�B]�B
     *
     * @param argInParameterList �t�B�[���h[inParameterList]�ɐݒ肷��l�B
     */
    public void setInParameterList(final List<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure> argInParameterList) {
        fInParameterList = argInParameterList;
    }

    /**
     * �t�B�[���h [inParameterList] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [SQL���̓p�����[�^�̃��X�g�B]�B
     * �f�t�H���g: [new java.util.ArrayList<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure>()]�B
     *
     * @return �t�B�[���h[inParameterList]����擾�����l�B
     */
    public List<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure> getInParameterList() {
        return fInParameterList;
    }

    /**
     * �t�B�[���h [outParameterList] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [(caller)�ďo�^SQL��SQL�o�̓p�����[�^�̃��X�g�BCaller�̏ꍇ�ɂ̂ݗ��p����܂��B]�B
     *
     * @param argOutParameterList �t�B�[���h[outParameterList]�ɐݒ肷��l�B
     */
    public void setOutParameterList(final List<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure> argOutParameterList) {
        fOutParameterList = argOutParameterList;
    }

    /**
     * �t�B�[���h [outParameterList] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [(caller)�ďo�^SQL��SQL�o�̓p�����[�^�̃��X�g�BCaller�̏ꍇ�ɂ̂ݗ��p����܂��B]�B
     * �f�t�H���g: [new java.util.ArrayList<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure>()]�B
     *
     * @return �t�B�[���h[outParameterList]����擾�����l�B
     */
    public List<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure> getOutParameterList() {
        return fOutParameterList;
    }

    /**
     * �t�B�[���h [resultSetColumnList] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [(iterator)�����^SQL�̌������ʂ̗񃊃X�g�BIterator�̏ꍇ�ɂ̂ݗ��p����܂��B]�B
     *
     * @param argResultSetColumnList �t�B�[���h[resultSetColumnList]�ɐݒ肷��l�B
     */
    public void setResultSetColumnList(final List<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure> argResultSetColumnList) {
        fResultSetColumnList = argResultSetColumnList;
    }

    /**
     * �t�B�[���h [resultSetColumnList] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [(iterator)�����^SQL�̌������ʂ̗񃊃X�g�BIterator�̏ꍇ�ɂ̂ݗ��p����܂��B]�B
     * �f�t�H���g: [new java.util.ArrayList<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure>()]�B
     *
     * @return �t�B�[���h[resultSetColumnList]����擾�����l�B
     */
    public List<blanco.dbmetadata.valueobject.BlancoDbMetaDataColumnStructure> getResultSetColumnList() {
        return fResultSetColumnList;
    }

    /**
     * �t�B�[���h [single] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [��s���񂪂��邩�ǂ����Bxml��single�ɑ����B]�B
     *
     * @param argSingle �t�B�[���h[single]�ɐݒ肷��l�B
     */
    public void setSingle(final boolean argSingle) {
        fSingle = argSingle;
    }

    /**
     * �t�B�[���h [single] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [��s���񂪂��邩�ǂ����Bxml��single�ɑ����B]�B
     * �f�t�H���g: [false]�B
     *
     * @return �t�B�[���h[single]����擾�����l�B
     */
    public boolean getSingle() {
        return fSingle;
    }

    /**
     * �t�B�[���h [scroll] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [(iterator)�����^SQL�̃J�[�\���X�N���[���̑����Bxml��scroll�ɑ����Bforward_only�Ȃǂ��i�[�����B]�B
     *
     * @param argScroll �t�B�[���h[scroll]�ɐݒ肷��l�B
     */
    public void setScroll(final int argScroll) {
        fScroll = argScroll;
    }

    /**
     * �t�B�[���h [scroll] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [(iterator)�����^SQL�̃J�[�\���X�N���[���̑����Bxml��scroll�ɑ����Bforward_only�Ȃǂ��i�[�����B]�B
     *
     * @return �t�B�[���h[scroll]����擾�����l�B
     */
    public int getScroll() {
        return fScroll;
    }

    /**
     * �t�B�[���h [updatable] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [(iterator)�����^SQL�̍X�V�\�����Bxml��updatable�ɑ����B]�B
     *
     * @param argUpdatable �t�B�[���h[updatable]�ɐݒ肷��l�B
     */
    public void setUpdatable(final boolean argUpdatable) {
        fUpdatable = argUpdatable;
    }

    /**
     * �t�B�[���h [updatable] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [(iterator)�����^SQL�̍X�V�\�����Bxml��updatable�ɑ����B]�B
     * �f�t�H���g: [false]�B
     *
     * @return �t�B�[���h[updatable]����擾�����l�B
     */
    public boolean getUpdatable() {
        return fUpdatable;
    }

    /**
     * �t�B�[���h [dynamic-sql] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [���ISQL���ǂ����B]�B
     *
     * @param argDynamicSql �t�B�[���h[dynamic-sql]�ɐݒ肷��l�B
     */
    public void setDynamicSql(final boolean argDynamicSql) {
        fDynamicSql = argDynamicSql;
    }

    /**
     * �t�B�[���h [dynamic-sql] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [���ISQL���ǂ����B]�B
     * �f�t�H���g: [false]�B
     *
     * @return �t�B�[���h[dynamic-sql]����擾�����l�B
     */
    public boolean getDynamicSql() {
        return fDynamicSql;
    }

    /**
     * �t�B�[���h [use-bean-parameter] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [�p�����[�^��Bean�𗘗p���邩�ǂ����B]�B
     *
     * @param argUseBeanParameter �t�B�[���h[use-bean-parameter]�ɐݒ肷��l�B
     */
    public void setUseBeanParameter(final boolean argUseBeanParameter) {
        fUseBeanParameter = argUseBeanParameter;
    }

    /**
     * �t�B�[���h [use-bean-parameter] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [�p�����[�^��Bean�𗘗p���邩�ǂ����B]�B
     * �f�t�H���g: [false]�B
     *
     * @return �t�B�[���h[use-bean-parameter]����擾�����l�B
     */
    public boolean getUseBeanParameter() {
        return fUseBeanParameter;
    }

    /**
     * �t�B�[���h [statementTimeout] �̒l��ݒ肵�܂��B
     *
     * �t�B�[���h�̐���: [�X�e�[�g�����g�E�^�C���A�E�g (�b)]�B
     *
     * @param argStatementTimeout �t�B�[���h[statementTimeout]�ɐݒ肷��l�B
     */
    public void setStatementTimeout(final int argStatementTimeout) {
        fStatementTimeout = argStatementTimeout;
    }

    /**
     * �t�B�[���h [statementTimeout] �̒l���擾���܂��B
     *
     * �t�B�[���h�̐���: [�X�e�[�g�����g�E�^�C���A�E�g (�b)]�B
     * �f�t�H���g: [-1]�B
     *
     * @return �t�B�[���h[statementTimeout]����擾�����l�B
     */
    public int getStatementTimeout() {
        return fStatementTimeout;
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
        buf.append("blanco.db.common.valueobject.BlancoDbSqlInfoStructure[");
        buf.append("name=" + fName);
        buf.append(",package=" + fPackage);
        buf.append(",type=" + fType);
        buf.append(",description=" + fDescription);
        buf.append(",query=" + fQuery);
        buf.append(",inParameterList=" + fInParameterList);
        buf.append(",outParameterList=" + fOutParameterList);
        buf.append(",resultSetColumnList=" + fResultSetColumnList);
        buf.append(",single=" + fSingle);
        buf.append(",scroll=" + fScroll);
        buf.append(",updatable=" + fUpdatable);
        buf.append(",dynamic-sql=" + fDynamicSql);
        buf.append(",use-bean-parameter=" + fUseBeanParameter);
        buf.append(",statementTimeout=" + fStatementTimeout);
        buf.append("]");
        return buf.toString();
    }
}
