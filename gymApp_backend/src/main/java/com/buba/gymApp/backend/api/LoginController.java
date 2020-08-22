package com.buba.gymApp.backend.api;

import com.buba.gymApp.backend.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1/login")
public class LoginController {

    private final LoginController loginController;

    @Autowired
    public LoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}
