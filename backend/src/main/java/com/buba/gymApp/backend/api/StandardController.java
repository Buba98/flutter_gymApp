package com.buba.gymApp.backend.api;

import com.buba.gymApp.backend.model.administrationComponents.Session;
import com.buba.gymApp.backend.service.AccessService;
import com.buba.gymApp.backend.utils.StatusResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("api/v1/standard")
@RestController
public class StandardController {
    private final AccessService accessService;

    @Autowired
    public StandardController(AccessService accessService) {
        this.accessService = accessService;
    }

    @PostMapping("/signIn")
    public String signIn(@RequestBody String jsonString) {
        JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();

        String email = json.get("email").getAsString();
        String password = json.get("password").getAsString();

        Session sessionUUID = accessService.getUUIDForSignIn(email, password);

        StatusResponse response;

        if (sessionUUID == null)
            response = new StatusResponse(401, "Email and/or password are not correct");
        else
            response = new StatusResponse(200, sessionUUID.toString());

        return new Gson().toJson(response);

    }
}
