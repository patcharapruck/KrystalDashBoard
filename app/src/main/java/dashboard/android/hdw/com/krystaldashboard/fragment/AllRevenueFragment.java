package dashboard.android.hdw.com.krystaldashboard.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.dto.CompareCollectionDto;
import dashboard.android.hdw.com.krystaldashboard.dto.DashBoardDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;
import dashboard.android.hdw.com.krystaldashboard.manager.http.HttpManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.CompareManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.DashBoradManager;
import dashboard.android.hdw.com.krystaldashboard.util.sharedprefmanager.SharedPrefDateManager;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllRevenueFragment extends Fragment {

    private LineChart chart;

    TextView Revrnus,Income,Unpaid;
    DecimalFormat formatter;
    ArrayList<Float> revenue;
    int size;

    CompareCollectionDto dto;
    DashBoardDto dtomain;
    public AllRevenueFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_allrevenue,container,false);
        teqAPICompare(SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getKeyDatePay()
                ,SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getKey7Date());
        reqAPI(SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getreqDate());
        initInstances(rootView);
        return rootView;

    }

    private void initInstances(View rootView) {

        chart = rootView.findViewById(R.id.linechart);
        Revrnus = (TextView) rootView.findViewById(R.id.revrnus);
        Income = (TextView) rootView.findViewById(R.id.income);
        Unpaid = (TextView) rootView.findViewById(R.id.unpaid);

    }

    private void ChartCompare() {


        chart.setViewPortOffsets(0, 0, 0, 0);
        chart.setBackgroundColor(Color.WHITE);

        // no description text
        chart.getDescription().setEnabled(false);
        // enable touch gestures
        chart.setTouchEnabled(true);
        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(false);
        chart.setMaxHighlightDistance(300);
        setData(size);
        chart.getAxisRight().setEnabled(true);
        chart.getLegend().setEnabled(false);
//        chart.animateXY(2000, 2000);
        // don't forget to refresh the drawing

        XAxis x = chart.getXAxis();
        x.setEnabled(false);

        YAxis y = chart.getAxisLeft();
//        y.setTypeface(tfLight);
        y.setLabelCount(6, false);
        y.setTextColor(Color.parseColor("#4d4d4d"));
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.WHITE);
//        y.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        y.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        chart.invalidate();
    }

    private void setData(int count) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = revenue.get(i);
            values.add(new Entry(i, val));
        }

        LineDataSet set1;


        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");
            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setDrawFilled(true);
            set1.setDrawCircles(true);
            set1.setLineWidth(2f);
            set1.setCircleRadius(4f);
            set1.setCircleColor(Color.parseColor("#B35FF0"));//point on line
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setColor(Color.parseColor("#B35FF0"));//line
            set1.setFillColor(Color.parseColor("#E5CCF9"));
            set1.setFillAlpha(100);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });
            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                Drawable col = ContextCompat.getDrawable(getContext(),R.drawable.fade_blue);
                set1.setFillDrawable(col);
            } else {
                set1.setFillColor(Color.BLACK);
            }

            if (chart.getData() != null &&
                    chart.getData().getDataSetCount() > 0) {
                chart.getData().notifyDataChanged();
                chart.notifyDataSetChanged();
            } else {
                // create a dataset and give it a type

            }
            // create a data object with the data sets
            LineData data = new LineData(set1);
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            chart.setData(data);
        }
    }

    private float valueCreditPayments(int i) {
        float CreditPayments;
        try {
            CreditPayments = dto.getObject().get(i).getCreditPayments();
        } catch (NullPointerException e) {
            return 0f;
        }
        return CreditPayments;
    }

    private float valueCreditCardPayments(int i) {
        float CreditCardPayments;
        try {
            CreditCardPayments = dto.getObject().get(i).getCreditCardPayments();
        } catch (NullPointerException e) {
            return 0f;
        }
        return CreditCardPayments;
    }

    private float valueCashPayments(int i) {
        float CashPayments;
        try {
            CashPayments = dto.getObject().get(i).getCashPayments();
        } catch (NullPointerException e) {
            return 0f;
        }
        return CashPayments;
    }

    private void teqAPICompare(String s, String key7Date) {
        String nn = "{\"property\":[],\"criteria\":{\"opening\":false,\"sql-obj-command\":\"( tb_sales_shift.open_date >= '"+key7Date+" 00:00:00' AND tb_sales_shift.open_date <= '"+s+" 23:59:59')\"},\"orderBy\":{},\"pagination\":{}}";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),nn);
        Call<CompareCollectionDto> call = HttpManager.getInstance().getService().loadAPIcompare(requestBody);
        call.enqueue(new Callback<CompareCollectionDto>() {

            @Override
            public void onResponse(Call<CompareCollectionDto> call, Response<CompareCollectionDto> response) {
                if(response.isSuccessful()){
                     dto = response.body();
                     size = dto.getObject().size();
                     revenue = new ArrayList<Float>(size);
                    for(int i=0;i<size;i++){
                        float Cash = valueCashPayments(i);
                        float Credit = valueCreditCardPayments(i);
                        float CardCreddit = valueCreditPayments(i);
                        float Sum = Cash + Credit + CardCreddit;
                        revenue.add(Sum);
                    }
                    ChartCompare();
                }else {
                    try {
                        Toast.makeText(getContext(),response.errorBody().string(),Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<CompareCollectionDto> call, Throwable t) {
                Toast.makeText(getContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void reqAPI(String date) {
        String nn = "{\"property\":[],\"criteria\":{\"sql-obj-command\":\"( tb_sales_shift.open_date >= '"+date+" 00:00:00' AND tb_sales_shift.open_date <= '"+date+" 23:59:59')\",\"summary-date\":\"*\"},\"orderBy\":{\"InvoiceDocument-id\":\"desc\"},\"pagination\":{}}";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),nn);
        Call<DashBoardDto> call = HttpManager.getInstance().getService().loadAPI(requestBody);
        call.enqueue(new Callback<DashBoardDto>() {
            @Override
            public void onResponse(Call<DashBoardDto> call, Response<DashBoardDto> response) {
                if(response.isSuccessful()){
                    dtomain = response.body();

                    setTextdata();
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

    private void setTextdata() {

        formatter = new DecimalFormat("#,###,##0.00");

        Revrnus.setText(formatter.format(dtomain.getObject().getRevenue()));
        Income.setText(formatter.format(dtomain.getObject().getIncome()));
        Unpaid.setText(formatter.format(dtomain.getObject().getUnpaid()));
    }
}
