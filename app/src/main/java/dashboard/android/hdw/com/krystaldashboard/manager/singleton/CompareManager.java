package dashboard.android.hdw.com.krystaldashboard.manager.singleton;

import android.content.Context;

import dashboard.android.hdw.com.krystaldashboard.dto.CompareCollectionDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;

public class CompareManager {

    private static CompareManager instance;
    private CompareCollectionDto compareDao;
    public static CompareManager getInstance() {
        if (instance == null)
            instance = new CompareManager();
        return instance;
    }

    private Context mContext;

    public CompareCollectionDto getCompareDao() {
        return compareDao;
    }

    public void setCompareDao(CompareCollectionDto compareDao) {
        this.compareDao = compareDao;
    }

    private CompareManager() {
        mContext = Contextor.getInstance().getmContext();
    }

}
