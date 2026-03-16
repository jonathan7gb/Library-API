package com.weg.library.dto.book;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Schema(description = "Objeto que representa os dados de um livro enviados para API")
public record BookRequestDto(
        @Schema(
                description = "Título do Livro",
                example = "O homem mais rico da babilônia",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "O título é obrigatório")
        @NotNull(message = "O título não pode ser nulo")
        String title,

        @Schema(
                description = "Autor do Livro",
                example = "Santos Dumont",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "O autor é obrigatório")
        @NotNull(message = "O autor não pode ser nulo")
        String author,

        @Schema(
                description = "Ano de publicação do Livro",
                example = "1920",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "O ano de publicação é obrigatório")
        @NotNull(message = "O ano de publicação não pode ser nulo")
        @Past(message = "O ano não pode superior ao ano atual")
        int publication_year
) {
}
