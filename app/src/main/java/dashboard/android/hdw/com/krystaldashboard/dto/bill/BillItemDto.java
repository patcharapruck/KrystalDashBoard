package dashboard.android.hdw.com.krystaldashboard.dto.bill;

import java.util.List;

public class BillItemDto {

    private Long id;
    private String invoiceCode;
    private BillฺMemberAccountDto memberAccount;
    private String customerName;
    private BillPlaceDto place;
    private Long pax;
    private List<BillItemListDto> itemList;
    private String foodAndProductItemList;
    private String serviceDrinkItemList;
    private String memberItemList;
    private String useWhiskyMemberList;
    private String useEntertainMemberList;
    private Long baseVat;
    private Long vat;
    private Long discount;
    private String discountType;
    private Long totalDiscount;
    private Long percentOfServiceCharge;
    private Long totalServiceCharge;
    private Long drinkBalance;
    private Long whiskyBalance;
    private String useWhiskyMember;
    private Long cashPayments;
    private Long creditPayments;
    private Long creditCardPayments;
    private Long memberDebitPayments;
    private Long entertainPayments;
    private Long serivceDrinkCharge;
    private Long serviceDringQty;
    private Long foodPrice;
    private Long serviceCharge;
    private Long productPrice;
    private Long memberCharge;
    private Long totalPrice;
    private Long unpaidAmount;

    private String checkin;
    private BillSalesDto sales;
    private BillDocumentStatusDto documentStatus;

    private List<BillTransactionPaymentListDto> transactionPaymentList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public BillฺMemberAccountDto getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(BillฺMemberAccountDto memberAccount) {
        this.memberAccount = memberAccount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BillPlaceDto getPlace() {
        return place;
    }

    public void setPlace(BillPlaceDto place) {
        this.place = place;
    }

    public Long getPax() {
        return pax;
    }

    public void setPax(Long pax) {
        this.pax = pax;
    }

    public List<BillItemListDto> getItemList() {
        return itemList;
    }

    public void setItemList(List<BillItemListDto> itemList) {
        this.itemList = itemList;
    }

    public String getFoodAndProductItemList() {
        return foodAndProductItemList;
    }

    public void setFoodAndProductItemList(String foodAndProductItemList) {
        this.foodAndProductItemList = foodAndProductItemList;
    }

    public String getServiceDrinkItemList() {
        return serviceDrinkItemList;
    }

    public void setServiceDrinkItemList(String serviceDrinkItemList) {
        this.serviceDrinkItemList = serviceDrinkItemList;
    }

    public String getMemberItemList() {
        return memberItemList;
    }

    public void setMemberItemList(String memberItemList) {
        this.memberItemList = memberItemList;
    }

    public String getUseWhiskyMemberList() {
        return useWhiskyMemberList;
    }

    public void setUseWhiskyMemberList(String useWhiskyMemberList) {
        this.useWhiskyMemberList = useWhiskyMemberList;
    }

    public String getUseEntertainMemberList() {
        return useEntertainMemberList;
    }

    public void setUseEntertainMemberList(String useEntertainMemberList) {
        this.useEntertainMemberList = useEntertainMemberList;
    }

    public Long getBaseVat() {
        return baseVat;
    }

    public void setBaseVat(Long baseVat) {
        this.baseVat = baseVat;
    }

    public Long getVat() {
        return vat;
    }

    public void setVat(Long vat) {
        this.vat = vat;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Long getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Long totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public Long getPercentOfServiceCharge() {
        return percentOfServiceCharge;
    }

    public void setPercentOfServiceCharge(Long percentOfServiceCharge) {
        this.percentOfServiceCharge = percentOfServiceCharge;
    }

    public Long getTotalServiceCharge() {
        return totalServiceCharge;
    }

    public void setTotalServiceCharge(Long totalServiceCharge) {
        this.totalServiceCharge = totalServiceCharge;
    }

    public Long getDrinkBalance() {
        return drinkBalance;
    }

    public void setDrinkBalance(Long drinkBalance) {
        this.drinkBalance = drinkBalance;
    }

    public Long getWhiskyBalance() {
        return whiskyBalance;
    }

    public void setWhiskyBalance(Long whiskyBalance) {
        this.whiskyBalance = whiskyBalance;
    }

    public String getUseWhiskyMember() {
        return useWhiskyMember;
    }

    public void setUseWhiskyMember(String useWhiskyMember) {
        this.useWhiskyMember = useWhiskyMember;
    }

    public Long getCashPayments() {
        return cashPayments;
    }

    public void setCashPayments(Long cashPayments) {
        this.cashPayments = cashPayments;
    }

    public Long getCreditPayments() {
        return creditPayments;
    }

    public void setCreditPayments(Long creditPayments) {
        this.creditPayments = creditPayments;
    }

    public Long getCreditCardPayments() {
        return creditCardPayments;
    }

    public void setCreditCardPayments(Long creditCardPayments) {
        this.creditCardPayments = creditCardPayments;
    }

    public Long getMemberDebitPayments() {
        return memberDebitPayments;
    }

    public void setMemberDebitPayments(Long memberDebitPayments) {
        this.memberDebitPayments = memberDebitPayments;
    }

    public Long getEntertainPayments() {
        return entertainPayments;
    }

    public void setEntertainPayments(Long entertainPayments) {
        this.entertainPayments = entertainPayments;
    }

    public Long getSerivceDrinkCharge() {
        return serivceDrinkCharge;
    }

    public void setSerivceDrinkCharge(Long serivceDrinkCharge) {
        this.serivceDrinkCharge = serivceDrinkCharge;
    }

    public Long getServiceDringQty() {
        return serviceDringQty;
    }

    public void setServiceDringQty(Long serviceDringQty) {
        this.serviceDringQty = serviceDringQty;
    }

    public Long getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Long foodPrice) {
        this.foodPrice = foodPrice;
    }

    public Long getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Long serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public Long getMemberCharge() {
        return memberCharge;
    }

    public void setMemberCharge(Long memberCharge) {
        this.memberCharge = memberCharge;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getUnpaidAmount() {
        return unpaidAmount;
    }

    public void setUnpaidAmount(Long unpaidAmount) {
        this.unpaidAmount = unpaidAmount;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public BillSalesDto getSales() {
        return sales;
    }

    public void setSales(BillSalesDto sales) {
        this.sales = sales;
    }

    public BillDocumentStatusDto getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(BillDocumentStatusDto documentStatus) {
        this.documentStatus = documentStatus;
    }

    public List<BillTransactionPaymentListDto> getTransactionPaymentList() {
        return transactionPaymentList;
    }

    public void setTransactionPaymentList(List<BillTransactionPaymentListDto> transactionPaymentList) {
        this.transactionPaymentList = transactionPaymentList;
    }
}
