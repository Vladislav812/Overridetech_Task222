package com.example.Overridetech.propertiesConfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties
@PropertySource(value = "classpath:application.yml", factory = PropFactory.class)
public class PureProperties {
    private String maxCar;
    private String sortingOffAttribute;

    public String getMaxCar() {
        return maxCar;
    }

    public void setMaxCar(String maxCar) {
        this.maxCar = maxCar;
    }

    public String getSortingOffAttribute() {
        return sortingOffAttribute;
    }

    public void setSortingOffAttribute(String sortingOffAttribute) {
        this.sortingOffAttribute = sortingOffAttribute;
    }

}
