package com.weg.library.mapper;

import com.weg.library.dto.loan.LoanRequestDto;
import com.weg.library.dto.loan.LoanResponseDto;
import com.weg.library.model.Loan;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    public Loan toEntity(LoanRequestDto loanRequestDto){
        return new Loan(loanRequestDto.book_id(), loanRequestDto.user_id(),loanRequestDto.loan_date(), loanRequestDto.return_date());
    }

    public LoanResponseDto toDto(Loan loan){
        return new LoanResponseDto(loan.getId(), loan.getBook_id(), loan.getUser_id(), loan.getLoan_date(), loan.getReturn_date());
    }
}
