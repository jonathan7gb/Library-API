package com.weg.library.controller;

import com.weg.library.dto.loan.LoanRequestDto;
import com.weg.library.dto.loan.LoanResponseDto;
import com.weg.library.model.Loan;
import com.weg.library.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
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

    @Operation(
            summary = "Cadastrar Empréstimo!",
            description = "Cadastra um novo empréstimo no Library API."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro empréstimo com sucesso!"),
    })
    @PostMapping
    public ResponseEntity<LoanResponseDto> save(
            @RequestBody @Valid LoanRequestDto loan
    ){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(loanService.saveLoan(loan));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Operation(
            summary = "Listar Empréstimos!",
            description = "Lista todos os empréstimos cadastrados no Library API."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empréstimos encontrados com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Nenhum empréstimos encontrado!"),
    })
    @GetMapping
    public ResponseEntity<List<LoanResponseDto>> findAllLoans(){
        List<LoanResponseDto> loans = new ArrayList<>();
        try{
            return ResponseEntity.ok().body(loanService.findAllLoans());
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Operation(
            summary = "Atualiza Empréstimo por ID!",
            description = "Atualiza o empréstimo correspondente com o ID inserido no Library API."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empréstimo atualizado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Nenhum livro empréstimo com esse ID!"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<LoanResponseDto> updateLoan(
            @PathVariable Long id,
            @RequestBody @Valid LoanRequestDto loan
    ){
        try{
            return ResponseEntity.ok().body(loanService.updateLoan(loan,id));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Operation(
            summary = "Busca Empréstimo por ID!",
            description = "Lista o empréstimo correspondente com o ID inserido no Library API."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empréstimo encontrado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Nenhum empréstimo encontrado com esse ID!"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<LoanResponseDto> findLoanById(
            @PathVariable Long id
    ){
        try{
            return ResponseEntity.ok().body(loanService.findLoanById(id));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Operation(
            summary = "Deleta Empréstimo por ID!",
            description = "Deleta o empréstimo correspondente com o ID inserido no Library API."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empréstimo deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Nenhum empréstimo encontrado com esse ID!"),
    })
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

    @Operation(
            summary = "Listar Empréstimos por Usuário!",
            description = "Lista todos os empréstimos por usuário cadastrados no Library API."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empréstimos encontrados com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Nenhum empréstimos encontrado!"),
    })
    @GetMapping("/user_loans/{id}")
    public ResponseEntity<List<LoanResponseDto>> findAllLoansByUserID(
            @PathVariable Long id
    ){
        List<Loan> loans = new ArrayList<>();
        try{
            return ResponseEntity.ok().body(loanService.findAllLoansByUser(id));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Operation(
            summary = "Atualizar a Data de Retorno do Empréstimo por ID!",
            description = "Atualizar a data de retorno do empréstimo correspondente com o ID inserido no Library API."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empréstimo deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Nenhum empréstimo encontrado com esse ID!"),
    })
    @PutMapping("/register_return_date/{id}")
    public ResponseEntity<String> registerReturnDate(
            @PathVariable Long id,
            @RequestBody @Valid Loan loan
    ){
        try{
            loanService.registerReturnDate(id, loan.getReturn_date());
            return ResponseEntity.ok().body("Return date registered!");
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
