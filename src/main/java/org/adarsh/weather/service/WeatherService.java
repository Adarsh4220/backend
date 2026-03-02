package org.adarsh.weather.service;
import java.util.Optional;
import org.adarsh.weather.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.adarsh.weather.exception.CityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.adarsh.weather.entity.SearchHistory;
import org.adarsh.weather.repository.SearchHistoryRepository;
import java.time.LocalDateTime;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    private static final Logger logger =
            LoggerFactory.getLogger(WeatherService.class);

    @Autowired
    private SearchHistoryRepository historyRepository;

    @Autowired
    private RestTemplate restTemplate;
    @Cacheable(value = "weather", key = "#city")
    public WeatherDTO getWeatherByCity(String city) {

        logger.info("Fetching weather for city: {}", city);

        String url = apiUrl + "?q=" + city + "&appid=" + apiKey + "&units=metric";


        try {

            WeatherResponse response =
                    restTemplate.getForObject(url, WeatherResponse.class);

// SAVE SEARCH HISTORY
            SearchHistory existingCity =
                    historyRepository.findTopByCityIgnoreCaseOrderBySearchedAtDesc(response.getName());

            if (existingCity != null) {

                existingCity.setSearchedAt(LocalDateTime.now());
                historyRepository.save(existingCity);

            } else {

                SearchHistory history = new SearchHistory();
                history.setCity(response.getName());
                history.setSearchedAt(LocalDateTime.now());
                historyRepository.save(history);
            }


            return new WeatherDTO(
                    response.getName(),
                    response.getMain().getTemp(),
                    response.getWeather().get(0).getMain(),
                    response.getMain().getHumidity()
            );

        } catch (HttpClientErrorException.NotFound ex) {

            logger.error("City not found: {}", city);
            throw new CityNotFoundException(city);
        }
    }
}