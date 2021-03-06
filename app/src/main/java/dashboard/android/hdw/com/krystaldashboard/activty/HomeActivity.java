package dashboard.android.hdw.com.krystaldashboard.activty;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.dto.CompareCollectionDto;
import dashboard.android.hdw.com.krystaldashboard.dto.DashBoardDto;
import dashboard.android.hdw.com.krystaldashboard.dto.DateDto;
import dashboard.android.hdw.com.krystaldashboard.fragment.DrinkFragment;
import dashboard.android.hdw.com.krystaldashboard.fragment.HomeFragment;
import dashboard.android.hdw.com.krystaldashboard.fragment.PRFragment;
import dashboard.android.hdw.com.krystaldashboard.fragment.RevenueFragment;
import dashboard.android.hdw.com.krystaldashboard.fragment.TableFragment;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;
import dashboard.android.hdw.com.krystaldashboard.manager.http.HttpManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.CompareManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.DashBoradManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.DateManager;
import dashboard.android.hdw.com.krystaldashboard.util.sharedprefmanager.SharedPrefDateManager;
import dashboard.android.hdw.com.krystaldashboard.util.sharedprefmanager.SharedPrefDatePayManager;
import dashboard.android.hdw.com.krystaldashboard.util.sharedprefmanager.SharedPrefUser;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;

    String formatDateTime,formatDateTimetoday,formatDateTime2,formatDategeneral,format7DateTime;
    DateDto dateDto;
    Button datehome;

    ImageButton ImageButtonDoc;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.item_logout) {

            SharedPrefUser.getInstance(Contextor.getInstance().getmContext()).logout();

            Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
            this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getDateTime();
        teqAPICompare(formatDateTime2);
    }

    @Override
    protected void onPostCreate(@android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    private void initInstances() {

        textView = findViewById(R.id.text);
        toolbar = findViewById(R.id.toolbar);

        ImageButtonDoc = (ImageButton) findViewById(R.id.imagebutton_doc);
        ImageButtonDoc.setOnClickListener(this);

        datehome = (Button) findViewById(R.id.datehome);
        datehome.setOnClickListener(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.item_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.item_revrnue:
                        fragment = new RevenueFragment();
                        break;

                    case R.id.item_drink:
                        fragment = new DrinkFragment();
                        break;

                    case R.id.item_pr:
                        fragment = new PRFragment();
                        break;

                    case R.id.item_table:
                        fragment = new TableFragment();
                        break;
                }

                return loadFragment(fragment);
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.item_home);

    }

    private void getDateTime() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        DateFormat dateFormatth = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
