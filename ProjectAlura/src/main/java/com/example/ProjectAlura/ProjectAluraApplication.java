package com.example.ProjectAlura;

import com.example.ProjectAlura.main.Main;
import com.example.ProjectAlura.repository.AuthorRepository;
import com.example.ProjectAlura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class ProjectAluraApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectAluraApplication.class, args);
	}

}
