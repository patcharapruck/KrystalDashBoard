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
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.NotPayManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.PayManager;

public class GraphTableFragment extends Fragment {


    PieChart pieChart;

    NotPayItemColleationDto Notdto;
    PayItemColleationDto Paydto;

    DecimalFormat formatter;

    public GraphTableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_graph_table, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {

        formatter = new DecimalFormat("#,###,##0.00");

        Notdto = NotPayManager.getInstance().getNotpayItemColleationDao();
        Paydto = PayManager.getInstance().getPayItemColleationDao();


        Double sum=0.0;

//        for (int i=0;i<Paydto.getObject().size();i++){
//            sum = sum + Paydto.getObject().get(i).getTotalPrice();
//        }
//
        Double sum2=0.0;
//
//        for (int i=0;i<Notdto.getObject().size();i++){
//            sum2 = sum2 + Notdto.getObject().get(i).getTotalPrice();
//        }
        pieChart = (PieChart) rootView.findViewById(R.id.chart1);

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

    String[] countries ;

    private void setData(Double count, Double range) {
        ArrayList<PieEntry> values = new ArrayList<>();

        countries = new String[]{formatter.format(count), formatter.format(range)};

        double sum = count+range;

        int pg1,pg2;

        try {
            pg1 = (int) (sum/(sum+count))*100;
        }catch (Exception e){
            pg1 = 0;
        }

        try {
            pg2 = (int) (sum/(sum+range))*100;
        }catch (Exception e){
            pg2 = 0;
        }

            values.add(new PieEntry(pg1, countries[0]));
            values.add(new PieEntry(pg2, countries[1]));

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

}
