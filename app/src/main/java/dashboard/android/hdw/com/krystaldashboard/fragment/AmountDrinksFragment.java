package dashboard.android.hdw.com.krystaldashboard.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.dto.DashBoardDto;
import dashboard.android.hdw.com.krystaldashboard.dto.ObjectItemDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;
import dashboard.android.hdw.com.krystaldashboard.manager.http.HttpManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.DashBoradManager;
import dashboard.android.hdw.com.krystaldashboard.util.sharedprefmanager.SharedPrefDateManager;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AmountDrinksFragment extends Fragment {

    TextView totalAmountDrinks,buyAmountDrinks,entertainAmountDrinks,purchaseAmountDrinks;

    ArrayList<Long> totalAllProduct;
    ArrayList<Long> entertainProduct;
    ArrayList<Long> purchaseProduct;
    ArrayList<Long> buyProduct;

    DashBoardDto dto;
    ObjectItemDto Odto;
    int size;

    public AmountDrinksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_amount_drinks, container, false);
        reqAPI(SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getreqDate());
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {

        totalAmountDrinks = (TextView) rootView.findViewById(R.id.textview_total_amount_drinks);
        buyAmountDrinks = (TextView) rootView.findViewById(R.id.textview_buy_amount_drinks);
        entertainAmountDrinks = (TextView) rootView.findViewById(R.id.textview_entertain_amount_drinks);
        purchaseAmountDrinks = (TextView) rootView.findViewById(R.id.textview_purchase_amount_drinks);

    }

    private void setViewDrink() {

        Odto = dto.getObject();


        try {
            size = Odto.getSummaryUseProductList().size();
        }catch (Exception e){
            Toast.makeText(Contextor.getInstance().getmContext(),"ไม่มีข้อมูลที่จะแสดงผล",Toast.LENGTH_SHORT).show();
        }


        totalAllProduct = new ArrayList<>(size);
        entertainProduct = new ArrayList<>(size);
        purchaseProduct = new ArrayList<>(size);
        buyProduct = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {

            Long total = gettotalProduct(i);
            Long entertain = getEntertain(i);
            Long purchase = getPurchase(i);
            Long withdraw = getWithdraw(i);

            totalAllProduct.add(total);
            entertainProduct.add(entertain);
            purchaseProduct.add(purchase);
            buyProduct.add(withdraw);
        }

        Long sumtotal = 0L, sumentertain = 0L, sumpurchase = 0L, sumwithdraw = 0L;
        for (int i = 0; i < size; i++) {
            sumtotal = sumtotal + totalAllProduct.get(i);
            sumentertain = sumentertain + entertainProduct.get(i);
            sumpurchase = sumpurchase + purchaseProduct.get(i);
            sumwithdraw = sumwithdraw + buyProduct.get(i);
        }

        totalAmountDrinks.setText(sumtotal.toString());
        if (sumtotal > 0) {
            totalAmountDrinks.setTextColor(Color.parseColor("#232323"));
        }
        entertainAmountDrinks.setText(sumentertain.toString());
        if (sumentertain > 0) {
            entertainAmountDrinks.setTextColor(Color.parseColor("#232323"));
        }
        purchaseAmountDrinks.setText(sumwithdraw.toString());
        if (sumpurchase > 0) {
            purchaseAmountDrinks.setTextColor(Color.parseColor("#232323"));
        }
        buyAmountDrinks.setText(sumpurchase.toString());
        if (sumwithdraw > 0) {
            buyAmountDrinks.setTextColor(Color.parseColor("#232323"));
        }
    }

    private Long getWithdraw(int i) {
        Long withdraw;
        try {
            withdraw = Odto.getSummaryUseProductList().get(i).getWithdrawUse();
        } catch (NullPointerException e) {
            return 0L;
        }
        return withdraw;
    }

    private Long getPurchase(int i) {
        Long purchase;
        try {
            purchase = Odto.getSummaryUseProductList().get(i).getPurchaseAmount();
        } catch (NullPointerException e) {
            return 0L;
        }
        return purchase;
    }

    private Long getEntertain(int i) {
        Long enter;
        try {
            enter = Odto.getSummaryUseProductList().get(i).getEntertainAmount();
        } catch (NullPointerException e) {
            return 0L;
        }
        return enter;
    }

    private Long gettotalProduct(int i) {
        Long total;
        try {
            total = Odto.getSummaryUseProductList().get(i).getTotalAll();
        } catch (NullPointerException e) {
            return 0L;
        }
        return total;
    }


    private void reqAPI(String date) {
        String nn = "{\"property\":[],\"criteria\":{\"sql-obj-command\":\"( tb_sales_shift.open_date >= '"+date+" 00:00:00' AND tb_sales_shift.open_date <= '"+date+" 23:59:59')\",\"summary-date\":\"*\"},\"orderBy\":{\"InvoiceDocument-id\":\"desc\"},\"pagination\":{}}";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),nn);
        Call<DashBoardDto> call = HttpManager.getInstance().getService().loadAPI(requestBody);
        call.enqueue(new Callback<DashBoardDto>() {
            @Override
            public void onResponse(Call<DashBoardDto> call, Response<DashBoardDto> response) {
                if(response.isSuccessful()){
                    dto = response.body();
                    setViewDrink();
                }
                else {
                    Toast.makeText(getContext(),"เกิดข้อผิดพลาด",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<DashBoardDto> call, Throwable t) {
                Toast.makeText(getContext(),"ไม่สามารถเชื่อมต่อกับข้อมูลได้",Toast.LENGTH_LONG).show();
            }
        });
    }

}
