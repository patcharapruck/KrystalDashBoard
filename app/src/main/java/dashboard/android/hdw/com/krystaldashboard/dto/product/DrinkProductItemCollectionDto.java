package dashboard.android.hdw.com.krystaldashboard.dto.product;

public class DrinkProductItemCollectionDto {

    private DrinkProductItemDto product;
    private Long withdrawUse;
    private Long purchaseAmount;
    private Long entertainAmount;
    private Long totalAll;

    public DrinkProductItemDto getProduct() {
        return product;
    }

    public void setProduct(DrinkProductItemDto product) {
        this.product = product;
    }

    public Long getWithdrawUse() {
        return withdrawUse;
    }

    public void setWithdrawUse(Long withdrawUse) {
        this.withdrawUse = withdrawUse;
    }

    public Long getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(Long purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public Long getEntertainAmount() {
        return entertainAmount;
    }

    public void setEntertainAmount(Long entertainAmount) {
        this.entertainAmount = entertainAmount;
    }

    public Long getTotalAll() {
        return totalAll;
    }

    public void setTotalAll(Long totalAll) {
        this.totalAll = totalAll;
    }
}
