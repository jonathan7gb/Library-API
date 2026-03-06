package com.weg.library.dto.loan;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record LoanRequestDto (

        @NotBlank(message = "O id do livro é obrigatório")
        @NotNull(message = "O id do livro não pode ser nulo")
        Long book_id,

        @NotBlank(message = "O id do usuário é obrigatório")
        @NotNull(message = "O id do usuário não pode ser nulo")
        Long user_id,

        @NotBlank(message = "A data de empréstimo é obrigatória")
        @NotNull(message = "A data de empréstimo não pode ser nula")
        LocalDate loan_date,

        @NotBlank(message = "A data de devolução é obrigatória")
        @NotNull(message = "A data de devolução não pode ser nula")
        @Future(message = "A data de devolução não pode ser no passado")
        LocalDate return_date
){
}
