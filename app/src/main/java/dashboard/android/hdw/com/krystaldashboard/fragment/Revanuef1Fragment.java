package dashboard.android.hdw.com.krystaldashboard.fragment;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import dashboard.android.hdw.com.krystaldashboard.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Revanuef1Fragment extends Fragment {


    public Revanuef1Fragment() {
        // Required empty public constructor
    }
        ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_revanue_f1,container,false);
        initInstances(rootView);
        return rootView;




    }

    private void initInstances(View rootView) {

        progressBar = rootView.findViewById(R.id.horizontal_progress_bar);
        progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#7F6CFF"), PorterDuff.Mode.SRC_IN);


    }

}
