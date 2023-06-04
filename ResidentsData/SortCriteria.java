package ResidentsData;

public class SortCriteria {
    private String field;
    private String sortOrder;

    public SortCriteria(String field, String sortOrder) {
        this.field = field;
        this.sortOrder = sortOrder;
    }

    public String getField() {
        return field;
    }

    public String getSortOrder() {
        return sortOrder;
    }
}
