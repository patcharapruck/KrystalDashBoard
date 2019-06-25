package dashboard.android.hdw.com.krystaldashboard.fragment;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.activty.HomeActivity;
import dashboard.android.hdw.com.krystaldashboard.dto.ObjectItemDto;
import dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus.NotPayItemColleationDto;
import dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus.PayItemColleationDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.DashBoradManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.DateManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.NotPayManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.PayManager;
import dashboard.android.hdw.com.krystaldashboard.util.sharedprefmanager.SharedPrefDateManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class Revanuef1Fragment extends Fragment{

    ProgressBar progressBar;
    DecimalFormat formatter;
    TextView TextViewTableMoneyRevanue
            ,TextViewAmountTableRevanue,TextViewAmountAll,TextViewMoneyRevanue;

    NotPayItemColleationDto Notdto;
    PayItemColleationDto Paydto;
    ObjectItemDto ODto;

    Button ButtonDate;
    Double TotalRevanue,TableMoneyRevanue;
    String UpdateTimeRevanue;
    Long AmountTableRevanue;
    Long NotTableRevanue;

    public Revanuef1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_revanue_f1,container,false);
        initInstances(rootView);
        return rootView;

    }

    private void initInstances(View rootView) {

        formatter = new DecimalFormat("#,###,##0.00");

        Notdto = NotPayManager.getInstance().getNotpayItemColleationDao();
        Paydto = PayManager.getInstance().getPayItemColleationDao();
        ODto = DashBoradManager.getInstance().getDto().getObject();

        progressBar = rootView.findViewById(R.id.horizontal_progress_bar);
        progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#7F6CFF"), PorterDuff.Mode.SRC_IN);

        TextViewTableMoneyRevanue = (TextView) rootView.findViewById(R.id.textview_money_revanue);
        TextViewAmountTableRevanue = (TextView) rootView.findViewById(R.id.table_revanue);
        TextViewAmountAll = (TextView) rootView.findViewById(R.id.textview_amount_table_revanue);

        setTextView();
    }

    private void setTextView() {

        AmountTableRevanue = Paydto.getPagination().getTotalItem();
        NotTableRevanue = Notdto.getPagination().getTotalItem();
        TotalRevanue = ODto.getRevenue();
        TableMoneyRevanue = ODto.getIncome();

        Long dD = AmountTableRevanue+NotTableRevanue;

        Double p = Double.valueOf(AmountTableRevanue);
        Double p2 = Double.valueOf(dD);

        Double p3 = p/p2;

        int ss = (int) (p3*100);

//        DateFormat dateFormatth = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//        String formatDategeneral = dateFormatth.format(DateManager.getInstance().getDateDto().getCalendar().getTime());
//
//        Date d =new Date();
//        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
//        String currentDateTimeString = sdf.format(d);

        Double sum=0.0;

        int sizeloop;

        try {
            sizeloop = Paydto.getObject().size();
        }catch (NullPointerException e){
            sizeloop = 0;
        }
        for (int i=0;i<sizeloop;i++){
            sum = sum + Paydto.getObject().get(i).getTotalPrice();
        }

//        TextViewMoneyRevanue.setText(formatter.format(TableMoneyRevanue));
        TextViewAmountTableRevanue.setText(AmountTableRevanue.toString());
        TextViewAmountAll.setText(dD.toString());

        Double sum2 = sum + ODto.getCreditPayments()+ODto.getUnpaid();

        TextViewTableMoneyRevanue.setText(formatter.format(sum2));
//        TextViewTableMoneyRevanue.setText(formatter.format(sum2)+" ผลต่าง "+(ODto.getIncome()-sum2));

//        Toast.makeText(Contextor.getInstance().getmContext(),ODto.getIncome().toString(),Toast.LENGTH_LONG).show();

        progressBar.setProgress(ss);
    }

//    @Override
//    public void onClick(View v) {
//
//        if(v == ButtonDate){
//            setDateDialog();
//        }
//    }
//
//    private void setDateDialog() {
//
//        int day = SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getDateofMonth();
//        int month = SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getMonth();
//        int year = SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getYear();
//
//        final DatePickerDialog dialog = new DatePickerDialog(getContext(),new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                month++;
//                String mm = ""+month;
//                String dd = ""+dayOfMonth;
//
//                if (month<10){
//                    mm = "0"+month;
//                }
//                if (dayOfMonth < 10){
//                    dd = "0"+dayOfMonth;
//                }
//                String datecalendat, datecalendat2;
//                String fulldate;
//                datecalendat = year+ "/" + mm + "/" +dd;
//                datecalendat2 = year+ "-" + mm + "-" +dd;
//                fulldate = dd+ "/" + mm + "/" +year;
//
//                SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext())
//                        .saveDatereq(datecalendat,datecalendat2);
//
//                SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).saveDateFull(fulldate);
//
//                SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext())
//                        .saveDateCalendar(dayOfMonth,month,year);
//
//
//            }
//        },year,month-1,day);
//
//        Date date = null;
//        Date d = null;
//        String oldDateString = "2019/01/06";
//        String NewDateString = SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getKeyDate();
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
//        try {
//            d = sdf.parse(oldDateString);
//            date = sdf.parse(NewDateString);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        dialog.show();
//        dialog.getDatePicker().setMinDate(d.getTime());
//        dialog.getDatePicker().setMaxDate(date.getTime());
//    }
}
