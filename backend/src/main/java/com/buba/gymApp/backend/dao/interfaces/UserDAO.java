package com.buba.gymApp.backend.dao.interfaces;

import com.buba.gymApp.backend.model.administrationComponents.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface UserDAO {

    User insertUser(String name, String surname, String email, String fiscalCode, Date birthday, String password, Date[] insurances, String phone, boolean owner);

    List<User> selectAllUsers();

    boolean deleteUserById(int id);

    User updateUserById(User user);

    User selectUserById(int id);

    User selectUserByEmail(String email);

    User selectUserByFiscalCode(String fiscalCode);

    List<String[]> selectForAutocomplete(String name, String surname);
}
