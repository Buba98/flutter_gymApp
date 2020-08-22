package com.buba.gymApp.backend.model.person;

import com.buba.gymApp.backend.model.Entry;
import com.buba.gymApp.backend.model.training.TrainingSchedule;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Person {
    private final UUID id;
    @NotBlank
    private final String name;
    private final String surname;
    private final String cellphone;
    private final Date birthday;
    private final String email;
    private final String password;

    public Person(@JsonProperty("id") UUID id, @JsonProperty("name") String name, @JsonProperty("surname") String surname, @JsonProperty("cellphone") String cellphone, @JsonProperty("birthday") Date birthday, String email, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.cellphone = cellphone;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getSurname() {
        return surname;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getEmail() {
        return email;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }
}
