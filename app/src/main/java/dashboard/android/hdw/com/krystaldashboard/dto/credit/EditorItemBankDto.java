package dashboard.android.hdw.com.krystaldashboard.dto.credit;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class EditorItemBankDto {

    @SerializedName("recorder") private Long recorderData;
    @SerializedName("createDate") private Timestamp createDateEdit;
    @SerializedName("editor") private Long editorData;
    @SerializedName("lastUpdate") private Timestamp lastUpdateEdit;
    @SerializedName("id") private Long idEdit;
    @SerializedName("userType") private String userTypeData;
    @SerializedName("authentication") private String authenticationData;
    @SerializedName("permissionGroup") private String permissionGroupData;
    @SerializedName("active") private Boolean activeEdit;
    @SerializedName("deleted") private Boolean deletedEdit;

    public Long getRecorderData() {
        return recorderData;
    }

    public void setRecorderData(Long recorderData) {
        this.recorderData = recorderData;
    }

    public Timestamp getCreateDateEdit() {
        return createDateEdit;
    }

    public void setCreateDateEdit(Timestamp createDateEdit) {
        this.createDateEdit = createDateEdit;
    }

    public Long getEditorData() {
        return editorData;
    }

    public void setEditorData(Long editorData) {
        this.editorData = editorData;
    }

    public Timestamp getLastUpdateEdit() {
        return lastUpdateEdit;
    }

    public void setLastUpdateEdit(Timestamp lastUpdateEdit) {
        this.lastUpdateEdit = lastUpdateEdit;
    }

    public Long getIdEdit() {
        return idEdit;
    }

    public void setIdEdit(Long idEdit) {
        this.idEdit = idEdit;
    }

    public String getUserTypeData() {
        return userTypeData;
    }

    public void setUserTypeData(String userTypeData) {
        this.userTypeData = userTypeData;
    }

    public String getAuthenticationData() {
        return authenticationData;
    }

    public void setAuthenticationData(String authenticationData) {
        this.authenticationData = authenticationData;
    }

    public String getPermissionGroupData() {
        return permissionGroupData;
    }

    public void setPermissionGroupData(String permissionGroupData) {
        this.permissionGroupData = permissionGroupData;
    }

    public Boolean getActiveEdit() {
        return activeEdit;
    }

    public void setActiveEdit(Boolean activeEdit) {
        this.activeEdit = activeEdit;
    }

    public Boolean getDeletedEdit() {
        return deletedEdit;
    }

    public void setDeletedEdit(Boolean deletedEdit) {
        this.deletedEdit = deletedEdit;
    }
}
