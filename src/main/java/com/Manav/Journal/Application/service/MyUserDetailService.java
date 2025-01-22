package com.Manav.Journal.Application.service;

import com.Manav.Journal.Application.model.User;
import com.Manav.Journal.Application.model.UserPrinciple;
import com.Manav.Journal.Application.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements  UserDetailsService {
    @Autowired
    private UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrinciple(user);
    }
}
