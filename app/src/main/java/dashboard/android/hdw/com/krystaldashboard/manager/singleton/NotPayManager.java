package dashboard.android.hdw.com.krystaldashboard.manager.singleton;

import android.content.Context;

import dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus.NotPayItemColleationDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;

public class NotPayManager {

    private static NotPayManager instance;
    private NotPayItemColleationDto notpayItemColleationDao;
    public static NotPayManager getInstance() {
        if (instance == null)
            instance = new NotPayManager();
        return instance;
    }

    public NotPayItemColleationDto getNotpayItemColleationDao() {
        return notpayItemColleationDao;
    }

    public void setNotpayItemColleationDao(NotPayItemColleationDto notpayItemColleationDao) {
        this.notpayItemColleationDao = notpayItemColleationDao;
    }

    private Context mContext;

    private NotPayManager() {
        mContext = Contextor.getInstance().getmContext();
    }
}
