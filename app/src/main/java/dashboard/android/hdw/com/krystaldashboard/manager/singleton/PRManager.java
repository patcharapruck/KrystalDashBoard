package dashboard.android.hdw.com.krystaldashboard.manager.singleton;

import android.content.Context;

import dashboard.android.hdw.com.krystaldashboard.dto.CompareCollectionDto;
import dashboard.android.hdw.com.krystaldashboard.dto.pr.PRItemCollectionDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;

public class PRManager {

    private static PRManager instance;
    private CompareCollectionDto pr;
    public static PRManager getInstance() {
        if (instance == null)
            instance = new PRManager();
        return instance;
    }

    private Context mContext;

    public CompareCollectionDto getPr() {
        return pr;
    }

    public void setPr(PRItemCollectionDto pr) {
        this.pr = pr;
    }

    private PRManager() {
        mContext = Contextor.getInstance().getmContext();
    }

}
