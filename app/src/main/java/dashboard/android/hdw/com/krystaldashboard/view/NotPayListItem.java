package dashboard.android.hdw.com.krystaldashboard.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.TextView;

import java.text.DecimalFormat;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.view.basecustom.BaseCustomViewGroup;
import dashboard.android.hdw.com.krystaldashboard.view.basecustom.BundleSavedState;

public class NotPayListItem extends BaseCustomViewGroup {

    TextView tvNpyId,tvNpyBill,tvNpyRoom,tvNpySale,tvNpyMonny;
    String tNpyMonny;

    public NotPayListItem(Context context) {
            super(context);
            initInflate();
            initInstances();
        }

    public NotPayListItem(Context context, AttributeSet attrs) {
            super(context, attrs);
            initInflate();
            initInstances();
            initWithAttrs(attrs, 0, 0);
        }

    public NotPayListItem(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            initInflate();
            initInstances();
            initWithAttrs(attrs, defStyleAttr, 0);
        }

    @TargetApi(28)
    public NotPayListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
            initInflate();
            initInstances();
            initWithAttrs(attrs, defStyleAttr, defStyleRes);
        }
    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    }

    private void initInflate() {
        inflate(getContext(), R.layout.customview_notpay, this);
    }

    private void initInstances() {

        tvNpyId = (TextView)findViewById(R.id.tvNpyId);
        tvNpyBill = (TextView)findViewById(R.id.tvNpyBill);
        tvNpyRoom = (TextView)findViewById(R.id.tvNpyRoom);
        tvNpySale = (TextView)findViewById(R.id.tvNpySale);
        tvNpyMonny = (TextView)findViewById(R.id.tvNpyMonny);
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

    public void setNotPayId(String NotpayId){
        tvNpyId.setText(NotpayId);
    }

    public void setNotPayBill(String NotpayBill){
        tvNpyBill.setText(NotpayBill);
    }

    public void setNotPayRoom(String NotpayRoom){
        tvNpyRoom.setText(NotpayRoom);
    }

    public void setNotPaySale(String NotpaySaleId , String NotpaySaleName){
        tvNpySale.setText(NotpaySaleId+":"+NotpaySaleName);
    }

    public void setNotPayMoney(Double NotpayMoney){
        DecimalFormat formatter = new DecimalFormat("#,###,##0.00");
        tNpyMonny = formatter.format(NotpayMoney);
        tvNpyMonny.setText(tNpyMonny);
    }
}
