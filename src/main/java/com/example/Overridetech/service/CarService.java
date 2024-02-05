package com.example.Overridetech.service;

import com.example.Overridetech.model.Car;

import java.util.List;

public interface CarService {
    public List<Car> getCarsList(Integer count, String sortBy);
//    public List<Car> fetchQuantity(Integer quantity);
}
