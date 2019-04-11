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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

    ViewPager viewPager;
    TabLayout tabLayout;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private Boolean checkPay = false,checkNotPay=false ;
    ProgressDialog progress;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showProgress();
        reqAPIpay(SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getKeyDatePay());
        reqAPInotpay(SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getKeyDatePay());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_table,null);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {

        viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        setPager();
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FragmentPay();
                default:
                    return new FragmentNotPay();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    private void setPager() {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        viewPager = (ViewPager) viewPager.findViewById(R.id.pager);
        viewPager.setAdapter(mSectionsPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
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

                    if(checkPay == true && checkNotPay == true){
                        progress.dismiss();
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

                    if(checkPay == true && checkNotPay == true){
                        progress.dismiss();
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
}
