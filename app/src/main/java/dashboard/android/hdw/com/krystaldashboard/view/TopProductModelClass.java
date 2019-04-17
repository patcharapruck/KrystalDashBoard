package dashboard.android.hdw.com.krystaldashboard.view;

public class TopProductModelClass {

    String Image , NameProduct , AmountProduct;

   public TopProductModelClass(String img ,String name ,String amount){
        this.Image = img;
        this.NameProduct = name;
        this.AmountProduct = amount;
   }

    public String getImage() {
        return Image;
    }

    public String getNameProduct() {
        return NameProduct;
    }

    public String getAmountProduct() {
        return AmountProduct;
    }
}
