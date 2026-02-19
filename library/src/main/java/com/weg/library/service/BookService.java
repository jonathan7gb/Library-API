package com.weg.library.service;

import com.weg.library.model.Book;
import com.weg.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService (BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book) throws SQLException{
        if(book == null){
            throw new RuntimeException("Book can't be null!");
        }

        return bookRepository.save(book);
    }

    public List<Book> findAllBooks() throws SQLException {
        List<Book> bookList = bookRepository.findAll();

        if(bookList.isEmpty()){
            throw new RuntimeException("No Books found!");
        }

        return bookList;
    }

    public Book updateBook(Book book, Long id) throws SQLException{
        Book bookFound = bookRepository.findById(id);

        if(bookFound == null){
            throw new RuntimeException("No books with this Id found!");
        }

        bookRepository.update(book, id);

        return book;
    }

    public Book findBookById(Long id) throws SQLException {
        Book bookFound = bookRepository.findById(id);
        if(bookFound == null){
            throw new RuntimeException("No books with this Id found!");
        }
        return bookFound;
    }

    public void deleteBookById(Long id) throws SQLException {
        if(bookRepository.findById(id) == null) {
            throw new RuntimeException("No books with this Id found!");
        }

        bookRepository.deleteById(id);
    }

}
