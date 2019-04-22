package dashboard.android.hdw.com.krystaldashboard.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
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
    TextView TotalSum, TotalKungthap, TotalTanachat, TotalTthaipanich, TotalKhunoot;
    Double creditall = 0.0, amax = 0.0, jcb = 0.0, master = 0.0, unipay = 0.0, visa = 0.0;

    String Kungthap, Tanachat, Tthaipanich, Khunoot;

    ArrayList<Double> total = new ArrayList<>();

    ArrayList<String> creditAll = new ArrayList<>();
    ArrayList<String> Amax = new ArrayList<>();
    ArrayList<String> Jcb = new ArrayList<>();
    ArrayList<String> Master = new ArrayList<>();
    ArrayList<String> Unipay = new ArrayList<>();
    ArrayList<String> Visa = new ArrayList<>();
    ArrayList<String> Total = new ArrayList<>();


    LinearLayout creditA, creditB, creditC, creditD;

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
        creditC = (LinearLayout) rootView.findViewById(R.id.creditC);
        creditD = (LinearLayout) rootView.findViewById(R.id.creditD);

        creditA.setOnClickListener(this);
        creditB.setOnClickListener(this);
        creditC.setOnClickListener(this);
        creditD.setOnClickListener(this);

        barChart = rootView.findViewById(R.id.barchart);

        formatter = new DecimalFormat("#,###,##0.00");

        TotalSum = (TextView) rootView.findViewById(R.id.textview_sum);
        TotalKungthap = (TextView) rootView.findViewById(R.id.textview_kungthap);
        TotalTthaipanich = (TextView) rootView.findViewById(R.id.textview_thaipanich);
        TotalTanachat = (TextView) rootView.findViewById(R.id.textview_tanachat);
        TotalKhunoot = (TextView) rootView.findViewById(R.id.textview_khunoot);


        try {
            setdata();
        } catch (Exception e) {
            Toast.makeText(Contextor.getInstance().getmContext(), "ไม่มีข้อมูลที่จะแสดงผล", Toast.LENGTH_SHORT).show();
        }

    }

    private void setdata() {


        //creditall = DashBoradManager.getInstance().getDto().getObject().getCreditCardPayments();

        for (int i = 0; true; i++) {
            CreditItemColleationDto Credit;
            try {
                Credit = DashBoradManager.getInstance().getDto()
                        .getObject().getIncomeByCreditCardList().get(i);
            } catch (Exception e) {
                break;
            }

            amax = Credit.getAmax();
            jcb = Credit.getJcb();
            master = Credit.getMaster();
            unipay = Credit.getUnipay();
            visa = Credit.getVisa();

            total.add(i, amax + jcb + master + unipay + visa);
            creditall = creditall + total.get(i);
            Total.add(i, formatter.format(total.get(i)));

            if (Credit.getBank().getBankName().equals("ธนาคารธนชาต (T-BANK)")) {
                Tanachat = Total.get(i);
                TotalTanachat.setText(Tanachat);
            } else if (Credit.getBank().getBankName().equals("ธนาคารกรุงเทพ (BBL)")) {
                Kungthap = Total.get(i);
                TotalKungthap.setText(Kungthap);
            } else if (Credit.getBank().getBankName().equals("ธนาคารไทยพาณิชย์ (SCB)")) {
                Tthaipanich = Total.get(i);
                TotalTthaipanich.setText(Tthaipanich);
            } else if (Credit.getBank().getBankName().equals("บัญชีคุณอ๊อด")) {
                Khunoot = Total.get(i);
                TotalKhunoot.setText(Khunoot);
            }
        }


        TotalSum.setText(formatter.format(creditall) + " บาท");


//        TotalSum.setText(totalts);
//        TotalSum.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
//
//        TotalKTextView.setText(totalks);
//        TotalKTextView.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = 0; true; i++) {

            try {
                values.add(new BarEntry(i, total.get(i).floatValue()));
            } catch (Exception e) {
                break;
            }

        }

        BarDataSet set1;
        set1 = new BarDataSet(values, "Credit");
        set1.setColors(new int[]{Color.parseColor("#204298"),
                Color.parseColor("#F37637"),
                Color.parseColor("#502984"),
                Color.parseColor("#19237E")});
        set1.setValueTextColor(Color.BLACK);
        BarData data = new BarData(set1);
        data.setValueFormatter(new MyFormatCredit());
        barChart.setData(data);

        String[] creditName = new String[]{"กรุงเทพ", "ธนชาต", "ไทยพาณิชย์", "คุณอ๊อต"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(creditName));
        xAxis.setCenterAxisLabels(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);

        barChart.setDragEnabled(false);

        barChart.setVisibleXRangeMaximum(4);
        data.setBarWidth(0.5f);

        YAxis yAxis = barChart.getAxisLeft();
        YAxis leftAxis = barChart.getAxisLeft();
//        leftAxis.setTypeface(tfLight);
        leftAxis.setLabelCount(8, false);
        leftAxis.setTextColor(Color.parseColor("#4D4D4D"));
//        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

//        // Hide grid lines
        barChart.getAxisLeft().setEnabled(true);
        barChart.getAxisRight().setEnabled(false);
//        // Hide graph description
        barChart.getDescription().setEnabled(false);
//        // Hide graph legend
        barChart.getLegend().setEnabled(false);
        barChart.setDrawValueAboveBar(true);
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

        if (v == creditA) {
            DialogCraditFragment dialogCraditFragment = new DialogCraditFragment();
            dialogCraditFragment.setNameCredit("ธนาคารกรุงเทพ (BBL)", Kungthap);
            dialogCraditFragment.show(getFragmentManager(), "DialogCraditFragment");
        }

        if (v == creditB) {
            DialogCraditFragment dialogCraditFragment = new DialogCraditFragment();
            dialogCraditFragment.setNameCredit("ธนาคารธนชาต (T-BANK)", Tanachat);
            dialogCraditFragment.show(getFragmentManager(), "DialogCraditFragment");
        }

        if (v == creditC) {
            DialogCraditFragment dialogCraditFragment = new DialogCraditFragment();
            dialogCraditFragment.setNameCredit("ธนาคารไทยพาณิชย์ (SCB)", Tthaipanich);
            dialogCraditFragment.show(getFragmentManager(), "DialogCraditFragment");
        }

        if (v == creditD) {
            DialogCraditFragment dialogCraditFragment = new DialogCraditFragment();
            dialogCraditFragment.setNameCredit("บัญชีคุณอ๊อด", Khunoot);
            dialogCraditFragment.show(getFragmentManager(), "DialogCraditFragment");
        }
    }
}
