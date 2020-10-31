package com.buba.gymApp.backend.service;

import com.buba.gymApp.backend.dao.sessionDAO.SessionDAO;
import com.buba.gymApp.backend.dao.userDAO.UserDAO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolService {

    private final UserDAO userDAO;
    private final SessionDAO sessionDAO;

    @Autowired
    public ToolService(@Qualifier("postgresUser") UserDAO userDAO, @Qualifier("postgresSession") SessionDAO sessionDAO){
        this.userDAO = userDAO;
        this.sessionDAO = sessionDAO;
    }

    public JsonArray autocomplete(String context, JsonObject pattern){
        try {
            JsonArray response = null;
            switch (context) {
                case "user":
                    List<String[]> results = userDAO.selectForAutocomplete(pattern.get("name").getAsString(), pattern.get("surname").getAsString());
                    response = new JsonArray();
                    JsonObject jsonObject;
                    for (String[] result : results){
                        jsonObject = new JsonObject();
                        jsonObject.addProperty("name", result[0]);
                        jsonObject.addProperty("surname", result[1]);
                        jsonObject.addProperty("birthday", result[2]);
                        jsonObject.addProperty("fiscalCode", result[3]);
                        response.add(jsonObject);
                    }
                    System.out.println(response.toString());
                    break;
            }
            return response;
        }catch (NullPointerException e){
            return null;
        }
    }
}
