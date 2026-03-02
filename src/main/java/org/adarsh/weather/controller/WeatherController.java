package org.adarsh.weather.controller;



import org.adarsh.weather.model.WeatherDTO;
import org.adarsh.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService service;

    @GetMapping("/{city}")
    public WeatherDTO getWeather(@PathVariable String city) {
        return service.getWeatherByCity(city);
    }
}