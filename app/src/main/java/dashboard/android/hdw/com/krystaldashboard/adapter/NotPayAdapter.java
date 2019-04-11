package dashboard.android.hdw.com.krystaldashboard.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus.notpayment.NotPayItemDto;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.NotPayManager;
import dashboard.android.hdw.com.krystaldashboard.view.NotPayListItem;

public class NotPayAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        if(NotPayManager.getInstance().getNotpayItemColleationDao() == null)
            return 0;
        if (NotPayManager.getInstance().getNotpayItemColleationDao().getObject() == null)
            return 0;
        return NotPayManager.getInstance().getNotpayItemColleationDao().getObject().size();
    }

    @Override
    public Object getItem(int position) {
        return NotPayManager.getInstance().getNotpayItemColleationDao().getObject().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NotPayListItem item;
        if(convertView != null){
            item = (NotPayListItem) convertView;
        }else{
            item = new NotPayListItem(parent.getContext());
        }

        NotPayItemDto dao1 = (NotPayItemDto) getItem(position);

        try {
            item.setNotPayId(dao1.getInvoiceCode());
        }catch (NullPointerException e){
            item.setNotPayId("null");
        }
        try {
            item.setNotPayBill(dao1.getCustomerName());
        }catch (NullPointerException e){
            item.setNotPayBill("null");
        }
        try {
            item.setNotPayRoom(dao1.getPlace().getPlaceCode());
        }catch (NullPointerException e){
            item.setNotPayRoom("null");
        }
        try {
            item.setNotPaySale(dao1.getSales().getEmployeeCode(),dao1.getSales().getNickName());
        }catch (NullPointerException e){
            item.setNotPaySale("00","null");
        }
        try {
            item.setNotPayMoney(dao1.getTotalPrice());
        }catch (NullPointerException e){
            item.setNotPayMoney(0d);
        }

        return item;
    }
}
