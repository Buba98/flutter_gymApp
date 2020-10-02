package com.buba.gymApp.backend.service;

import com.buba.gymApp.backend.dao.UserDAO;
import com.buba.gymApp.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(@Qualifier("postgres") UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public int addUser(User user){
        return userDAO.insertUser(user);
    }

    public List<User> getAllUsers(){
        return userDAO.selectAllUsers();
    }

    public Optional<User> getUserById(int id){
        return userDAO.selectUserById(id);
    }

    public int deleteUser(int id){
        return userDAO.deleteUserById(id);
    }

    public int updateUser(int id, User user){
        return userDAO.updateUserById(id, user);
    }
}
