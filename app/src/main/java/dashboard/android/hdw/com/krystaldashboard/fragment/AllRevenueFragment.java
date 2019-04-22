package dashboard.android.hdw.com.krystaldashboard.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import dashboard.android.hdw.com.krystaldashboard.R;

public class AllRevenueFragment extends Fragment {

    private LineChart chart;

    public AllRevenueFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_allrevenue,container,false);
        initInstances(rootView);
        return rootView;

    }

    private void initInstances(View rootView) {
        chart = rootView.findViewById(R.id.linechart);
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
        setData(20, 10000000);
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

    private void setData(int count, int range) {

        ArrayList<Entry> values = new ArrayList<>();
        ArrayList<Entry> values1 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * (range + 1)) + 10000000;
            values.add(new Entry(i, val));
        }

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * (range + 1)) + 10000000;
            values1.add(new Entry(i, val));
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
}
