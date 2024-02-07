package com.example.Overridetech.service;

import com.example.Overridetech.exception.IncorrectSortingParameterException;
import com.example.Overridetech.model.Car;
import com.example.Overridetech.properties.CarApplicationProperties;
import com.example.Overridetech.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Service
@ControllerAdvice
@EnableConfigurationProperties
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    CarApplicationProperties carApplicationProperties;

    @Override
    public List<Car> getCarsList(Integer count, String sortBy) {
        if (carApplicationProperties.getSortingOffAttribute().equals(sortBy)) {
            throw new IncorrectSortingParameterException(sortBy);
        }

        if (count != null) {
            return sortBy == null || sortBy.isEmpty() ? (count >= carApplicationProperties.getMaxCar() ?
                    carRepository.fetchAll() : carRepository.fetchByQuantity(count)) :
                    (count >= carApplicationProperties.getMaxCar() ?
                            carRepository.fetchAndSort(sortBy) : carRepository.fetchByQuantityAndSort(sortBy, count));

        }
        return sortBy == null || sortBy.isEmpty() ? carRepository.fetchAll() : carRepository.fetchAndSort(sortBy);
    }

    @ExceptionHandler(IncorrectSortingParameterException.class)
    protected ResponseEntity<Object> handleConflict(IncorrectSortingParameterException e) {
        String message = "Requested parameter \"sortBy " + e.getParameter() + "\" is forbidden for use!";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
