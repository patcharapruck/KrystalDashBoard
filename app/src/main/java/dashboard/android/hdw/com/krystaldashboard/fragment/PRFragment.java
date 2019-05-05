package dashboard.android.hdw.com.krystaldashboard.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.adapter.PRListAdapter;
import dashboard.android.hdw.com.krystaldashboard.dto.CompareCollectionDto;
import dashboard.android.hdw.com.krystaldashboard.dto.pr.PRItemCollectionDto;
import dashboard.android.hdw.com.krystaldashboard.dto.pr.PRItemDto;
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

public class PRFragment extends Fragment implements View.OnClickListener {
    Spinner spins;
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

    int sizeonfloor,sizenull,sizeisfalse,sizeistrue;

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
            mTypeSearch.add("รหัส,ชื่อ,นามสกุล,ชื่อเล่น ของพนักงาน");
            mTypeSearch.add("ตำแหน่ง");
        }
    }
    private void initInstances(View rootView) {

        TextViewSearch = (TextView) rootView.findViewById(R.id.textview_serach);
        EditTextSearchTable = (EditText) rootView.findViewById(R.id.edittext_search_table);

        listViewPR = (ListView) rootView.findViewById(R.id.list_pr);
        spins = (Spinner) rootView.findViewById(R.id.spinspr);

        TextViewOnfloor = (TextView) rootView.findViewById(R.id.textview_Onfloor) ;
        TextViewNullDrink = (TextView) rootView.findViewById(R.id.textview_null_drink);
        TextViewIsFalse = (TextView) rootView.findViewById(R.id.textview_isfalse);
        TextViewIsTrue = (TextView) rootView.findViewById(R.id.textview_istrue);

        CardOnfloor = (CardView) rootView.findViewById(R.id.card_onfloor);
        CardIsdrinkTrue = (CardView) rootView.findViewById(R.id.card_isdrink_trus);
        CardIsdrinkFalse = (CardView) rootView.findViewById(R.id.card_isdrink_false);
        CardNulldrink = (CardView) rootView.findViewById(R.id.card_null_drink);

        CardOnfloor.setOnClickListener(this);
        CardIsdrinkFalse.setOnClickListener(this);
        CardIsdrinkTrue.setOnClickListener(this);
        CardNulldrink.setOnClickListener(this);

        createTypeSearchData();

        ArrayAdapter<String> spinsSearch = new ArrayAdapter<String>(getContext()
                ,R.layout.support_simple_spinner_dropdown_item,mTypeSearch);
        spins.setAdapter(spinsSearch);
        spins.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(mTypeSearch.get(position).equals("รหัส,ชื่อ,นามสกุล,ชื่อเล่น ของพนักงาน")){
                    typeSearch = "รหัส,ชื่อ,นามสกุล,ชื่อเล่น ของพนักงาน";
                    TextViewSearch.setText(typeSearch);
                    ch = 0;
                }else if(mTypeSearch.get(position).equals("ตำแหน่ง")){
                    typeSearch = "ตำแหน่ง";
                    TextViewSearch.setText(typeSearch);
                    ch =1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listViewPR.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });


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
                else if (ch == 1){
                    Search = ",\"PrDrinkCenter-positionNameTh\":\""+s+"\"";
                    setDataSearch(chCrad,Search);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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


    private void reqAPIPR(String s) {
        final Context mcontext = Contextor.getInstance().getmContext();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),s);
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
            public void onFailure(Call<dashboard.android.hdw.com.krystaldashboard.dto.pr.PRItemCollectionDto> call, Throwable t) {
                Toast.makeText(mcontext,t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setdataview(PRItemCollectionDto dto) {
        count = 0;
        sizeonfloor = dto.getObject().size();
        TextViewOnfloor.setText(String.valueOf(sizeonfloor));
        for(int i=0;i<sizeonfloor;i++){
            if(dto.getObject().get(i).getItemQuantity()==0L){
                count++;
            }
        }
        sizenull = count;
        TextViewNullDrink.setText(String.valueOf(sizenull));
        sizeisfalse = sizeonfloor-(sizenull+sizeistrue);
        TextViewIsFalse.setText(String.valueOf(sizeisfalse));
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

                    setdataview(dto);

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

                    TextViewIsTrue.setText(String.valueOf(sizeistrue));

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
