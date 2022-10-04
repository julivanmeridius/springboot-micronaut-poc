package br.com.angratech.micronaut.repository;

import br.com.angratech.micronaut.entity.Book;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.H2) 
public interface BookRepository extends CrudRepository<Book, Long> { 

    Book save(String title, int pages);
}