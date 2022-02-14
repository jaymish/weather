package com.egen.weather1.controller;


import com.egen.weather1.model.Weather;
import com.egen.weather1.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/weather")
public class WeatherController {

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService){
        this.weatherService=weatherService;
    }

    @GetMapping(path = "/simple")
    public String mySimpleGetMethod(){
        return "My simple Method";
    }

    @PostMapping("/addReading")
    public boolean addWeatherReading(@RequestBody Weather weather){
        System.out.println(weather);
        weatherService.addWeatherReadings(weather);
        return true;
    }

    @GetMapping("/sortedreadings")
    public List<Weather> getAll(){
        return weatherService.getWeatherReadingsSorted();
    }
    /*@PostMapping("/addReadingJson")
    public boolean addWeatherReadingJson(@RequestBody JsonNode weather){
        System.out.println(weather);
        return true;
    }*/
}
