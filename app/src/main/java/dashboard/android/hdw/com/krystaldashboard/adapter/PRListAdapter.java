package dashboard.android.hdw.com.krystaldashboard.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import dashboard.android.hdw.com.krystaldashboard.view.CustomViewPR;

public class PRListAdapter extends BaseAdapter {
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

        CustomViewPR item;
        if(convertView != null){
            item = (CustomViewPR) convertView;
        }else{
            item = new CustomViewPR(parent.getContext());
        }

        return item;
    }
}
