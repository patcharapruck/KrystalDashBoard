package dashboard.android.hdw.com.krystaldashboard.dto.bill;

public class BillฺMemberAccountDto {

    private Long id;
    private String memberCode;
    private String memberAccountName;
    private Long productAmountNotActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getMemberAccountName() {
        return memberAccountName;
    }

    public void setMemberAccountName(String memberAccountName) {
        this.memberAccountName = memberAccountName;
    }

    public Long getProductAmountNotActive() {
        return productAmountNotActive;
    }

    public void setProductAmountNotActive(Long productAmountNotActive) {
        this.productAmountNotActive = productAmountNotActive;
    }
}
