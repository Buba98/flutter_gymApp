package com.buba.gymApp.backend.dao.userDAO;

import com.buba.gymApp.backend.dao.userSubscriptionDAO.UserSubscriptionDAO;
import com.buba.gymApp.backend.model.administrationComponents.User;
import com.buba.gymApp.backend.utils.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository("postgresUser")
public class UserDataAccessService implements UserDAO {

    private final JdbcTemplate jdbcTemplate;
    private final UserSubscriptionDAO userSubscriptionDAO;

    @Autowired
    public UserDataAccessService(JdbcTemplate jdbcTemplate, @Qualifier("postgresUserSubscription") UserSubscriptionDAO userSubscriptionDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.userSubscriptionDAO = userSubscriptionDAO;
    }

    @Override
    public User insertUser(User user) {
        String sql = "INSERT INTO \"user\" (name, surname, email, fiscalcode, birthday, password, phonenumber, owner) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Object[] objects = new Object[]{user.getName(), user.getSurname(), user.getFiscalCode(), user.getBirthday(), user.getPassword(), user.getPhoneNumber(), user.isOwner()};

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, objects, keyHolder);

        user.setId(keyHolder.getKey().intValue());

        return user;
    }

    @Override
    public List<User> selectAllUsers() {
        String sql = "SELECT * FROM \"user\"";
        return jdbcTemplate.query(sql, (resultSet, i) -> fromResultSetToUser(resultSet));
    }

    @Override
    public boolean deleteUserById(int id) {

        String sql = "DELETE FROM \"user\" WHERE id = ?";
        return jdbcTemplate.update(sql, id) == 1;
    }

    @Override
    public boolean updateUserById(User user) {
        try {
            String sql = "UPDATE user SET name = ?, surname = ?, email = ?, fiscalcode = ?, birthday = ?, password = ?,  phonenumber = ?, insurances = ?, owner = ? WHERE id = ?";
            Object[] objects = new Object[]{user.getName(), user.getSurname(), user.getEmail(), user.getFiscalCode(), user.getBirthday(), user.getPassword(), user.getPhoneNumber(), Converters.createSqlArray(user.getInsurances(), jdbcTemplate, "date"), user.isOwner(), user.getId()};
            jdbcTemplate.update(sql, objects);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public User selectUserById(int id) {
        String sql = "SELECT * FROM \"user\" WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> fromResultSetToUser(resultSet));
    }

    @Override
    public User selectUserByEmail(String email) {
        String sql = "SELECT * FROM \"user\" WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, ((resultSet, i) -> fromResultSetToUser(resultSet)));
    }

    @Override
    public User selectUserByFiscalCode(String fiscalCode) {
        String sql = "SELECT * FROM \"user\" WHERE fiscalcode = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{fiscalCode}, ((resultSet, i) -> fromResultSetToUser(resultSet)));
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
        List<Integer> userSubscriptions = userSubscriptionDAO.getAllUserSubscriptionsIdsByUserId(resultSet.getInt("id"));
        List<Date> insurances = Arrays.asList((Date[]) resultSet.getArray("insurances").getArray());
        boolean owner = resultSet.getBoolean("owner");

        return new User(id, name, surname, fiscalCode, birthday, userTrainingSchedule, email, password, phoneNumber, userSubscriptions, insurances, owner);
    }
}
