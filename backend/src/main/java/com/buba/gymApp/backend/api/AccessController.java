package com.buba.gymApp.backend.api;

import com.buba.gymApp.backend.service.AccessService;
import com.buba.gymApp.backend.utils.Converters;
import com.buba.gymApp.backend.utils.StatusResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("api/v1/access")
@RestController
public class AccessController {
    private final AccessService accessService;

    @Autowired
    public AccessController(AccessService accessService) {
        this.accessService = accessService;
    }

    @PostMapping("/signIn")
    public String signIn(@RequestBody String jsonString) {
        JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();

        String email = json.get("email").getAsString();
        String password = json.get("password").getAsString();

        UUID sessionUUID = accessService.getUUIDForSignIn(email, password);

        StatusResponse response;

        if (sessionUUID == null)
            response = new StatusResponse(401, "Email and/or password are not correct");
        else
            response = new StatusResponse(200, sessionUUID.toString());

        return new Gson().toJson(response);

    }

    @PostMapping("/signUp")
    public String singUp(@RequestBody String jsonString) {
        JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();

        String fiscalCode = json.get("fiscalCode").getAsString();
        String name = json.get("name").getAsString();
        String surname = json.get("surname").getAsString();
        String birthday = json.get("birthday").getAsString();
        String email = json.get("email").getAsString();
        String password = json.get("password").getAsString();
        String phoneNumber = json.get("phoneNumber").getAsString();

        StatusResponse response;

        switch (accessService.addUser(fiscalCode, name, surname, Converters.fromStringToUtil(birthday), email, password, phoneNumber)){
            case 0:
                response = new StatusResponse(409, "Fiscal code already exists");
                break;
            case 1:
                response = new StatusResponse(409, "Email already exists");
                break;
            case 2:
                response = new StatusResponse(200, "Success");
                break;
            default:
                response = new StatusResponse(500, "Generic server error");
                break;
        }

        return new Gson().toJson(response);
    }
}
