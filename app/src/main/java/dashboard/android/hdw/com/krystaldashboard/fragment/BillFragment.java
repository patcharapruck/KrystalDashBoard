package dashboard.android.hdw.com.krystaldashboard.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dashboard.android.hdw.com.krystaldashboard.R;

public class BillFragment extends Fragment {

    public BillFragment() {
        super();
    }

    public static BillFragment newInstance() {
        BillFragment fragment = new BillFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bill, container, false);
        initInstances(rootView);
        return rootView;

    }

    private void initInstances(View rootView) {
    }
}
