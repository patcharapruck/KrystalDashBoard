package dashboard.android.hdw.com.krystaldashboard.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.fragment.dialogfragment.DialogCraditFragment;


public class CraditFragment extends Fragment implements View.OnClickListener {


    BarChart barChart;

    Double creditall,
            amaxt = 0.0, amaxk = 0.0,
            jcbt = 0.0, jcbk = 0.0,
            mastert = 0.0, masterk = 0.0,
            unipayt = 0.0, unipayk = 0.0,
            visat = 0.0, visak = 0.0;

    String creditalls,
            amaxts, amaxks,
            jcbts, jcbks,
            masterts, masterks,
            unipayts, unipayks,
            visats, visaks;

    LinearLayout creditA,creditB;
    Button creditABtn,creditBBtn;

    public CraditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cradit, container, false);
        initInstances(rootView);
        return rootView;

    }

    private void initInstances(View rootView) {

        creditA = (LinearLayout) rootView.findViewById(R.id.creditA);
        creditB = (LinearLayout) rootView.findViewById(R.id.creditB);

        creditA.setOnClickListener(this);
        creditB.setOnClickListener(this);

        barChart = rootView.findViewById(R.id.barchart);

        amaxt = 10D;
        amaxk = 20D;

        jcbt = 0D;
        jcbk = 10D;

        mastert = 0D;
        masterk = 0D;

        unipayt = 0D;
        unipayk = 5D;

        visat = 30D;
        visak = 25D;


        BarDataSet barDataSet1 = new BarDataSet(bar_B1(), "ธนาคารธนชาต");
        barDataSet1.setColors(Color.rgb(243, 112, 35));
        BarDataSet barDataSet2 = new BarDataSet(bar_B2(), "ธนาคารกรุงเทพ");
        barDataSet2.setColors(Color.rgb(0, 28, 122));

        BarData data = new BarData(barDataSet1, barDataSet2);
        barChart.setData(data);

        String[] creditName = new String[]{"A-MAX ", " JCB ", "MASTER", "UNIPAY", "VISA"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(creditName));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);

        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(5);

        //set Label Center
        float barSpace = 0.05f;
        float groupSpace = 0.66f;
        data.setBarWidth(0.12f);
        //(barwidth + barspace) * no of bars + groupspace = 1

        barChart.getXAxis().setAxisMinimum(0);
        barChart.getXAxis().setAxisMaximum(0 + barChart.getBarData().getGroupWidth(groupSpace, barSpace) * 5);
        barChart.getAxisLeft().setAxisMinimum(0);
        barChart.groupBars(0, groupSpace, barSpace);


        // Hide grid lines
        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        // Hide graph description
        barChart.getDescription().setEnabled(false);
        // Hide graph legend
        barChart.getLegend().setEnabled(false);

        barChart.invalidate();
    }

    private ArrayList<BarEntry> bar_B1() {
        ArrayList<BarEntry> barBnk1 = new ArrayList<>();
        barBnk1.add(new BarEntry(1, amaxt.longValue()));
        barBnk1.add(new BarEntry(2, jcbt.longValue()));
        barBnk1.add(new BarEntry(3, mastert.longValue()));
        barBnk1.add(new BarEntry(4, unipayt.longValue()));
        barBnk1.add(new BarEntry(5, visat.longValue()));
        return barBnk1;
    }

    //BBL(ธนาคารกรุงเทพ)
    private ArrayList<BarEntry> bar_B2() {
        ArrayList<BarEntry> barBnk2 = new ArrayList<>();
        barBnk2.add(new BarEntry(1, amaxk.longValue()));
        barBnk2.add(new BarEntry(2, jcbk.longValue()));
        barBnk2.add(new BarEntry(3, masterk.longValue()));
        barBnk2.add(new BarEntry(4, unipayk.longValue()));
        barBnk2.add(new BarEntry(5, visak.longValue()));
        return barBnk2;
    }

    @Override
    public void onClick(View v) {

        if(v == creditA){
            DialogCraditFragment dialogCraditFragment = new DialogCraditFragment();
            dialogCraditFragment.show(getFragmentManager(),"DialogCraditFragment");
        }

        if(v == creditB){

        }
    }
}
