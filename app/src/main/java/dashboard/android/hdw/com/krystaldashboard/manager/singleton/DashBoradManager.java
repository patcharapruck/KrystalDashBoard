package dashboard.android.hdw.com.krystaldashboard.manager.singleton;

import android.content.Context;

import dashboard.android.hdw.com.krystaldashboard.dto.DashBoardDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;

public class DashBoradManager {

    private static DashBoradManager instance;
    private DashBoardDto Dao;
    public static DashBoradManager getInstance() {
        if (instance == null)
            instance = new DashBoradManager();
        return instance;
    }



    public DashBoardDto getDao() {
        return Dao;
    }

    public void setDao(DashBoardDto dao) {
        Dao = dao;
    }

    private Context mContext;

    private DashBoradManager() {
        mContext = Contextor.getInstance().getmContext();
    }
}
