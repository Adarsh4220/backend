package org.adarsh.weather.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherDTO {

    private String city;
    private double temperature;
    private String condition;
    private int humidity;
}