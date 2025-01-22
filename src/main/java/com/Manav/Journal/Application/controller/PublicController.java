package com.Manav.Journal.Application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Manav.Journal.Application.model.User;
import com.Manav.Journal.Application.service.UserService;

@RestController
@RequestMapping("public")  // Updated for consistency with the method's purpose
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping()  // Define the route for registration
    public boolean setuser(@RequestBody User user) {
        return userService.setuser(user);  // Logic to save user
    }
}
