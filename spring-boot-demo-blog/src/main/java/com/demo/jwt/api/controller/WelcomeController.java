package com.demo.jwt.api.controller;

import com.demo.jwt.api.entity.AuthRequest;
import com.demo.jwt.api.entity.Books;
import com.demo.jwt.api.entity.Const;
import com.demo.jwt.api.service.BooksService;
import com.demo.jwt.api.util.JwtUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * WelcomeController is the Controller Class File
 * 
 * 
 * @author Gowtham
 *
 */
@RestController
public class WelcomeController {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	BooksService booksService;

	/***
	 * creating a get mapping that retrieves the initial string content
	 */
	@GetMapping("/")
	public String welcome() {
		return "Welcome to Blog !!";
	}

	/**
	 * generateToken method is used to generate the token
	 * 
	 * @param authRequest
	 * @return Strig
	 * @throws Exception
	 */
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception ex) {
			throw new Exception("inavalid username/password");
		}
		return jwtUtil.generateToken(authRequest.getUserName());
	}

	
	/**
	 * creating a get mapping that retrieves all the books detail from the database
	 * 
	 * @return List<String>
	 */
	@GetMapping("/book")
	private List<String> getAllBooks() {
		List<Books> booksList = booksService.getAllBooks();
		  List<String> bookDetails = booksList.stream().map(book ->{
			 StringBuffer ff = new StringBuffer();
			 String bookDetail = book.getBookname()+"^^^^^^^"+book.getAuthor()+"\n";
			 return bookDetail;
		}).collect(Collectors.toList());
		return bookDetails;
	}

	/**
	 * creating a get mapping that retrieves the detail of a specific book
	 * 
	 * @param bookid
	 * @return List<String>
	 */
	@GetMapping("/book/{bookid}")
	private List<String> getBooks(@PathVariable("bookid") int bookid) {
		List<String> stringList = new ArrayList<>();
		Books book = booksService.getBooksById(bookid);
		StringBuilder ff = new StringBuilder();
		 String bookDetail = "\t"+book.getBookname()+"\t"+"^^^^^^^"+"\t"+book.getAuthor();
		 stringList.add("*******Author Name   :"+book.getAuthor()+"********************"+"\r\n");
		 stringList.add("*******Book Name     :"+book.getBookname()+"********************"+"\r\n");
		 stringList.add("*******Book Price    :"+book.getPrice()+"********************"+"\\r\\n");
		 stringList.add(bookDetail+"\n");
		 stringList.add(Const.TEXT_CONTENT);
		 stringList.add(Const.TEXT_CONTENT+'\n');
		return stringList;
	}

	/**
	 * creating a delete mapping that deletes a specified book
	 * 
	 * @param bookid
	 */
	@DeleteMapping("/book/{bookid}")
	private void deleteBook(@PathVariable("bookid") int bookid) {
		booksService.delete(bookid);
	}

	/**
	 * creating post mapping that post the book detail in the database
	 * 
	 * @param books
	 * @return int
	 */
	@PostMapping("/books")
	private int saveBook(@RequestBody Books books) {
		booksService.saveOrUpdate(books);
		return books.getBookid();
	}

	/**
	 * creating put mapping that updates the book detail
	 * 
	 * @param books
	 * @return Book
	 */
	@PutMapping("/books")
	private Books update(@RequestBody Books books) {
		booksService.saveOrUpdate(books);
		return books;
	}
}
