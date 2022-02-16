package com.egen.weather1.controller;


import com.egen.weather1.model.Weather;
import com.egen.weather1.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "My Simple Method")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "This is OK")
    })
    public String mySimpleGetMethod(){
        return "My simple Method";
    }

    @PostMapping("/addReading")
    public boolean addWeatherReading(@RequestBody Weather weather) throws JsonProcessingException {
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
