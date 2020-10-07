package com.buba.gymApp.backend.utils;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class Converters {

    public static java.util.Date fromStringToUtil(String stringDate) {
        return new java.util.Date();
    }

    public static java.sql.Array createSqlArray(List list, JdbcTemplate jdbcTemplate, String type) throws SQLException {
        return jdbcTemplate.getDataSource().getConnection().createArrayOf(type, list.toArray());
    }

}
