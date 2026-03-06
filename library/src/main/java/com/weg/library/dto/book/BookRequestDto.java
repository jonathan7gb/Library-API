package com.weg.library.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record BookRequestDto(
        @NotBlank(message = "O título é obrigatório")
        @NotNull(message = "O título não pode ser nulo")
        String title,

        @NotBlank(message = "O autor é obrigatório")
        @NotNull(message = "O autor não pode ser nulo")
        String author,

        @NotBlank(message = "O ano de publicação é obrigatório")
        @NotNull(message = "O ano de publicação não pode ser nulo")
        @Past(message = "O ano não pode superior ao ano atual")
        int publication_year
) {
}
