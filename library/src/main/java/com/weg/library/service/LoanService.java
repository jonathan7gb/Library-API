package com.weg.library.service;

import com.weg.library.model.Loan;
import com.weg.library.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService (LoanRepository loanRepository){
        this.loanRepository = loanRepository;
    }

    public Loan saveLoan(Loan loan) throws SQLException {
        if(loan == null){
            throw new RuntimeException("Loan can't be null!");
        }

        return loanRepository.save(loan);
    }

    public List<Loan> findAllLoans() throws SQLException {
        List<Loan> loanList = loanRepository.findAll();

        if(loanList.isEmpty()){
            throw new RuntimeException("No Loans found!");
        }

        return loanList;
    }

    public Loan updateLoan(Loan loan, Long id) throws SQLException{
        Loan loanFound = loanRepository.findById(id);

        if(loanFound == null){
            throw new RuntimeException("No loans with this Id found!");
        }

        loanRepository.update(loan, id);

        return loan;
    }

    public Loan findLoanById(Long id) throws SQLException {
        Loan loanFound = loanRepository.findById(id);
        if(loanFound == null){
            throw new RuntimeException("No loans with this Id found!");
        }
        return loanFound;
    }

    public void deleteLoanById(Long id) throws SQLException {
        if(loanRepository.findById(id) == null) {
            throw new RuntimeException("No loans with this Id found!");
        }

        loanRepository.deleteById(id);
    }

    public List<Loan> findAllLoansByUser(Long id) throws SQLException {
        List<Loan> loanList = loanRepository.findAllByUserId(id);

        if(loanList.isEmpty()){
            throw new RuntimeException("No Loans found!");
        }

        return loanList;
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
