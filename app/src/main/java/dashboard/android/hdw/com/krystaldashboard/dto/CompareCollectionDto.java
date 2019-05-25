package dashboard.android.hdw.com.krystaldashboard.dto;

import java.util.List;

public class CompareCollectionDto {

    private Long statusCode;
    private String message;
    private ComparePaginationItemDto pagination;
    private List<ObjectListItemDto> object;

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

    public ComparePaginationItemDto getPagination() {
        return pagination;
    }

    public void setPagination(ComparePaginationItemDto pagination) {
        this.pagination = pagination;
    }

    public List<ObjectListItemDto> getObject() {
        return object;
    }

    public void setObject(List<ObjectListItemDto> object) {
        this.object = object;
    }
}
