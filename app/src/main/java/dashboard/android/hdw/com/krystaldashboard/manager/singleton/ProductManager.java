package dashboard.android.hdw.com.krystaldashboard.manager.singleton;

import android.content.Context;

import dashboard.android.hdw.com.krystaldashboard.dto.product.ProductSortDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;

public class ProductManager {

    private static ProductManager instance;

    private ProductSortDto productSortDao;

    public static ProductManager getInstance() {
        if (instance == null)
            instance = new ProductManager();
        return instance;
    }

    public ProductSortDto getProductSortDto() {
        return productSortDao;
    }

    public void setProductSortDto(ProductSortDto productSortDto) {
        this.productSortDao = productSortDto;
    }

    private Context mContext;

    private ProductManager() {
        mContext = Contextor.getInstance().getmContext();
    }

}
