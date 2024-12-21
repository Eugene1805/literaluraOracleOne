package com.example.ProjectAlura.repository;

import com.example.ProjectAlura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    Optional<Book> getByTitle(String title);

    List<Book> findByLanguages(String language);
}
