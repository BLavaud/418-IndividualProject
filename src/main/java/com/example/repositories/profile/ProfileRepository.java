package com.example.repositories.profile;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProfileRepository extends MongoRepository<Profile,String>{
    //public List<Profile> findByEmail(String email);
    public List<Profile> findByName(String name);

    
}

