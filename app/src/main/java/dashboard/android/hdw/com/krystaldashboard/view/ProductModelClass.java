package dashboard.android.hdw.com.krystaldashboard.view;

public class ProductModelClass {

    private String NameProduct,ImageProduct;
    private Long WithdrawProduct,PurchaseProduct,EntertainProduct,TotalProduct;

   public ProductModelClass(String name , String img , Long amountW,Long amountP,Long amountE,Long amountT){
        this.ImageProduct = img;
        this.NameProduct = name;
        this.WithdrawProduct = amountW;
        this.PurchaseProduct = amountP;
        this.EntertainProduct = amountE;
        this.TotalProduct = amountT;
   }

    public String getNameProduct() {
        return NameProduct;
    }

    public String getImageProduct() {
        return ImageProduct;
    }

    public Long getWithdrawProduct() {
        return WithdrawProduct;
    }

    public Long getPurchaseProduct() {
        return PurchaseProduct;
    }

    public Long getEntertainProduct() {
        return EntertainProduct;
    }

    public Long getTotalProduct() {
        return TotalProduct;
    }
}
