package com.buba.gymApp.backend.dao.userDAO;

import com.buba.gymApp.backend.model.administrationComponents.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    User insertUser(User user);

    List<User> selectAllUsers();

    boolean deleteUserById(int id);

    boolean updateUserById(User user);

    User selectUserById(int id);

    User selectUserByEmail(String email);

    User selectUserByFiscalCode(String fiscalCode);
}
