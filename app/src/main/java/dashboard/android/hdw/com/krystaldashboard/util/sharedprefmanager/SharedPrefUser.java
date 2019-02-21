package dashboard.android.hdw.com.krystaldashboard.util.sharedprefmanager;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefUser {

    private static SharedPrefUser mInstance;
    private static Context mCtx;

    private static final String SHARED_LOGIN = "myaccount";
    private static final String SHARED_TOKEN = "myToken";
    private static final String KEY_USER = "user";
    private static final String KEY_PASS= "pass";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_REMEMBER = "remember";

    SharedPrefUser(Context context){
        mCtx = context;
    }

    public static synchronized SharedPrefUser getInstance(Context context){
        if (mInstance == null){
            mInstance = new SharedPrefUser(context);
        }
        return mInstance;
    }

    public boolean saveLogin(String user,String pass,String token,Boolean remember){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_LOGIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        SharedPreferences sharedPreferencestoken = mCtx.getSharedPreferences(SHARED_TOKEN, Context.MODE_PRIVATE);
        SharedPreferences.Editor usertoken = sharedPreferencestoken.edit();

        editor.putString(KEY_USER,user);
        editor.putString(KEY_PASS,pass);
        editor.putBoolean(KEY_REMEMBER,remember);

        usertoken.putString(KEY_TOKEN,token);

        editor.apply();
        usertoken.apply();

        return true;
    }

    public boolean logout(){
        SharedPreferences sharedPreferencestoken = mCtx.getSharedPreferences(SHARED_TOKEN, Context.MODE_PRIVATE);
        SharedPreferences.Editor usertoken = sharedPreferencestoken.edit();

        usertoken.clear();
        usertoken.apply();
        return true;
    }

    public String getUsername(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_LOGIN, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER,"");
    }
    public String getPassword(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_LOGIN, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PASS,"");
    }
    public String getToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_TOKEN, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_TOKEN,"");
    }

    public Boolean getRemember(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_LOGIN, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(KEY_REMEMBER,false);
    }


}
