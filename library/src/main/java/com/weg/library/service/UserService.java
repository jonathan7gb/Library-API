package com.weg.library.service;

import com.weg.library.model.User;
import com.weg.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User saveUser(User user) throws SQLException {
        if(user == null){
            throw new RuntimeException("User can't be null!");
        }

        return userRepository.save(user);
    }

    public List<User> findAllUsers() throws SQLException {
        List<User> userList = userRepository.findAll();

        if(userList.isEmpty()){
            throw new RuntimeException("No Users found!");
        }

        return userList;
    }

    public User uopdateUser(User user, Long id) throws SQLException{
        user = userRepository.findById(id);

        if(user == null){
            throw new RuntimeException("No users with this Id found!");
        }

        userRepository.update(user, id);

        return user;
    }

    public User findUserById(Long id) throws SQLException {
        User userFound = userRepository.findById(id);
        if(userFound == null){
            throw new RuntimeException("No users with this Id found!");
        }
        return userFound;
    }

    public void deleteUserById(Long id) throws SQLException {
        if(userRepository.findById(id) == null) {
            throw new RuntimeException("No users with this Id found!");
        }

        userRepository.deleteById(id);
    }
}
