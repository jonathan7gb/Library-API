package com.weg.library.controller;

import com.weg.library.model.Loan;
import com.weg.library.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<Loan> save(
            @RequestBody Loan loan
    ){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(loanService.saveLoan(loan));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Loan>> findAllLoans(){
        List<Loan> loans = new ArrayList<>();
        try{
            return ResponseEntity.ok().body(loanService.findAllLoans());
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Loan> updateLoan(
            @PathVariable Long id,
            @RequestBody Loan loan
    ){
        try{
            return ResponseEntity.ok().body(loanService.updateLoan(loan,id));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> findLoanById(
            @PathVariable Long id
    ){
        try{
            return ResponseEntity.ok().body(loanService.findLoanById(id));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteLoanById(
            @PathVariable Long id
    ){
        try{
            loanService.deleteLoanById(id);
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/user_loans/{id}")
    public ResponseEntity<List<Loan>> findAllLoans(
            @PathVariable Long id
    ){
        List<Loan> loans = new ArrayList<>();
        try{
            return ResponseEntity.ok().body(loanService.findAllLoansByUser(id));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/register_return_date/{id}")
    public ResponseEntity<String> registerReturnDate(
            @PathVariable Long id,
            @RequestBody Loan loan
    ){
        try{
            loanService.registerReturnDate(id, loan.getReturn_date());
            return ResponseEntity.ok().body("Return date registered!");
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
