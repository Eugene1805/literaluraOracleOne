package com.example.ProjectAlura.main;

import com.example.ProjectAlura.dto.BookDTO;
import com.example.ProjectAlura.model.Author;
import com.example.ProjectAlura.model.Book;
import com.example.ProjectAlura.model.BookData;
import com.example.ProjectAlura.repository.AuthorRepository;
import com.example.ProjectAlura.repository.BookRepository;
import com.example.ProjectAlura.service.DataConversor;
import com.example.ProjectAlura.service.APIUse;

import java.util.*;

public class Main {
    private Scanner keyboard = new Scanner(System.in);
    private APIUse APIUse = new APIUse();
    private final String BASE_URL = "https://gutendex.com/books/";
    private DataConversor conversor = new DataConversor();
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private List<Book> books = new ArrayList();

    public Main(BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    public Main(){}

    public void showMenu() {
        int option = -1; // Inicializamos la opción
        while (option != 0) {
            // Menú con formato limpio
            var menu = """
                1 - Buscar libro por título
                2 - Listar libros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos en determinado año
                5 - Listar libros por idioma

                0 - Salir
                """;
            System.out.println(menu);

            try {
                System.out.print("Ingresa tu opción: ");
                option = keyboard.nextInt(); // Leer la opción del usuario

                // Consumir el salto de línea sobrante
                keyboard.nextLine();

                // Evaluar la opción elegida
                switch (option) {
                    case 1 -> searchBookByTitle();
                    case 2 -> showBooks();
                    case 3 -> showAuthors();
                    case 4 -> showAliveAuthors();
                    case 5 -> showBooksByLang();
                    case 0 -> System.out.println("Hasta pronto...");
                    default -> System.out.println("Opción inválida, intenta de nuevo.");
                }
            } catch (InputMismatchException e) {
                // Capturamos el error si la entrada no es un número
                System.out.println("Entrada inválida. Por favor, ingresa un número entero.");
                keyboard.nextLine(); // Limpiar el buffer para evitar un bucle infinito
            }
        }
    }


    private void showAliveAuthors() {
        System.out.println("Ingrese el ano");
        Integer year = keyboard.nextInt();
        List<Author> authors = authorRepository.findByYear(year);
        if(authors != null){
            authors.forEach(this::showAuthor);
        }

    }

    private void showBooksByLang() {
        System.out.println("Ingrese el idioma");
        var languages = """
                es [Español] en [English] fr [Frances]  ch [Chino]  
                """;
        System.out.println(languages);
        var option = keyboard.nextLine();
        if(option.length() != 2){
            System.out.println("Ingrese solo las dos letras correspondientes");
        }else {
            List<Book> bookList = bookRepository.findByLanguages(option.toLowerCase());
            if ((!bookList.isEmpty())) {
                bookList.forEach(this::showBook);
            }else{
                System.out.println("No hay libros en " + option);
            }
        }

    }

    private void showAuthors() {
        List<Author> registeredAuthors = authorRepository.findAll();
        registeredAuthors.forEach(this::showAuthor);
    }

    private void showBooks() {
        List<Book> registeredBooks = bookRepository.findAll();
        registeredBooks.forEach(this::showBook);
    }

    private void searchBookByTitle() {
        System.out.println("Ingrese el nombre del libro:");
        String bookName = keyboard.nextLine();

        // Buscar primero en la base de datos si el libro ya existe
        Optional<Book> existingBook = bookRepository.getByTitle(bookName);
        if (existingBook.isPresent()) {
            System.out.println("Libro encontrado en la base de datos:");
            showBook(existingBook.get());
            return;
        }

        // Si el libro no existe, obtener datos de la API
        var json = APIUse.getData(BASE_URL + "?search=" + bookName.replace(" ", "+"));
        BookData bookData = conversor.getData(json, BookData.class);

        // Filtrar el libro buscado
        BookDTO bookDTO = bookData.results().stream()
                .filter(t -> t.title().equalsIgnoreCase(bookName))
                .findFirst()
                .orElse(null);

        if (bookDTO == null) {
            System.out.println("Libro no encontrado en la API.");
            return;
        }

        // Verificar si el autor ya existe en la base de datos
        String authorName = bookDTO.authors().get(0).name();
        Author author = authorRepository.findByName(authorName)
                .orElseGet(() -> {
                    // Crear y guardar el autor si no existe
                    Author newAuthor = new Author(bookDTO.authors().get(0));
                    return authorRepository.save(newAuthor);
                });

        // Crear y guardar el nuevo libro
        Book newBook = new Book(bookDTO);
        newBook.setAuthor(author);
        bookRepository.save(newBook);

        System.out.println("Libro guardado en la base de datos:");
        showBook(newBook);
    }



    private void showBook(Book book) {
        System.out.println(book.toString());
    }

    private void showAuthor(Author author){
        System.out.println(author.toString());
    }

}
