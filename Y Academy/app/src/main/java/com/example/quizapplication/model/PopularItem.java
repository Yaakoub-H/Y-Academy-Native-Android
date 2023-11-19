package com.example.quizapplication.model;

public class PopularItem {
    private String title;
    private String description;
    private String price;
    private int iconResource;
    private double rating;
    private String studentCount;

    public PopularItem(String title, String description, String price, int iconResource, double rating, String studentCount) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.iconResource = iconResource;
        this.rating = rating;
        this.studentCount = studentCount;
    }

    // Getters for the new fields
    public double getRating() {
        return rating;
    }

    public String getStudentCount() {
        return studentCount;
    }

//    public int getImageResource() {
//        return imageResource;
//    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public int getIconResource() { // Add this method
        return iconResource;
    }
}
