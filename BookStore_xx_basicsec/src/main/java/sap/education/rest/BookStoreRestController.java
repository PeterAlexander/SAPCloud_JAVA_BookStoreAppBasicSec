package sap.education.rest;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import sap.education.model.Book;
import sap.education.model.BookStoreRepository;
import sap.education.utils.CustomErrorType;

@RestController
@RequestMapping("/rest")
public class BookStoreRestController {

	private BookStoreRepository bookStoreRepository;

	@Autowired
	public BookStoreRestController(BookStoreRepository readingListRepository) {
		this.bookStoreRepository = readingListRepository;
	}

	@Autowired(required = false)
	DataSource dataSource;

	// ----Retrieve All books----------------

	@RequestMapping(value = "/books", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ResponseEntity<List<Book>> listAllBooks() {
		List<Book> books = bookStoreRepository.findAll();
		if (books.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);

		}
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}

	// -----Retrieve Single Book--------------

	@RequestMapping(value = "/book/{isbn}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ResponseEntity<?> getBookByISBN(@PathVariable("isbn") String isbn) {
		Book book = bookStoreRepository.findByIsbn(isbn);
		if (book == null) {
			return new ResponseEntity(new CustomErrorType("Book with isbn " + isbn + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}

	// -----Create a Book----------------------

	@RequestMapping(value = "/book/{book}", method = RequestMethod.PUT)
	public ResponseEntity<?> createNewBookEntry(@RequestBody Book book, UriComponentsBuilder ucBuilder) {

		bookStoreRepository.saveAndFlush(book);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/rest/books/{isbn}").buildAndExpand(book.getIsbn()).toUri());
		return new ResponseEntity<Book>(HttpStatus.CREATED);
	}

	// // ------------------- Delete a
	// User-----------------------------------------
	//
	@RequestMapping(value = "/book/{isbn}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBookByISBN(@PathVariable("isbn") String isbn) {
		Book book = bookStoreRepository.findByIsbn(isbn);
		if (book == null) {

			return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + isbn + " not found."),
					HttpStatus.NOT_FOUND);
		}

		bookStoreRepository.deleteByIsbn(isbn);
		return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
	}

}
