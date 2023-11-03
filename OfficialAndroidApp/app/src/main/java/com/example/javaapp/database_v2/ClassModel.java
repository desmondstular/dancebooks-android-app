package com.example.javaapp.database_v2;

public class ClassModel {
    private String className;
    private int year;
    private float cost;
    private int capacity;
    private int enrolled;

    // CLASS MODEL CONSTRUCTOR
    public ClassModel(String className, int year, float cost, int capacity, int enrolled) {
        this.className = className;
        this.year = year;
        this.cost = cost;
        this.capacity = capacity;
        this.enrolled = enrolled;
    }

    // CLASS MODEL GETTERS
    public String getClassName() {
        return className;
    }

    public int getYear() {
        return year;
    }

    public float getCost() {
        return cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolled() {
        return enrolled;
    }

    // CLASS MODEL SETTERS
    public void setClassName(String className) {
        this.className = className;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setEnrolled(int enrolled) {
        this.enrolled = enrolled;
    }

    @Override
    public String toString() {
        return  className + " " + year + " " + cost + " " + enrolled + "/" + capacity;
    }

}
