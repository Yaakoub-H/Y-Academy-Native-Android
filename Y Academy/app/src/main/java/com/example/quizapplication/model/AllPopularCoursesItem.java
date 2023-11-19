// AllPopularCoursesItem.java
package com.example.quizapplication.model;

public class AllPopularCoursesItem {

    private String categoryTitle;
    private int iconResource;
    private String textDescription;
    private String priceValue;
    private String textPrice;

    public AllPopularCoursesItem(String categoryTitle, int iconResource, String textDescription, String priceValue, String textPrice) {
        this.categoryTitle = categoryTitle;
        this.iconResource = iconResource;
        this.textDescription = textDescription;
        this.priceValue = priceValue;
        this.textPrice = textPrice;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public int getIconResource() {
        return iconResource;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public String getPriceValue() {
        return priceValue;
    }

    public String getTextPrice() {
        return textPrice;
    }
}