//        calendar.add(Calendar.DATE,+1);

        formatDateTime = dateFormat.format(calendar.getTime());
        formatDateTimetoday = dateFormat.format(calendar.getTime());

        Calendar day7 = Calendar.getInstance();
        day7.add(Calendar.DATE,-7);


        dateDto = new DateDto();
        dateDto.setCalendar(calendar);
        dateDto.setDateToday(date);
        DateManager.getInstance().setDateDto(dateDto);

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);

        formatDateTime2 = dateFormat2.format(calendar.getTime());
        formatDategeneral = dateFormatth.format(calendar.getTime());
        format7DateTime = dateFormat2.format(day7.getTime());

        SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext())
                .saveDatereq(formatDateTime,formatDateTime2);

        SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext())
                .saveDateMax(formatDateTimetoday);

        SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext())
                .saveDateFull(formatDategeneral);

        SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext())
                .saveDate7(format7DateTime);

        SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext())
                .saveDateCalendar(day,month,year);

    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
            initInstances();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void reqAPI(String date) {
        final Context mcontext = Contextor.getInstance().getmContext();
        String nn = "{\"property\":[],\"criteria\":{\"sql-obj-command\":\"( tb_sales_shift.open_date >= '"+date+" 00:00:00' AND tb_sales_shift.open_date <= '"+date+" 23:59:59')\",\"summary-date\":\"*\"},\"orderBy\":{\"InvoiceDocument-id\":\"desc\"},\"pagination\":{}}";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),nn);
        Call<DashBoardDto> call = HttpManager.getInstance().getService().loadAPI(requestBody);
        call.enqueue(new Callback<DashBoardDto>() {
            @Override
            public void onResponse(Call<DashBoardDto> call, Response<DashBoardDto> response) {
                if(response.isSuccessful()){
                    DashBoardDto dao = response.body();
                    DashBoradManager.getInstance().setDto(dao);

                    initInstances();

                }else {
                    if(response.code() == 403){
                        SharedPrefUser.getInstance(Contextor.getInstance().getmContext()).logout();
                        SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).logoutDate();
                        SharedPrefDatePayManager.getInstance(Contextor.getInstance().getmContext()).logoutPay();
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        HomeActivity.this.startActivity(intent);
                        finish();

                    }else{
                        Toast.makeText(mcontext,"เกิดข้อผิดพลาด",Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<DashBoardDto> call, Throwable t) {
                Toast.makeText(mcontext,"ไม่สามารถเชื่อมต่อกับข้อมูลได้",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setDateDialog() {

        int day = SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getDateofMonth();
        int month = SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getMonth();
        int year = SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getYear();

        final DatePickerDialog dialog = new DatePickerDialog(HomeActivity.this,new DatePickerDialog.OnDateSetListener() {
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
                String datecalendat, datecalendat2;
                String fulldate;
                datehome.setText(dd+ "/" + mm + "/" +year);
                datecalendat = year+ "/" + mm + "/" +dd;
                datecalendat2 = year+ "-" + mm + "-" +dd;
                fulldate = dd+ "/" + mm + "/" +year;

                SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext())
                        .saveDatereq(datecalendat,datecalendat2);

                SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).saveDateFull(fulldate);

                SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext())
                        .saveDateCalendar(dayOfMonth,month,year);

            }
        },year,month-1,day);

        Date date = null;
        Date d = null;
        String oldDateString = "2019/01/06";
        String NewDateString = SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getKeyDate();

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

    private void teqAPICompare(String s) {
        final Context mcontext = Contextor.getInstance().getmContext();
        String nn = "{\"property\":[],\"criteria\":{\"opening\":false,\"sql-obj-command\":\"( tb_sales_shift.open_date >= '"+s+" 00:00:00' AND tb_sales_shift.open_date <= '"+s+" 23:59:59')\"},\"orderBy\":{},\"pagination\":{}}";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),nn);
        Call<CompareCollectionDto> call = HttpManager.getInstance().getService().loadAPIcompare(requestBody);
        call.enqueue(new Callback<CompareCollectionDto>() {

            @Override
            public void onResponse(Call<CompareCollectionDto> call, Response<CompareCollectionDto> response) {
                if(response.isSuccessful()){
                    CompareCollectionDto dao = response.body();

                    if(dao.getPagination().getTotalItem()==0){
                        getDateTime2();
                    }
                    else {
                        reqAPI(formatDateTime);
                    }

                }else {
                    if(response.code() == 403){
                        SharedPrefUser.getInstance(Contextor.getInstance().getmContext()).logout();
                        SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).logoutDate();
                        SharedPrefDatePayManager.getInstance(Contextor.getInstance().getmContext()).logoutPay();
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        HomeActivity.this.startActivity(intent);
                        finish();

                    }else{
                        Toast.makeText(mcontext,"เกิดข้อผิดพลาด",Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<CompareCollectionDto> call, Throwable t) {
                Toast.makeText(mcontext,t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getDateTime2() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        DateFormat dateFormatth = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,-1);

        formatDateTime = dateFormat.format(calendar.getTime());
        formatDateTimetoday = dateFormat.format(calendar.getTime());

        Calendar day7 = Calendar.getInstance();
        day7.add(Calendar.DATE,-7);


        dateDto = new DateDto();
        dateDto.setCalendar(calendar);
        dateDto.setDateToday(date);
        DateManager.getInstance().setDateDto(dateDto);

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);

        formatDateTime2 = dateFormat2.format(calendar.getTime());
        formatDategeneral = dateFormatth.format(calendar.getTime());
        format7DateTime = dateFormat2.format(day7.getTime());

        SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext())
                .saveDatereq(formatDateTime,formatDateTime2);

        SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext())
                .saveDateMax(formatDateTimetoday);

        SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext())
                .saveDateFull(formatDategeneral);

        SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext())
                .saveDate7(format7DateTime);

        SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext())
                .saveDateCalendar(day,month,year);

        teqAPICompare(formatDateTime2);
    }

    @Override
    public void onClick(View v) {
        if(v == datehome){
            setDateDialog();
        }

        if(v == ImageButtonDoc){
            Intent intent = new Intent(HomeActivity.this, CompareReceipts.class);
            startActivity(intent);
        }
    }

}
