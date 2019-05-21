package dashboard.android.hdw.com.krystaldashboard.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    ProgressBar BackgroundProgressbar,StatsProgressbar,StatsProgressbar2,StatsProgressbar3;

    DateDto dateDto;

    Long id;
    int sizeonfloor,sizenull,sizeisfalse,sizeistrue;
    int count;
    int pr[];
    public DashBooardPRFragment() {

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

        BackgroundProgressbar = (ProgressBar) rootView.findViewById(R.id.background_progressbar);
        StatsProgressbar = (ProgressBar) rootView.findViewById(R.id.stats_progressbar);
        StatsProgressbar2 = (ProgressBar) rootView.findViewById(R.id.stats_progressbar2);
        StatsProgressbar3 = (ProgressBar) rootView.findViewById(R.id.stats_progressbar3);

        dateDto = DateManager.getInstance().getDateDto();
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        String formatDateTime2 = dateFormat2.format(dateDto.getCalendar().getTime());


    }

    private void setdataview(PRItemCollectionDto dto) {

        PrRun.setText(String.valueOf(sizeistrue));

        count = 0;
        sizeonfloor = dto.getObject().size();
        NumberOfCalories.setText(String.valueOf(sizeonfloor));
        PrOnfloor.setText(String.valueOf(sizeonfloor));
        for(int i=0;i<sizeonfloor;i++){
            if(dto.getObject().get(i).getItemQuantity()==0L){
                count++;
            }
        }
        sizenull = count;
        PrNotDrink.setText(String.valueOf(sizenull));
        sizeisfalse = sizeonfloor-(sizenull+sizeistrue);
        PrEmpty.setText(String.valueOf(sizeisfalse));

        setProgress();

    }

    private void setProgress() {

        int pr[] = {sizeonfloor,sizeisfalse,sizeistrue,sizenull};
        int pr2[] = pr;
        int tmp;
        for(int i=0;i<pr.length;i++){
            for (int j=0;j<pr.length-i-1;j++){
                if(pr[j+1]>pr[j]){
                    tmp = pr[j];
                    pr[j] = pr[j+1];
                    pr[j+1] = tmp;
                }
            }
        }

        double d;
        int progress;

        for(int i=0;i<pr2.length;i++){

            if(i==0){
                for (int j=0;j<pr2.length;j++){
                    if(pr[i] == pr2[j]){
                        d =  (double) pr2[j] / (double) sizeonfloor;
                        progress = (int) (d*100);
                        BackgroundProgressbar.setProgress(progress);

//                        if(pr2[j]==sizeonfloor)
//                          BackgroundProgressbar.setBackgroundColor(Color.parseColor("#007AFF"));
//                        else if(pr2[j]==sizeistrue)
//                            BackgroundProgressbar.setBackgroundColor(Color.parseColor("#06B085"));
//                        else if(pr2[j]==sizeisfalse)
//                            BackgroundProgressbar.setBackgroundColor(Color.parseColor("#EF483E"));
//                        else if(pr2[j]==sizenull)
//                            BackgroundProgressbar.setBackgroundColor(Color.parseColor("#FFC108"));
                    }
                }
            }
            else if(i==1){
                for (int j=0;j<pr2.length;j++){
                    if(pr[i] == pr2[j]){
                        d =  (double) pr2[j] / (double) sizeonfloor;
                        progress = (int) (d*100);
                        StatsProgressbar.setProgress(progress);

//                        if(pr2[j]==sizeonfloor)
//                            StatsProgressbar.setBackgroundColor(Color.parseColor("#007AFF"));
//                        else if(pr2[j]==sizeistrue)
//                            StatsProgressbar.setBackgroundColor(Color.parseColor("#06B085"));
//                        else if(pr2[j]==sizeisfalse)
//                            StatsProgressbar.setBackgroundColor(Color.parseColor("#EF483E"));
//                        else if(pr2[j]==sizenull)
//                            StatsProgressbar.setBackgroundColor(Color.parseColor("#FFC108"));
                    }
                }
            }
            else if(i==2){
                for (int j=0;j<pr2.length;j++){
                    if(pr[i] == pr2[j]){
                        d =  (double) pr2[j] / (double) sizeonfloor;
                        progress = (int) (d*100);
                        StatsProgressbar2.setProgress(progress);

//                        if(pr2[j]==sizeonfloor)
//                            StatsProgressbar2.setBackgroundColor(Color.parseColor("#007AFF"));
//                        else if(pr2[j]==sizeistrue)
//                            StatsProgressbar2.setBackgroundColor(Color.parseColor("#06B085"));
//                        else if(pr2[j]==sizeisfalse)
//                            StatsProgressbar2.setBackgroundColor(Color.parseColor("#EF483E"));
//                        else if(pr2[j]==sizenull)
//                            StatsProgressbar2.setBackgroundColor(Color.parseColor("#FFC108"));
                    }
                }
            }
            else if(i==3){
                for (int j=0;j<pr2.length;j++){
                    if(pr[i] == pr2[j]){
                        d =  (double) pr2[j] / (double) sizeonfloor;
                        progress = (int) (d*100);
                        StatsProgressbar3.setProgress(progress);

//                        if(pr2[j]==sizeonfloor)
//                            StatsProgressbar3.setBackgroundColor(Color.parseColor("#007AFF"));
//                        else if(pr2[j]==sizeistrue)
//                            StatsProgressbar3.setBackgroundColor(Color.parseColor("#06B085"));
//                        else if(pr2[j]==sizeisfalse)
//                            StatsProgressbar3.setBackgroundColor(Color.parseColor("#EF483E"));
//                        else if(pr2[j]==sizenull)
//                            StatsProgressbar3.setBackgroundColor(Color.parseColor("#FFC108"));
                    }
                }
            }

        }




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

                   // id = dao.getObject().get(0).getId();
                  //  reqAPIPRistrue("{\"criteria\":{\"PrDrinkCenter-salesShiftId\":"+id+",\"PrDrinkCenter-isDrink\":\"true\"},\"property\":[],\"pagination\": { } }");
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

    private void reqAPIPROnfloor(String s) {
        final Context mcontext = Contextor.getInstance().getmContext();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),s);
        Call<PRItemCollectionDto> call = HttpManager.getInstance().getService().loadAPIPR(requestBody);
        call.enqueue(new Callback<PRItemCollectionDto>() {

            @Override
            public void onResponse(Call<PRItemCollectionDto> call, Response<PRItemCollectionDto> response) {
                if(response.isSuccessful()){
                    PRItemCollectionDto dto = response.body();

                    try{
                        setdataview(dto);
                    }catch (Exception e){

                    }


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
            public void onFailure(Call<dashboard.android.hdw.com.krystaldashboard.dto.pr.PRItemCollectionDto> call, Throwable t) {
                Toast.makeText(mcontext,t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
