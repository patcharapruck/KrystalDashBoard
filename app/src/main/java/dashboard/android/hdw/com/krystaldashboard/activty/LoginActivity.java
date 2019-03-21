package dashboard.android.hdw.com.krystaldashboard.activty;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.dto.login.LoginItemDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;
import dashboard.android.hdw.com.krystaldashboard.manager.http.HttpLoginManager;
import dashboard.android.hdw.com.krystaldashboard.util.sharedprefmanager.SharedPrefUser;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    TextInputEditText username,password;



    CheckBox cbRemember;

    CardView btnlogin;
    TextView tvLogin;

    String user="",
            pass="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logiin);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        InitInstances();
    }

    private void InitInstances() {
        username = (TextInputEditText) findViewById(R.id.userId);
        password = (TextInputEditText) findViewById(R.id.passId);
        btnlogin = (CardView) findViewById(R.id.btnLogin);
        tvLogin = (TextView) findViewById(R.id.tvLogin);
        cbRemember = (CheckBox) findViewById(R.id.cbRemember);

        cbRemember.setChecked(SharedPrefUser.getInstance(Contextor.getInstance().getmContext()).getRemember());

        if(SharedPrefUser.getInstance(Contextor.getInstance().getmContext()).getRemember()==true){
            username.setText(SharedPrefUser.getInstance(Contextor.getInstance().getmContext()).getUsername());
            password.setText(SharedPrefUser.getInstance(Contextor.getInstance().getmContext()).getPassword());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnlogin.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    public void onClick(View v) {
        if(v == btnlogin||v == tvLogin){
            user =  username.getText().toString();
            pass =  password.getText().toString();
            Boolean aBoolean = cbRemember.isChecked();

            reqLogin(user,pass,aBoolean);
        }
    }

    public void reqLogin(final String user, final String pass, final Boolean aBoolean) {

        final Context mcontext = Contextor.getInstance().getmContext();
        String nn = "{\"authentication\":{\"username\":\""+user+"\",\"password\":\""+pass+"\"}}";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),nn);
        Call<LoginItemDto> call = HttpLoginManager.getInstance().getService().loadAPIToken(requestBody);
        call.enqueue(new Callback<LoginItemDto>() {

            @Override
            public void onResponse(Call<LoginItemDto> call, Response<LoginItemDto> response) {

                if(response.isSuccessful()){

                    if(response.body().getStatusCode() == 200){
                        LoginItemDto dao = response.body();
                        SharedPrefUser.getInstance(mcontext)
                                .saveLogin(user,pass,dao.getObject().getAuthentication().getAccessToken(),aBoolean);


                        //Toast.makeText(mcontext,"เข้าสู่ระบบ",Toast.LENGTH_LONG).show();

                        if(SharedPrefUser.getInstance(mcontext).getToken().length()>0){
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            finish();
                            startActivity(intent);
                        }
                    }

                    else if (response.body().getStatusCode() == 404){
                        Toast.makeText(mcontext,"รหัสผ่านไม่ถูกต้อง",Toast.LENGTH_LONG).show();
                    }


                }else {
                    Toast.makeText(mcontext,"เกิดข้อผิดพลาด",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<LoginItemDto> call, Throwable t) {
                Toast.makeText(mcontext,"ไม่สามารถเชื่อมต่อกับข้อมูลได้",Toast.LENGTH_LONG).show();
            }
        });
    }
}
