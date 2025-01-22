package com.Manav.Journal.Application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Manav.Journal.Application.model.JournalEntry;
import com.Manav.Journal.Application.model.User;
import com.Manav.Journal.Application.repo.JournalRepo;
import com.Manav.Journal.Application.repo.UserRepo;

@Service
public class JournalService {

    @Autowired
    private JournalRepo repo;

    @Autowired 
    private  UserRepo userRepo;

  
    public JournalEntry saveEntry(JournalEntry entry){
        entry.setDate(LocalDateTime.now());
        return repo.save(entry);
    }

    public List<JournalEntry> getEntries(){
        return repo.findAll();
    }

    @Transactional
    public JournalEntry save(JournalEntry entry, String username) {
        // Fetch user by username
        User userdb = userRepo.findByUsername(username);
    
        if (userdb == null) {
            throw new IllegalArgumentException("User not found with username: " + username);
        }
        entry.setDate(LocalDateTime.now());
        repo.save(entry);
        userdb.getJournalEntries().add(entry);
        userRepo.save(userdb);
    
        return entry;
    }

    public Optional<JournalEntry> findJournalById(String id) {
      return repo.findById(id);
    }


    @Transactional
    public boolean deleteEntryById(String id, String username) {
        User userdb = userRepo.findByUsername(username);
        if(userdb != null){

            userdb.getJournalEntries().removeIf(x -> x.getId().equals(id));
            repo.deleteById(id);

            return true;
         
        }
        return false;
    }
    
    
}
