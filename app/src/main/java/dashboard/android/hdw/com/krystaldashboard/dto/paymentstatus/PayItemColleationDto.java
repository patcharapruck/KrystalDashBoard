package dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus;

import java.util.List;

import dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus.pagination.PaginationPayDto;
import dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus.payment.PayItemDto;

public class PayItemColleationDto {

    private Long statusCode;
    private String message;
    private PaginationPayDto pagination;
    private List<PayItemDto> object;

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

    public PaginationPayDto getPagination() {
        return pagination;
    }

    public void setPagination(PaginationPayDto pagination) {
        this.pagination = pagination;
    }

    public List<PayItemDto> getObject() {
        return object;
    }

    public void setObject(List<PayItemDto> object) {
        this.object = object;
    }
}
