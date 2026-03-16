package com.weg.library.controller;

import com.weg.library.dto.user.UserRequestDto;
import com.weg.library.dto.user.UserResponseDto;
import com.weg.library.model.User;
import com.weg.library.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Cadastrar Usuário!",
            description = "Cadastra um novo usuário no Library API."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso!"),
    })
    @PostMapping
    public ResponseEntity<UserResponseDto> save(
            @RequestBody @Valid UserRequestDto user
    ){
        try{
           return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Operation(
            summary = "Listar Usuários!",
            description = "Lista todos os usuários cadastrados no Library API."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Nenhum usuários encontrado!"),
    })
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUsers(){
        try{
            return ResponseEntity.ok().body(userService.findAllUsers());
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Operation(
            summary = "Atualiza Usuário por ID!",
            description = "Atualiza o usuário correspondente com o ID inserido no Library API."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado com esse ID!"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid UserRequestDto user
    ){
        try{
            return ResponseEntity.ok().body(userService.updateUser(user,id));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Operation(
            summary = "Busca Usuário por ID!",
            description = "Lista o usuário correspondente com o ID inserido no Library API."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado com esse ID!"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(
            @PathVariable Long id
    ){
        try{
            return ResponseEntity.ok().body(userService.findUserById(id));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Operation(
            summary = "Deleta Usuário por ID!",
            description = "Deleta o usuário correspondente com o ID inserido no Library API."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado com esse ID!"),
    })
    @DeleteMapping("/{id}")
    public void deleteUserById(
            @PathVariable Long id
    ){
        try{
            userService.deleteUserById(id);
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
