package dashboard.android.hdw.com.krystaldashboard.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.text.DecimalFormat;
import java.util.ArrayList;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.dto.credit.CreditItemColleationDto;
import dashboard.android.hdw.com.krystaldashboard.fragment.dialogfragment.DialogCraditFragment;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.DashBoradManager;
import dashboard.android.hdw.com.krystaldashboard.util.MyFormatCredit;


public class CraditFragment extends Fragment implements View.OnClickListener {


    BarChart barChart;
    TextView TotalSum,TotalKungthap,TotalTanachat,TotalTthaipanich,TotalKhunoot;
    Double creditall = 0.0, amax = 0.0, jcb = 0.0, master = 0.0, unipay = 0.0, visa = 0.0;

    ArrayList<Double> total = new ArrayList<>();

    ArrayList<String> creditAll = new ArrayList<>();
    ArrayList<String> Amax = new ArrayList<>();
    ArrayList<String> Jcb = new ArrayList<>();
    ArrayList<String> Master = new ArrayList<>();
    ArrayList<String> Unipay = new ArrayList<>();
    ArrayList<String> Visa = new ArrayList<>();
    ArrayList<String> Total = new ArrayList<>();


    LinearLayout creditA,creditB;
    Button creditABtn,creditBBtn;

    DecimalFormat formatter;

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

        formatter = new DecimalFormat("#,###,##0.00");

        TotalSum = (TextView) rootView.findViewById(R.id.textview_sum);
        TotalKungthap = (TextView) rootView.findViewById(R.id.textview_kungthap);
        TotalTthaipanich = (TextView) rootView.findViewById(R.id.textview_thaipanich);
        TotalTanachat = (TextView) rootView.findViewById(R.id.textview_tanachat);
        TotalKhunoot = (TextView) rootView.findViewById(R.id.textview_khunoot);



        try {
            setdata();
        }catch (Exception e){
            Toast.makeText(Contextor.getInstance().getmContext(),"ไม่มีข้อมูลที่จะแสดงผล",Toast.LENGTH_SHORT).show();
        }

    }

    private void setdata() {


        //creditall = DashBoradManager.getInstance().getDao().getObject().getCreditCardPayments();

        for(int i=0;true;i++){
            CreditItemColleationDto  Credit;
            try{
              Credit = DashBoradManager.getInstance().getDao()
                        .getObject().getIncomeByCreditCardList().get(i);
            }catch (Exception e){
                break;
            }

            amax = Credit.getAmax();
            jcb = Credit.getJcb();
            master = Credit.getMaster();
            unipay = Credit.getUnipay();
            visa = Credit.getVisa();

            total.add(i,amax + jcb + master + unipay + visa);
            creditall = creditall+total.get(i);
            Total.add(i,formatter.format(total.get(i)));
        }


        TotalSum.setText(formatter.format(creditall));
        TotalKungthap.setText(Total.get(1));
        TotalTanachat.setText(Total.get(0));
        TotalTthaipanich.setText(Total.get(2));
        TotalKhunoot.setText(Total.get(3));

//        TotalSum.setText(totalts);
//        TotalSum.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
//
//        TotalKTextView.setText(totalks);
//        TotalKTextView.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = 0; true ; i++) {

            try {
                values.add(new BarEntry(i,total.get(i).floatValue()));
            }catch (Exception e){
                break;
            }

        }

        BarDataSet set1;
        set1 = new BarDataSet(values,"Credit");
        set1.setColors(Color.parseColor("#001B7A"));
        set1.setValueTextColor(Color.BLACK);


        BarData data = new BarData(set1);
        data.setValueFormatter(new MyFormatCredit());
        barChart.setData(data);

        String[] creditName = new String[]{"กรุงเทพ","ธนชาต","ไทยพาณิชย์","คุณอ๊อต"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(creditName));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);

        barChart.setDragEnabled(false);
        barChart.setVisibleXRangeMaximum(4);

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

//    private ArrayList<Float> bar_B1() {
//        ArrayList<Float> barBnk1 = new ArrayList<>();
//        barBnk1.add(0, (float) ((amax /creditall)*100));
//        barBnk1.add(1, (float) ((jcb /creditall)*100));
//        barBnk1.add(2, (float) ((master /creditall)*100));
//        barBnk1.add(3, (float) ((unipay /creditall)*100));
//        barBnk1.add(4, (float) ((visa /creditall)*100));
//
//        return barBnk1;
//    }
//
//    //BBL(ธนาคารกรุงเทพ)
//    private ArrayList<Float> bar_B2() {
//        ArrayList<Float> barBnk2 = new ArrayList<>();
//
//        barBnk2.add(0, (float) ((amaxk/creditall)*100));
//        barBnk2.add(1, (float) ((jcbk/creditall)*100));
//        barBnk2.add(2, (float) ((masterk/creditall)*100));
//        barBnk2.add(3, (float) ((unipayk/creditall)*100));
//        barBnk2.add(4, (float) ((visak/creditall)*100));
//
//        return barBnk2;
//    }

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
