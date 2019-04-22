package dashboard.android.hdw.com.krystaldashboard.activty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.fragment.BillFragment;

public class BillActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_bill, BillFragment.newInstance())
                    .commit();
        }

        initInstances();
    }

    private void initInstances() {
    }
}
