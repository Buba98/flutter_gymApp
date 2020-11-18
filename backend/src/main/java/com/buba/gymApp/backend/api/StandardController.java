package com.buba.gymApp.backend.api;

import com.buba.gymApp.backend.model.administrationComponents.Session;
import com.buba.gymApp.backend.service.AccessService;
import com.buba.gymApp.backend.service.EmailService;
import com.buba.gymApp.backend.utils.Constants;
import com.buba.gymApp.backend.utils.StatusResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RequestMapping("api/v1/standard")
@RestController
public class StandardController {
    private final AccessService accessService;
    private final EmailService emailService;

    @Autowired
    public StandardController(AccessService accessService, EmailService emailService) {
        this.accessService = accessService;
        this.emailService = emailService;
    }

    @PostMapping("/signIn")
    public String signIn(@RequestBody String jsonString) {

        //check for correct json
        JsonObject json;
        try {
            json = JsonParser.parseString(jsonString).getAsJsonObject();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return new Gson().toJson(new StatusResponse(400, "Bad request"));
        }

        String email;
        String password;

        try {
            email = json.get("email").getAsString();
            password = json.get("password").getAsString();
        } catch (IllegalArgumentException | NullPointerException e ) {
            e.printStackTrace();
            return new Gson().toJson(new StatusResponse(400, "Bad request"));
        }

        Session session = accessService.getSessionForSignIn(email, password);

        StatusResponse response;

        if (session == null)
            response = new StatusResponse(401, "Email and/or password are not correct");
        else
            response = new StatusResponse(200, session.getUuid().toString());

        return Constants.gsonInstance.toJson(response);
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody String jsonString) {


        //check for correct json
        JsonObject json;
        try {
            json = JsonParser.parseString(jsonString).getAsJsonObject();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return new Gson().toJson(new StatusResponse(400, "Bad request"));
        }

        String email;

        try {
            email = json.get("email").getAsString();
        } catch (IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
            return new Gson().toJson(new StatusResponse(400, "Bad request"));
        }

        if (accessService.isEmailValid(email)) {
            if (emailService.sendEmail(email, "Password change", "")) //todo create message
                return new Gson().toJson(new StatusResponse(200, "Success"));
            else
                return new Gson().toJson(new StatusResponse(400, "Bad request"));
        } else
            return new Gson().toJson(new StatusResponse(600, "Internal server error"));
    }

    @PostMapping("/signOut")
    public String signOut(@RequestBody String jsonString){
        //check for correct json
        JsonObject json;
        try {
            json = JsonParser.parseString(jsonString).getAsJsonObject();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return new Gson().toJson(new StatusResponse(400, "Bad request"));
        }

        UUID uuid;

        try {
            uuid = UUID.fromString(json.get("uuidAuthentication").getAsString());
        } catch (IllegalArgumentException | NullPointerException e ) {
            e.printStackTrace();
            return new Gson().toJson(new StatusResponse(400, "Bad request"));
        }

        if (!accessService.isValidUUID(uuid))
            return new Gson().toJson(new StatusResponse(400, "Bad request"));

        if (accessService.deleteSession(uuid))
            return new Gson().toJson(new StatusResponse(200, "Success"));
        else
            return new Gson().toJson(new StatusResponse(600, "Internal server error"));
    }
}
