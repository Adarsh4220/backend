package org.adarsh.weather.model;

import lombok.Data;
import java.util.List;

@Data
public class WeatherResponse {

    private String name; // city name
    private WeatherMain main;
    private List<WeatherInfo> weather;
}
