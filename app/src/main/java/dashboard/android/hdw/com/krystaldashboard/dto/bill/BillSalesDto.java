package dashboard.android.hdw.com.krystaldashboard.dto.bill;

public class BillSalesDto {

    private Long id;
    private String employeeCode;
    private String firstname;
    private String lastname;
    private String nickName;
    private String gender;
    private String image;
    private Long serviceChargePerDrink;
    private String employeeType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getServiceChargePerDrink() {
        return serviceChargePerDrink;
    }

    public void setServiceChargePerDrink(Long serviceChargePerDrink) {
        this.serviceChargePerDrink = serviceChargePerDrink;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }
}
