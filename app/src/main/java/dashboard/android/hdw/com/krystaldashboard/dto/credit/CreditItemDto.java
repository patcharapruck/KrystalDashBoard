package dashboard.android.hdw.com.krystaldashboard.dto.credit;

import java.sql.Timestamp;

public class CreditItemDto {

    private RecorderItemDto recorde;
    private Timestamp createDate;
    private EditorItemBankDto editor;
    private Timestamp lastUpdate;
    private Long id;
    private String bankName;
    private String image;
    private Boolean active;
    private Boolean deleted;

    public RecorderItemDto getRecorde() {
        return recorde;
    }

    public void setRecorde(RecorderItemDto recorde) {
        this.recorde = recorde;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public EditorItemBankDto getEditor() {
        return editor;
    }

    public void setEditor(EditorItemBankDto editor) {
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
