package dashboard.android.hdw.com.krystaldashboard.dto.pr;

import java.util.List;


public class PRItemCollectionDto {

    private Long statusCode;
    private String message;
    private Object pagination;
    private List<PRItemDto> object;

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

    public Object getPagination() {
        return pagination;
    }

    public void setPagination(Object pagination) {
        this.pagination = pagination;
    }

    public List<PRItemDto> getObject() {
        return object;
    }

    public void setObject(List<PRItemDto> object) {
        this.object = object;
    }
}
