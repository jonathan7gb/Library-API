package com.weg.library.dto.book;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto que representa os dados de um livro retornados pela API")
public record BookResponseDto(
        @Schema(
                description = "Código de identificação único do livro gerado pelo banco",
                example = "1",
                accessMode = Schema.AccessMode.READ_ONLY
        )
        Long id,

        @Schema(
                description = "Título do Livro",
                example = "O homem mais rico da babilônia",
                accessMode = Schema.AccessMode.READ_ONLY
        )
        String title,

        @Schema(
                description = "Autor do Livro",
                example = "Santos Dumont",
                accessMode = Schema.AccessMode.READ_ONLY
        )
        String author,

        @Schema(
                description = "Ano de publicação do Livro",
                example = "1920",
                accessMode = Schema.AccessMode.READ_ONLY
        )
        int publication_year
){
}