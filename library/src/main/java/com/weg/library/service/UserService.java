package com.weg.library.service;

import com.weg.library.dto.user.UserRequestDto;
import com.weg.library.dto.user.UserResponseDto;
import com.weg.library.mapper.UserMapper;
import com.weg.library.model.Book;
import com.weg.library.model.User;
import com.weg.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService (UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDto saveUser(UserRequestDto user) throws SQLException {
        if(user == null){
            throw new RuntimeException("User can't be null!");
        }

        User usersaved = userRepository.save(userMapper.toEntity(user));
        return userMapper.toDto(usersaved);
    }

    public List<UserResponseDto> findAllUsers() throws SQLException {
        List<User> userList = userRepository.findAll();
        List<UserResponseDto> userListDto = new ArrayList<>();

        if(userList.isEmpty()){
            throw new RuntimeException("No Users found!");
        }

        for(User u : userList){
            userListDto.add(userMapper.toDto(u));
        }

        return userListDto;
    }

    public UserResponseDto updateUser(UserRequestDto user, Long id) throws SQLException{
        User userFound = userRepository.findById(id);

        if(userFound == null){
            throw new RuntimeException("No users with this Id found!");
        }

        userRepository.update(userMapper.toEntity(user), id);

        return userMapper.toDto(userMapper.toEntity(user));
    }

    public UserResponseDto findUserById(Long id) throws SQLException {
        User userFound = userRepository.findById(id);
        if(userFound == null){
            throw new RuntimeException("No users with this Id found!");
        }
        return userMapper.toDto(userFound);
    }

    public void deleteUserById(Long id) throws SQLException {
        if(userRepository.findById(id) == null) {
            throw new RuntimeException("No users with this Id found!");
        }

        userRepository.deleteById(id);
    }
}
