package dashboard.android.hdw.com.krystaldashboard.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.TextView;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.view.basecustom.BaseCustomViewGroup;
import dashboard.android.hdw.com.krystaldashboard.view.basecustom.BundleSavedState;

public class CustomViewPR extends BaseCustomViewGroup {

    TextView textviewNamePR,textviewNicknamePR, textviewIdPR
            ,textviewPosition,textviewOnfloor,textviewStart,textviewAll,textViewRundrink;


    public CustomViewPR(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public CustomViewPR(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public CustomViewPR(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(28)
    public CustomViewPR(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    }

    private void initInflate() {
        inflate(getContext(), R.layout.customview_pr, this);
    }

    private void initInstances() {
        textviewNamePR = (TextView) findViewById(R.id.textview_name_pr);
        textviewNicknamePR = (TextView) findViewById(R.id.textview_nickname_pr);
        textviewIdPR = (TextView) findViewById(R.id.textview_id_pr);
        textViewRundrink = (TextView) findViewById(R.id.text_rundrink);
        textviewPosition = (TextView) findViewById(R.id.textview_position_pr);
        textviewStart = (TextView) findViewById(R.id.textview_start_pr);
        textviewOnfloor = (TextView) findViewById(R.id.textview_onfloor_pr);
        textviewAll = (TextView) findViewById(R.id.textview_all_pr);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        BundleSavedState savedState = new BundleSavedState(superState);
        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        Bundle bundle = ss.getBundle();

    }

    public void setNamePR(String firtname,String lastname){
        textviewNamePR.setText(firtname+" "+lastname);
    }
    public void setNicknamePR(String nickname){
        textviewNicknamePR.setText(nickname);
    }
    public void setIdPR(String id){
        textviewIdPR.setText(id);
    }
    public void setRundrinkPR(String table){
        textViewRundrink.setText(table);
    }
    public void setTextviewPosition(String position){
        textviewPosition.setText(position);
    }
    public void setTextviewOnfloor(String time){
        textviewOnfloor.setText(time);
    }
    public void setTextviewStart(Long start){
        textviewStart.setText(start.toString());
    }
    public void setTextviewAll(Long all){
        textviewAll.setText(all.toString());
    }

}
