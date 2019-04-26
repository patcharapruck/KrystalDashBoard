package dashboard.android.hdw.com.krystaldashboard.activty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.fragment.BillFragment;

public class BillActivity extends AppCompatActivity {

    Toolbar toolbar;

    String CodeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        Intent id = new Intent();
        CodeId = id.getStringExtra("Codeid");

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_bill, BillFragment.newInstance(CodeId))
                    .commit();
        }

        initInstances();
    }

    private void initInstances() {

        toolbar = findViewById(R.id.tbBill);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
