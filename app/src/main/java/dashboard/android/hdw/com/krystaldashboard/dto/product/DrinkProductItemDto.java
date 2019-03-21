package dashboard.android.hdw.com.krystaldashboard.dto.product;

import java.sql.Timestamp;

public class DrinkProductItemDto {

    private Long reorder;
    private Timestamp createDate;
    private DrinkEditItemDto editor;
    private Timestamp lastUpdate;
    private Long id;
    private String productCode;
    private String productNameTh;
    private String productNameEn;
    private String shortName;
    private String description;
    private Double priceForMember;
    private Double priceForNoneMember;
    private Double cost;
    private Double discountForMember;
    private Double discountForNoneMember;
    private String discountType;
    private String image;
    private DrinkBrandItemDto brand;
    private DrinkProductCatItemDto productCategory;
    private DrinkUnitDto unit;
    private Boolean drink;
    private Boolean active;
    private Boolean deleted;


    public Long getReorder() {
        return reorder;
    }

    public void setReorder(Long reorder) {
        this.reorder = reorder;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public DrinkEditItemDto getEditor() {
        return editor;
    }

    public void setEditor(DrinkEditItemDto editor) {
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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductNameTh() {
        return productNameTh;
    }

    public void setProductNameTh(String productNameTh) {
        this.productNameTh = productNameTh;
    }

    public String getProductNameEn() {
        return productNameEn;
    }

    public void setProductNameEn(String productNameEn) {
        this.productNameEn = productNameEn;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPriceForMember() {
        return priceForMember;
    }

    public void setPriceForMember(Double priceForMember) {
        this.priceForMember = priceForMember;
    }

    public Double getPriceForNoneMember() {
        return priceForNoneMember;
    }

    public void setPriceForNoneMember(Double priceForNoneMember) {
        this.priceForNoneMember = priceForNoneMember;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getDiscountForMember() {
        return discountForMember;
    }

    public void setDiscountForMember(Double discountForMember) {
        this.discountForMember = discountForMember;
    }

    public Double getDiscountForNoneMember() {
        return discountForNoneMember;
    }

    public void setDiscountForNoneMember(Double discountForNoneMember) {
        this.discountForNoneMember = discountForNoneMember;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public DrinkBrandItemDto getBrand() {
        return brand;
    }

    public void setBrand(DrinkBrandItemDto brand) {
        this.brand = brand;
    }

    public DrinkProductCatItemDto getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(DrinkProductCatItemDto productCategory) {
        this.productCategory = productCategory;
    }

    public DrinkUnitDto getUnit() {
        return unit;
    }

    public void setUnit(DrinkUnitDto unit) {
        this.unit = unit;
    }

    public Boolean getDrink() {
        return drink;
    }

    public void setDrink(Boolean drink) {
        this.drink = drink;
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
