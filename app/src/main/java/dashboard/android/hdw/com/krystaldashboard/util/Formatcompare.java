package dashboard.android.hdw.com.krystaldashboard.util;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Formatcompare implements IValueFormatter {

    private DecimalFormat mFormat;
    private String money;

    public Formatcompare() {
        mFormat = new DecimalFormat("#.##");
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {

        double s = value / 1000000.0;
        double s2 = value / 10000.0;
        BigDecimal bd1 = new BigDecimal(s).setScale(2, RoundingMode.FLOOR);
        BigDecimal bd2 = new BigDecimal(s2).setScale(2, RoundingMode.FLOOR);
        if (value > 999999) {
            money = mFormat.format(bd1)+"M";
        } else if (value > 99999){
            money = String.format("%dK",(int) (value / 1000.0));
        } else if (value > 9999){
            money = mFormat.format(bd2)+"K";;
        } else if (value > 999){
            money = String.format("%.2fK",(value / 100000.0));
        } else if (value == 0){
            money = String.format("0");
        }

        return money;
    }
}
