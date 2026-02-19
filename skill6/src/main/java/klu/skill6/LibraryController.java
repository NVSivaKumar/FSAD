package klu.skill6;


import klu.skill6.Book;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private List<Book> bookStore = new ArrayList<>();

    // 2. /welcome
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Library!";
    }

    // 3. /count
    @GetMapping("/count")
    public int totalBooks() {
        return bookStore.size();
    }

    // 4. /price
    @GetMapping("/price")
    public double samplePrice() {
        return 29.99;
    }

    // 5. /books
    @GetMapping("/books")
    public List<String> getBookTitles() {
        return Arrays.asList("Java Basics", "Spring Boot Guide", "REST API Design");
    }

    // 6. /books/{id}
    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable int id) {
        return "Details for Book ID: " + id;
    }

    // 7. /search?title=
    @GetMapping("/search")
    public String searchBook(@RequestParam String title) {
        return "Searching for book: " + title;
    }

    // 8. /author/{name}
    @GetMapping("/author/{name}")
    public String getAuthor(@PathVariable String name) {
        return "Books written by: " + name;
    }

    // 9. /addbook
    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book) {
        bookStore.add(book);
        return "Book added successfully!";
    }

    // 10. /viewbooks
    @GetMapping("/viewbooks")
    public List<Book> viewBooks() {
        return bookStore;
    }
}

