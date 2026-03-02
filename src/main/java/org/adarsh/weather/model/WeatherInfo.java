package org.adarsh.weather.model;

import lombok.Data;

@Data
public class WeatherInfo {
    private String main;
    private String description;
}