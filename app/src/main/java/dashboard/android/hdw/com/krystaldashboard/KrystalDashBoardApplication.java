package dashboard.android.hdw.com.krystaldashboard;

import android.app.Application;

import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;

public class KrystalDashBoardApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
