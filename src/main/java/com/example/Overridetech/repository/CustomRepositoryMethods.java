package com.example.Overridetech.repository;

import com.example.Overridetech.model.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomRepositoryMethods {

    @Query(value = "select * from cars", nativeQuery = true)
    public List<Car> fetchAll();

    @Query(value = "select * from cars limit :quantity", nativeQuery = true)
    public List<Car> fetchByQuantity(@Param("quantity") int quantity);

    @Query(value = "select * from cars order by :sortBy desc limit :quantity", nativeQuery = true)
    public List<Car> fetchByQuantityAndSort(@Param("sortBy") String sortBy, @Param("quantity") Integer quantity);

    @Query(value = "select * from cars order by :sortBy desc", nativeQuery = true)
    public List<Car> fetchAndSort(@Param("sortBy") String sortBy);
}
git 