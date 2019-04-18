package dashboard.android.hdw.com.krystaldashboard.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.ArrayList;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.adapter.TopProductAdapter;
import dashboard.android.hdw.com.krystaldashboard.view.TopProductModelClass;

public class DrinkFragment extends Fragment {


    CardView cardView;
    ExpandableRelativeLayout mycontent;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_drink,null);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        ArrayList<TopProductModelClass> items = new ArrayList<>();
        TopProductAdapter adapter = new TopProductAdapter(getContext(), items);

        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView.setAdapter(adapter);

        // let's create 10 random items

        for (int i = 0; i < 10; i++) {
            items.add(new TopProductModelClass("aaaa","vbvb","fff"));
            adapter.notifyDataSetChanged();
        }

// Expandable card
        cardView = (CardView) rootView.findViewById(R.id.carddrink);
        mycontent = (ExpandableRelativeLayout)rootView.findViewById(R.id.mycontent);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mycontent.toggle();
            }
        });

    }



}
