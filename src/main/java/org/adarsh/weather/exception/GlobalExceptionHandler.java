package org.adarsh.weather.exception;

import org.adarsh.weather.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleCityNotFound(CityNotFoundException ex) {

        return new ErrorResponse(
                LocalDateTime.now(),
                404,
                "City Not Found",
                ex.getMessage()
        );
    }
}