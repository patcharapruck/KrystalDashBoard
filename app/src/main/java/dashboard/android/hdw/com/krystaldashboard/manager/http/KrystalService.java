package dashboard.android.hdw.com.krystaldashboard.manager.http;


import dashboard.android.hdw.com.krystaldashboard.dto.DashBoardDto;
import dashboard.android.hdw.com.krystaldashboard.dto.login.LoginItemDto;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface KrystalService {

    @POST("restaurant/v1/bpm/dashboard/summary")
    Call<DashBoardDto> loadAPI(@Body RequestBody json);

    @POST("restaurant/v1/salesshift/search")
    Call<Object> loadAPIcompare(@Body RequestBody json);

    @POST("restaurant/v1/invoicedocument/search")
    Call<Object> loadAPIPay(@Body RequestBody json);

    @POST("restaurant/v1/invoicedocument/search")
    Call<Object> loadAPINotPay(@Body RequestBody json);

    @POST("restaurant/v1/login")
    Call<LoginItemDto> loadAPIToken(@Body RequestBody login);
}
