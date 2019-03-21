package dashboard.android.hdw.com.krystaldashboard.dto.credit;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class RecorderItemDto {

    @SerializedName("recorder") private Long recorder;
    @SerializedName("createDate") private Timestamp createDate;
    @SerializedName("editor") private Long editor;
    @SerializedName("lastUpdate") private Timestamp lastUpdate;
    @SerializedName("id") private Long id;
    @SerializedName("userType") private String userType;
    @SerializedName("authentication") private String authentication;
    @SerializedName("permissionGroup") private String permissionGroup;
    @SerializedName("active") private Boolean active;
    @SerializedName("deleted") private Boolean deleted;

    public Long getRecorder() {
        return recorder;
    }

    public void setRecorder(Long recorder) {
        this.recorder = recorder;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Long getEditor() {
        return editor;
    }

    public void setEditor(Long editor) {
        this.editor = editor;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getPermissionGroup() {
        return permissionGroup;
    }

    public void setPermissionGroup(String permissionGroup) {
        this.permissionGroup = permissionGroup;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
