package com.weg.library.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDto(
        @NotBlank(message = "O nome é obrigatório")
        @NotNull(message = "O nome não pode ser nulo")
        String name,

        @NotBlank(message = "O email é obrigatório")
        @NotNull(message = "O email não pode ser nulo")
        String email
) {
}
