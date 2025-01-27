package com.Manav.Journal.Application.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WeatherObject {

    public Current Current;
    @Data
    public class Current{

    public int temperature;
   
    @JsonProperty("weather_description")
    public ArrayList<String> weatherDescriptions;

    public int feelslike;
    
}



}
