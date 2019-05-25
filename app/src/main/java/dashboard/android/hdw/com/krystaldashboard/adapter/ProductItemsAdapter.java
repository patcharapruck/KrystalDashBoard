package dashboard.android.hdw.com.krystaldashboard.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.ArrayList;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.ProductManager;
import dashboard.android.hdw.com.krystaldashboard.view.ProductModelClass;

public class ProductItemsAdapter extends RecyclerView.Adapter<ProductItemsAdapter.CustomViewProduct> {

    private Context context;
    private ArrayList<ProductModelClass> items;

    public ProductItemsAdapter(Context context, ArrayList<ProductModelClass> item){
        this.context = context;
        this.items = item;
    }

    @NonNull
    @Override
    public CustomViewProduct onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CustomViewProduct(LayoutInflater.from(context).inflate(R.layout.customview_drink_0, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewProduct customViewProduct, int i) {
        customViewProduct.TextViewProductName.setText(items.get(i).getNameProduct());
        customViewProduct.TextViewProductTotal.setText(items.get(i).getTotalProduct().toString());
        customViewProduct.TextViewProductBuy.setText(items.get(i).getWithdrawProduct().toString());
        customViewProduct.TextViewProductPurchase.setText(items.get(i).getPurchaseProduct().toString());
        customViewProduct.TextViewProductEntertain.setText(items.get(i).getEntertainProduct().toString());
        Glide.with(context)
                .load(items.get(i).getImageProduct())
                .into(customViewProduct.imageView);

        customViewProduct.mycontent.toggle();
//        customViewProduct.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                customViewProduct.mycontent.toggle();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if(ProductManager.getInstance().getProductSortDto() == null)
            return 0;
        return ProductManager.getInstance().getProductSortDto().getNameProductSort().length;
    }

    public class CustomViewProduct extends RecyclerView.ViewHolder {

        TextView TextViewProductName,TextViewProductTotal,TextViewProductPurchase
                ,TextViewProductBuy,TextViewProductEntertain;

        ImageView imageView;

        CardView cardView;
        ExpandableRelativeLayout mycontent;

        public CustomViewProduct(@NonNull View itemView) {
            super(itemView);

            TextViewProductName = (TextView) itemView.findViewById(R.id.textview_product_name);
            TextViewProductTotal = (TextView) itemView.findViewById(R.id.textview_product_total);
            TextViewProductPurchase = (TextView) itemView.findViewById(R.id.textview_product_purchase);
            TextViewProductBuy = (TextView) itemView.findViewById(R.id.textview_product_buy);
            TextViewProductEntertain = (TextView) itemView.findViewById(R.id.textview_product_entertain);

            imageView = (ImageView) itemView.findViewById(R.id.imageview_product);
            cardView = (CardView) itemView.findViewById(R.id.carddrink);
            mycontent = (ExpandableRelativeLayout)itemView.findViewById(R.id.mycontent);
        }
    }
}

