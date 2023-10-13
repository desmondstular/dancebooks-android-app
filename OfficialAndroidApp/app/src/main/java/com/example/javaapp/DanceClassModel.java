package com.example.javaapp;

public class DanceClassModel {
    private int id;
    private String className;
    private int classYear;
    private int annualPrice;

    private int biYearlyPrice;
    private int monthlyPrice;

    public DanceClassModel(int id, String className, int yearOfClass, int annualPrice,
                           int biYearlyPrice, int monthlyPrice) {
        this.id = id;
        this.className = className;
        this.classYear = yearOfClass;
        this.annualPrice = annualPrice;
        this.biYearlyPrice = biYearlyPrice;
        this.monthlyPrice = monthlyPrice;
    }
    //Getters
    public int getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public int getClassYear() {
        return classYear;
    }

    public int getAnnualPrice() {
        return annualPrice;
    }
    //ToString
    @Override
    public String toString() {
        return "DanceClass{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", yearOfClass=" + classYear +
                ", annualPrice=" + annualPrice +
                '}';
    }
    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setClassYear(int classYear) {
        this.classYear = classYear;
    }

    public void setAnnualPrice(int annualPrice) {
        this.annualPrice = annualPrice;
    }

    public void setBiYearlyPrice(int biYearlyPrice) {
        this.biYearlyPrice = biYearlyPrice;
    }

    public void setMonthlyPrice(int monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }
}
