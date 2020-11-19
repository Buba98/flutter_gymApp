package com.buba.gymApp.backend.model.administrationComponents;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.jdbc.core.RowMapper;

import java.util.Date;

public class User {

    private Integer id;

    private String fiscalCode;

    private String name;

    private String surname;

    private Date birthday;

    private String email;

    private String password;

    private String phoneNumber;

    private Date[] insurances;

    private boolean owner;

    public User(Integer id, String name, String surname, String fiscalCode, Date birthday, String email,
                String password, String phoneNumber, Date[] insurances, boolean owner) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.fiscalCode = fiscalCode;
        this.email = email;
        this.birthday = birthday;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.insurances = insurances;
        this.owner = owner;
    }

    private User(){}

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date[] getInsurances() {
        return insurances;
    }

    public void setInsurances(Date[] insurances) {
        this.insurances = insurances;
    }

    public static RowMapper<User> mapper(){
        return (resultSet, i) -> {
            User user = new User();
            user.birthday = new Date(resultSet.getDate("birthday").getTime());
            user.email = resultSet.getString("email");
            user.fiscalCode = resultSet.getString("fiscalCode");
            user.id = resultSet.getInt("id");
            user.insurances = (Date[]) resultSet.getArray("insurances").getArray();
            user.name = resultSet.getString("name");
            user.owner = resultSet.getBoolean("owner");
            user.password = resultSet.getString("password");
            user.phoneNumber = resultSet.getString("phone");
            user.surname = resultSet.getString("surname");

            return user;
        };
    }

    public JsonObject json(){
        JsonObject toReturn = new JsonObject();

        toReturn.addProperty("name", name);
        toReturn.addProperty("surname", surname);
        toReturn.addProperty("email", email);
        toReturn.addProperty("fiscalCode", fiscalCode);
        toReturn.addProperty("birthday", birthday.toString());
        toReturn.addProperty("phone", phoneNumber);
        toReturn.addProperty("owner", owner);

        JsonArray insurances = new JsonArray(this.insurances.length);

        for (Date date : this.insurances){
            insurances.add(date.toString());
        }

        toReturn.add("insurances", insurances);

        return toReturn;
    }
}
