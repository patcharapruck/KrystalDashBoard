package dashboard.android.hdw.com.krystaldashboard.manager.singleton;

import android.content.Context;

import dashboard.android.hdw.com.krystaldashboard.dto.DashBoardDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;

public class DashBoradManager {

    private static DashBoradManager instance;
    private DashBoardDto Dto;
    public static DashBoradManager getInstance() {
        if (instance == null)
            instance = new DashBoradManager();
        return instance;
    }



    public DashBoardDto getDto() {
        return Dto;
    }

    public void setDto(DashBoardDto dto) {
        Dto = dto;
    }

    private Context mContext;

    private DashBoradManager() {
        mContext = Contextor.getInstance().getmContext();
    }
}
