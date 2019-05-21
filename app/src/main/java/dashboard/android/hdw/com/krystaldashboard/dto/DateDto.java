package dashboard.android.hdw.com.krystaldashboard.dto;

import java.util.Calendar;
import java.util.Date;

public class DateDto {

    private Calendar calendar;
    private Date dateToday;

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Date getDateToday() {
        return dateToday;
    }

    public void setDateToday(Date dateToday) {
        this.dateToday = dateToday;
    }
}
