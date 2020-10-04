package com.buba.gymApp.backend.dao;

import com.buba.gymApp.backend.model.administrationComponents.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository("postgres")
public class UserDataAccessService implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertUser(User user) {
        return 0;
    }

    @Override
    public List<User> selectAllUsers() {
        String sql = "SELECT * FROM user";
        List<User> users = jdbcTemplate.query(sql, (resultSet, i) -> fromResultSetToUser(resultSet));
        return users;
    }

    @Override
    public int deleteUserById(int id) {
        return 0;
    }

    @Override
    public int updateUserById(int id, User user) {
        return 0;
    }

    @Override
    public Optional<User> selectUserById(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        User user =  jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> fromResultSetToUser(resultSet));
        return Optional.ofNullable(user);
    }

    @Override
    public User selectUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{email}, ((resultSet, i) -> fromResultSetToUser(resultSet)));
        return user;
    }

    private User fromResultSetToUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String fiscalCode = resultSet.getString("fiscalCode");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        List<Integer> userTrainingSchedule = new ArrayList<>();//todo
        Date birthday = resultSet.getDate("birthday");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String phoneNumber = resultSet.getString("phoneNumber");
        List<Integer> userSubscriptions = new ArrayList<>(); //todo
        List<Date> insurances = Arrays.asList((Date[]) resultSet.getArray("insurances").getArray());

        return new User(id, name, surname, fiscalCode, birthday, userTrainingSchedule, email, password, phoneNumber, userSubscriptions, insurances);
    }
}
