package com.example.javaapp.database_v2;

import java.util.Objects;

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
    @Override
    public String toString() {
        return  className + " " + year + " Cost: " + cost + " Capacity: " + enrolled + "/" + capacity;
    }

    public String nameYearAvailability(){
        int availability = capacity - enrolled;
        return className + ", " + year + ", " + availability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassModel)) return false;
        ClassModel that = (ClassModel) o;
        return year == that.year && Objects.equals(className, that.className);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, year);
    }
}
