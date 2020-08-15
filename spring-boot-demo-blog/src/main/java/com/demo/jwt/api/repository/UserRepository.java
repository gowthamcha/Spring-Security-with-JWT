package com.demo.jwt.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.jwt.api.entity.User;

/**
 * UserRepository extends with JpaRepository  for DB operations
 * 
 * @author Gowtham
 *
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String username);
}
