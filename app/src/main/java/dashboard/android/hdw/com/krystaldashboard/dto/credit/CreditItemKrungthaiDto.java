package dashboard.android.hdw.com.krystaldashboard.dto.credit;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class CreditItemKrungthaiDto {

    @SerializedName("recorde") private Object recordeKrungthai;
    @SerializedName("createDate") private Timestamp createDateKrungthai;
    @SerializedName("editor") private Object editorKrungthai;
    @SerializedName("lastUpdate") private Timestamp lastUpdateKrungthai;
    @SerializedName("id") private Long idKrungthai;
    @SerializedName("bankName") private String bankNameKrungthai;
    @SerializedName("image") private String imageKrungthai;
    @SerializedName("active") private boolean activeKrungthai;
    @SerializedName("deleted") private boolean deletedKrungthai;

    public Object getRecordeKrungthai() {
        return recordeKrungthai;
    }

    public void setRecordeKrungthai(Object recordeKrungthai) {
        this.recordeKrungthai = recordeKrungthai;
    }

    public Timestamp getCreateDateKrungthai() {
        return createDateKrungthai;
    }

    public void setCreateDateKrungthai(Timestamp createDateKrungthai) {
        this.createDateKrungthai = createDateKrungthai;
    }

    public Object getEditorKrungthai() {
        return editorKrungthai;
    }

    public void setEditorKrungthai(Object editorKrungthai) {
        this.editorKrungthai = editorKrungthai;
    }

    public Timestamp getLastUpdateKrungthai() {
        return lastUpdateKrungthai;
    }

    public void setLastUpdateKrungthai(Timestamp lastUpdateKrungthai) {
        this.lastUpdateKrungthai = lastUpdateKrungthai;
    }

    public Long getIdKrungthai() {
        return idKrungthai;
    }

    public void setIdKrungthai(Long idKrungthai) {
        this.idKrungthai = idKrungthai;
    }

    public String getBankNameKrungthai() {
        return bankNameKrungthai;
    }

    public void setBankNameKrungthai(String bankNameKrungthai) {
        this.bankNameKrungthai = bankNameKrungthai;
    }

    public String getImageKrungthai() {
        return imageKrungthai;
    }

    public void setImageKrungthai(String imageKrungthai) {
        this.imageKrungthai = imageKrungthai;
    }

    public boolean isActiveKrungthai() {
        return activeKrungthai;
    }

    public void setActiveKrungthai(boolean activeKrungthai) {
        this.activeKrungthai = activeKrungthai;
    }

    public boolean isDeletedKrungthai() {
        return deletedKrungthai;
    }

    public void setDeletedKrungthai(boolean deletedKrungthai) {
        this.deletedKrungthai = deletedKrungthai;
    }
}
