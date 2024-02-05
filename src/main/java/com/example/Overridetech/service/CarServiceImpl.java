package com.example.Overridetech.service;

import com.example.Overridetech.model.Car;
import com.example.Overridetech.repository.CarRepository;
import com.example.Overridetech.repository.CarRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepositoryImpl carRepository;
    @Value("${maxCar}")
    private String maxCar;

    @Override
    public List<Car> getCarsList(Integer count, String sortBy) {
        if (count != null) {
            return sortBy == null || sortBy.isEmpty() ? (count >= Integer.parseInt(maxCar) ?
                    carRepository.fetchAll() : carRepository.fetchByQuantity(count)) :
                    (count >= Integer.parseInt(maxCar) ?
                            carRepository.fetchAndSort(sortBy) : carRepository.fetchByQuantityAndSort(sortBy, count));
        }
        return sortBy == null || sortBy.isEmpty() ? carRepository.fetchAll() : carRepository.fetchAndSort(sortBy);
    }

//    @Override
//    public List<Car> fetchQuantity(Integer quantity) {
//        return carRepository.fetchByQuantity(quantity);
//    }

}
