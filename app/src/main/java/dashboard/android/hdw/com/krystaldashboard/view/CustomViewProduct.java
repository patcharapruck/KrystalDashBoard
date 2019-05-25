package dashboard.android.hdw.com.krystaldashboard.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.view.basecustom.BaseCustomViewGroup;
import dashboard.android.hdw.com.krystaldashboard.view.basecustom.BundleSavedState;

public class CustomViewProduct extends BaseCustomViewGroup {

    TextView TextViewProductName,TextViewProductTotal,TextViewProductPurchase
            ,TextViewProductBuy,TextViewProductEntertain;

    ImageView imageView;

    CardView cardView;
    ExpandableRelativeLayout mycontent;

    public CustomViewProduct(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public CustomViewProduct(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public CustomViewProduct(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(28)
    public CustomViewProduct(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);

    }
    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    }

    private void initInflate() {
        inflate(getContext(), R.layout.customview_drink_0, this);
    }

    private void initInstances() {

        TextViewProductName = (TextView) findViewById(R.id.textview_product_name);
        TextViewProductTotal = (TextView) findViewById(R.id.textview_product_total);
        TextViewProductPurchase = (TextView) findViewById(R.id.textview_product_purchase);
        TextViewProductBuy = (TextView) findViewById(R.id.textview_product_buy);
        TextViewProductEntertain = (TextView) findViewById(R.id.textview_product_entertain);

        imageView = (ImageView) findViewById(R.id.imageview_product);
        cardView = (CardView) findViewById(R.id.carddrink);
        mycontent = (ExpandableRelativeLayout)findViewById(R.id.mycontent);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mycontent.toggle();
            }
        });
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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height =  Integer.MAX_VALUE >> 2;
        int expandSpec = MeasureSpec.makeMeasureSpec(height,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
//        setMeasuredDimension(width,height);
    }

    public void setNameProduct(int count,String nameProduct){
        TextViewProductName.setText(count+"."+nameProduct);
    }

    public void setTotalProduct(Long totalProduct){
        TextViewProductTotal.setText(totalProduct.toString());
    }

    public void setWithdrawProduct(Long withdrawProduct){
        TextViewProductBuy.setText(withdrawProduct.toString());
    }

    public void setPurchaseProduct(Long purchaseProduct){
        TextViewProductPurchase.setText(purchaseProduct.toString());
    }

    public void setEntertainProduct(Long entertainProduct){
        TextViewProductEntertain.setText(entertainProduct.toString());
    }
    public void setImageProduct(String url){
        Glide.with(getContext())
                .load(url)
                .into(imageView);
    }

}
