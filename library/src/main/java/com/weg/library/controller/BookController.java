package com.weg.library.controller;

import com.weg.library.model.Book;
import com.weg.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> save(
            @RequestBody Book book
    ){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(book));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAllBooks(){
        List<Book> books = new ArrayList<>();
        try{
           return ResponseEntity.ok().body(bookService.findAllBooks());
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Long id,
            @RequestBody Book book
    ){
        try{
            return ResponseEntity.ok().body(bookService.updateBook(book,id));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(
            @PathVariable Long id
    ){
        try{
            return ResponseEntity.ok().body(bookService.findBookById(id));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(
            @PathVariable Long id
    ){
        try{
            bookService.deleteBookById(id);
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
