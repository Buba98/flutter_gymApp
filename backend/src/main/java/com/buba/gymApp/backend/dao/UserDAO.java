package com.buba.gymApp.backend.dao;

import com.buba.gymApp.backend.model.administrationComponents.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    int insertUser(User user);

    List<User> selectAllUsers();

    int deleteUserById(int id);

    int updateUserById(int id, User user);

    Optional<User> selectUserById(int id);

    User selectUserByEmail(String email);
}
