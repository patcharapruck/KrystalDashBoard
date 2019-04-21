package dashboard.android.hdw.com.krystaldashboard.fragment;


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
import dashboard.android.hdw.com.krystaldashboard.adapter.NotPayAdapter;

public class FragmentNotPay extends Fragment {

    ListView listNotpay;
    NotPayAdapter notPayAdapter;

    public FragmentNotPay() {
        super();
    }

    public static FragmentNotPay newInstance() {
        FragmentNotPay fragment = new FragmentNotPay();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_not_pay, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        listNotpay = (ListView)rootView.findViewById(R.id.list_notpay);
        notPayAdapter = new NotPayAdapter();
        notPayAdapter.notifyDataSetChanged();
        listNotpay.setAdapter(notPayAdapter);

        listNotpay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

}
