package com.Manav.Journal.Application.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Manav.Journal.Application.model.WeatherObject;

@Service
public class WeatherService {

    @Value("${weather_api_key}")
    private String apiKey;
    private final static String api = "http://api.weatherstack.com/current?access_key=ApiKey&query=City\n" + //
                "";


    
    private final RestTemplate restTemplate;
    public WeatherService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    
    public WeatherObject weatherService(String city){
        String finalApi = api.replace("City",city).replace("ApiKey", apiKey);
        ResponseEntity<WeatherObject> response = restTemplate.exchange(finalApi, HttpMethod.GET,null, WeatherObject.class);
        WeatherObject body = response.getBody();
        return body;
    }
}
