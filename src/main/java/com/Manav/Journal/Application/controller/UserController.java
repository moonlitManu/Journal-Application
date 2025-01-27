package com.Manav.Journal.Application.controller;


import com.Manav.Journal.Application.model.User;
import com.Manav.Journal.Application.model.WeatherObject;
import com.Manav.Journal.Application.service.UserService;
import com.Manav.Journal.Application.service.WeatherService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private WeatherService weatherService;

    public UserController(UserService userService, WeatherService weatherService){
        this.userService = userService;
        this.weatherService = weatherService;
    }
   
     @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boolean result = userService.setuser(user, username);
        if(result == true){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
        }
    }

   

   @DeleteMapping("{username}")
   public void delete(@PathVariable String username){
    userService.deletebyusername(username);
   }

   @GetMapping()
   public ResponseEntity<?> getMethodName() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    WeatherObject obj = weatherService.weatherService("Mathura");
    String greeting = "";
    if(obj != null){
         greeting = "Hi " + username + " Today it feels like " + obj.getCurrent().getFeelslike();
    }
    return new ResponseEntity<>(greeting, HttpStatus.OK);
   }
   
}
