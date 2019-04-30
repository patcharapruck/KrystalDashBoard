package dashboard.android.hdw.com.krystaldashboard.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.view.basecustom.BaseCustomViewGroup;

public class CustomViewBill extends BaseCustomViewGroup {

    TextView TextViewDeacriptionBill,TextViewQtyBill,TextViewAmountBill
            ,TextViewDiscountBill,TextViewPrice;

    public CustomViewBill(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public CustomViewBill(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public CustomViewBill(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(28)
    public CustomViewBill(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    }

    private void initInstances() {

        TextViewDeacriptionBill = (TextView) findViewById(R.id.textview_deacription_bill);
        TextViewQtyBill = (TextView) findViewById(R.id.textview_qty_bill);
        TextViewAmountBill = (TextView) findViewById(R.id.textview_amount_bill);
        TextViewDiscountBill = (TextView) findViewById(R.id.textview_discount_bill);
        TextViewPrice = (TextView) findViewById(R.id.textview_price);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.customview_bill, this);
    }


    public void setDeacriptionBill(String type){
        TextViewDeacriptionBill.setText(type);
    }
    public void setQtyBill(Long type){
        TextViewQtyBill.setText(type.toString());
    }
    public void setAmountBill(String type){
        TextViewAmountBill.setText(type);
    }
    public void setDiscountBill(String type){
        TextViewDiscountBill.setText(type);
    }
    public void setPrice(String type){
        TextViewPrice.setText(type);
    }

}
