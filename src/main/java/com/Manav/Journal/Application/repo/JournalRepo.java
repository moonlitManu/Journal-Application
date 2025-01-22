package com.Manav.Journal.Application.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.Manav.Journal.Application.model.JournalEntry;

@Repository
public interface JournalRepo extends MongoRepository<JournalEntry,String>{
    
}
