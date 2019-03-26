package dashboard.android.hdw.com.krystaldashboard.fragment.dialogfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dashboard.android.hdw.com.krystaldashboard.R;

public class DialogCraditFragment extends DialogFragment {

    private static final String TAG = "DialogCraditFragment";

    TextView aMaxTextView,jcbTextView,masterTextView,unipayTextView,visaTextView;

    public DialogCraditFragment(){
        super();
    }

    public static DialogCraditFragment newInstance() {
        DialogCraditFragment fragment = new DialogCraditFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialogpopup, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {

        aMaxTextView = (TextView) rootView.findViewById(R.id.textview_amax);
        jcbTextView = (TextView) rootView.findViewById(R.id.textview_jcb);
        masterTextView = (TextView) rootView.findViewById(R.id.textview_master);
        unipayTextView = (TextView) rootView.findViewById(R.id.textview_unipay);
        visaTextView = (TextView) rootView.findViewById(R.id.textview_visa);

        
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
