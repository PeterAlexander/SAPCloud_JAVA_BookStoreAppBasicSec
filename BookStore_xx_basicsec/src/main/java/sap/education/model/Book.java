package sap.education.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author Peter Alexander  24.04.2017. Excercise cp100
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Book.findByIsbn", query = "SELECT p FROM Book p WHERE p.isbn=?1"),
		@NamedQuery(name = "Book.deleteByIsbn", query = "DELETE FROM Book p WHERE p.isbn=?1") })

public class Book {

	// Constructor

	protected Book() {
	}

	public Book(String reader, String isbn, String title, String author, String description) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.description = description;
		
	}

//	//Excercise 
//	public Book(String reader, String isbn, String title, String author, String description, String category) {
//		this.isbn = isbn;
//		this.title = title;
//		this.author = author;
//		this.description = description;
//		this.category = category;
//		
//	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String isbn;
	private String title;
	private String author;
	private String description;
	// - Excercise : private String category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Excercise
	 */

	// public String getCategory() {
	// return category;
	// }
	//
	// public void setCategory(String category) {
	// this.category = category;
	// }

}
