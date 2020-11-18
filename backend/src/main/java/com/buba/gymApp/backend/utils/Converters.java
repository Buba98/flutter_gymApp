package com.buba.gymApp.backend.utils;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Converters {

    public static java.util.Date fromStringToUtil(String stringDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
        return formatter.parse(stringDate);
    }

    public static java.sql.Array createSqlArray(Date[] list, JdbcTemplate jdbcTemplate, String type){
        java.sql.Array intArray = null;
        try {
            intArray = jdbcTemplate.getDataSource().getConnection().createArrayOf(type, list.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return intArray;
    }

}
