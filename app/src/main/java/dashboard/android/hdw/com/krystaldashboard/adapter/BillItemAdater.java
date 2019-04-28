package dashboard.android.hdw.com.krystaldashboard.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import dashboard.android.hdw.com.krystaldashboard.view.CustomViewBill;

public class BillItemAdater extends BaseAdapter {
    @Override
    public int getCount() {
        return 6;
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
        CustomViewBill item;
        if(convertView != null){
            item = (CustomViewBill) convertView;
        }else{
            item = new CustomViewBill(parent.getContext());
        }

        return item;
    }
}
