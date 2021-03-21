package com.example.repositories.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String>
{      
    //public User findByEm(String name);
    public List<User> findByEmail(String email);
}
