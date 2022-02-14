package com.egen.weather1.service.impl;

import com.egen.weather1.model.Weather;
import com.egen.weather1.repo.WeatherRepository;
import com.egen.weather1.service.WeatherService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class DefaultWeatherService implements WeatherService {
    //List<Weather> list=new LinkedList<>();

    WeatherRepository weatherRepository;

    public DefaultWeatherService(WeatherRepository weatherRepository){
        this.weatherRepository=weatherRepository;
    }
    @Override
    public boolean addWeatherReadings(Weather weather) {
        //list.add(weather);
        weatherRepository.save(weather);
        return true;
    }

    @Override
    public List<Weather> getWeatherReadingsSorted() {
        List<Weather> weatherList= (List<Weather>) weatherRepository.findAll();
        weatherList.sort(Comparator.comparing(Weather::getTimestamp));
        return weatherList;
    }
}
