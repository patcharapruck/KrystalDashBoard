package dashboard.android.hdw.com.krystaldashboard.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import dashboard.android.hdw.com.krystaldashboard.view.CustomViewBillType;

public class BillTypeAdater extends BaseAdapter {
    @Override
    public int getCount() {
        return 3;
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

        return item;
    }
}
