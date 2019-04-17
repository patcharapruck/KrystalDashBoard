package dashboard.android.hdw.com.krystaldashboard.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.adapter.ProductListAdapter;
import dashboard.android.hdw.com.krystaldashboard.adapter.TopProductAdapter;
import dashboard.android.hdw.com.krystaldashboard.view.TopProductModelClass;

public class DrinkFragment extends Fragment {

    ArrayList<TopProductModelClass> items;
    TopProductAdapter adapter;
    RecyclerView recyclerView;

    ListView listProduct;
    ProductListAdapter productListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_drink,null);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        items = new ArrayList<>();
        adapter = new TopProductAdapter(getContext(), items);

        recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

        listProduct  = (ListView) rootView.findViewById(R.id.list_item_product);
        productListAdapter = new ProductListAdapter();
        listProduct.setAdapter(productListAdapter);
        // let's create 10 random items

        for (int i = 0; i < 10; i++) {
            items.add(new TopProductModelClass("aaaa","vbvb",""+i));
            adapter.notifyDataSetChanged();
        }
    }
}
