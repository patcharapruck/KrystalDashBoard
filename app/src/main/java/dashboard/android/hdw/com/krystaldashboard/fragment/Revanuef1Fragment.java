package dashboard.android.hdw.com.krystaldashboard.fragment;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.dto.ObjectItemDto;
import dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus.NotPayItemColleationDto;
import dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus.PayItemColleationDto;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.DashBoradManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.NotPayManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.PayManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class Revanuef1Fragment extends Fragment {

    ProgressBar progressBar;
    DecimalFormat formatter;
    TextView TextViewTotalRevanue,TextViewUpdateTimeRevanue,TextViewTableMoneyRevanue,TextViewAmountTableRevanue;

    NotPayItemColleationDto Notdto;
    PayItemColleationDto Paydto;
    ObjectItemDto ODto;

    Double TotalRevanue,TableMoneyRevanue;
    String UpdateTimeRevanue;
    Long AmountTableRevanue;

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

        TextViewTotalRevanue = (TextView) rootView.findViewById(R.id.textview_total_revanue);
        TextViewTableMoneyRevanue = (TextView) rootView.findViewById(R.id.textview_table_money_revanue);
        TextViewUpdateTimeRevanue = (TextView) rootView.findViewById(R.id.textview_update_time_revanue);
        TextViewAmountTableRevanue = (TextView) rootView.findViewById(R.id.textview_amount_table_revanue);

        setTextView();
    }

    private void setTextView() {

        AmountTableRevanue = Paydto.getPagination().getTotalItem();
        TotalRevanue = ODto.getRevenue();
        int pg;

        try {
            pg = (int) (AmountTableRevanue/(AmountTableRevanue+Notdto.getPagination().getTotalItem()))*100;
        }catch (Exception e){
            pg = 0;
        }


        DateFormat dateFormatth = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        Calendar calendartoday = Calendar.getInstance();
        calendar.setTime(date);
        calendartoday.setTime(date);
        calendar.add(Calendar.DATE,-1);
        String formatDategeneral = dateFormatth.format(calendar.getTime());

        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
        String currentDateTimeString = sdf.format(d);

        Double sum=0.0;

        for (int i=0;i<Paydto.getObject().size();i++){
            sum = sum + Paydto.getObject().get(i).getTotalPrice();
        }

        TextViewAmountTableRevanue.setText(AmountTableRevanue.toString());
        TextViewTotalRevanue.setText(formatter.format(TotalRevanue));
        TextViewUpdateTimeRevanue.setText("อัพเดทรายรับล่าสุด "+formatDategeneral+" "+currentDateTimeString);
        TextViewTableMoneyRevanue.setText(formatter.format(sum));
        progressBar.setProgress(pg);
    }

}
