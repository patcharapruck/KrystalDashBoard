package dashboard.android.hdw.com.krystaldashboard.activty;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.dto.CompareCollectionDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;
import dashboard.android.hdw.com.krystaldashboard.manager.http.HttpManager;
import dashboard.android.hdw.com.krystaldashboard.util.Formatcompare;
import dashboard.android.hdw.com.krystaldashboard.util.sharedprefmanager.SharedPrefDateManager;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompareReceipts extends AppCompatActivity implements View.OnClickListener {

    LineChart lineChart;

    Button sp2, sp3,btncalCP;
    Toolbar toolbar;
    ArrayList<Float> income;
    ArrayList<Float> revenue;

    Date testdate1=null;
    Date testdate2=null;

    String datestart="",datestop="";

    int size;
    CompareCollectionDto Dto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adjustFontScale( getResources().getConfiguration());
        setContentView(R.layout.activity_compare_receipts);
        InitInstant();
    }

    private void teqAPICompare() {
        final Context mcontext = Contextor.getInstance().getmContext();
        String nn = "{\"property\":[],\"criteria\":{\"opening\":false,\"sql-obj-command\":\"( tb_sales_shift.open_date >= '"+datestart+" 00:00:00' AND tb_sales_shift.open_date <= '"+datestop+" 23:59:59')\"},\"orderBy\":{},\"pagination\":{}}";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),nn);
        Call<CompareCollectionDto> call = HttpManager.getInstance().getService().loadAPIcompare(requestBody);
        call.enqueue(new Callback<CompareCollectionDto>() {

            @Override
            public void onResponse(Call<CompareCollectionDto> call, Response<CompareCollectionDto> response) {
                if(response.isSuccessful()){
                    Dto = response.body();
                    setListData();
                }else {
                    try {
                        Toast.makeText(mcontext,response.errorBody().string(),Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<CompareCollectionDto> call, Throwable t) {
                Toast.makeText(mcontext,t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setListData() {

        this.size = Dto.getObject().size();
        income = new ArrayList<Float>(size);
        revenue = new ArrayList<Float>(size);

        for(int i=0;i<size;i++){

            float Cash = valueCashPayments(i);
            float Credit = valueCreditCardPayments(i);
            float CardCreddit = valueCreditPayments(i);
            float Sum = Cash + Credit + CardCreddit;

            income.add(valueTotal(i));
            revenue.add(Sum);
        }

        ChartCompare();
    }

    private float valueTotal(int i) {
        float Total;
        try {
            Total = Dto.getObject().get(i).getTotalIncome();
        } catch (NullPointerException e) {
            return 0f;
        }
        return Total;
    }

    private float valueCreditPayments(int i) {
        float CreditPayments;
        try {
            CreditPayments = Dto.getObject().get(i).getCreditPayments();
        } catch (NullPointerException e) {
            return 0f;
        }
        return CreditPayments;
    }

    private float valueCreditCardPayments(int i) {
        float CreditCardPayments;
        try {
            CreditCardPayments = Dto.getObject().get(i).getCreditCardPayments();
        } catch (NullPointerException e) {
            return 0f;
        }
        return CreditCardPayments;
    }

    private float valueCashPayments(int i) {
        float CashPayments;
        try {
            CashPayments = Dto.getObject().get(i).getCashPayments();
        } catch (NullPointerException e) {
            return 0f;
        }
        return CashPayments;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    //line chart
    private void ChartCompare(){

        lineChart = (LineChart) findViewById(R.id.lineChart);

        LineDataSet lineDataSet = new LineDataSet(dataValues1(),"รายรับจริง");
        lineDataSet.setColor(Color.parseColor("#ffa500"));
        lineDataSet.setCircleColor(Color.parseColor("#8b008b"));
        lineDataSet.setCircleRadius(4f);


        LineDataSet lineDataSet1 = new LineDataSet(dataValues2(),"รายรับตามบิล");
        lineDataSet1.setColor(Color.parseColor("#006400"));
        lineDataSet1.setCircleColor(Color.parseColor("#8b008b"));
        lineDataSet1.setCircleRadius(4f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        ArrayList<ILineDataSet> dataSets1 = new ArrayList<>();
        dataSets1.add(lineDataSet1);
        LineData data = new LineData(lineDataSet,lineDataSet1);

        data.setValueFormatter(new Formatcompare());
        data.setValueTextSize(12f);
        lineDataSet.setLineWidth(3f);
        lineDataSet1.setLineWidth(3f);
        lineChart.setData(data);
        lineChart.invalidate();
        lineChart.getAxisLeft().setEnabled(true);
        lineChart.getAxisRight().setDrawAxisLine(true);
        lineChart.getAxisRight().setDrawGridLines(false);
        lineChart.getXAxis().setDrawAxisLine(true);
        lineChart.getXAxis().setDrawGridLines(false);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);

//        YAxis lefttAxis = lineChart.getAxisLeft();
//        lefttAxis.setEnabled(false);

        Description description = new Description();
        description.setText("by HandyWings.co.,ltd");
        lineChart.setDescription(description);
    }

    private ArrayList<Entry> dataValues1(){
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        for(int i=0;i<size;i++){
            dataVals.add(new Entry(i,revenue.get(i)));
        }
        return dataVals;
    }

    private ArrayList<Entry> dataValues2(){
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        for(int i=0;i<size;i++){
            dataVals.add(new Entry(i,income.get(i)));
        }
        return dataVals;
    }

    private void InitInstant() {
        String date = SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getKeyDateFull();
        toolbar = findViewById(R.id.tbCompare);
        toolbar.setTitle("เปรียบเทียบรายรับ");
        toolbar.setSubtitle(date);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        toolbar.setSubtitleTextColor(Color.parseColor("#FFFFFF"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        btncalCP = (Button)findViewById(R.id.btncalCP);
        sp2 = findViewById(R.id.datestart);
        sp3 = findViewById(R.id.datestop);

        btncalCP.setOnClickListener(this);
        sp2.setOnClickListener(this);
        sp3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == btncalCP){

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            try {
                testdate1 = sdf.parse(datestart);
                testdate2 = sdf.parse(datestop);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(datestart.length()<=0||datestop.length()<=0){
                Toast.makeText(Contextor.getInstance().getmContext(),"กำหนดช่วงวันแสดงผล",Toast.LENGTH_LONG).show();
            }
            else if (datestart.equals(datestop)){
                Toast.makeText(Contextor.getInstance().getmContext(),"วันที่กำหนดตรงกัน",Toast.LENGTH_LONG).show();
            }
            else if (testdate1.after(testdate2)){
                Toast.makeText(Contextor.getInstance().getmContext(),"เกิดข้อผิดพลาดของรูปแบบวันที่",Toast.LENGTH_LONG).show();
            }
            else{
                try {
                    teqAPICompare();
                }catch (Exception e){
                    Toast.makeText(Contextor.getInstance().getmContext(),"เกิดข้อผิดพลาด",Toast.LENGTH_SHORT).show();
                }

            }

        }
        if(v==sp2){
            calendarsetA();
        }
        if(v == sp3){
            calendarsetB();
        }
    }

    private void calendarsetA() {
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        c.add(Calendar.DATE,-1);
        final int dayofmonth = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH)+1;
        int year = c.get(Calendar.YEAR);
        Date date = c.getTime();
        Date d = null;
        String oldDateString = "2019-01-06";

        final DatePickerDialog dialog = new DatePickerDialog(CompareReceipts.this,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                String mm = ""+month;
                String dd = ""+dayOfMonth;

                if (month<10){
                    mm = "0"+month;
                }
                if (dayOfMonth < 10){
                    dd = "0"+dayOfMonth;
                }

                datestart = year+ "-" + mm + "-" +dd;
                sp2.setText(datestart);

            }
        },year,month-1,dayofmonth);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            d = sdf.parse(oldDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dialog.getDatePicker().setMinDate(d.getTime());
        dialog.getDatePicker().setMaxDate(date.getTime());
        dialog.show();

    }

    private void calendarsetB() {
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        c.add(Calendar.DATE,-1);
        final int dayofmonth = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH)+1;
        int year = c.get(Calendar.YEAR);
        Date date = c.getTime();
        Date d = null;
        String oldDateString = "2019-01-06";

        final DatePickerDialog dialog = new DatePickerDialog(CompareReceipts.this,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                String mm = ""+month;
                String dd = ""+dayOfMonth;

                if (month<10){
                    mm = "0"+month;
                }
                if (dayOfMonth < 10){
                    dd = "0"+dayOfMonth;
                }

                datestop = year+ "-" + mm + "-" +dd;
                sp3.setText(datestop);

            }
        },year,month-1,dayofmonth);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            d = sdf.parse(oldDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dialog.getDatePicker().setMinDate(d.getTime());
        dialog.getDatePicker().setMaxDate(date.getTime());
        dialog.show();

    }


    public void adjustFontScale(Configuration configuration) {

        configuration.fontScale = (float) 1.0;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        getBaseContext().getResources().updateConfiguration(configuration, metrics);
    }
}
