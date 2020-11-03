package com.buba.gymApp.backend.api;

import com.buba.gymApp.backend.service.AccessService;
import com.buba.gymApp.backend.service.PaymentService;
import com.buba.gymApp.backend.service.ToolService;
import com.buba.gymApp.backend.utils.Constants;
import com.buba.gymApp.backend.utils.Converters;
import com.buba.gymApp.backend.utils.RequestType;
import com.buba.gymApp.backend.utils.StatusResponse;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.ConstantBootstraps;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/administration")
@RestController
public class AdministrationController {
    private final AccessService accessService;
    private final PaymentService paymentService;
    private final ToolService toolService;

    @Autowired
    public AdministrationController(AccessService accessService, PaymentService paymentService, ToolService toolService) {
        this.accessService = accessService;
        this.paymentService = paymentService;
        this.toolService = toolService;
    }

    @PostMapping
    public String initialChecks(@RequestBody String jsonString) {

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
        } catch (IllegalArgumentException | NullPointerException e ) {
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
                return entranceRegistration(json);
            case NEW_SUBSCRIPTION_TYPE:
                return newSubscriptionType(json);
            case AUTOCOMPLETE:
                return autocomplete(json);
            default:
                return new Gson().toJson(new StatusResponse(400, "Bad request"));
        }

    }

    private String newSubscriptionType(JsonObject json){
        int mouthDuration = json.get("mouthDuration").getAsInt();
        float cost = json.get("cost").getAsFloat();
        int maxEntrances  = json.get("maxEntrances").getAsInt();

        switch (paymentService.addSubscription(maxEntrances, cost, mouthDuration)){
            case 0:
                return new Gson().toJson(new StatusResponse(400, "Subscription already exists"));
            case 1:
                return new Gson().toJson(new StatusResponse(200, "Ok"));
            default:
                return new Gson().toJson(new StatusResponse(600, "Internal server error"));

        }
    }

    private String insurancePayment(JsonObject json) {

        int id;

        try {
            id = Integer.parseInt(json.get("userId").getAsString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new Gson().toJson(new StatusResponse(400, "Bad request"));
        }

        switch (paymentService.addInsurancePaymentByUserId(id)){
            case 0:
                return new Gson().toJson(new StatusResponse(400, "User not exist"));
            case 1:
                return new Gson().toJson(new StatusResponse(200, "OK"));
            case 2:
                return new Gson().toJson(new StatusResponse(400, "Insurance not expired yet"));
            default:
                return new Gson().toJson(new StatusResponse(600, "Internal server error"));
        }
    }

    private String signUp(JsonObject json) {

        String fiscalCode;
        String name;
        String surname;
        Date birthday;
        String email;
        String password;
        String phoneNumber;
        boolean owner;

        try {
            fiscalCode = json.get("fiscalCode").getAsString();
            name = json.get("name").getAsString();
            surname = json.get("surname").getAsString();
            birthday = Converters.fromStringToUtil(json.get("birthday").getAsString());
            email = json.get("email").getAsString();
            password = json.get("password").getAsString();
            phoneNumber = json.get("phoneNumber").getAsString();
            owner = json.get("owner").getAsBoolean();

        } catch (NullPointerException | ParseException e){
            e.printStackTrace();
            return new Gson().toJson(new StatusResponse(400, "Error in request"));
        }

        StatusResponse response;

        switch (accessService.signUp(fiscalCode, name, surname, birthday, email, password, phoneNumber, owner)) {
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
                response = new StatusResponse(600, "Internal server error");
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

        switch (paymentService.addUserSubscription(id, subscriptionId)){
            case 0:
                return new Gson().toJson(new StatusResponse(400, "Subscription type doesn't exists"));
            case 1:
                return new Gson().toJson(new StatusResponse(400, "User doesn't exists"));
            case 2:
                return new Gson().toJson(new StatusResponse(200, "OK"));
            case 3:
                return new Gson().toJson(new StatusResponse(400, "User subscription not expired yet"));
            default:
                return new Gson().toJson(new StatusResponse(600, "Internal server error"));
        }
    }

    private String entranceRegistration(JsonObject json){
        int id;

        try {
            id = accessService.getUserIdByUUID(UUID.fromString(json.get("uuidAuthentication").getAsString()));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new Gson().toJson(new StatusResponse(400, "Bad request"));
        }

        switch (paymentService.addEntrance(id)){
            case 0:
                return new Gson().toJson(new StatusResponse(200, "OK"));
            case 1:
                return new Gson().toJson(new StatusResponse(400, "No valid subscription found"));
            default:
                return new Gson().toJson(new StatusResponse(600, "Internal server error"));
        }
    }

    private String autocomplete(JsonObject json) {
        try {
            return Constants.gsonInstance.toJson(new StatusResponse(200, toolService.autocomplete(json.get("context").getAsString(), json.get("pattern").getAsJsonObject()).toString()));
        } catch (NullPointerException e) {
            e.printStackTrace();
            return new Gson().toJson(new StatusResponse(400, "Error in request"));
        }

    }
}
