package dashboard.android.hdw.com.krystaldashboard.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dashboard.android.hdw.com.krystaldashboard.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AmountDrinksFragment extends Fragment {


    public AmountDrinksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_amount_drinks, container, false);
    }

}
