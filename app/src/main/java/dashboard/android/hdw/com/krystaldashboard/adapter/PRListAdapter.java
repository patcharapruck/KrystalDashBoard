package dashboard.android.hdw.com.krystaldashboard.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import dashboard.android.hdw.com.krystaldashboard.dto.pr.PRItemCollectionDto;
import dashboard.android.hdw.com.krystaldashboard.dto.pr.PRItemDto;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.PRManager;
import dashboard.android.hdw.com.krystaldashboard.view.CustomViewPR;

public class PRListAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        if(PRManager.getInstance().getPr()==null)
            return 0;
        if(PRManager.getInstance().getPr().getObject()==null)
            return 0;
        return PRManager.getInstance().getPr().getObject().size();

    }

    @Override
    public Object getItem(int position) {
        return PRManager.getInstance().getPr().getObject().get(position);
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

        PRItemDto dto = (PRItemDto) getItem(position);

        try {
            item.setNamePR(dto.getFirstname(),dto.getLastname());
        }catch (NullPointerException e){
            item.setNamePR("","Null");
        }
        try {
            item.setNicknamePR(dto.getNickName());
        }catch (NullPointerException e){
            item.setNicknamePR("Null");
        }
        try {
            item.setIdPR(dto.getEmployeeCode());
        }catch (NullPointerException e){
            item.setIdPR("Null");
        }
        try {
            item.setRundrinkPR(dto.getPlaceCode());
        }catch (NullPointerException e){
            item.setRundrinkPR("Null");
        }
        try {
            item.setTextviewPosition(dto.getPositionNameTh());
        }catch (NullPointerException e){
            item.setTextviewPosition("Null");
        }

        try {
            item.setTextviewOnfloor(dto.getTimeOnFloor());
        }catch (NullPointerException e){
            item.setTextviewOnfloor("Null");
        }

        try {
            item.setTextviewStart(dto.getStartDrink());
        }catch (NullPointerException e){
            item.setTextviewStart(0L);
        }

        try {
            item.setTextviewAll(dto.getTotalQuantity());
        }catch (NullPointerException e){
            item.setTextviewAll(0L);
        }


        return item;
    }
}
