package br.com.angratech.micronaut;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.angratech.micronaut.entity.Book;
import br.com.angratech.micronaut.repository.BookRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) 
public class BookResourceTest {

	@LocalServerPort 
    private int port;
	
	@Autowired 
    private TestRestTemplate restTemplate; 
	
	@Autowired 
    private BookRepository bookRepository;
	
	@Test
	void getBooks() {
		
		assertEquals(0, booksJsonArray().length);
		
		Book cleanArchitecture = bookRepository.save("Clean Architecture", 100);
		Book[] books = booksJsonArray();
		
		assertEquals(1, booksJsonArray().length);
		assertNotNull(books[0].id());
		assertEquals(books[0].pages(),100);
		assertEquals(books[0].title(), "Clean Architecture");
		
		bookRepository.delete(cleanArchitecture);
        assertEquals(0, booksJsonArray().length);
		
	}
	
	private Book[] booksJsonArray() {
        return restTemplate.getForObject(booksRequestUriString(), Book[].class);
    }

	private String booksRequestUriString() {
		return UriComponentsBuilder.fromUriString("http://localhost:" + port)
                .path("books")
                .build()
                .toUriString();
	}
}
