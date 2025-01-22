package com.Manav.Journal.Application.service;


import com.Manav.Journal.Application.model.User;
import com.Manav.Journal.Application.repo.UserRepo;

import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@Slf4j
public class UserService {
    
    @Autowired
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    @Autowired
    public UserRepo userRepo;

    
    // to save new user
    public boolean setuser(User user){
      try{
        user.setRoles("USER");
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return true;
      }catch(Exception e){
        log.info("Error occured", e);
        return false;
      }
    }

    // function to create an admin
    public User setAdmin(User user){
        user.setRoles("ADMIN");
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return user;
    }
    public boolean setuser(User user, String username){
      User userdb = userRepo.findByUsername(username);

        if (userdb == null) {
            return false;
        }
        userdb.setUsername(user.getUsername());
        userdb.setPassword(encoder.encode(user.getPassword()));
        userdb.setRoles("USER");
        userRepo.save(userdb);
        return true;

    }
    
    public List<User> getUser(){
        return userRepo.findAll();
    }

    public User findByUsername(String username) {
       return userRepo.findByUsername(username);
    }

    public void deletebyusername(String username) {
        userRepo.deleteByUsername(username);
    }
  
}
