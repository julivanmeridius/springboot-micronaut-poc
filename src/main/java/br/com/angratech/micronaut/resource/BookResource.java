package br.com.angratech.micronaut.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.angratech.micronaut.entity.Book;
import br.com.angratech.micronaut.repository.BookRepository;

@RestController
public class BookResource {
	
	@Autowired
	private BookRepository repository;
	
	@GetMapping("/books")
	Iterable<Book> list(){
		return repository.findAll();
	}
	
}

