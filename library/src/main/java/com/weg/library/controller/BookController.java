package com.weg.library.controller;

import com.weg.library.dto.book.BookRequestDto;
import com.weg.library.dto.book.BookResponseDto;
import com.weg.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(name="Books", description = "Endpoints para gerencimento do livros no sistema")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(
            summary = "Cadastrar Livro!",
            description = "Cadastra um novo livro no Library API."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro cadastrado com sucesso!"),
    })
    @PostMapping
    public ResponseEntity<BookResponseDto> save(
            @RequestBody @Valid BookRequestDto book
    ){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(book));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Operation(
            summary = "Listar Livros!",
            description = "Lista todos os livros cadastrados no Library API."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livros encontrados com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Nenhum livro encontrado!"),
    })
    @GetMapping
    public ResponseEntity<List<BookResponseDto>> findAllBooks(){
        List<BookResponseDto> books = new ArrayList<>();
        try{
           return ResponseEntity.ok().body(bookService.findAllBooks());
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Operation(
            summary = "Atualiza Livro por ID!",
            description = "Atualiza o livro correspondente com o ID inserido no Library API."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Nenhum livro encontrado com esse ID!"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(
            @PathVariable Long id,
            @RequestBody @Valid BookRequestDto book
    ){
        try{
            return ResponseEntity.ok().body(bookService.updateBook(book,id));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Operation(
            summary = "Busca Livro por ID!",
            description = "Lista o livro correspondente com o ID inserido no Library API."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro encontrado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Nenhum livro encontrado com esse ID!"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> findBookById(
            @PathVariable Long id
    ){
        try{
            return ResponseEntity.ok().body(bookService.findBookById(id));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Operation(
            summary = "Deleta Livro por ID!",
            description = "Deleta o livro correspondente com o ID inserido no Library API."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Nenhum livro encontrado com esse ID!"),
    })
    @DeleteMapping("/{id}")
    public void deleteBookById(
            @PathVariable Long id
    ){
        try{
            bookService.deleteBookById(id);
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
