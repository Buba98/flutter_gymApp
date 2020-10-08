package com.buba.gymApp.backend.api;

import com.buba.gymApp.backend.service.AccessService;
import com.buba.gymApp.backend.service.PaymentService;
import com.buba.gymApp.backend.utils.Converters;
import com.buba.gymApp.backend.utils.RequestType;
import com.buba.gymApp.backend.utils.StatusResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("api/v1/administration")
@RestController
public class AdministrationController {
    private final AccessService accessService;
    private final PaymentService paymentService;

    @Autowired
    public AdministrationController(AccessService accessService, PaymentService paymentService) {
        this.accessService = accessService;
        this.paymentService = paymentService;
    }

    @PostMapping
    public String singUp(@RequestBody String jsonString) {


        //check for correct json
        JsonObject json;
        try {
            json = JsonParser.parseString(jsonString).getAsJsonObject();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return new Gson().toJson(new StatusResponse(400, "Bad request"));
        }

        //check user of request is owner
        UUID uuid;

        try {
            uuid = UUID.fromString(json.get("uuidAuthentication").getAsString());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new Gson().toJson(new StatusResponse(400, "Bad request"));
        }

        if (!accessService.isOwner(uuid)) {
            return new Gson().toJson(new StatusResponse(401, "Not authorized"));
        }

        //request type check
        RequestType requestType;
        try {
            requestType = RequestType.getEnumByInt(Integer.parseInt(json.get("requestType").getAsString()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new Gson().toJson(new StatusResponse(400, "Bad request"));
        }

        switch (requestType) {
            case SIGN_UP:
                return signUp(json);
            case INSURANCE_PAYMENT:
                return insurancePayment(json);
            case SUBSCRIPTION_PAYMENT:
                return subscriptionPayment(json);
            case ENTRANCE:

            default:
                return new Gson().toJson(new StatusResponse(400, "Bad request"));
        }

    }

    private String entranceCheck(){

    }

    private String insurancePayment(JsonObject json) {

        int id;

        try {
            id = Integer.parseInt(json.get("userId").getAsString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new Gson().toJson(new StatusResponse(400, "Bad request"));
        }

        if (paymentService.addInsurancePaymentByUserId(id))
            return new Gson().toJson(new StatusResponse(200, "OK"));
        else
            return new Gson().toJson(new StatusResponse(400, "User not exist"));
    }

    private String signUp(JsonObject json) {

        String fiscalCode = json.get("fiscalCode").getAsString();
        String name = json.get("name").getAsString();
        String surname = json.get("surname").getAsString();
        String birthday = json.get("birthday").getAsString();
        String email = json.get("email").getAsString();
        String password = json.get("password").getAsString();
        String phoneNumber = json.get("phoneNumber").getAsString();
        boolean owner = json.get("owner").getAsBoolean();

        StatusResponse response;

        switch (accessService.signUp(fiscalCode, name, surname, Converters.fromStringToUtil(birthday), email, password, phoneNumber, owner)) {
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

    private String subscriptionPayment(JsonObject json) {
        int id;
        int subscriptionId;

        try {
            id = Integer.parseInt(json.get("userId").getAsString());
            subscriptionId = Integer.parseInt(json.get("subscriptionId").getAsString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new Gson().toJson(new StatusResponse(400, "Bad request"));
        }

        if (paymentService.addSubscription(id, subscriptionId))
            return new Gson().toJson(new StatusResponse(200, "OK"));
        else
            return new Gson().toJson(new StatusResponse(400, "User not exist"));
    }
}
