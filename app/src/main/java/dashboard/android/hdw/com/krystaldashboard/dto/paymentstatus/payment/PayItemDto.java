package dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus.payment;

public class PayItemDto {


    private Long id;
    private String invoiceCode;
    private String customerName;
    private PayplaceItemDto place;
    private Double totalPrice;
    private PaysalesItemDto sales;

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

    public PayplaceItemDto getPlace() {
        return place;
    }

    public void setPlace(PayplaceItemDto place) {
        this.place = place;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PaysalesItemDto getSales() {
        return sales;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSales(PaysalesItemDto sales) {
        this.sales = sales;
    }
}
