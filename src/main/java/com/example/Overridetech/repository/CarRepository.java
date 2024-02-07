package com.example.Overridetech.repository;

import com.example.Overridetech.model.Car;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CarRepository {
    @PersistenceContext
    private EntityManager manager;

    public List<Car> fetchAll() {
        return manager.createQuery("SELECT c FROM Car c").getResultList();
    }

    public List<Car> fetchByQuantity(int quantity) {
        return manager.createQuery("SELECT c from Car c").setMaxResults(quantity).getResultList();
    }

    public List<Car> fetchByQuantityAndSort(String sortBy, int quantity) {
        return manager.createQuery("SELECT c FROM Car c ORDER By c." + sortBy + " DESC").setMaxResults(quantity).getResultList();
    }

    public List<Car> fetchAndSort(String sortBy) {
        return manager.createQuery("SELECT c FROM Car c ORDER By c." + sortBy + " DESC").getResultList();
    }

    ;

}
