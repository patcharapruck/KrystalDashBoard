package dashboard.android.hdw.com.krystaldashboard.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.adapter.PRAdapter;
import dashboard.android.hdw.com.krystaldashboard.dto.CompareCollectionDto;
import dashboard.android.hdw.com.krystaldashboard.dto.pr.PRItemCollectionDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;
import dashboard.android.hdw.com.krystaldashboard.manager.http.HttpManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.CompareManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.PRManager;
import dashboard.android.hdw.com.krystaldashboard.util.sharedprefmanager.SharedPrefDateManager;
import dashboard.android.hdw.com.krystaldashboard.view.PRModelClass;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PRFragment extends Fragment implements View.OnClickListener {
//    Spinner spins;
    private ArrayList<String> mTypeSearch = new ArrayList<String>();

    TextView TextViewSearch;
    EditText EditTextSearchTable;

    TextView TextViewOnfloor,TextViewNullDrink,TextViewIsFalse,TextViewIsTrue;

    CardView CardOnfloor,CardIsdrinkFalse,CardIsdrinkTrue,CardNulldrink;
    String typeSearch = "";

    String json;
    String Search="";
    int ch=0,chCrad =0;
    Long id;

    int count;

    ArrayList<PRModelClass> items;
    PRAdapter adapter;
    RecyclerView recyclerView;

    int sizeonfloor,sizenull,sizeisfalse,sizeistrue;

    PRItemCollectionDto dto;

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
            mTypeSearch.add("รหัส,ชื่อ,นามสกุล,ชื่อเล่น ของพนักงาน");
            mTypeSearch.add("ตำแหน่ง");
        }
    }
    private void initInstances(View rootView) {

        TextViewSearch = (TextView) rootView.findViewById(R.id.textview_serach);
        EditTextSearchTable = (EditText) rootView.findViewById(R.id.edittext_search_table);

        recyclerView = rootView.findViewById(R.id.recycler_view_pr);

        TextViewOnfloor = (TextView) rootView.findViewById(R.id.textview_Onfloor) ;
        TextViewNullDrink = (TextView) rootView.findViewById(R.id.textview_null_drink);
        TextViewIsFalse = (TextView) rootView.findViewById(R.id.textview_isfalse);
        TextViewIsTrue = (TextView) rootView.findViewById(R.id.textview_istrue);

        CardOnfloor = (CardView) rootView.findViewById(R.id.card_onfloor);
        CardIsdrinkTrue = (CardView) rootView.findViewById(R.id.card_isdrink_trus);
        CardIsdrinkFalse = (CardView) rootView.findViewById(R.id.card_isdrink_false);
        CardNulldrink = (CardView) rootView.findViewById(R.id.card_null_drink);

        typeSearch = "รหัส,ชื่อ,นามสกุล,ชื่อเล่น ของพนักงาน";
        TextViewSearch.setText(typeSearch);
        ch = 0;

        CardOnfloor.setOnClickListener(this);
        CardIsdrinkFalse.setOnClickListener(this);
        CardIsdrinkTrue.setOnClickListener(this);
        CardNulldrink.setOnClickListener(this);


        EditTextSearchTable.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(ch == 0){
                    Search = ",\"PrDrinkCenter-employeeCode-firstname-lastname-nickName\":\""+s+"\"";
                    setDataSearch(chCrad,Search);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void setRecyclerView() {
        items = new ArrayList<>();
        adapter = new PRAdapter(getContext(), items);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
//        recyclerView.setNestedScrollingEnabled(false);
//        recyclerView.setNestedScrollingEnabled(false);

        int size;
        try {
            size = dto.getObject().size();
        }catch (NullPointerException e){
            size = 0;
        }

        for (int i = 0; i < size; i++) {

            String name,nickName,employeeCode,runDrink,position,onFloor;
            Long start,all;

            try {
                name = dto.getObject().get(i).getFirstname()+" "+dto.getObject().get(i).getLastname();
            }catch (NullPointerException e){
                name = "-";
            }
            try {
                nickName = dto.getObject().get(i).getNickName();
            }catch (NullPointerException e){
                nickName = "-";
            }
            try {
                employeeCode = dto.getObject().get(i).getEmployeeCode();
            }catch (NullPointerException e){
                employeeCode = "-";
            }
            try {
                runDrink = dto.getObject().get(i).getPlaceCode();
            }catch (NullPointerException e){
                runDrink = "-";
            }
            try {
                position = dto.getObject().get(i).getPositionNameTh();
            }catch (NullPointerException e){
                position = "-";
            }

            try {
                onFloor = dto.getObject().get(i).getTimeOnFloor();
            }catch (NullPointerException e){
                onFloor = "-";
            }

            try {
                start = dto.getObject().get(i).getStartDrink();
            }catch (NullPointerException e){
                start = 0L;
            }

            try {
                all = dto.getObject().get(i).getTotalQuantity();
            }catch (NullPointerException e){
                all = 0L;
            }

            items.add(new PRModelClass(name,nickName,employeeCode,runDrink,position,onFloor,start,all));
            adapter.notifyDataSetChanged();
        }
    }

    private void setDataSearch(int chCrad, String search) {
        if(chCrad == 0){
            json = "{\"criteria\":{\"PrDrinkCenter-salesShiftId\":"+id+""+Search+"},\"property\":[],\"pagination\": { } }";
            reqAPIPR(json);
        }else if(chCrad ==1){
            json = "{\"criteria\":{\"PrDrinkCenter-salesShiftId\":"+id+",\"PrDrinkCenter-isDrink\":\"true\""+Search+"},\"property\":[],\"pagination\": { } }";
            reqAPIPR(json);
        }else if(chCrad ==2){
            json = "{\"criteria\":{\"PrDrinkCenter-salesShiftId\":"+id+",\"sql-obj-command\":\"(f:itemQuantity = 0 OR f:itemQuantity IS NULL)\""+Search+"},\"property\":[],\"pagination\":{}}";
        }else if(chCrad ==3){
            json = "{\"criteria\":{\"PrDrinkCenter-salesShiftId\":"+id+",\"PrDrinkCenter-isDrink\":\"false\"\"+Search+\"},\"property\":[],\"pagination\":{}}";
            reqAPIPR(json);
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
                    CompareManager.getInstance().setCompareDao(dao);
                    id = dao.getObject().get(0).getId();
                    json = "{\"criteria\":{\"PrDrinkCenter-salesShiftId\":"+id+"},\"property\":[],\"pagination\": { } }";
                    reqAPIPR(json);
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


    private void reqAPIPR(String s) {
        final Context mcontext = Contextor.getInstance().getmContext();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),s);
        Call<PRItemCollectionDto> call = HttpManager.getInstance().getService().loadAPIPR(requestBody);
        call.enqueue(new Callback<PRItemCollectionDto>() {

            @Override
            public void onResponse(Call<PRItemCollectionDto> call, Response<PRItemCollectionDto> response) {
                if(response.isSuccessful()){
                    dto = response.body();
                    PRManager.getInstance().setPr(dto);

                    setRecyclerView();
                    reqAPIPRistrue("{\"criteria\":{\"PrDrinkCenter-salesShiftId\":"+id+",\"PrDrinkCenter-isDrink\":\"true\"},\"property\":[],\"pagination\": { } }");
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

    @Override
    public void onClick(View v) {
        if(v == CardOnfloor){
            chCrad =0;
            json = "{\"criteria\":{\"PrDrinkCenter-salesShiftId\":"+id+"},\"property\":[],\"pagination\": { } }";
            reqAPIPR(json);
        }
        if(v == CardIsdrinkTrue){
            chCrad =1;
            json = "{\"criteria\":{\"PrDrinkCenter-salesShiftId\":"+id+",\"PrDrinkCenter-isDrink\":\"true\"},\"property\":[],\"pagination\": { } }";
            reqAPIPR(json);
        }
        if(v == CardIsdrinkFalse){
            chCrad = 2;
            json = "{\"criteria\":{\"PrDrinkCenter-salesShiftId\":"+id+",\"sql-obj-command\":\"(f:itemQuantity = 0 OR f:itemQuantity IS NULL)\"},\"property\":[],\"pagination\":{}}";
            reqAPIPR(json);
        }
        if(v == CardNulldrink){
            chCrad =3;
            json = "{\"criteria\":{\"PrDrinkCenter-salesShiftId\":"+id+",\"PrDrinkCenter-isDrink\":\"false\"},\"property\":[],\"pagination\":{}}";
            reqAPIPR(json);
        }
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

                    TextViewOnfloor.setText(String.valueOf(sizeonfloor));

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

                    TextViewIsTrue.setText(String.valueOf(sizeistrue));

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

                    TextViewIsFalse.setText(String.valueOf(sizeisfalse));

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

                    TextViewNullDrink.setText(String.valueOf(sizenull));

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
