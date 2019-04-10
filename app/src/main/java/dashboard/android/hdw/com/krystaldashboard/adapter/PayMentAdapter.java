package dashboard.android.hdw.com.krystaldashboard.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import dashboard.android.hdw.com.krystaldashboard.view.PaymentListItem;

public class PayMentAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 20;
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
        PaymentListItem item;
        if(convertView != null){
            item = (PaymentListItem) convertView;
        }else{
            item = new PaymentListItem(parent.getContext());
        }

        return item;
    }
}
