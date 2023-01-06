package fr.greta.domes.controller.animal;

public class AnimalSearchQuery {
    private double minPrice;
    private double maxPrice;
    private int minAge;
    private int maxAge;
    private String categoryName;
    private String specieName;

    private int pageNumber;
    private int pageSize;

    public AnimalSearchQuery() {
    }

    public AnimalSearchQuery(double minPrice, double maxPrice, int minAge, int maxAge, String categoryName, String specieName, int pageNumber, int pageSize) {
        this.minPrice = minPrice;
        setMaxPrice(maxPrice);
        this.minAge = minAge;
        setMaxAge(maxAge);
        setCategoryName(categoryName);
        setSpecieName(specieName);
        setPageNumber(pageNumber);
        this.pageSize = pageSize;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        if (categoryName.equals("TOUTES")) {
            this.categoryName = "";
            return;
        }
        this.categoryName = categoryName;
    }

    public String getSpecieName() {
        return specieName;
    }

    public void setSpecieName(String specieName) {
        if (specieName == null || specieName.equals("TOUTES")) {
            this.specieName = "";
            return;
        }
        this.specieName = specieName;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        if (maxPrice <= 1.0) {
            this.maxPrice = 999.99;
            return;
        }
        this.maxPrice = maxPrice;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        if (maxAge <= 1) {
            this.maxAge = 999;
            return;
        }
        this.maxAge = maxAge;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber - 1;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "AnimalSearchQuery{" +
                "CategoryName='" + categoryName + '\'' +
                ", specieName='" + specieName + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }
}
