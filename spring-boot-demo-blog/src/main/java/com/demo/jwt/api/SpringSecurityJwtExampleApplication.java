package com.demo.jwt.api;

import com.demo.jwt.api.entity.User;
import com.demo.jwt.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

/**
 * Main Application
 * 
 * @author GowthamCha
 *
 */
@SpringBootApplication
public class SpringSecurityJwtExampleApplication {
    @Autowired
    private UserRepository repository;

    /**
     * initUsers method is used to Inserting Values to the DB
     */
    @PostConstruct
    public void initUsers() {
    	List<User> userList =new ArrayList<>();
    	userList.add( new User(101, "Gowtham", "password", "gowtham@gmail.com"));
    	userList.add( new User(102, "saleem", "pwd1", "user1@gmail.com"));
    	userList.add(  new User(103, "user2", "pwd2", "user2@gmail.com"));
    	userList.add(  new User(104, "user3", "pwd3", "user3@gmail.com"));
    	
        repository.saveAll(userList);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtExampleApplication.class, args);
    }

}
