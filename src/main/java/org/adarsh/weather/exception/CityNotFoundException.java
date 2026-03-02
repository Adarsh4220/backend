package org.adarsh.weather.exception;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException(String city) {
        super("City " + city + " does not exist");
    }
}
