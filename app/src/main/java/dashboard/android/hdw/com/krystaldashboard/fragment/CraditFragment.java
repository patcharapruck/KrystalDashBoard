package dashboard.android.hdw.com.krystaldashboard.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.dto.credit.CreditItemColleationDto;
import dashboard.android.hdw.com.krystaldashboard.fragment.dialogfragment.DialogCraditFragment;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.DashBoradManager;
import dashboard.android.hdw.com.krystaldashboard.util.MyFormatCredit;


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


        try {
            setdata();
        }catch (Exception e){
            Toast.makeText(Contextor.getInstance().getmContext(),"ไม่มีข้อมูลที่จะแสดงผล",Toast.LENGTH_SHORT).show();
        }

    }

    private void setdata() {

        CreditItemColleationDto   B1 = DashBoradManager.getInstance().getDao().getObject().getIncomeByCreditCardList().get(0);
        CreditItemColleationDto   B2 = DashBoradManager.getInstance().getDao().getObject().getIncomeByCreditCardList().get(1);

        creditall = DashBoradManager.getInstance().getDao().getObject().getCreditCardPayments();

        amaxt = B1.getAmax();
        amaxk = B2.getAmax();

        jcbt = B1.getJcb();
        jcbk = B2.getJcb();

        mastert = B1.getMaster();
        masterk = B2.getMaster();

        unipayt = B1.getUnipay();
        unipayk = B2.getUnipay();

        visat = B1.getVisa();
        visak = B2.getVisa();


        ArrayList<BarEntry> values = new ArrayList<>();
        ArrayList<BarEntry> values2 = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            float val1 = bar_B1().get(i);
            float val2 = bar_B2().get(i);
            values.add(new BarEntry(i, new float[]{val1}));
            values2.add(new BarEntry(i, new float[]{val2}));
        }


        BarDataSet set1;

        set1 = new BarDataSet(values,"Credit");
        set1.setColors(Color.parseColor("#001B7A"));
        set1.setValueTextColor(Color.BLACK);

        BarDataSet set2;

        set2 = new BarDataSet(values2,"Credit");
        set2.setColors(Color.parseColor("#f37023"));
        set2.setValueTextColor(Color.BLACK);


        BarData data = new BarData(set1,set2);
        data.setValueFormatter(new MyFormatCredit());
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

    private ArrayList<Float> bar_B1() {
        ArrayList<Float> barBnk1 = new ArrayList<>();
        barBnk1.add(0, (float) ((amaxt/creditall)*100));
        barBnk1.add(1, (float) ((jcbt/creditall)*100));
        barBnk1.add(2, (float) ((mastert/creditall)*100));
        barBnk1.add(3, (float) ((unipayt/creditall)*100));
        barBnk1.add(4, (float) ((visat/creditall)*100));

        return barBnk1;
    }

    //BBL(ธนาคารกรุงเทพ)
    private ArrayList<Float> bar_B2() {
        ArrayList<Float> barBnk2 = new ArrayList<>();

        barBnk2.add(0, (float) ((amaxk/creditall)*100));
        barBnk2.add(1, (float) ((jcbk/creditall)*100));
        barBnk2.add(2, (float) ((masterk/creditall)*100));
        barBnk2.add(3, (float) ((unipayk/creditall)*100));
        barBnk2.add(4, (float) ((visak/creditall)*100));

        return barBnk2;
    }

    @Override
    public void onClick(View v) {

        if(v == creditA){
            DialogCraditFragment dialogCraditFragment = new DialogCraditFragment();
            dialogCraditFragment.show(getFragmentManager(),"DialogCraditFragment");
        }

        if(v == creditB){
            DialogCraditFragment dialogCraditFragment = new DialogCraditFragment();
            dialogCraditFragment.show(getFragmentManager(),"DialogCraditFragment");
        }
    }
}
