package dashboard.android.hdw.com.krystaldashboard.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;

import dashboard.android.hdw.com.krystaldashboard.dto.bill.BillItemDto;
import dashboard.android.hdw.com.krystaldashboard.dto.bill.BillItemListDto;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.BillManager;
import dashboard.android.hdw.com.krystaldashboard.view.CustomViewBill;

public class BillItemAdater extends BaseAdapter {

    String Type;
    DecimalFormat formatter;
    ArrayList<Integer> index = new ArrayList<>();

    public void setType(String type) {
        this.Type = type;
    }

    @Override
    public int getCount() {

        index.clear();

        if(BillManager.getInstance().getBillCollectionDto()==null)
            return 0;
        if(BillManager.getInstance().getBillCollectionDto().getObject().get(0).getItemList()==null)
            return 0;
        int size = BillManager.getInstance().getBillCollectionDto().getObject().get(0).getItemList().size();

            for (int i=0;i<size;i++){
                if(Type.equals(BillManager.getInstance().getBillCollectionDto()
                        .getObject().get(0).getItemList().get(i).getIncomeType())){

                    index.add(i);
                }
            }

            return index.size();

    }

    @Override
    public Object getItem(int position) {
        return BillManager.getInstance().getBillCollectionDto().getObject().get(0).getItemList().get(index.get(position));
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomViewBill item;
        if(convertView != null){
            item = (CustomViewBill) convertView;
        }else{
            item = new CustomViewBill(parent.getContext());
        }

        BillItemListDto billItemDto = (BillItemListDto) getItem(position);
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
