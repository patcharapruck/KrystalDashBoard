package dashboard.android.hdw.com.krystaldashboard.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
import android.widget.TextView;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.adapter.BillItemAdater;
import dashboard.android.hdw.com.krystaldashboard.view.basecustom.BaseCustomViewGroup;

public class CustomViewBillType extends BaseCustomViewGroup {

    ListView listViewBill;
    BillItemAdater billItemAdater;

    String Type;
    TextView TextFood;

    public CustomViewBillType(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public CustomViewBillType(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public CustomViewBillType(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(28)
    public CustomViewBillType(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    }

    private void initInstances() {
        listViewBill = (ListView) findViewById(R.id.listview);
        TextFood = (TextView) findViewById(R.id.textfood);

        billItemAdater = new BillItemAdater();
        billItemAdater.setType(Type);
        listViewBill.setAdapter(billItemAdater);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.customview_bill_type, this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    public void setTypeBill(String type){
        TextFood.setText(type);
    }




}