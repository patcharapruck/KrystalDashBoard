package dashboard.android.hdw.com.krystaldashboard.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import dashboard.android.hdw.com.krystaldashboard.dto.bill.BillArrayDto;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.BillArrayManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.BillManager;
import dashboard.android.hdw.com.krystaldashboard.view.CustomViewBill;
import dashboard.android.hdw.com.krystaldashboard.view.CustomViewBillType;

public class BillTypeAdater extends BaseAdapter {


    @Override
    public int getCount() {
//        return 3;
        return BillArrayManager.getInstance().getBillArrayDto().getTypeBill().size();
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
        CustomViewBillType item;
        if(convertView != null){
            item = (CustomViewBillType) convertView;
        }else{
            item = new CustomViewBillType(parent.getContext());
        }

        String dto = BillArrayManager.getInstance().getBillArrayDto().getTypeBill().get(position);

        BillArrayDto billArrayDto = new BillArrayDto();
        billArrayDto.setTypemenu(dto);
        BillArrayManager.getInstance().setBillArrayDto(billArrayDto);

        try {
            item.setTypeBill(dto);
        }catch (NullPointerException e){
            item.setTypeBill("-");
        }

//        BillItemAdater item2 = new BillItemAdater();
//        try {
//            item2.setType(dto);
//        }catch (NullPointerException e){
//            item2.setType("-");
//        }

        return item;
    }
}
