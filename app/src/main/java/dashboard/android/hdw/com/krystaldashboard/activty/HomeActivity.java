package dashboard.android.hdw.com.krystaldashboard.activty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Calendar;

import dashboard.android.hdw.com.krystaldashboard.R;

public class HomeActivity extends AppCompatActivity {

    private long pausedMillis;
    private Boolean c = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        pausedMillis = Calendar.getInstance().getTimeInMillis();
        c = true;
    }


    @Override
    protected void onResume() {
        super.onResume();

        try {
            long currentMillis = Calendar.getInstance().getTimeInMillis();
            long aa = currentMillis - pausedMillis;
            if ( c == true && currentMillis - pausedMillis  > 1000 * 60 * 1 ) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                System.out.println("trueeeeeeee");
                // Toast.makeText(HomeActivity.this, "Log out", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("falseeeeeee");
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
