package dashboard.android.hdw.com.krystaldashboard.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.adapter.PayMentAdapter;

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
        payMentAdapter = new PayMentAdapter();
        payMentAdapter.notifyDataSetChanged();
        listViewPay.setAdapter(payMentAdapter);

        listViewPay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
}
