package dashboard.android.hdw.com.krystaldashboard.util.sharedprefmanager;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefDateManager {


    private static SharedPrefDateManager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "mydate";
    private static final String KEY_DATE = "date";
    private static final String KEY_DATE_OF_MONTH = "dateofmonth";
    private static final String KEY_MONTH= "month";
    private static final String KEY_YEAR = "year";
    private static final String KEY_REQ_DATE = "reqdate";
    private static final String KEY_DATE_FULL = "datefull";
    private static final String KEY_DATE_PAY = "datepayment";


    SharedPrefDateManager(Context context) {
        mCtx = context;

    }

    public static synchronized SharedPrefDateManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefDateManager(context);
        }
        return mInstance;
    }

    public boolean saveDateCalendar(int dayofmonth,int month,int year){

    SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_DATE_OF_MONTH,dayofmonth);
        editor.putInt(KEY_MONTH,month);
        editor.putInt(KEY_YEAR,year);

        editor.apply();

        return true;
}

    public boolean saveDatereq(String date,String date2){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_REQ_DATE,date);
        editor.putString(KEY_DATE_PAY ,date2);
        editor.apply();

        return true;
    }

    public boolean saveDateMax(String date){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_DATE,date);
        editor.apply();

        return true;
    }

    public boolean saveDateFull(String date){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_DATE_FULL,date);
        editor.apply();

        return true;
    }

    public boolean logoutDate(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    public int getDateofMonth(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_DATE_OF_MONTH,0);
    }
    public int getMonth(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_MONTH,0);
    }
    public int getYear(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_YEAR,0);
    }

    public String getreqDate(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_REQ_DATE,null);
    }

    public static String getKeyDate() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_DATE,null);
    }

    public static String getKeyDateFull() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_DATE_FULL,null);
    }

    public static String getKeyDatePay() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_DATE_PAY,null);
    }

}
