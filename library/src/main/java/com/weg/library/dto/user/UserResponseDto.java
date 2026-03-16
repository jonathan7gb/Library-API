package com.weg.library.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto que representa os dados de um usuário retornados pela API")
public record UserResponseDto (
        @Schema(
                description = "Id do usuário",
                example = "3",
                accessMode = Schema.AccessMode.READ_ONLY
        )
        Long id,

        @Schema(
                description = "Nome do usuário",
                example = "Jonathan Luis Uber",
                accessMode = Schema.AccessMode.READ_ONLY
        )
        String name,

        @Schema(
                description = "Email do usuário",
                example = "jonathan@gmail.com",
                accessMode = Schema.AccessMode.READ_ONLY
        )
        String email
){
}
