package dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus.notpayment;

public class NotPayItemDto {

    private String invoiceCode;
    private String customerName;
    private NotPayplaceItemDto place;
    private Double totalPrice;
    private NotPaysalesItemDto sales;

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public NotPayplaceItemDto getPlace() {
        return place;
    }

    public void setPlace(NotPayplaceItemDto place) {
        this.place = place;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public NotPaysalesItemDto getSales() {
        return sales;
    }

    public void setSales(NotPaysalesItemDto sales) {
        this.sales = sales;
    }
}
