package com.weg.library.dto.loan;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

@Schema(description = "Objeto que representa os dados de um empréstimo enviados para API")
public record LoanRequestDto (

        @Schema(
                description = "ID do Livro",
                example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "O id do livro é obrigatório")
        @NotNull(message = "O id do livro não pode ser nulo")
        Long book_id,

        @Schema(
                description = "ID do usuário",
                example = "2",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "O id do usuário é obrigatório")
        @NotNull(message = "O id do usuário não pode ser nulo")
        Long user_id,

        @Schema(
                description = "Data do empréstimo",
                example = "12/10/2025",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "A data de empréstimo é obrigatória")
        @NotNull(message = "A data de empréstimo não pode ser nula")
        LocalDate loan_date,

        @Schema(
                description = "Data de Devolução",
                example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "A data de devolução é obrigatória")
        @NotNull(message = "A data de devolução não pode ser nula")
        @Future(message = "A data de devolução não pode ser no passado")
        LocalDate return_date
){
}
