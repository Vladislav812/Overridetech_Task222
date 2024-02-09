package com.example.Overridetech.controller;

import com.example.Overridetech.exception.IncorrectSortingParameterException;
import com.example.Overridetech.model.Car;
import com.example.Overridetech.repository.CarRepository;
import com.example.Overridetech.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
@Controller
public class CarController extends ResponseEntityExceptionHandler {
    @Autowired
    private CarService carService;

    @GetMapping(path = "cars")
    public String showCars(@RequestParam(required = false, value = "count") Integer count,
                           @RequestParam(required = false, value = "sortBy") String sortBy,
                           Model model) {

        List<Car> carsList = carService.getCarsList(count, sortBy);

        model.addAttribute("cars", carsList);
        return "cars";
    }

    @ExceptionHandler(IncorrectSortingParameterException.class)
    protected ResponseEntity<Object> handleConflict(IncorrectSortingParameterException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
