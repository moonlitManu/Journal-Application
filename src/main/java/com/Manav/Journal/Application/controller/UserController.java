package com.Manav.Journal.Application.controller;


import com.Manav.Journal.Application.model.User;
import com.Manav.Journal.Application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

   
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
}
