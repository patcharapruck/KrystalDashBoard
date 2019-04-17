package dashboard.android.hdw.com.krystaldashboard.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.view.TopProductModelClass;

public class TopProductAdapter extends RecyclerView.Adapter<TopProductAdapter.CustomViewTopproduct> {

    private Context context;
    private ArrayList<TopProductModelClass> items;

    public TopProductAdapter(Context context, ArrayList<TopProductModelClass> item){
        this.context = context;
        this.items = item;
    }

    @NonNull
    @Override
    public TopProductAdapter.CustomViewTopproduct onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CustomViewTopproduct(LayoutInflater.from(context).inflate(R.layout.customview_drink1, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TopProductAdapter.CustomViewTopproduct customViewTopproduct, int i) {
        customViewTopproduct.TextViewAmountProduct.setText(items.get(i).getAmountProduct());
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class CustomViewTopproduct extends RecyclerView.ViewHolder {

        TextView TextViewAmountProduct,TextViewNameProduct;
        ImageView ImgProduct;

        public CustomViewTopproduct(@NonNull View itemView) {
            super(itemView);

            TextViewAmountProduct = (TextView) itemView.findViewById(R.id.amount_product);
            TextViewNameProduct = (TextView) itemView.findViewById(R.id.name_product);
            ImgProduct = (ImageView) itemView.findViewById(R.id.image_view);
        }
    }
}

