package dashboard.android.hdw.com.krystaldashboard.util;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

public class Formatcompare implements IValueFormatter {

    private DecimalFormat mFormat;
    private String money;

    public Formatcompare() {
        mFormat = new DecimalFormat("###,###,##0.00");
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        if (value > 999999) {
            money = String.format("%.2fM", value / 1000000.0);
        } else {
            money = String.format("%.2fK", value / 100000.0);
        }
        return money;
    }
}
