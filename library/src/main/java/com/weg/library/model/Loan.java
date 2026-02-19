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

}
