package com.buba.gymApp.backend.api;

import com.buba.gymApp.backend.service.AccessService;
import com.buba.gymApp.backend.service.PaymentService;
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

@RequestMapping("api/v1/payment")
@RestController
public class PaymentController {

    private final AccessService accessService;
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(AccessService accessService, PaymentService paymentService) {
        this.accessService = accessService;
        this.paymentService = paymentService;
    }

    @PostMapping("/insurance")
    public String insurancePayment(@RequestBody String jsonString) {
        JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();

        StatusResponse statusResponse;
        UUID uuid;

        try {
            uuid = UUID.fromString(json.get("uuidAuthentication").getAsString());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            statusResponse = new StatusResponse(400, "Bad request");
            return new Gson().toJson(statusResponse);
        }

        if (!accessService.isOwner(uuid)) {
            statusResponse = new StatusResponse(401, "Not authorized");
            return new Gson().toJson(statusResponse);
        }

        Integer id;

        try {
            id = Integer.parseInt(json.get("userId").getAsString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            statusResponse = new StatusResponse(400, "Bad request");
            return new Gson().toJson(statusResponse);
        }

        if (paymentService.addInsurancePaymentByUserId(id))
            statusResponse = new StatusResponse(200, "OK");
        else
            statusResponse = new StatusResponse(400, "User not exist");

        return new Gson().toJson(statusResponse);
    }

    @PostMapping
    public String subscriptionPayment(@RequestBody String jsonString) {
        JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();

        StatusResponse statusResponse;
        UUID uuid;

        try {
            uuid = UUID.fromString(json.get("uuidAuthentication").getAsString());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            statusResponse = new StatusResponse(400, "Bad request");
            return new Gson().toJson(statusResponse);
        }

        if (!accessService.isOwner(uuid)) {
            statusResponse = new StatusResponse(401, "Not authorized");
            return new Gson().toJson(statusResponse);
        }

        Integer id;
        Integer subscriptionId;

        try {
            id = Integer.parseInt(json.get("userId").getAsString());
            subscriptionId = Integer.parseInt(json.get("subscriptionId").getAsString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            statusResponse = new StatusResponse(400, "Bad request");
            return new Gson().toJson(statusResponse);
        }

        if (paymentService.addSubscription(id, subscriptionId))
            statusResponse = new StatusResponse(200, "OK");
        else
            statusResponse = new StatusResponse(400, "User not exist");

        return new Gson().toJson(statusResponse);
    }
}
