package dashboard.android.hdw.com.krystaldashboard.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.BillArrayManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.BillItemsizeManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.BillManager;
import dashboard.android.hdw.com.krystaldashboard.view.BillModelClass;
import dashboard.android.hdw.com.krystaldashboard.view.TopProductModelClass;

public class BillItemsListAdapter extends RecyclerView.Adapter<BillItemsListAdapter.CustomViewBillItem> {

    private Context context;
    private ArrayList<BillModelClass> items;

    public BillItemsListAdapter(Context context, ArrayList<BillModelClass> item){
        this.context = context;
        this.items = item;
    }

    @NonNull
    @Override
    public CustomViewBillItem onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CustomViewBillItem(LayoutInflater.from(context).inflate(R.layout.customview_bill, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewBillItem customViewBillItem, int i) {
        customViewBillItem.TextViewDeacriptionBill.setText(items.get(i).getDeacriptionBill());
        customViewBillItem.TextViewQtyBill.setText(String.valueOf(items.get(i).getQtyBill()));
        customViewBillItem.TextViewAmountBill.setText(items.get(i).getAmountBill());
        customViewBillItem.TextViewDiscountBill.setText(items.get(i).getDiscountBill());
        customViewBillItem.TextViewPrice.setText(items.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
//        int size = 0,sizeitem = 0;
//        String type = null;
//
//        if(BillManager.getInstance().getBillCollectionDto()==null)
//            return 0;
//        if(BillManager.getInstance().getBillCollectionDto().getObject().get(0).getItemList()==null)
//            return 0;
//
//            sizeitem = BillManager.getInstance().getBillCollectionDto().getObject().get(0).getItemList().size();
//            for (int j = 0; j < BillArrayManager.getInstance().getBillArrayDto().getTypeBill().size(); j++) {
//
//                for (int k =0;k<sizeitem;k++) {
//                    type = BillManager.getInstance().getBillCollectionDto().getObject().get(0).getItemList().get(k).getIncomeType();
//                    if (BillArrayManager.getInstance().getBillArrayDto().getTypeBill().get(j).equals(type)) {
//                        size++;
//                    }
//                }
//            }
        return BillItemsizeManager.getInstance().getSizedto().getSizebillItems();
    }

    public class CustomViewBillItem extends RecyclerView.ViewHolder {

        TextView TextViewDeacriptionBill,TextViewQtyBill,TextViewAmountBill
                ,TextViewDiscountBill,TextViewPrice;

        public CustomViewBillItem(@NonNull View itemView) {
            super(itemView);

            TextViewDeacriptionBill = (TextView) itemView.findViewById(R.id.textview_deacription_bill);
            TextViewQtyBill = (TextView) itemView.findViewById(R.id.textview_qty_bill);
            TextViewAmountBill = (TextView) itemView.findViewById(R.id.textview_amount_bill);
            TextViewDiscountBill = (TextView) itemView.findViewById(R.id.textview_discount_bill);
            TextViewPrice = (TextView) itemView.findViewById(R.id.textview_price);

        }
    }
}

