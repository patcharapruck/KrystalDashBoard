package dashboard.android.hdw.com.krystaldashboard.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;

import dashboard.android.hdw.com.krystaldashboard.dto.bill.BillItemDto;
import dashboard.android.hdw.com.krystaldashboard.dto.bill.BillItemListDto;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.BillArrayManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.BillManager;
import dashboard.android.hdw.com.krystaldashboard.view.CustomViewBill;

public class BillItemAdater extends BaseAdapter {

    DecimalFormat formatter;

    @Override
    public int getCount() {

        if(BillManager.getInstance().getBillCollectionDto()==null)
            return 0;
        if(BillManager.getInstance().getBillCollectionDto().getObject().get(0).getItemList()==null)
            return 0;
        return BillManager.getInstance().getBillCollectionDto().getObject().get(0).getItemList().size();

    }

    @Override
    public Object getItem(int position) {
        return BillManager.getInstance().getBillCollectionDto().getObject().get(0).getItemList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomViewBill item;
        String type = BillArrayManager.getInstance().getBillArrayDto().getTypemenu();
        BillItemListDto billItemDto = (BillItemListDto) getItem(position);
        if(convertView != null && type.equals(billItemDto.getIncomeType())){
            item = (CustomViewBill) convertView;
        }else if(convertView == null && type.equals(billItemDto.getIncomeType())){
            item = new CustomViewBill(parent.getContext());
        }else {
            item = null;
            return item;
        }


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
