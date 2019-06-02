package dashboard.android.hdw.com.krystaldashboard.view;

public class BillModelClass {

    private String DeacriptionBill , AmountBill , DiscountBill , Price;
    private Long QtyBill;

   public BillModelClass(String deacriptionBill,Long qtyBill, String amountBill, String discountBill,String price){
        this.DeacriptionBill = deacriptionBill;
        this.QtyBill = qtyBill;
        this.AmountBill = amountBill;
        this.DiscountBill = discountBill;
        this.Price = price;
   }

    public String getDeacriptionBill() {
        return DeacriptionBill;
    }

    public String getAmountBill() {
        return AmountBill;
    }

    public String getDiscountBill() {
        return DiscountBill;
    }

    public String getPrice() {
        return Price;
    }

    public Long getQtyBill() {
        return QtyBill;
    }
}
