package com.Manav.Journal.Application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Manav.Journal.Application.model.User;
import com.Manav.Journal.Application.service.UserService;


@RestController
@RequestMapping("ADMIN")
public class AdminController {
    
    @Autowired
    private UserService userService;
     @GetMapping
    public List<User> getuser(){
        return userService.getUser();
    }

    @PostMapping
    public User createAdmin(@RequestBody User user){
      
       return userService.setAdmin(user);
        
    }
}
