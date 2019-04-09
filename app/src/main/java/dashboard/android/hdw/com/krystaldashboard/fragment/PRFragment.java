package dashboard.android.hdw.com.krystaldashboard.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.adapter.PRListAdapter;
import dashboard.android.hdw.com.krystaldashboard.adapter.PayMentAdapter;

public class PRFragment extends Fragment {
    
    ListView listViewPR;
    PRListAdapter prListAdapter;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pr,null);
        initInstances(rootView);
        return rootView;

    }

    private void initInstances(View rootView) {

        listViewPR = (ListView) rootView.findViewById(R.id.list_pr);

        prListAdapter = new PRListAdapter();
        listViewPR.setAdapter(prListAdapter);
    }
}
