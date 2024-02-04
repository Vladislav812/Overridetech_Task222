package com.example.Overridetech.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String vendor;
    private String model;
    private int manufacturingYear;

    public String getVendor() {
        return vendor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getManufacturingYear() {
        return manufacturingYear;
    }

    public void setManufacturingYear(int manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }

    public Car() {
    }

    public Car(long id, String vendor, String model, int manufacturingYear) {
        this.id = id;
        this.vendor = vendor;
        this.model = model;
        this.manufacturingYear = manufacturingYear;
    }

    public Car(String vendor, String model, int manufacturingYear) {
        this.vendor = vendor;
        this.model = model;
        this.manufacturingYear = manufacturingYear;
    }
}
