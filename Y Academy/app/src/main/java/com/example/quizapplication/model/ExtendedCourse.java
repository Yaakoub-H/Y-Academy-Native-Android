package com.example.quizapplication.model;

public class ExtendedCourse {

    private int courseId;  // Added courseId property
    private int numberOfClasses;
    private int numberOfHours;
    private String about;
    private String instructorName;
    private String difficultyLevel;
    private int numberOfQuizzes;

    public ExtendedCourse(int courseId, int numberOfClasses, int numberOfHours,
                          String about, String instructorName, String difficultyLevel, int numberOfQuizzes) {
        this.courseId = courseId;
        this.numberOfClasses = numberOfClasses;
        this.numberOfHours = numberOfHours;
        this.about = about;
        this.instructorName = instructorName;
        this.difficultyLevel = difficultyLevel;
        this.numberOfQuizzes = numberOfQuizzes;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    public void setNumberOfClasses(int numberOfClasses) {
        this.numberOfClasses = numberOfClasses;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public int getNumberOfQuizzes() {
        return numberOfQuizzes;
    }

    public void setNumberOfQuizzes(int numberOfQuizzes) {
        this.numberOfQuizzes = numberOfQuizzes;
    }
}
