package dashboard.android.hdw.com.krystaldashboard.activty;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.dto.DashBoardDto;
import dashboard.android.hdw.com.krystaldashboard.fragment.DrinkFragment;
import dashboard.android.hdw.com.krystaldashboard.fragment.HomeFragment;
import dashboard.android.hdw.com.krystaldashboard.fragment.PRFragment;
import dashboard.android.hdw.com.krystaldashboard.fragment.RevenueFragment;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;
import dashboard.android.hdw.com.krystaldashboard.manager.http.HttpManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.DashBoradManager;
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

    Button datehome;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
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
        textView = findViewById(R.id.text);
        toolbar = findViewById(R.id.toolbar);

        getDateTime();

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
                }

                return loadFragment(fragment);
            }
        });
            bottomNavigationView.setSelectedItemId(R.id.item_home);

    }

    private void getDateTime() {
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
    protected void onStart() {
        super.onStart();
        reqAPI(SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getreqDate());
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

                String aa = String.valueOf(response.raw().code());

                if(response.isSuccessful()){
                    DashBoardDto dao = response.body();
                    DashBoradManager.getInstance().setDao(dao);
                }else {
                    if(response.code() == 403){
                        SharedPrefUser.getInstance(Contextor.getInstance().getmContext()).logout();
                        SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).logoutDate();
                        SharedPrefDatePayManager.getInstance(Contextor.getInstance().getmContext()).logoutPay();

                        Toast.makeText(Contextor.getInstance().getmContext(),aa,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        mcontext.startActivity(intent);
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
                String datecalendat;
                datehome.setText(year+ "/" + mm + "/" +dd);
                datecalendat = year+ "/" + mm + "/" +dd;

                SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext())
                        .saveDatereq(datecalendat);

                reqAPI(SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getreqDate());

                SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext())
                        .saveDateCalendar(dayOfMonth,month,year);

            }
        },year,month-1,day);

        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        c.add(Calendar.DATE,-1);
        Date date = c.getTime();
        Date d = null;
        String oldDateString = "2019/01/06";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
        try {
            d = sdf.parse(oldDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dialog.show();
        dialog.getDatePicker().setMinDate(d.getTime());
        dialog.getDatePicker().setMaxDate(date.getTime());
    }

    @Override
    public void onClick(View v) {
        if(v == datehome){
            setDateDialog();
        }
    }
}
