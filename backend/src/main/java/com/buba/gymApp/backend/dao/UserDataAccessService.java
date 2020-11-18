package com.buba.gymApp.backend.dao;

import com.buba.gymApp.backend.dao.interfaces.UserDAO;
import com.buba.gymApp.backend.model.administrationComponents.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.*;

@Repository("postgresUser")
public class UserDataAccessService implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Insert a new user into DB
     * @param name name
     * @param surname surname
     * @param email email
     * @param fiscalCode fiscal code
     * @param birthday birthday
     * @param password password
     * @param insurances insurances
     * @param phone phone
     * @param owner owner
     * @return return the user if it has been created, null otherwise
     */
    @Override
    public User insertUser(String name, String surname, String email, String fiscalCode, Date birthday, String password, Date[] insurances, String phone, boolean owner) {

        String sql = "INSERT INTO \"user\" (name, surname, email, \"fiscalCode\", birthday, password, insurances, phone, owner) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, surname);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, fiscalCode);
                preparedStatement.setDate(5, new java.sql.Date(birthday.getTime()));
                preparedStatement.setString(6, password);
                preparedStatement.setArray(7, connection.createArrayOf("date", insurances));
                preparedStatement.setString(8, phone);
                preparedStatement.setBoolean(9, owner);
                return preparedStatement;
            });
            return new User(Objects.requireNonNull(keyHolder.getKey()).intValue(), name, surname, fiscalCode, birthday, email, password, phone, insurances, owner);
        } catch (DataAccessException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return Return all the users of DB
     */
    @Override
    public List<User> selectAllUsers() {
        String sql = "SELECT * FROM \"user\"";
        try {
            return jdbcTemplate.query(sql, User.mapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * delete an user from DB
     * @param id id of user
     * @return true if it has been created successfully, false otherwise
     */
    @Override
    public boolean deleteUserById(int id) {

        String sql = "DELETE FROM \"user\" WHERE id = ?";
        try {
            return jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                return preparedStatement;
            }) == 1;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update an user
     * @param user user already updated
     * @return the updated updated user if has been updated, null otherwise
     */
    @Override
    public User updateUserById(User user) {
        String sql = "UPDATE \"user\" SET name = ?, surname = ?, email = ?, \"fiscalCode\" = ?, birthday = ?, password = ?,  phone = ?, insurances = ?, owner = ? WHERE id = ?";
        try {
            jdbcTemplate.update(connection->{
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getSurname());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getFiscalCode());
                preparedStatement.setDate(5, new java.sql.Date(user.getBirthday().getTime()));
                preparedStatement.setString(6, user.getPassword());
                preparedStatement.setString(7, user.getPhoneNumber());

                java.sql.Date[] insurances = new java.sql.Date[user.getInsurances().length];
                for (int i = 0; i < user.getInsurances().length; i++){
                    insurances[i] = new java.sql.Date(user.getInsurances()[i].getTime());
                }

                preparedStatement.setArray(8, connection.createArrayOf("date", insurances));
                preparedStatement.setBoolean(9, user.isOwner());
                preparedStatement.setInt(10, user.getId());

                return preparedStatement;
            });
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Select user by id
     * @param id id
     * @return the user if it has been found, null otherwise
     */
    @Override
    public User selectUserById(int id) {
        String sql = "SELECT * FROM \"user\" WHERE id = ?";
        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setInt(1, id), User.mapper()).get(0);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Select user by email
     * @param email email
     * @return the user if it has been found, null otherwise
     */
    @Override
    public User selectUserByEmail(String email) {
        String sql = "SELECT * FROM \"user\" WHERE email = ?";
        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setString(1, email), User.mapper()).get(0);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Select user by email
     * @param fiscalCode fiscal code
     * @return the user if it has been found, null otherwise
     */
    @Override
    public User selectUserByFiscalCode(String fiscalCode) {
        String sql = "SELECT * FROM \"user\" WHERE \"fiscalCode\" = ?";
        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setString(1, fiscalCode), User.mapper()).get(0);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String[]> selectForAutocomplete(String name, String surname){
        String sql = "SELECT name, surname, birthday, \"fiscalCode\" FROM \"user\" WHERE name LIKE ? AND surname LIKE ?";

        return jdbcTemplate.query(sql, new Object[]{"%" + name + "%" , "%" + surname + "%"}, (resultSet, i) -> new String[]{resultSet.getString("name"), resultSet.getString("surname"), resultSet.getDate("birthday").toString(), resultSet.getString("fiscalCode")});

    }
}