package dashboard.android.hdw.com.krystaldashboard.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.activty.BillActivity;
import dashboard.android.hdw.com.krystaldashboard.adapter.PayMentAdapter;
import dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus.PayItemColleationDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;
import dashboard.android.hdw.com.krystaldashboard.manager.http.HttpManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.PayManager;
import dashboard.android.hdw.com.krystaldashboard.util.sharedprefmanager.SharedPrefDateManager;
import dashboard.android.hdw.com.krystaldashboard.util.sharedprefmanager.SharedPrefDatePayManager;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPay extends Fragment {

    ListView listViewPay;
    PayMentAdapter payMentAdapter;

    public FragmentPay() {
            super();
    }


    public static FragmentPay newInstance() {
        FragmentPay fragment = new FragmentPay();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pay, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        listViewPay = (ListView) rootView.findViewById(R.id.list_pay);

        listViewPay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = (String) listViewPay.getItemAtPosition(position);
//                Toast.makeText(getContext(),"You selected : " + parent.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), BillActivity.class);
                getContext().startActivity(intent);
            }
        });
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

    @Override
    public void onStart() {
        super.onStart();
        reqAPIpay(SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getKeyDatePay());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void reqAPIpay(String date) {
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

                    payMentAdapter = new PayMentAdapter();
                    payMentAdapter.notifyDataSetChanged();
                    listViewPay.setAdapter(payMentAdapter);

                    SharedPrefDatePayManager.getInstance(Contextor.getInstance().getmContext())
                            .savePay(dao.getPagination().getTotalItem());

                }else {
                    Toast.makeText(mcontext,"เกิดข้อผิดพลาด",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PayItemColleationDto> call, Throwable t) {
                Toast.makeText(mcontext,"ไม่สามารถเชื่อมต่อได้",Toast.LENGTH_LONG).show();
            }
        });

    }
}
