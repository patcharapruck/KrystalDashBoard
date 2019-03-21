package dashboard.android.hdw.com.krystaldashboard.dto;

public class DashBoardDto {

    private Long statusCode;
    private String message;
    private ObjectItemDto object;

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

    public ObjectItemDto getObject() {
        return object;
    }

    public void setObject(ObjectItemDto object) {
        this.object = object;
    }
}
