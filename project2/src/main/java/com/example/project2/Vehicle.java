package com.example.project2;

import javax.persistence.*;

@Entity
@Table(name = "Vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    private String makeModel;
    private int year;
    private int retailPrice;

    public Vehicle() {

    }

    public Vehicle(int id, String makeModel, int year, int retailPrice) {
        this.id = id;
        this.makeModel = makeModel;
        this.year = year;
        this.retailPrice = retailPrice;
    }

    public Vehicle(String makeModel, int year, int retailPrice) {
        this.id = id;
        this.makeModel = makeModel;
        this.year = year;
        this.retailPrice = retailPrice;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", makeModel='" + makeModel + '\'' +
                ", year=" + year +
                ", retailPrice=" + retailPrice +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }
}
