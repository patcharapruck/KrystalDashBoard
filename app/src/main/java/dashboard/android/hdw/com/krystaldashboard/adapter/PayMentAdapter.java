package dashboard.android.hdw.com.krystaldashboard.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus.payment.PayItemDto;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.PayManager;
import dashboard.android.hdw.com.krystaldashboard.view.PaymentListItem;

public class PayMentAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        if(PayManager.getInstance().getPayItemColleationDao() == null)
            return 0;
        if (PayManager.getInstance().getPayItemColleationDao().getObject() == null)
            return 0;
        return PayManager.getInstance().getPayItemColleationDao().getObject().size();
//        return 18;
    }

    @Override
    public Object getItem(int position) {
        return PayManager.getInstance().getPayItemColleationDao().getObject().get(position);
//        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PaymentListItem item;
        if(convertView != null){
            item = (PaymentListItem) convertView;
        }else{
            item = new PaymentListItem(parent.getContext());
        }

        PayItemDto dao = (PayItemDto) getItem(position);

        try {
            item.setPayId(dao.getInvoiceCode());
        }catch (NullPointerException e){
            item.setPayId("null");
        }
        try {
            item.setPayBill(dao.getCustomerName());
        }catch (NullPointerException e){
            item.setPayBill("null");
        }
        try {
            item.setPayRoom(dao.getPlace().getPlaceCode());
        }catch (NullPointerException e){
            item.setPayRoom("null");
        }
        try {
            item.setPaySale(dao.getSales().getEmployeeCode(),dao.getSales().getNickName());
        }catch (NullPointerException e){
            item.setPaySale("00","null");
        }
        try {
            item.setPayMoney(dao.getTotalPrice());
        }catch (NullPointerException e){
            item.setPayMoney(0d);
        }

        return item;
    }
}
