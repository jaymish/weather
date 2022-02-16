package com.egen.weather1.service;

import com.egen.weather1.model.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface WeatherService {
    boolean addWeatherReadings(Weather weather) throws JsonProcessingException;
    List<Weather> getWeatherReadingsSorted();
}
