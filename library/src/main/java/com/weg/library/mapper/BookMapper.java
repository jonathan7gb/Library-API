package com.weg.library.mapper;

import com.weg.library.dto.book.BookRequestDto;
import com.weg.library.dto.book.BookResponseDto;
import com.weg.library.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toEntity(BookRequestDto bookRequestDto){
        return new Book(bookRequestDto.title(), bookRequestDto.author(),bookRequestDto.publication_year());
    }

    public BookResponseDto toDto(Book book){
        return new BookResponseDto(book.getId(), book.getTitle(), book.getAuthor(), book.getPublication_year());
    }
}
