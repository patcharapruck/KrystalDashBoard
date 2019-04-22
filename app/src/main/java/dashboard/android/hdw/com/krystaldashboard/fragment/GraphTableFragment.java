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
import java.util.ArrayList;

import dashboard.android.hdw.com.krystaldashboard.R;

public class GraphTableFragment extends Fragment {


    PieChart pieChart;

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
        pieChart = (PieChart) rootView.findViewById(R.id.chart1);
        moveOffScreen();
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setRotationEnabled(false);

        pieChart.setMaxAngle(180);
        pieChart.setRotationAngle(180);
        pieChart.setCenterTextOffset(0, -20);
        setData(2, 100);

        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setEntryLabelTextSize(0f);
    }

    String[] countries = new String[]{"โต๊ะที่ชำระแล้ว", "โต๊ะที่ยังไม่ชำระ"};

    private void setData(int count, int range) {
        ArrayList<PieEntry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int val = (int) ((Math.random() * range) + range / 5);
            values.add(new PieEntry(val, countries[i]));
        }

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
