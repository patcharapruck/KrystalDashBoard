package dashboard.android.hdw.com.krystaldashboard.dto.bill;

public class BillPlaceDto {

    private Long id;
    private String placeCode;
    private String image;
    private String placeNameTh;
    private String placeNameEn;
    private String placeSize;
    private Long serviceChargeMember;
    private Long serviceChargeNoneMember;
    private String placeType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPlaceNameTh() {
        return placeNameTh;
    }

    public void setPlaceNameTh(String placeNameTh) {
        this.placeNameTh = placeNameTh;
    }

    public String getPlaceNameEn() {
        return placeNameEn;
    }

    public void setPlaceNameEn(String placeNameEn) {
        this.placeNameEn = placeNameEn;
    }

    public String getPlaceSize() {
        return placeSize;
    }

    public void setPlaceSize(String placeSize) {
        this.placeSize = placeSize;
    }

    public Long getServiceChargeMember() {
        return serviceChargeMember;
    }

    public void setServiceChargeMember(Long serviceChargeMember) {
        this.serviceChargeMember = serviceChargeMember;
    }

    public Long getServiceChargeNoneMember() {
        return serviceChargeNoneMember;
    }

    public void setServiceChargeNoneMember(Long serviceChargeNoneMember) {
        this.serviceChargeNoneMember = serviceChargeNoneMember;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }
}
