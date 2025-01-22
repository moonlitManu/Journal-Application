package com.Manav.Journal.Application.controller;




import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Manav.Journal.Application.model.JournalEntry;
import com.Manav.Journal.Application.model.User;
import com.Manav.Journal.Application.service.JournalService;
import com.Manav.Journal.Application.service.UserService;

@RestController
@RequestMapping("/journal")
public class JournalController {
    
    @Autowired
    private JournalService journalService;
    
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> postEntry(@RequestBody JournalEntry entry){
      try{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        JournalEntry save = journalService.save(entry, username);
        return new ResponseEntity<>(save, HttpStatus.OK);
      }catch(Exception e){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    }

    @GetMapping
    public ResponseEntity<?> getEntryByUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userdb = userService.findByUsername(username);
        List<JournalEntry> all = userdb.getJournalEntries();
        return new ResponseEntity<>(all, HttpStatus.OK);

    }

    @GetMapping("{id}")
    public Optional<JournalEntry> findJournalById(@PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userdb = userService.findByUsername(username);
        List<JournalEntry> collect =userdb.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(collect != null){
            return journalService.findJournalById(id);
        }
        else{
            return null;
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEntryById(@PathVariable String id){
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String username = authentication.getName();
      if(journalService.deleteEntryById(id, username)){
        return new ResponseEntity<>("the entry is deleted", HttpStatus.OK);
      }
      return new ResponseEntity<>("entry not found", HttpStatus.BAD_REQUEST);
    }

   @PutMapping("{id}")
   public ResponseEntity<?> updateEntryId(@PathVariable String id, @RequestBody JournalEntry myEntry){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User userdb = userService.findByUsername(authentication.getName());
    if(userdb != null){
      JournalEntry entry = userdb.getJournalEntries()
      .stream()
      .filter(x -> x.getId().equals(id))
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException("Journal entry not found with id: " + id));

      entry.setTitle(myEntry.getTitle());
      entry.setContent(myEntry.getContent());
      entry.setDate(LocalDateTime.now());

      journalService.saveEntry(entry);
      return new ResponseEntity<>("done", HttpStatus.OK);

    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   }
      
  
}
