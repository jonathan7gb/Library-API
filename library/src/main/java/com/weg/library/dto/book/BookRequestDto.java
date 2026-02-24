package com.weg.library.dto.book;

public record BookRequestDto(
        String title,
        String author,
        int publication_year
) {
}
