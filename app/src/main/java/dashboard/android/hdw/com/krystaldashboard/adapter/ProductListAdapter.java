package dashboard.android.hdw.com.krystaldashboard.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import dashboard.android.hdw.com.krystaldashboard.dto.product.ProductSortDto;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.ProductManager;
import dashboard.android.hdw.com.krystaldashboard.view.CustomViewProduct;

public class ProductListAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        if(ProductManager.getInstance().getProductSortDto() == null)
            return 0;
        return ProductManager.getInstance().getProductSortDto().getNameProductSort().length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomViewProduct item;
        if(convertView != null){
            item = (CustomViewProduct) convertView;
        }else{
            item = new CustomViewProduct(parent.getContext());
        }

        ProductSortDto dto = ProductManager.getInstance().getProductSortDto();

        try {
            item.setNameProduct(position+1,dto.getNameProductSort()[position]);
        }catch (NullPointerException e){
            item.setNameProduct(0,"null");
        }
        try {
            item.setWithdrawProduct(dto.getWithdrawProductSort()[position]);
        }catch (NullPointerException e){
            item.setWithdrawProduct(0L);
        }
        try {
            item.setPurchaseProduct(dto.getPurchaseProductSort()[position]);
        }catch (NullPointerException e){
            item.setPurchaseProduct(0L);
        }
        try {
            item.setEntertainProduct(dto.getEntertainProductSort()[position]);
        }catch (NullPointerException e){
            item.setEntertainProduct(0L);
        }
        try {
            item.setTotalProduct(dto.getTotalAllProductSort()[position]);
        }catch (NullPointerException e){
            item.setTotalProduct(0L);
        }

        try {
            item.setImageProduct(dto.getImageProductSort()[position]);
        }catch (NullPointerException e){
            item.setImageProduct("");
        }



        return item;
    }
}
