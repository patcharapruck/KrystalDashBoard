package dashboard.android.hdw.com.krystaldashboard.fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.dto.DashBoardDto;
import dashboard.android.hdw.com.krystaldashboard.dto.ObjectItemDto;
import dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus.NotPayItemColleationDto;
import dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus.PayItemColleationDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;
import dashboard.android.hdw.com.krystaldashboard.manager.http.HttpManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.DashBoradManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.DateManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.NotPayManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.PayManager;
import dashboard.android.hdw.com.krystaldashboard.util.sharedprefmanager.SharedPrefDateManager;
import dashboard.android.hdw.com.krystaldashboard.util.sharedprefmanager.SharedPrefDatePayManager;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RevenueFragment extends Fragment implements View.OnClickListener {

    ProgressDialog progress;
    DecimalFormat formatter;
    private Boolean checkPay = false,checkNotPay=false , checkdashboard=false , checkall = false;

    TextView TextViewTotalRevanue,TextViewUpdateTimeRevanue,TextviewDateShow;
    String DataShow;
    String datecalendat, datecalendat2;

    FragmentTransaction f1,f2,f3;

    NotPayItemColleationDto Notdto;
    PayItemColleationDto Paydto;
    ObjectItemDto ODto;

    View rootView ;
    Button ButtonDate;
    Double TotalRevanue;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showProgress();

        checkPay = false;
        checkNotPay=false;
        checkdashboard=false;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_revanue,null);
        reqAPI(SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getreqDate());
        reqAPIpay(SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getKeyDatePay());
        reqAPInotpay(SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getKeyDatePay());
        TextviewDateShow = (TextView)rootView.findViewById(R.id.textview_date_show);
        DataShow = SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getKeyDateFull();
        datecalendat = SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getKeyDate();
        return rootView;
    }

    private void initInstances(View rootView) {


        formatter = new DecimalFormat("#,###,##0.00");
        Notdto = NotPayManager.getInstance().getNotpayItemColleationDao();
        Paydto = PayManager.getInstance().getPayItemColleationDao();
        ODto = DashBoradManager.getInstance().getDto().getObject();

        setChildFragment();

        TextViewTotalRevanue = (TextView) rootView.findViewById(R.id.textview_total_revanue);
        TextViewUpdateTimeRevanue = (TextView) rootView.findViewById(R.id.textview_update_time_revanue);

//        fragment1 = (Fragment) rootView.findViewById(R.id.fragment_revanue_f1);
        ButtonDate = (Button) rootView.findViewById(R.id.button_date);
        ButtonDate.setOnClickListener(this);
        setTextView();

    }

    private void setChildFragment() {
        f1 = getChildFragmentManager().beginTransaction();
        f1.replace(R.id.fragment_revanue_f1, new Revanuef1Fragment());
        f1.commit();

        f2 = getChildFragmentManager().beginTransaction();
        f2.replace(R.id.fragment_revanue_table, new TableRevanueFragment());
        f2.commit();

        f3 = getChildFragmentManager().beginTransaction();
        f3.replace(R.id.fragment_revanue_f2, new Revanuef2Fragment());
        f3.commit();
    }

    private void setTextView() {

        TotalRevanue = ODto.getRevenue();

        DateFormat dateFormatth = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String formatDategeneral = dateFormatth.format(DateManager.getInstance().getDateDto().getCalendar().getTime());

        Date d =new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
        String currentDateTimeString = sdf.format(d);

//        TextViewMoneyRevanue.setText(formatter.format(TableMoneyRevanue));
        TextViewTotalRevanue.setText(formatter.format(TotalRevanue));
        TextViewUpdateTimeRevanue.setText("อัพเดทรายรับล่าสุด "+formatDategeneral+" "+currentDateTimeString);
        TextviewDateShow.setText("แสดงข้อมูลของวันที่  "+DataShow);
    }

    public void reqAPI(String date) {
        checkdashboard = false;
        final Context mcontext = Contextor.getInstance().getmContext();
        String nn = "{\"property\":[],\"criteria\":{\"sql-obj-command\":\"( tb_sales_shift.open_date >= '"+date+" 00:00:00' AND tb_sales_shift.open_date <= '"+date+" 23:59:59')\",\"summary-date\":\"*\"},\"orderBy\":{\"InvoiceDocument-id\":\"desc\"},\"pagination\":{}}";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),nn);
        Call<DashBoardDto> call = HttpManager.getInstance().getService().loadAPI(requestBody);
        call.enqueue(new Callback<DashBoardDto>() {
            @Override
            public void onResponse(Call<DashBoardDto> call, Response<DashBoardDto> response) {
                String aa = String.valueOf(response.raw().code());
                if(response.isSuccessful()){
                    DashBoardDto dao = response.body();
                    DashBoradManager.getInstance().setDto(dao);
                    checkdashboard = true;

                    if(checkPay == true && checkNotPay == true && checkdashboard == true){
                        progress.dismiss();
                        initInstances(rootView);
                    }
                }
                else {
                    progress.dismiss();
                    Toast.makeText(mcontext,"เกิดข้อผิดพลาด",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<DashBoardDto> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(mcontext,"ไม่สามารถเชื่อมต่อกับข้อมูลได้",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void reqAPInotpay(String date) {
        checkNotPay = false;
        final Context mcontext = Contextor.getInstance().getmContext();
        String nn = "{\"criteria\":{\"sql-obj-command\":\"f:documentStatus.id = 22 and " +
                "(f:salesShift.openDate >= '"+date+" 00:00:00' AND f:salesShift.openDate <= '"+date+" 23:59:59')\"}," +
                "\"property\":[\"memberAccount->customerMemberAccount\",\"sales->employee\",\"place\",\"transactionPaymentList\",\"documentStatus\",\"salesShift\"]," +
                "\"pagination\":{},\"orderBy\":{\"InvoiceDocument-id\":\"DESC\"}}";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),nn);
        Call<NotPayItemColleationDto> call = HttpManager.getInstance().getService().loadAPINotPay(requestBody);
        call.enqueue(new Callback<NotPayItemColleationDto>() {
            @Override
            public void onResponse(Call<NotPayItemColleationDto> call, Response<NotPayItemColleationDto> response) {
                if(response.isSuccessful()){
                    NotPayItemColleationDto dao = response.body();
                    NotPayManager.getInstance().setNotpayItemColleationDao(dao);
                    checkNotPay = true;
                    SharedPrefDatePayManager.getInstance(Contextor.getInstance().getmContext())
                            .saveNotPay(dao.getPagination().getTotalItem());

                    if(checkPay == true && checkNotPay == true && checkdashboard == true){
                        progress.dismiss();
                        initInstances(rootView);
                    }
                }else {
                    progress.dismiss();
                    Toast.makeText(mcontext,"เกิดข้อผิดพลาด",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<NotPayItemColleationDto> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(mcontext,"ไม่สามารถเชื่อมต่อได้",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void reqAPIpay(String date) {
        checkPay = false;
        final Context mcontext = Contextor.getInstance().getmContext();
        String nn = "{\"criteria\":{\"sql-obj-command\":\"f:documentStatus.id = 21 and " +
                "(f:salesShift.openDate >= '"+date+" 00:00:00' AND f:salesShift.openDate <= '"+date+" 23:59:59')\"}," +
                "\"property\":[\"memberAccount->customerMemberAccount\",\"sales->employee\",\"place\",\"transactionPaymentList\",\"documentStatus\",\"salesShift\"]," +
                "\"pagination\":{},\"orderBy\":{\"InvoiceDocument-id\":\"DESC\"}}";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),nn);
        Call<PayItemColleationDto> call = HttpManager.getInstance().getService().loadAPIPay(requestBody);
        call.enqueue(new Callback<PayItemColleationDto>() {
            @Override
            public void onResponse(Call<PayItemColleationDto> call, Response<PayItemColleationDto> response) {
                if(response.isSuccessful()){
                    PayItemColleationDto dao = response.body();
                    PayManager.getInstance().setPayItemColleationDao(dao);
                    checkPay = true;
                    SharedPrefDatePayManager.getInstance(Contextor.getInstance().getmContext())
                            .savePay(dao.getPagination().getTotalItem());
                    if(checkPay == true && checkNotPay == true && checkdashboard == true){
                        progress.dismiss();
                        initInstances(rootView);
//
                    }
                }else {
                    progress.dismiss();
                    Toast.makeText(mcontext,"เกิดข้อผิดพลาด",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<PayItemColleationDto> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(mcontext,"ไม่สามารถเชื่อมต่อได้",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void showProgress() {
        progress = new ProgressDialog(getContext());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
    }

    @Override
    public void onClick(View v) {
        if(v == ButtonDate){
            setDateDialog();
        }
    }

    private void setDateDialog() {

        int day = SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getDateofMonth();
        int month = SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getMonth();
        int year = SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getYear();

        final DatePickerDialog dialog = new DatePickerDialog(getContext(),new DatePickerDialog.OnDateSetListener() {
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
                String fulldate;
                datecalendat = year+ "/" + mm + "/" +dd;
                datecalendat2 = year+ "-" + mm + "-" +dd;
                fulldate = dd+ "/" + mm + "/" +year;


                DataShow = fulldate;
                reqAPI(datecalendat);
                reqAPIpay(datecalendat2);
                reqAPInotpay(datecalendat2);


            }
        },year,month-1,day);

        Date date = null;
        Date d = null;
        String oldDateString = "2019/01/06";
        String NewDateString = datecalendat;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
        try {
            d = sdf.parse(oldDateString);
            date = sdf.parse(NewDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dialog.show();
        dialog.getDatePicker().setMinDate(d.getTime());
        dialog.getDatePicker().setMaxDate(date.getTime());
    }
}
