package dashboard.android.hdw.com.krystaldashboard.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.dto.CompareCollectionDto;
import dashboard.android.hdw.com.krystaldashboard.dto.DateDto;
import dashboard.android.hdw.com.krystaldashboard.dto.pr.PRItemCollectionDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;
import dashboard.android.hdw.com.krystaldashboard.manager.http.HttpManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.CompareManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.DateManager;
import dashboard.android.hdw.com.krystaldashboard.util.sharedprefmanager.SharedPrefDateManager;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashBooardPRFragment extends Fragment {


    TextView PrOnfloor,NumberOfCalories,PrEmpty,PrRun,PrNotDrink;
//    ProgressBar BackgroundProgressbar,StatsProgressbar,StatsProgressbar2,StatsProgressbar3;

    AnimatedPieView mAnimatedPieView;
    AnimatedPieViewConfig config;

    DateDto dateDto;
    String formatDateTime2;
    Long id;
    int sizeonfloor,sizenull,sizeisfalse,sizeistrue;
    int count;
    public DashBooardPRFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getTime();
    }

    private void getTime() {
        dateDto = DateManager.getInstance().getDateDto();
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Date date = dateDto.getDateToday();
        Calendar calendar = dateDto.getCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,-1);
        formatDateTime2 = dateFormat2.format(calendar.getTime());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dash_booard_pr, container, false);
        teqAPICompare(SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getKeyDatePay());
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {

        PrOnfloor = (TextView)rootView.findViewById(R.id.pr_oofloor);
        NumberOfCalories = (TextView)rootView.findViewById(R.id.number_of_calories);
        PrEmpty = (TextView)rootView.findViewById(R.id.pr_empty);
        PrRun = (TextView)rootView.findViewById(R.id.pr_run);
        PrNotDrink = (TextView)rootView.findViewById(R.id.pr_not_drink);
        mAnimatedPieView = rootView.findViewById(R.id.drew2);

    }

    private void setProgress() {

        config = new AnimatedPieViewConfig();
        config.startAngle(-90)// Starting angle offset
                .addData(new SimplePieInfo(sizenull,getResources().getColor(R.color.pr_empty), "ว่าง"))
                .addData(new SimplePieInfo(sizeistrue,getResources().getColor(R.color.pr_run), "รันดื่มอยู่"))//Data (bean that implements the IPieInfo interface)
                .addData(new SimplePieInfo(sizeisfalse,getResources().getColor(R.color.pr_none), "ไม่มีดื่ม")).textSize(30).animatePie(false).canTouch(false);
        mAnimatedPieView.applyConfig(config);
        mAnimatedPieView.start();

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

                    id = dao.getObject().get(0).getId();
                    reqAPIPRistrue("{\"criteria\":{\"PrDrinkCenter-salesShiftId\":"+id+",\"PrDrinkCenter-isDrink\":\"true\"},\"property\":[],\"pagination\": { } }");
                }else {
                    try {
//                        progress.dismiss();
                        Toast.makeText(mcontext,response.errorBody().string(),Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
//                        progress.dismiss();
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


    private void reqAPIPRistrue(String s) {
        final Context mcontext = Contextor.getInstance().getmContext();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),s);
        Call<PRItemCollectionDto> call = HttpManager.getInstance().getService().loadAPIPR(requestBody);
        call.enqueue(new Callback<PRItemCollectionDto>() {

            @Override
            public void onResponse(Call<PRItemCollectionDto> call, Response<PRItemCollectionDto> response) {
                if(response.isSuccessful()){
                    PRItemCollectionDto dto = response.body();
                    try {
                        sizeistrue = dto.getObject().size();
                    }catch (Exception e){
                        sizeistrue = 0;
                    }
                    PrRun.setText(String.valueOf(sizeistrue));

                    reqAPIPRisfalse("{\"criteria\":{\"PrDrinkCenter-salesShiftId\":"+id+",\"sql-obj-command\":\"(f:itemQuantity = 0 OR f:itemQuantity IS NULL)\"},\"property\":[],\"pagination\":{}}");

                }else {
                    try {
                        Toast.makeText(mcontext,response.errorBody().string(),Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<dashboard.android.hdw.com.krystaldashboard.dto.pr.PRItemCollectionDto> call, Throwable t) {
                Toast.makeText(mcontext,t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void reqAPIPROnfloor(String s) {
        final Context mcontext = Contextor.getInstance().getmContext();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),s);
        Call<PRItemCollectionDto> call = HttpManager.getInstance().getService().loadAPIPR(requestBody);
        call.enqueue(new Callback<PRItemCollectionDto>() {

            @Override
            public void onResponse(Call<PRItemCollectionDto> call, Response<PRItemCollectionDto> response) {
                if(response.isSuccessful()){
                    PRItemCollectionDto dto = response.body();

                    try {
                        sizeonfloor = dto.getObject().size();
                    }catch (Exception e){
                        sizeonfloor = 0;
                    }

                    PrOnfloor.setText(String.valueOf(sizeonfloor));
                    NumberOfCalories.setText(String.valueOf(sizeonfloor));
                    setProgress();

                }else {
                    try {
                        Toast.makeText(mcontext,response.errorBody().string(),Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<PRItemCollectionDto> call, Throwable t) {
                Toast.makeText(mcontext,t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void reqAPIPRisfalse(String s) {
        final Context mcontext = Contextor.getInstance().getmContext();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),s);
        Call<PRItemCollectionDto> call = HttpManager.getInstance().getService().loadAPIPR(requestBody);
        call.enqueue(new Callback<PRItemCollectionDto>() {

            @Override
            public void onResponse(Call<PRItemCollectionDto> call, Response<PRItemCollectionDto> response) {
                if(response.isSuccessful()){
                    PRItemCollectionDto dto = response.body();

                    try {
                        sizeisfalse = dto.getObject().size();
                    }catch (Exception e){
                        sizeisfalse = 0;
                    }

                    PrNotDrink.setText(String.valueOf(sizeisfalse));

                    reqAPIPREmty("{\"criteria\":{\"PrDrinkCenter-salesShiftId\":"+id+",\"PrDrinkCenter-isDrink\":\"false\"},\"property\":[],\"pagination\":{}}");

                }else {
                    try {
                        Toast.makeText(mcontext,response.errorBody().string(),Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<PRItemCollectionDto> call, Throwable t) {
                Toast.makeText(mcontext,t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void reqAPIPREmty(String s) {
        final Context mcontext = Contextor.getInstance().getmContext();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),s);
        Call<PRItemCollectionDto> call = HttpManager.getInstance().getService().loadAPIPR(requestBody);
        call.enqueue(new Callback<PRItemCollectionDto>() {

            @Override
            public void onResponse(Call<PRItemCollectionDto> call, Response<PRItemCollectionDto> response) {
                if(response.isSuccessful()){
                    PRItemCollectionDto dto = response.body();

                    try {
                        sizenull = dto.getObject().size();
                    }catch (Exception e){
                        sizenull = 0;
                    }

                    PrEmpty.setText(String.valueOf(sizenull));

                    reqAPIPROnfloor("{\"criteria\":{\"PrDrinkCenter-salesShiftId\":"+id+"},\"property\":[],\"pagination\": { } }");

                }else {
                    try {
                        Toast.makeText(mcontext,response.errorBody().string(),Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<PRItemCollectionDto> call, Throwable t) {
                Toast.makeText(mcontext,t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
