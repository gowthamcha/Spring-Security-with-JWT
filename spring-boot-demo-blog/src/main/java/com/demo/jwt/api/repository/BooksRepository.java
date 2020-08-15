package com.demo.jwt.api.repository;

import org.springframework.data.repository.CrudRepository;
import com.demo.jwt.api.entity.Books;


/**
 * BooksRepository extends with CrudRepository for DB operations
 * 
 * @author Gowtham
 *
 */
public interface BooksRepository extends CrudRepository<Books, Integer> {
}
