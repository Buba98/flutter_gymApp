package com.buba.gymApp.backend.model.person;

import java.util.Date;
import java.util.UUID;

public class Owner extends Person{
    public Owner(UUID id, String name, String surname, String cellphone, Date birthday, String email, String password) {
        super(id, name, surname, cellphone, birthday, email, password);
    }
}
