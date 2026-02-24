package com.weg.library.service;

import com.weg.library.dto.book.BookRequestDto;
import com.weg.library.dto.book.BookResponseDto;
import com.weg.library.mapper.BookMapper;
import com.weg.library.model.Book;
import com.weg.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public BookResponseDto saveBook(BookRequestDto book) throws SQLException{
        if(book == null){
            throw new RuntimeException("Book can't be null!");
        }

        Book booksaved = bookRepository.save(bookMapper.toEntity(book));
        return bookMapper.toDto(booksaved);
    }

    public List<BookResponseDto> findAllBooks() throws SQLException {
        List<Book> bookList = bookRepository.findAll();
        List<BookResponseDto> bookListDto =  new ArrayList<>();

        if(bookList.isEmpty()){
            throw new RuntimeException("No Books found!");
        }

        for(Book b : bookList){
            bookListDto.add(bookMapper.toDto(b));
        }

        return bookListDto;
    }

    public BookResponseDto updateBook(BookRequestDto book, Long id) throws SQLException{
        Book bookFound = bookRepository.findById(id);

        if(bookFound == null){
            throw new RuntimeException("No books with this Id found!");
        }

        bookRepository.update(bookMapper.toEntity(book), id);


        return bookMapper.toDto(bookMapper.toEntity(book));
    }

    public BookResponseDto findBookById(Long id) throws SQLException {
        Book bookFound = bookRepository.findById(id);
        if(bookFound == null){
            throw new RuntimeException("No books with this Id found!");
        }
        return bookMapper.toDto(bookFound);
    }

    public void deleteBookById(Long id) throws SQLException {
        if(bookRepository.findById(id) == null) {
            throw new RuntimeException("No books with this Id found!");
        }

        bookRepository.deleteById(id);
    }

}
