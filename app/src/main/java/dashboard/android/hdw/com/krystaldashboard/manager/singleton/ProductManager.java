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

    public ProductSortDto getProductSortDao() {
        return productSortDao;
    }

    public void setProductSortDao(ProductSortDto productSortDao) {
        this.productSortDao = productSortDao;
    }

    private Context mContext;

    private ProductManager() {
        mContext = Contextor.getInstance().getmContext();
    }

}
