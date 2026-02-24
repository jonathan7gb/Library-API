package com.weg.library.dto.loan;

import java.time.LocalDate;

public record LoanResponseDto(
        Long id,
        Long book_id,
        Long user_id,
        LocalDate loan_date,
        LocalDate return_date
) {
}
