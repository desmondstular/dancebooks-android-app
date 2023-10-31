package com.example.javaapp.database_v1;

public class DanceClassModel {
    private String className;
    private int classYear;
    private float classLumpSumCost;
    private float classBiAnnualCost;
    private float classMonthlyCost;

    public DanceClassModel(String className, int classYear, float classLumpSumCost,
                           float classBiAnnualCost, float classMonthlyCost) {
        this.className = className;
        this.classYear = classYear;
        this.classLumpSumCost = classLumpSumCost;
        this.classBiAnnualCost = classBiAnnualCost;
        this.classMonthlyCost = classMonthlyCost;
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

    public float getClassBiAnnualCost() {
        return classBiAnnualCost;
    }

    public float getClassMonthlyCost() {
        return classMonthlyCost;
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

    public void setClassBiAnnualCost(float classBiAnnualCost) {
        this.classBiAnnualCost = classBiAnnualCost;
    }

    public void setClassMonthlyCost(float classMonthlyCost) {
        this.classMonthlyCost = classMonthlyCost;
    }
}
