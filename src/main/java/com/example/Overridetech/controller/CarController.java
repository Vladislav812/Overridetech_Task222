package com.example.Overridetech.controller;

import com.example.Overridetech.model.Car;
import com.example.Overridetech.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
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

@Controller
@ControllerAdvice
public class CarController extends ResponseEntityExceptionHandler {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private Environment environment;

    @GetMapping(path = "cars")
    public String showCars(@RequestParam(required = false, value = "count") Integer count,
                           @RequestParam(required = false, value = "sortBy") String sortBy,
                           Model model) {

        if (environment.getProperty("sortingOffAttribute").equals(sortBy))
            throw new IncorrectSortingParameterException(sortBy);

        List<Car> carsList = sortBy == null || sortBy.isEmpty() ? carRepository.findAll() : carRepository.findAll(Sort.by(Sort.Direction.DESC, sortBy));
        count = count == null ? carsList.size() : count;
        count = count >= Integer.parseInt(environment.getProperty("maxCar")) ? carsList.size() : count;
        carsList = count == null ? carsList : carsList.subList(0, count);

        model.addAttribute("cars", carsList);
        return "cars";
    }

    @ExceptionHandler(IncorrectSortingParameterException.class)
    protected ResponseEntity<Object> handleConflict(IncorrectSortingParameterException e) {
        String message = "Requested parameter \"sortBy " + e.getParameter() + "\" is forbidden for use!";
        HttpHeaders headers = new HttpHeaders();
        headers.add("customHeader", "customHeaderValue");
        headers.add("sortBy", e.getParameter());
        return new ResponseEntity<>(message, headers, HttpStatus.BAD_REQUEST);
    }
}
