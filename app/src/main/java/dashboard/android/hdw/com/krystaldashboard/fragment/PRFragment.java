package dashboard.android.hdw.com.krystaldashboard.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import android.widget.Toast;

import java.io.IOException;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.adapter.PRListAdapter;
import dashboard.android.hdw.com.krystaldashboard.dto.CompareCollectionDto;
import dashboard.android.hdw.com.krystaldashboard.dto.pr.PRItemCollectionDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;
import dashboard.android.hdw.com.krystaldashboard.manager.http.HttpManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.CompareManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.PRManager;
import dashboard.android.hdw.com.krystaldashboard.util.sharedprefmanager.SharedPrefDateManager;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PRFragment extends Fragment {
    Spinner spins;
    private ArrayList<String> mTypeSearch = new ArrayList<String>();
    ListView listViewPR;
    PRListAdapter prListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pr,null);
        teqAPICompare(SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getKeyDatePay());
        initInstances(rootView);

        return rootView;

    }
    private void createTypeSearchData() {

        if (mTypeSearch.isEmpty()){
            mTypeSearch.add("ชื่อจริง");
            mTypeSearch.add("ชื่อเล่น");
            mTypeSearch.add("ID");
            mTypeSearch.add("ตำแหน่ง");
        }
    }
    private void initInstances(View rootView) {

        listViewPR = (ListView) rootView.findViewById(R.id.list_pr);

//        if (listViewPR.getCount() != 0){
//            ViewGroup.LayoutParams listViewParams = listViewPR.getLayoutParams();
//            int itemHeight = listViewPR.getChildAt(0).getHeight() + 1 ;
//            listViewParams.height = listViewPR.getCount() * itemHeight;
//            listViewPR.setLayoutParams(listViewParams);
//        }


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
                    CompareManager.getInstance().setCompareDao(dao);
                    reqAPIPR(dao.getObject().get(0).getId());

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


    private void reqAPIPR(Long s) {
        final Context mcontext = Contextor.getInstance().getmContext();
        String nn ="{\"criteria\":{\"PrDrinkCenter-salesShiftId\":"+s+",\"PrDrinkCenter-isDrink\":\"false\"},\"property\":[],\"pagination\": { } }";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),nn);
        Call<PRItemCollectionDto> call = HttpManager.getInstance().getService().loadAPIPR(requestBody);
        call.enqueue(new Callback<PRItemCollectionDto>() {

            @Override
            public void onResponse(Call<PRItemCollectionDto> call, Response<PRItemCollectionDto> response) {
                if(response.isSuccessful()){
                    PRItemCollectionDto dto = response.body();
                    PRManager.getInstance().setPr(dto);

                    prListAdapter = new PRListAdapter();
                    prListAdapter.notifyDataSetChanged();
                    listViewPR.setAdapter(prListAdapter);

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
            public void onFailure(Call<dashboard.android.hdw.com.krystaldashboard.dto.pr.PRItemCollectionDto> call, Throwable t) {
                Toast.makeText(mcontext,t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
