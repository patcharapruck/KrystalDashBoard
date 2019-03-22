package dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus;

import java.util.List;
import dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus.notpayment.NotPayItemDto;
import dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus.pagination.PaginationNotPayDto;

public class NotPayItemColleationDto {

    private Long statusCode;
    private String message;
    private PaginationNotPayDto pagination;
    private List<NotPayItemDto> object;


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

    public PaginationNotPayDto getPagination() {
        return pagination;
    }

    public void setPagination(PaginationNotPayDto pagination) {
        this.pagination = pagination;
    }

    public List<NotPayItemDto> getObject() {
        return object;
    }

    public void setObject(List<NotPayItemDto> object) {
        this.object = object;
    }
}
