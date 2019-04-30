package dashboard.android.hdw.com.krystaldashboard.manager.singleton;

import android.content.Context;

import dashboard.android.hdw.com.krystaldashboard.dto.bill.BillArrayDto;
import dashboard.android.hdw.com.krystaldashboard.dto.product.ProductSortDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;

public class BillArrayManager {

    private static BillArrayManager instance;

    private BillArrayDto billArrayDto;

    public static BillArrayManager getInstance() {
        if (instance == null)
            instance = new BillArrayManager();
        return instance;
    }

    public BillArrayDto getBillArrayDto() {
        return billArrayDto;
    }

    public void setBillArrayDto(BillArrayDto billArrayDto) {
        this.billArrayDto = billArrayDto;
    }

    private Context mContext;

    private BillArrayManager() {
        mContext = Contextor.getInstance().getmContext();
    }

}
