package dashboard.android.hdw.com.krystaldashboard.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;


import dashboard.android.hdw.com.krystaldashboard.dto.bill.BillItemListDto;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.BillArrayManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.BillManager;
import dashboard.android.hdw.com.krystaldashboard.view.CustomViewBill;

public class BillItemAdater extends BaseAdapter {

    DecimalFormat formatter;
    String type;
    ArrayList<Integer> index;

    @Override
    public int getCount() {

        int size = 0;

        type = BillArrayManager.getInstance().getBillArrayDto().getTypemenu();
        index = new ArrayList<>();

        if(BillManager.getInstance().getBillCollectionDto()==null)
            return 0;
        if(BillManager.getInstance().getBillCollectionDto().getObject().get(0).getItemList()==null)
            return 0;
        for(int i=0;i<BillManager.getInstance().getBillCollectionDto().getObject().get(0).getItemList().size();i++){
            if(type.equals(BillManager.getInstance().getBillCollectionDto().getObject().get(0).getItemList().get(i).getIncomeType())){
                size++;
                index.add(i);
            }
        }

        return size;
    }

    @Override
    public Object getItem(int position) {
        return BillManager.getInstance().getBillCollectionDto().getObject().get(0).getItemList().get(position);
//        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomViewBill item;
        BillItemListDto billItemDto = (BillItemListDto) getItem(index.get(position));

        if(convertView != null){
            item = (CustomViewBill) convertView;
        }else{
            item = new CustomViewBill(parent.getContext());
        }
//
//
        formatter = new DecimalFormat("#,###,##0.00");

        try {
            item.setDeacriptionBill(billItemDto.getItemName());
        }catch (NullPointerException e){
            item.setDeacriptionBill("-");
        }
        try {
            item.setQtyBill(billItemDto.getQuantity());
        }catch (NullPointerException e){
            item.setQtyBill(0L);
        }
        try {
            item.setAmountBill(formatter.format(billItemDto.getAmount()));
        }catch (NullPointerException e){
            item.setAmountBill("0.00");
        }
        try {
            item.setDiscountBill(formatter.format(billItemDto.getDiscount()));
        }catch (NullPointerException e){
            item.setDiscountBill("0.00");
        }

        try {
            item.setPrice(formatter.format(billItemDto.getTotalPrice()));
        }catch (NullPointerException e){
            item.setPrice("0.00");
        }

        return item;
    }
}
