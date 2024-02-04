package com.example.Overridetech.repository;

import com.example.Overridetech.model.Car;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findByOrderByModelDesc();

    List<Car> findAll(Sort by);

    List<Car> findAll();
}
