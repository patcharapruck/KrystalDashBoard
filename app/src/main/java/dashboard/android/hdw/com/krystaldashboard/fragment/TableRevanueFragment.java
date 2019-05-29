package dashboard.android.hdw.com.krystaldashboard.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import dashboard.android.hdw.com.krystaldashboard.R;

public class TableRevanueFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_table_revanue,null);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {

        viewPager = (ViewPager) rootView.findViewById(R.id.pager_revanus);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs_revanus);
        setPager();
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new Revanuef2Fragment1();
                default:
                    return new Revanuef2Fragment2();
            }
        }
        @Override
        public int getCount() {
            return 2;
        }
    }

    private void setPager() {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
        viewPager = (ViewPager) viewPager.findViewById(R.id.pager_revanus);
        viewPager.setAdapter(mSectionsPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
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
