package dashboard.android.hdw.com.krystaldashboard.util;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

public class MyYxisValueFormatter implements IAxisValueFormatter {
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        String money = "0";
        money = String.format("%.2f", value / 1000000.0);
        return money;
    }
}
