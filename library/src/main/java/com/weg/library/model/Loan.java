package com.weg.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan {

    private Long id;
    private Long book_id;
    private Long user_id;
    private LocalDate loan_date;
    private LocalDate return_date;

    public Loan(Long book_id, Long user_id, LocalDate loan_date) {
        this.book_id = book_id;
        this.user_id = user_id;
        this.loan_date = loan_date;
    }

    public Loan(Long book_id, Long user_id, LocalDate loan_date, LocalDate return_date) {
        this.book_id = book_id;
        this.user_id = user_id;
        this.loan_date = loan_date;
        this.return_date = return_date;
    }
}
