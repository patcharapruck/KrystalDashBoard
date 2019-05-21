package dashboard.android.hdw.com.krystaldashboard.manager.singleton;

import android.content.Context;

import dashboard.android.hdw.com.krystaldashboard.dto.DateDto;
import dashboard.android.hdw.com.krystaldashboard.dto.product.ProductSortDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;

public class DateManager {

    private static DateManager instance;

    private DateDto dateDto;

    public static DateManager getInstance() {
        if (instance == null)
            instance = new DateManager();
        return instance;
    }

    public DateDto getDateDto() {
        return dateDto;
    }

    public void setDateDto(DateDto dateDto) {
        this.dateDto = dateDto;
    }

    private Context mContext;

    private DateManager() {
        mContext = Contextor.getInstance().getmContext();
    }

}
