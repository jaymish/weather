package com.egen.weather1.service;

import com.egen.weather1.model.Weather;

import java.util.List;

public interface WeatherService {
    boolean addWeatherReadings(Weather weather);
    List<Weather> getWeatherReadingsSorted();
}
