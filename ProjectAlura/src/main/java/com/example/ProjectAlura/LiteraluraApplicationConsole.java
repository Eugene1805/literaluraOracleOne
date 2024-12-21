package com.example.ProjectAlura;

import com.example.ProjectAlura.main.Main;
import com.example.ProjectAlura.repository.AuthorRepository;
import com.example.ProjectAlura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LiteraluraApplicationConsole implements CommandLineRunner {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void run(String... args) {
        Main main = new Main(bookRepository, authorRepository);
        main.showMenu();
    }
}