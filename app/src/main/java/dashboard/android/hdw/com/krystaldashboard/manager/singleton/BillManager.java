package dashboard.android.hdw.com.krystaldashboard.manager.singleton;

import android.content.Context;

import dashboard.android.hdw.com.krystaldashboard.dto.BillCollectionDto;
import dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus.PayItemColleationDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;

public class BillManager {

    private static BillManager instance;
    private BillCollectionDto billCollectionDto;
    public static BillManager getInstance() {
        if (instance == null)
            instance = new BillManager();
        return instance;
    }

    public BillCollectionDto getBillCollectionDto() {
        return billCollectionDto;
    }

    public void setBillCollectionDto(BillCollectionDto billCollectionDto) {
        this.billCollectionDto = billCollectionDto;
    }

    private Context mContext;

    private BillManager() {
        mContext = Contextor.getInstance().getmContext();
    }
}
