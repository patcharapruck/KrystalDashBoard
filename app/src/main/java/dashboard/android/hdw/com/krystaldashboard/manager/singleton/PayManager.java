package dashboard.android.hdw.com.krystaldashboard.manager.singleton;

import android.content.Context;

import dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus.PayItemColleationDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;

public class PayManager {

    private static PayManager instance;
    private PayItemColleationDto payItemColleationDao;
    public static PayManager getInstance() {
        if (instance == null)
            instance = new PayManager();
        return instance;
    }

    public PayItemColleationDto getPayItemColleationDao() {
        return payItemColleationDao;
    }

    public void setPayItemColleationDao(PayItemColleationDto payItemColleationDao) {
        this.payItemColleationDao = payItemColleationDao;
    }

    private Context mContext;

    private PayManager() {
        mContext = Contextor.getInstance().getmContext();
    }
}
