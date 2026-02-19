package com.weg.library.controller;

import com.weg.library.model.User;
import com.weg.library.service.UserService;
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

    @PostMapping
    public ResponseEntity<User> save(
            @RequestBody User user
    ){
        try{
           return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(){
        try{
            return ResponseEntity.ok().body(userService.findAllUsers());
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody User user
    ){
        try{
            return ResponseEntity.ok().body(userService.updateUser(user,id));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(
            @PathVariable Long id
    ){
        try{
            return ResponseEntity.ok().body(userService.findUserById(id));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

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
