package sap.education.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sap.education.model.Book;
import sap.education.model.BookStoreRepository;

@Controller
@RequestMapping("/")
public class BookStoreSiteController {
	

	private BookStoreRepository bookStoreRepository;


	@Autowired
	public BookStoreSiteController(BookStoreRepository bookStoreRepository) {
		this.bookStoreRepository = bookStoreRepository;
	}

	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllBooks(Model model) {
		model.addAttribute("books", bookStoreRepository.findAll());
		return "index";
	}


	@RequestMapping (method = RequestMethod.POST)
	public String addToBookStore( Book book) {
		bookStoreRepository.saveAndFlush(book);
		return "redirect:/";
	}
	
	
	
}
