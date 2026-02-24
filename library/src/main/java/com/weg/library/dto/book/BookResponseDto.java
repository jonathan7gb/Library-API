package com.weg.library.dto.book;

public record BookResponseDto(
    Long id,
    String title,
    String author,
    int publication_year
){
}