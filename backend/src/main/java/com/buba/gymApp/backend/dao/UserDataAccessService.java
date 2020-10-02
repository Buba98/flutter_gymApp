package com.buba.gymApp.backend.dao;

import com.buba.gymApp.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
        List<User> users = jdbcTemplate.query(sql, (resultSet, i) -> {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");

            return new User(id, name);
        });

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
        User user =  jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            int _id = resultSet.getInt("id");
            String name = resultSet.getString("name");

            return new User(_id, name);
        });
        return Optional.ofNullable(user);

    }
}
