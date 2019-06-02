package dashboard.android.hdw.com.krystaldashboard.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.dto.bill.BillItemListDto;
import dashboard.android.hdw.com.krystaldashboard.dto.bill.Sizedto;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.BillArrayManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.BillItemsizeManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.BillManager;
import dashboard.android.hdw.com.krystaldashboard.view.BillModelClass;
import dashboard.android.hdw.com.krystaldashboard.view.BillTypeModelClass;
import dashboard.android.hdw.com.krystaldashboard.view.TopProductModelClass;

public class BillTypeListAdapter extends RecyclerView.Adapter<BillTypeListAdapter.CustomViewBillType> {

    private Context context;
    private ArrayList<BillTypeModelClass> items;

    public BillTypeListAdapter(Context context, ArrayList<BillTypeModelClass> item){
        this.context = context;
        this.items = item;
    }

    @NonNull
    @Override
    public BillTypeListAdapter.CustomViewBillType onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CustomViewBillType(LayoutInflater.from(context).inflate(R.layout.customview_bill_type, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BillTypeListAdapter.CustomViewBillType customViewBillType, int i) {

        customViewBillType.TextFood.setText(items.get(i).getBillType());
        customViewBillType.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        customViewBillType.recyclerView.setAdapter(customViewBillType.adapter);

        ArrayList<Integer> index;
        DecimalFormat formatter = new DecimalFormat("#,###,##0.00");
        int size = 0,sizeitem = 0;
        String type;
        index = new ArrayList<>();
        Double sum=0.0;

        if(BillManager.getInstance().getBillCollectionDto()==null)
            type = "";
        else if(BillManager.getInstance().getBillCollectionDto().getObject().get(0).getItemList()==null)
            type = "";
        else{
            sizeitem = BillManager.getInstance().getBillCollectionDto().getObject().get(0).getItemList().size();
            for(int j=0;j<sizeitem;j++){
                type = BillManager.getInstance().getBillCollectionDto().getObject().get(0).getItemList().get(j).getIncomeType();
                if(BillArrayManager.getInstance().getBillArrayDto().getTypeBill().get(i).equals(type)){
                    sum = sum + BillManager.getInstance().getBillCollectionDto().getObject().get(0).getItemList().get(j).getTotalPrice();
                    size++;
                    index.add(j);
                }
            }

            Sizedto sizedto = new Sizedto();
            sizedto.setSizebillItems(size);
            BillItemsizeManager.getInstance().setSizedto(sizedto);
        }


        for (int j = 0; j <size; j++) {
            try {
                BillItemListDto billItemDto = (BillItemListDto) BillManager.getInstance().getBillCollectionDto().getObject().get(0).getItemList().get(index.get(j));
                customViewBillType.items.add(new BillModelClass(billItemDto.getItemName(),billItemDto.getQuantity()
                        ,formatter.format(billItemDto.getAmount()),formatter.format(billItemDto.getDiscount())
                        ,formatter.format(billItemDto.getTotalPrice())));

            }catch (Exception e){
               // break;
            }

            customViewBillType.adapter.notifyDataSetChanged();
        }

        customViewBillType.TextTotalSum.setText(formatter.format(sum));

    }

    @Override
    public int getItemCount() {

        if(BillArrayManager.getInstance().getBillArrayDto().getTypeBill()== null)
            return 0;
        else
            return BillArrayManager.getInstance().getBillArrayDto().getTypeBill().size();

    }

    public class CustomViewBillType extends RecyclerView.ViewHolder {

        TextView TextFood,TextTotalSum;
        ArrayList<BillModelClass> items;
        BillItemsListAdapter adapter;
        RecyclerView recyclerView;

        public CustomViewBillType(@NonNull View itemView) {
            super(itemView);

            TextTotalSum = (TextView) itemView.findViewById(R.id.textview_sum_total);
            TextFood = (TextView) itemView.findViewById(R.id.textfood);
            recyclerView = itemView.findViewById(R.id.recycler_view_billitem);
            items= new ArrayList<>();
            adapter = new BillItemsListAdapter(context, items);
        }
    }
}

