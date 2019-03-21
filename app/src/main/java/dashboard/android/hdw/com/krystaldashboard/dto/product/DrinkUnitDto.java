package dashboard.android.hdw.com.krystaldashboard.dto.product;

import java.sql.Timestamp;

public class DrinkUnitDto {

    private Long recorder;
    private Timestamp createDate;
    private Long editor;
    private Timestamp lastUpdate;
    private Long id;
    private String unitNameTh;
    private String unitNameEn;
    private String description;
    private Boolean active;
    private Boolean deleted;

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

    public String getUnitNameTh() {
        return unitNameTh;
    }

    public void setUnitNameTh(String unitNameTh) {
        this.unitNameTh = unitNameTh;
    }

    public String getUnitNameEn() {
        return unitNameEn;
    }

    public void setUnitNameEn(String unitNameEn) {
        this.unitNameEn = unitNameEn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
