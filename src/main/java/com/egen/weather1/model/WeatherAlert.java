package com.egen.weather1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WeatherAlert {
    private String alert;
    private Weather weather;
}
