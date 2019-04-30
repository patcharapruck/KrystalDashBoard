package dashboard.android.hdw.com.krystaldashboard.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus.NotPayItemColleationDto;
import dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus.PayItemColleationDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;
import dashboard.android.hdw.com.krystaldashboard.manager.http.HttpManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.NotPayManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.PayManager;
import dashboard.android.hdw.com.krystaldashboard.util.sharedprefmanager.SharedPrefDateManager;
import dashboard.android.hdw.com.krystaldashboard.util.sharedprefmanager.SharedPrefDatePayManager;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GraphTableFragment extends Fragment {


    PieChart pieChart;

    NotPayItemColleationDto Notdto;
    PayItemColleationDto Paydto;

    DecimalFormat formatter;

    String[] countries ;

    TextView TextViewPay,TextViewNotPay;

    public GraphTableFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_graph_table, container, false);
        reqAPInotpay(SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getKeyDatePay());
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {

        pieChart = (PieChart) rootView.findViewById(R.id.chart1);
        TextViewPay = (TextView) rootView.findViewById(R.id.text_pay);
        TextViewNotPay = (TextView) rootView.findViewById(R.id.text_notpay);
    }


    private void setGraphdata() {

        formatter = new DecimalFormat("#,###,##0.00");
        Double sum=0.0;
        Double sum2=0.0;

        moveOffScreen();
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setRotationEnabled(false);

        pieChart.setMaxAngle(180);
        pieChart.setRotationAngle(180);
        pieChart.setCenterTextOffset(0, -20);
        setData(sum, sum2);

        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setEntryLabelTextSize(0f);
    }


    private void setData(Double count, Double range) {
        ArrayList<PieEntry> values = new ArrayList<>();

        countries = new String[]{formatter.format(count), formatter.format(range)};
        double sum = count+range;

        int pay=0,not=0;

        try{
            pay = Paydto.getObject().size();
        }catch (Exception e){
            pay = 0;
        }

        try{
            not = Notdto.getObject().size();
        }catch (Exception e){
            not = 0;
        }


            values.add(new PieEntry(pay, countries[0]));
            values.add(new PieEntry(not, countries[1]));

            TextViewPay.setText(String.valueOf(pay));
            TextViewNotPay.setText(String.valueOf(not));

        PieDataSet dataSet = new PieDataSet(values, "");
        dataSet.setSelectionShift(5f);
        dataSet.setSliceSpace(3f);
        dataSet.setColors(new int[]{Color.parseColor("#007AFF"),
                Color.parseColor("#C4183B")});

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(0f);
        data.setValueTextColor(Color.WHITE);
        pieChart.setBackgroundColor(Color.WHITE);
        pieChart.setData(data);
        pieChart.invalidate();
    }

    private void moveOffScreen() {

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;
        int offset = (int) (height * 0.3);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) pieChart.getLayoutParams();
        params.setMargins(0, 0, 0, -offset);
        pieChart.setLayoutParams(params);
    }

    private void reqAPInotpay(String date) {
        String nn = "{\"criteria\":{\"sql-obj-command\":\"f:documentStatus.id = 22 and " +
                "(f:salesShift.openDate >= '"+date+" 00:00:00' AND f:salesShift.openDate <= '"+date+" 23:59:59')\"}," +
                "\"property\":[\"memberAccount->customerMemberAccount\",\"sales->employee\",\"place\",\"transactionPaymentList\",\"documentStatus\",\"salesShift\"]," +
                "\"pagination\":{},\"orderBy\":{\"InvoiceDocument-id\":\"DESC\"}}";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),nn);
        Call<NotPayItemColleationDto> call = HttpManager.getInstance().getService().loadAPINotPay(requestBody);
        call.enqueue(new Callback<NotPayItemColleationDto>() {
            @Override
            public void onResponse(Call<NotPayItemColleationDto> call, Response<NotPayItemColleationDto> response) {
                if(response.isSuccessful()){
                    Notdto = response.body();
                    reqAPIpay(SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getKeyDatePay());
                }else {
                    Toast.makeText(getContext(),"เกิดข้อผิดพลาด",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<NotPayItemColleationDto> call, Throwable t) {
                Toast.makeText(getContext(),"ไม่สามารถเชื่อมต่อได้",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void reqAPIpay(String date) {
        String nn = "{\"criteria\":{\"sql-obj-command\":\"f:documentStatus.id = 21 and " +
                "(f:salesShift.openDate >= '"+date+" 00:00:00' AND f:salesShift.openDate <= '"+date+" 23:59:59')\"}," +
                "\"property\":[\"memberAccount->customerMemberAccount\",\"sales->employee\",\"place\",\"transactionPaymentList\",\"documentStatus\",\"salesShift\"]," +
                "\"pagination\":{},\"orderBy\":{\"InvoiceDocument-id\":\"DESC\"}}";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),nn);
        Call<PayItemColleationDto> call = HttpManager.getInstance().getService().loadAPIPay(requestBody);
        call.enqueue(new Callback<PayItemColleationDto>() {
            @Override
            public void onResponse(Call<PayItemColleationDto> call, Response<PayItemColleationDto> response) {
                if(response.isSuccessful()){
                    Paydto = response.body();
                    setGraphdata();
                }else {
                    Toast.makeText(getContext(),"เกิดข้อผิดพลาด",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<PayItemColleationDto> call, Throwable t) {
                Toast.makeText(getContext(),"ไม่สามารถเชื่อมต่อได้",Toast.LENGTH_LONG).show();
            }
        });

    }


}
