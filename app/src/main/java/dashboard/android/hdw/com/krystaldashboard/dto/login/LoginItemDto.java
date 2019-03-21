package dashboard.android.hdw.com.krystaldashboard.dto.login;

public class LoginItemDto {

    private Long statusCode;
    private String message;
    private LoginObjectDto object;

    public Long getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Long statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginObjectDto getObject() {
        return object;
    }

    public void setObject(LoginObjectDto object) {
        this.object = object;
    }
}
