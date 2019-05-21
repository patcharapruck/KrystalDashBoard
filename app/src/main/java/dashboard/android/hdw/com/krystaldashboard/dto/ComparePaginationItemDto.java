package dashboard.android.hdw.com.krystaldashboard.dto;

public class ComparePaginationItemDto {

    private Long totalItem;
    private Long limitItem;
    private Long currentPage;

    public Long getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Long totalItem) {
        this.totalItem = totalItem;
    }

    public Long getLimitItem() {
        return limitItem;
    }

    public void setLimitItem(Long limitItem) {
        this.limitItem = limitItem;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }
}
