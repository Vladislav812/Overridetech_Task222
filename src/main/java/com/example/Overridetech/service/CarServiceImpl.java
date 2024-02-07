package com.example.Overridetech.service;

import com.example.Overridetech.exception.IncorrectSortingParameterException;
import com.example.Overridetech.model.Car;
import com.example.Overridetech.properties.CarApplicationProperties;
import com.example.Overridetech.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    CarApplicationProperties carApplicationProperties;

    @Override
    public List<Car> getCarsList(Integer count, String sortBy) {
        if (carApplicationProperties.getSortingOffAttribute().equals(sortBy)) {
            throw new IncorrectSortingParameterException("Requested parameter \"sortBy " + sortBy + "\" is forbidden for use!");
        }

        if (count != null) {
            return sortBy == null || sortBy.isEmpty() ? (count >= carApplicationProperties.getMaxCar() ?
                    carRepository.fetchAll() : carRepository.fetchByQuantity(count)) :
                    (count >= carApplicationProperties.getMaxCar() ?
                            carRepository.fetchAndSort(sortBy) : carRepository.fetchByQuantityAndSort(sortBy, count));

        }
        return sortBy == null || sortBy.isEmpty() ? carRepository.fetchAll() : carRepository.fetchAndSort(sortBy);
    }


}
