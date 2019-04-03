package com.mycompany.p1;

public final class MovieRevenue {

    private String title;
    private Double revenue;

    public MovieRevenue(String title, Double revenue){
        this.title = title;
        this.revenue = revenue;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(final Double revenue) {
        this.revenue = revenue;
    }

}
