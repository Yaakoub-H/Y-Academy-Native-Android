package com.example.quizapplication.model;

public class Course {

    private long courseId;
    private String courseName;
    private String category;
    private double price;
    private double rate;
    private int studentCount;
    private boolean isPopular;

    public Course(long courseId, String courseName, String category, double price, double rate, int studentCount, boolean isPopular) {
        this.courseId = courseId;

        this.courseName = courseName;
        this.category = category;
        this.price = price;
        this.rate = rate;
        this.studentCount = studentCount;
        this.isPopular = isPopular;
    }

    public Course(int courseId, String courseName ) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public Course(int courseId, String courseName, String category, double price, double rate) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.category = category;
        this.price = price;
        this.rate = rate;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }


    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public void setPopular(boolean popular) {
        isPopular = popular;
    }

    public long getCourseId() {
        return courseId;
    }


    public String getCourseName() {
        return courseName;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public double getRate() {
        return rate;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public boolean isPopular() {
        return isPopular;
    }
}
