package dashboard.android.hdw.com.krystaldashboard.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus.NotPayItemColleationDto;
import dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus.PayItemColleationDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;
import dashboard.android.hdw.com.krystaldashboard.manager.http.HttpManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.NotPayManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.PayManager;
import dashboard.android.hdw.com.krystaldashboard.util.sharedprefmanager.SharedPrefDateManager;
import dashboard.android.hdw.com.krystaldashboard.util.sharedprefmanager.SharedPrefDatePayManager;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableFragment extends Fragment {
    Spinner spins;
    private ArrayList<String> mTypeSearch = new ArrayList<String>();

    TextView textViewTable;
    EditText EdittextSearchTable;

    ViewPager viewPager;
    TabLayout tabLayout;
    SectionsPagerAdapter mSectionsPagerAdapter;

    String typeSearch = "";
    String dataSearch = "";

    int ch=0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_table,null);
        initInstances(rootView);
        return rootView;
    }
    private void createTypeSearchData() {

        if (mTypeSearch.isEmpty()){
            mTypeSearch.add("เลขที่เอกสาร");
            mTypeSearch.add("Table/Room");
        }
    }

    private void initInstances(View rootView) {

        viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        spins = (Spinner) rootView.findViewById(R.id.spins);
        textViewTable = (TextView) rootView.findViewById(R.id.textview_serach_table);
        EdittextSearchTable = (EditText) rootView.findViewById(R.id.edittext_search_table);

        setPager(dataSearch);

        createTypeSearchData();

        ArrayAdapter<String> spinsSearch = new ArrayAdapter<String>(getContext()
                ,R.layout.support_simple_spinner_dropdown_item,mTypeSearch);
        spins.setAdapter(spinsSearch);

        spins.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(mTypeSearch.get(position).equals("เลขที่เอกสาร")){
                    typeSearch = "InvoiceDocument-invoiceCode";
                    textViewTable.setText("เลขที่เอกสาร");
                }else if (mTypeSearch.get(position).equals("Table/Room")){
                    typeSearch = "Place-placeCode";
                    textViewTable.setText("Table/Room");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        EdittextSearchTable.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dataSearch = ","+typeSearch+":\""+s+"\"";
                setPager(dataSearch);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new FragmentPay(dataSearch);
                default:
                    return new FragmentNotPay(dataSearch);
            }
        }
        @Override
        public int getCount() {
            return 2;
        }
    }

    private void setPager(String s) {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
        viewPager = (ViewPager) viewPager.findViewById(R.id.pager);
        viewPager.setAdapter(mSectionsPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {

        }
    }

    //    private void reqAPInotpay(String date, final View rootView) {
//        checkNotPay = false;
//        final Context mcontext = Contextor.getInstance().getmContext();
//        String nn = "{\"criteria\":{\"sql-obj-command\":\"f:documentStatus.id = 22 and " +
//                "(f:salesShift.openDate >= '"+date+" 00:00:00' AND f:salesShift.openDate <= '"+date+" 23:59:59')\"}," +
//                "\"property\":[\"memberAccount->customerMemberAccount\",\"sales->employee\",\"place\",\"transactionPaymentList\",\"documentStatus\",\"salesShift\"]," +
//                "\"pagination\":{},\"orderBy\":{\"InvoiceDocument-id\":\"DESC\"}}";
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),nn);
//        Call<NotPayItemColleationDto> call = HttpManager.getInstance().getService().loadAPINotPay(requestBody);
//        call.enqueue(new Callback<NotPayItemColleationDto>() {
//            @Override
//            public void onResponse(Call<NotPayItemColleationDto> call, Response<NotPayItemColleationDto> response) {
//                if(response.isSuccessful()){
//                    NotPayItemColleationDto dao = response.body();
//                    NotPayManager.getInstance().setNotpayItemColleationDao(dao);
//
//                    checkNotPay = true;
//
//                    SharedPrefDatePayManager.getInstance(Contextor.getInstance().getmContext())
//                            .saveNotPay(dao.getPagination().getTotalItem());
//
//                    if(checkPay == true && checkNotPay == true){
//                        progress.dismiss();
//                        initInstances(rootView);
//                    }
//                }else {
//                    progress.dismiss();
//                    Toast.makeText(mcontext,"เกิดข้อผิดพลาด",Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<NotPayItemColleationDto> call, Throwable t) {
//                progress.dismiss();
//                Toast.makeText(mcontext,"ไม่สามารถเชื่อมต่อได้",Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }
//
//    private void reqAPIpay(String date, final View rootView) {
//        checkPay = false;
//        final Context mcontext = Contextor.getInstance().getmContext();
//        String nn = "{\"criteria\":{\"sql-obj-command\":\"f:documentStatus.id = 21 and " +
//                "(f:salesShift.openDate >= '"+date+" 00:00:00' AND f:salesShift.openDate <= '"+date+" 23:59:59')\"}," +
//                "\"property\":[\"memberAccount->customerMemberAccount\",\"sales->employee\",\"place\",\"transactionPaymentList\",\"documentStatus\",\"salesShift\"]," +
//                "\"pagination\":{},\"orderBy\":{\"InvoiceDocument-id\":\"DESC\"}}";
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),nn);
//        Call<PayItemColleationDto> call = HttpManager.getInstance().getService().loadAPIPay(requestBody);
//        call.enqueue(new Callback<PayItemColleationDto>() {
//            @Override
//            public void onResponse(Call<PayItemColleationDto> call, Response<PayItemColleationDto> response) {
//                if(response.isSuccessful()){
//                    PayItemColleationDto dao = response.body();
//                    PayManager.getInstance().setPayItemColleationDao(dao);
//
//                    checkPay = true;
//
//                    SharedPrefDatePayManager.getInstance(Contextor.getInstance().getmContext())
//                            .savePay(dao.getPagination().getTotalItem());
//
//                    if(checkPay == true && checkNotPay == true){
//                        progress.dismiss();
//                        initInstances(rootView);
//                    }
//
//                }else {
//                    progress.dismiss();
//                    Toast.makeText(mcontext,"เกิดข้อผิดพลาด",Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PayItemColleationDto> call, Throwable t) {
//                progress.dismiss();
//                Toast.makeText(mcontext,"ไม่สามารถเชื่อมต่อได้",Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }

//    private void showProgress() {
//        progress = new ProgressDialog(getContext());
//        progress.setTitle("Loading");
//        progress.setMessage("Wait while loading...");
//        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
//        progress.show();
//    }
}
