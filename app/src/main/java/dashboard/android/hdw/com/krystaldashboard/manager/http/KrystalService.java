package dashboard.android.hdw.com.krystaldashboard.manager.http;


import dashboard.android.hdw.com.krystaldashboard.dao.login.LoginItemDao;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface KrystalService {

    @POST("restaurant/v1/bpm/dashboard/summary")
    Call<Object> loadAPI(@Body RequestBody json);

    @POST("restaurant/v1/salesshift/search")
    Call<Object> loadAPIcompare(@Body RequestBody json);

    @POST("restaurant/v1/invoicedocument/search")
    Call<Object> loadAPIPay(@Body RequestBody json);

    @POST("restaurant/v1/invoicedocument/search")
    Call<Object> loadAPINotPay(@Body RequestBody json);

    @POST("restaurant/v1/login")
    Call<LoginItemDao> loadAPIToken(@Body RequestBody login);
}
