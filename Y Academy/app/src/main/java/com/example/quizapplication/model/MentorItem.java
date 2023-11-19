package com.example.quizapplication.model;

public class MentorItem {
    public int getImageResource() {
        return imageResource;
    }

    public String getName() {
        return name;
    }
    public String getCategoryType() {
        return categoryType;
    }

    private int imageResource;
    private String name;
    private String categoryType;

    public MentorItem(int imageResource, String name) {
        this.imageResource = imageResource;
        this.name = name;
    }

    public MentorItem(String name, String categoryType) {
        this.name = name;
        this.categoryType = categoryType;
    }

    // Add getters and setters as needed
}
