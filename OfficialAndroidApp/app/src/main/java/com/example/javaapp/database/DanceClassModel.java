package com.example.javaapp.database;

public class DanceClassModel {
    private String className;
    private int classYear;
    private float classLumpSumCost;

    public DanceClassModel(String className, int classYear, float classLumpSumCost){
        this.className = className;
        this.classYear = classYear;
        this.classLumpSumCost = classLumpSumCost;
    }

    public DanceClassModel(DanceClassModel danceClassModel) {

    }


    @Override
    public String toString() {
        return className + " " + classYear ;
    }

    public String getClassName() {
        return className;
    }

    public int getClassYear() {
        return classYear;
    }

    public float getClassLumpSumCost() {
        return classLumpSumCost;
    }


    public void setClassName(String className) {
        this.className = className;
    }

    public void setClassYear(int classYear) {
        this.classYear = classYear;
    }

    public void setClassLumpSumCost(float classLumpSumCost) {
        this.classLumpSumCost = classLumpSumCost;
    }
}
