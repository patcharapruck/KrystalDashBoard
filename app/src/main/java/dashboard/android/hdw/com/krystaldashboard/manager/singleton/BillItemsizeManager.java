package dashboard.android.hdw.com.krystaldashboard.manager.singleton;

import android.content.Context;

import dashboard.android.hdw.com.krystaldashboard.dto.BillCollectionDto;
import dashboard.android.hdw.com.krystaldashboard.dto.bill.Sizedto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;

public class BillItemsizeManager {

    private static BillItemsizeManager instance;
    private Sizedto sizedto;
    public static BillItemsizeManager getInstance() {
        if (instance == null)
            instance = new BillItemsizeManager();
        return instance;
    }

    public Sizedto getSizedto() {
        return sizedto;
    }

    public void setSizedto(Sizedto sizedto) {
        this.sizedto = sizedto;
    }

    private Context mContext;

    private BillItemsizeManager() {
        mContext = Contextor.getInstance().getmContext();
    }
}
