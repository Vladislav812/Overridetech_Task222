package com.example.Overridetech.controller;

import com.example.Overridetech.exception.IncorrectSortingParameterException;
import com.example.Overridetech.model.Car;
import com.example.Overridetech.propertiesConfig.PureProperties;
import com.example.Overridetech.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Sort;
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
    private PureProperties pureProperties;
//    @Value("${sortingOffAttribute}")
    private String sortingOffAttribute = pureProperties.getSortingOffAttribute();
//    @Value("${maxCar}")
    private String maxCar = pureProperties.getMaxCar();

    @GetMapping(path = "cars")
    public String showCars(@RequestParam(required = false, value = "count") Integer count,
                           @RequestParam(required = false, value = "sortBy") String sortBy,
                           Model model) {

        if (sortingOffAttribute.equals(sortBy))
            throw new IncorrectSortingParameterException(sortBy);

        List<Car> carsList = sortBy == null || sortBy.isEmpty() ? carRepository.findAll() : carRepository.findAll(Sort.by(Sort.Direction.DESC, sortBy));
        count = count == null ? carsList.size() : count;
        count = count >= Integer.parseInt(maxCar) ? carsList.size() : count;
        carsList = count == null ? carsList : carsList.subList(0, count);

        model.addAttribute("cars", carsList);
        return "cars";
    }

    @ExceptionHandler(IncorrectSortingParameterException.class)
    protected ResponseEntity<Object> handleConflict(IncorrectSortingParameterException e) {
        String message = "Requested parameter \"sortBy " + e.getParameter() + "\" is forbidden for use!";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
