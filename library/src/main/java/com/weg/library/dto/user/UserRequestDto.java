package com.weg.library.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Objeto que representa os dados de um usuário enviados para API")
public record UserRequestDto(
        @Schema(
                description = "Nome do usuário",
                example = "Jonathan Uber",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "O nome é obrigatório")
        @NotNull(message = "O nome não pode ser nulo")
        String name,

        @Schema(
                description = "Email do usuário",
                example = "jonathan@gmail.com",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "O email é obrigatório")
        @NotNull(message = "O email não pode ser nulo")
        @Email(message = "Formato de email inválido")
        String email
) {
}
