package com.weg.library.service;

import com.weg.library.dto.loan.LoanRequestDto;
import com.weg.library.dto.loan.LoanResponseDto;
import com.weg.library.mapper.LoanMapper;
import com.weg.library.model.Loan;
import com.weg.library.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;

    public LoanService (LoanRepository loanRepository, LoanMapper loanMapper){
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;
    }

    public LoanResponseDto saveLoan(LoanRequestDto loan) throws SQLException {
        if(loan == null){
            throw new RuntimeException("Loan can't be null!");
        }

        List<Loan> listLoan = loanRepository.findAll();
        for(Loan l: listLoan){
            if(Objects.equals(l.getBook_id(), loan.book_id()) && l.getReturn_date() == null){
                throw new RuntimeException("Loan cancelled. This book is already being used for another loan!");
            }
        }

        Loan loansaved = loanRepository.save(loanMapper.toEntity(loan));

        return loanMapper.toDto(loansaved);
    }

    public List<LoanResponseDto> findAllLoans() throws SQLException {
        List<Loan> loanList = loanRepository.findAll();
        List<LoanResponseDto> loanListDto = new ArrayList<>();

        if(loanList.isEmpty()){
            throw new RuntimeException("No Loans found!");
        }

        for(Loan l : loanList){
            loanListDto.add(loanMapper.toDto(l));
        }

        return loanListDto;
    }

    public LoanResponseDto updateLoan(LoanRequestDto loan, Long id) throws SQLException{
        Loan loanFound = loanRepository.findById(id);

        if(loanFound == null){
            throw new RuntimeException("No loans with this Id found!");
        }

        loanRepository.update(loanMapper.toEntity(loan), id);

        return loanMapper.toDto(loanMapper.toEntity(loan));
    }

    public LoanResponseDto findLoanById(Long id) throws SQLException {
        Loan loanFound = loanRepository.findById(id);
        if(loanFound == null){
            throw new RuntimeException("No loans with this Id found!");
        }
        return loanMapper.toDto(loanFound);
    }

    public void deleteLoanById(Long id) throws SQLException {
        if(loanRepository.findById(id) == null) {
            throw new RuntimeException("No loans with this Id found!");
        }

        loanRepository.deleteById(id);
    }

    public List<LoanResponseDto> findAllLoansByUser(Long id) throws SQLException {
        List<Loan> loanList = loanRepository.findAllByUserId(id);
        List<LoanResponseDto> loanListDto = new ArrayList<>();

        if(loanList.isEmpty()){
            throw new RuntimeException("No Loans found!");
        }

        for(Loan l : loanList){
            loanListDto.add(loanMapper.toDto(l));
        }

        return loanListDto;
    }

    public void registerReturnDate (Long id, LocalDate return_date) throws SQLException{

        if(loanRepository.findById(id) == null){
            throw new RuntimeException("No loans with this Id found!");
        }

        if(!loanRepository.registerReturnDate(id, return_date)){
            throw new RuntimeException("Error: return date not registered at the system");
        }

    }


}
