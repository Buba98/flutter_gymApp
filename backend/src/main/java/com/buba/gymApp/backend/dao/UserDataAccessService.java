package com.buba.gymApp.backend.dao;

import com.buba.gymApp.backend.dao.interfaces.UserDAO;
import com.buba.gymApp.backend.dao.interfaces.UserSubscriptionDAO;
import com.buba.gymApp.backend.model.administrationComponents.User;
import com.buba.gymApp.backend.utils.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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

        String sql = "INSERT INTO \"user\" (name, surname, email, \"fiscalCode\", birthday, password, insurances, phone, owner) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Object[] objects = new Object[]{ user.getName(), user.getSurname(), user.getEmail(), user.getFiscalCode(), user.getBirthday(), user.getPassword(), Converters.createSqlArray(new ArrayList<Date>(), jdbcTemplate, "date"), user.getPhoneNumber(), user.isOwner()};
        try {
            jdbcTemplate.update(sql, objects);
            return selectUserByEmail(user.getEmail());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> selectAllUsers() {
        String sql = "SELECT * FROM \"user\"";
        try {
            return jdbcTemplate.query(sql, (resultSet, i) -> fromResultSetToUser(resultSet));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteUserById(int id) {

        String sql = "DELETE FROM \"user\" WHERE id = ?";
        try {
            return jdbcTemplate.update(sql, id) == 1;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUserById(User user) {
        String sql = "UPDATE \"user\" SET name = ?, surname = ?, email = ?, \"fiscalCode\" = ?, birthday = ?, password = ?,  phone = ?, insurances = ?, owner = ? WHERE id = ?";
        Object[] objects;
        try {
            objects = new Object[]{user.getName(), user.getSurname(), user.getEmail(), user.getFiscalCode(), user.getBirthday(), user.getPassword(), user.getPhoneNumber(), Converters.createSqlArray(user.getInsurances(), jdbcTemplate, "date"), user.isOwner(), user.getId()};
            jdbcTemplate.update(sql, objects);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public User selectUserById(int id) {
        String sql = "SELECT * FROM \"user\" WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> fromResultSetToUser(resultSet));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User selectUserByEmail(String email) {
        String sql = "SELECT * FROM \"user\" WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{email}, ((resultSet, i) -> fromResultSetToUser(resultSet)));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User selectUserByFiscalCode(String fiscalCode) {
        String sql = "SELECT * FROM \"user\" WHERE \"fiscalCode\" = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{fiscalCode}, ((resultSet, i) -> fromResultSetToUser(resultSet)));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String[]> selectForAutocomplete(String name, String surname){
        String sql = "SELECT name, surname, birthday, \"fiscalCode\" FROM \"user\" WHERE name LIKE ? AND surname LIKE ?";

        return jdbcTemplate.query(sql, new Object[]{"%" + name + "%" , "%" + surname + "%"}, (resultSet, i) -> new String[]{resultSet.getString("name"), resultSet.getString("surname"), resultSet.getDate("birthday").toString(), resultSet.getString("fiscalcode")});

    }

    private User fromResultSetToUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String fiscalCode = resultSet.getString("fiscalCode");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        Date birthday = resultSet.getDate("birthday");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String phoneNumber = resultSet.getString("phoneNumber");
        List<Date> insurances = Arrays.asList((Date[]) resultSet.getArray("insurances").getArray());
        boolean owner = resultSet.getBoolean("owner");

        return new User(id, name, surname, fiscalCode, birthday, email, password, phoneNumber, insurances, owner);
    }


}