package com.weg.library.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookRequestDto(
        @NotBlank(message = "O título é obrigatório")
        @NotNull(message = "O título não pode ser nulo")
        String title,

        @NotBlank(message = "O autor é obrigatório")
        @NotNull(message = "O autor não pode ser nulo")
        String author,

        @NotBlank(message = "O ano de publicação é obrigatório")
        @NotNull(message = "O ano de publicação não pode ser nulo")
        int publication_year
) {
}
