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

public class PaymentListItem extends BaseCustomViewGroup {

    TextView tvPpyId,tvPpyBill,tvPpyRoom,tvPpySale,tvPpyMonny;
    String tPpyMonny;

    public PaymentListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public PaymentListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public PaymentListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public PaymentListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    }

    private void initInflate() {
        inflate(getContext(), R.layout.customview_payment, this);
    }

    private void initInstances() {

        tvPpyId = (TextView)findViewById(R.id.tvPpyId);
        tvPpyBill = (TextView)findViewById(R.id.tvPpyBill);
        tvPpyRoom = (TextView)findViewById(R.id.tvPpyRoom);
        tvPpySale = (TextView)findViewById(R.id.tvPpySale);
        tvPpyMonny = (TextView)findViewById(R.id.tvPpyMonny);
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

    public void setPayId(String payId){
        tvPpyId.setText(payId);
    }

    public void setPayBill(String payBill){
        tvPpyBill.setText(payBill);
    }

    public void setPayRoom(String payRoom){
        tvPpyRoom.setText(payRoom);
    }

    public void setPaySale(String paySaleId , String paySaleName){
        tvPpySale.setText(paySaleId+":"+paySaleName);
    }

    public void setPayMoney(Double payMoney){
        DecimalFormat formatter = new DecimalFormat("#,###,###.00");
        tPpyMonny = formatter.format(payMoney);
        tvPpyMonny.setText(tPpyMonny);
    }
}
