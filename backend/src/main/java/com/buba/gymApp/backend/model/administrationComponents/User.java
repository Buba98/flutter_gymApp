package com.buba.gymApp.backend.model.administrationComponents;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class User {

    private Integer id;

    private String fiscalCode;

    private String name;

    private String surname;

    private List<Integer> userTrainingSchedule;

    private Date birthday;

    private String email;

    private String password;

    private String phoneNumber;

    private List<Integer> userSubscriptions;

    private List<Date> insurances;

    private boolean owner;

    public User(@JsonProperty("id") Integer id,
                @JsonProperty("name") String name,
                @JsonProperty("surname") String surname,
                @JsonProperty("fiscalCode") String fiscalCode,
                @JsonProperty("birthday") Date birthday,
                @JsonProperty("trainingSchedule") List<Integer> userTrainingSchedule,
                @JsonProperty("email") String email,
                @JsonProperty("password") String password,
                @JsonProperty("phoneNumber") String phoneNumber,
                @JsonProperty("userSubscriptions") List<Integer> userSubscriptions,
                @JsonProperty("insurances") List<Date> insurances,
                @JsonProperty("owner") boolean owner) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.fiscalCode = fiscalCode;
        this.userTrainingSchedule = userTrainingSchedule;
        this.email = email;
        this.birthday = birthday;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userSubscriptions = userSubscriptions;
        this.insurances = insurances;
        this.owner = owner;
    }

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

    public List<Integer> getUserTrainingSchedule() {
        return userTrainingSchedule;
    }

    public void setUserTrainingSchedule(List<Integer> trainingSchedules) {
        this.userTrainingSchedule = trainingSchedules;
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

    public List<Integer> getUserSubscriptions() {
        return userSubscriptions;
    }

    public void setUserSubscriptions(List<Integer> userSubscriptions) {
        this.userSubscriptions = userSubscriptions;
    }

    public List<Date> getInsurances() {
        return insurances;
    }

    public void setInsurances(List<Date> insurances) {
        this.insurances = insurances;
    }
}
