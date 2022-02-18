package com.egen.weather1.service.impl;

import com.egen.weather1.awsMessaging.WeatherAlertSns;
import com.egen.weather1.model.Weather;
import com.egen.weather1.model.WeatherAlert;
import com.egen.weather1.repo.WeatherRepository;
import com.egen.weather1.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;

@Service
public class DefaultWeatherService implements WeatherService {
    //List<Weather> list=new LinkedList<>();

    private WeatherRepository weatherRepository;
    private RestTemplate restTemplate;
    private WeatherAlertSns weatherAlertSns;
    private ObjectMapper objectMapper;

    @Autowired
    public DefaultWeatherService(WeatherRepository weatherRepository, RestTemplate restTemplate, WeatherAlertSns weatherAlertSns, ObjectMapper objectMapper){
        this.weatherRepository=weatherRepository;
        this.restTemplate=restTemplate;
        this.weatherAlertSns=weatherAlertSns;
        this.objectMapper=objectMapper;
    }

    @Override
    public boolean addWeatherReadings(Weather weather) throws JsonProcessingException {
        //list.add(weather);
        weatherRepository.save(weather);
        /*if(weather.getTemperature()>25){
            WeatherAlert weatherAlert=new WeatherAlert("Too Hot", weather);
            String message = objectMapper.writeValueAsString(weatherAlert);
            weatherAlertSns.send("Temperature Alert", message);
//            return restTemplate.postForObject("http://localhost:8081/addReading",weatherAlert,boolean.class);
        }
        if (weather.getWind().getSpeed()>6){
            WeatherAlert weatherAlert=new WeatherAlert("Too Windy", weather);
            String message = objectMapper.writeValueAsString(weatherAlert);
            weatherAlertSns.send("Wind Alert", message);
//            return restTemplate.postForObject("http://localhost:8081/addReading",weatherAlert,boolean.class);
        }*/
        return true;
    }

    @Override
    public List<Weather> getWeatherReadingsSorted() {
        List<Weather> weatherList= (List<Weather>) weatherRepository.findAll();
        weatherList.sort(Comparator.comparing(Weather::getTimestamp));
        return weatherList;
    }
}
