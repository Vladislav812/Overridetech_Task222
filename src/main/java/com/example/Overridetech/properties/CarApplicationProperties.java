package com.example.Overridetech.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cars")
public class CarApplicationProperties {
    private int maxCar;

    public void setMaxCar(int maxCar) {
        this.maxCar = maxCar;
    }

    public int getMaxCar() {
        return maxCar;
    }

    public String getSortingOffAttribute() {
        return sortingOffAttribute;
    }

    public void setSortingOffAttribute(String sortingOffAttribute) {
        this.sortingOffAttribute = sortingOffAttribute;
    }

    private String sortingOffAttribute;
}
