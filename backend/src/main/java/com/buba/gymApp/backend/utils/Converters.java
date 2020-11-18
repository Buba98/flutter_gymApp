package com.buba.gymApp.backend.utils;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Converters {

    public static java.util.Date fromStringToUtil(String stringDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(stringDate);
    }
}
