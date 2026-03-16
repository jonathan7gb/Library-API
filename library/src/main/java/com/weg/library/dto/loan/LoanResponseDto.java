package com.weg.library.dto.loan;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Objeto que representa os dados de um empréstimo retornados pela API")
public record LoanResponseDto(
        @Schema(
                description = "Id do Empréstimo",
                example = "3",
                accessMode = Schema.AccessMode.READ_ONLY
        )
        Long id,

        @Schema(
                description = "Id do livro",
                example = "3",
                accessMode = Schema.AccessMode.READ_ONLY
        )
        Long book_id,

        @Schema(
                description = "Id do usuário",
                example = "3",
                accessMode = Schema.AccessMode.READ_ONLY
        )
        Long user_id,

        @Schema(
                description = "Data do Empréstimo",
                example = "30/10/2026",
                accessMode = Schema.AccessMode.READ_ONLY
        )
        LocalDate loan_date,

        @Schema(
                description = "Data de Devolução",
                example = "04/11/2026",
                accessMode = Schema.AccessMode.READ_ONLY
        )
        LocalDate return_date
) {
}
