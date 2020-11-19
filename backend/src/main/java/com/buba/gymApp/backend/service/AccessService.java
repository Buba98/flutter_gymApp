package com.buba.gymApp.backend.service;

import com.buba.gymApp.backend.dao.interfaces.*;
import com.buba.gymApp.backend.model.administrationComponents.Session;
import com.buba.gymApp.backend.model.administrationComponents.User;
import com.buba.gymApp.backend.model.administrationComponents.UserSubscription;
import com.buba.gymApp.backend.model.treaningComponents.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AccessService {

    private final UserDAO userDAO;
    private final SessionDAO sessionDAO;
    private final UserSubscriptionDAO userSubscriptionDAO;
    private final SubscriptionDAO subscriptionDAO;
    private final UserTrainingScheduleDAO userTrainingScheduleDAO;
    private final TrainingScheduleDAO trainingScheduleDAO;
    private final TrainingDAO trainingDAO;
    private final ExerciseInTrainingDAO exerciseInTrainingDAO;
    private final ExerciseDAO exerciseDAO;
    private final SetDAO setDAO;

    @Autowired
    public AccessService(@Qualifier("postgresUser") UserDAO userDAO,
                         @Qualifier("postgresSession") SessionDAO sessionDAO,
                         @Qualifier("postgresUserSubscription") UserSubscriptionDAO userSubscriptionDAO,
                         @Qualifier("postgresSubscription") SubscriptionDAO subscriptionDAO,
                         @Qualifier("postgresUserTrainingSchedule") UserTrainingScheduleDAO userTrainingScheduleDAO,
                         @Qualifier("postgresTrainingSchedule") TrainingScheduleDAO trainingScheduleDAO,
                         @Qualifier("postgresTraining") TrainingDAO trainingDAO,
                         @Qualifier("postgresExerciseInTraining") ExerciseInTrainingDAO exerciseInTrainingDAO,
                         @Qualifier("postgresExercise") ExerciseDAO exerciseDAO,
                         @Qualifier("postgresSet") SetDAO setDAO){
        this.userDAO = userDAO;
        this.sessionDAO = sessionDAO;
        this.userSubscriptionDAO = userSubscriptionDAO;
        this.subscriptionDAO = subscriptionDAO;
        this.userTrainingScheduleDAO = userTrainingScheduleDAO;
        this.trainingScheduleDAO = trainingScheduleDAO;
        this.trainingDAO = trainingDAO;
        this.exerciseInTrainingDAO = exerciseInTrainingDAO;
        this.exerciseDAO = exerciseDAO;
        this.setDAO = setDAO;
    }

    public int getUserIdByUUID(UUID uuid){
        Session session = sessionDAO.selectSessionByUUID(uuid);

        if (session == null)
            return -1;
        else
            return session.getUserId();
    }

    public Session getSessionForSignIn(String email, String password){
        User user = userDAO.selectUserByEmail(email);

        if (user == null)
            return null;
        else if (!user.getPassword().equals(password))
            return null;
        else
            return sessionDAO.insertSession(user.getId());
    }

    public int signUp(String fiscalCode, String name, String surname, Date birthday, String email, String password, String phoneNumber, boolean owner){

        if (userDAO.selectUserByEmail(email) != null)
            return 1;
        if (userDAO.selectUserByFiscalCode(fiscalCode) != null)
            return 0;

        if (userDAO.insertUser(name, surname, email, fiscalCode, birthday, password, null, phoneNumber, owner).getId() == null)
            return -1;
        else return 2;
    }

    public boolean isOwner(UUID id){
        User user = userDAO.selectUserById(sessionDAO.selectSessionByUUID(id).getUserId());
        if (user == null)
            return false;
        else
            return user.isOwner();
    }

    public boolean isValidUUID(UUID id){
        User user = userDAO.selectUserById(sessionDAO.selectSessionByUUID(id).getUserId());
        return user != null;
    }

    public boolean isEmailValid(String email){
        User user = userDAO.selectUserByEmail(email);

        return user != null;
    }

    public boolean deleteSession(UUID uuid){
        return sessionDAO.deleteSessionByUUID(uuid);
    }

    public JsonObject retrieveUserInfo(int id){

        JsonObject toReturn = new JsonObject();

        //support structures
        JsonArray jsonArray;
        List<Integer> list;
        List<Integer> list1;

        User user = userDAO.selectUserById(id);

        toReturn.add("user", user.json());

        List<UserSubscription> userSubscriptionList = userSubscriptionDAO.selectAllUserSubscriptionsByUserId(id);

        jsonArray = new JsonArray();

        list = new ArrayList<>();

        for (UserSubscription userSubscription : userSubscriptionList){
            jsonArray.add(userSubscription.json());
            if (!list.contains(userSubscription.getSubscriptionId()))
                list.add(userSubscription.getId());
        }

        toReturn.add("userSubscriptions", jsonArray);

        jsonArray = new JsonArray();

        for (int idSubscription : list){
            jsonArray.add(subscriptionDAO.selectSubscriptionById(idSubscription).json());
        }

        toReturn.add("subscriptions", jsonArray);


        //training handler
        List<UserTrainingSchedule> userTrainingScheduleList = userTrainingScheduleDAO.selectUserTrainingScheduleByUserId(id);

        jsonArray = new JsonArray();
        list = new ArrayList<>();


        for (UserTrainingSchedule userTrainingSchedule : userTrainingScheduleList){
            jsonArray.add(userTrainingSchedule.json());
            if (!list.contains(userTrainingSchedule.getTrainingScheduleId()))
                list.add(userTrainingSchedule.getTrainingScheduleId());
        }

        toReturn.add("userTrainingSchedule", jsonArray);

        jsonArray = new JsonArray();
        list1 = new ArrayList<>();
        TrainingSchedule trainingSchedule;

        for (int trainingScheduleId : list){
            trainingSchedule = trainingScheduleDAO.selectTrainingScheduleById(trainingScheduleId);
            jsonArray.add(trainingSchedule.json());
            for (int trainingId : trainingSchedule.getTrainingIds()){
                if (!list1.contains(trainingId))
                    list1.add(trainingId);
            }
        }

        toReturn.add("trainingSchedule", jsonArray);

        jsonArray = new JsonArray();
        Training training;
        list = new ArrayList<>();


        for (int trainingId : list1){
            training = trainingDAO.selectTrainingById(trainingId);
            jsonArray.add(training.json());
            for(int[] exercisesInTrainingIds : training.getExercisesInTraining()){
                for (int exerciseInTrainingId : exercisesInTrainingIds){
                    if (!list.contains(exerciseInTrainingId)){
                        list.add(exerciseInTrainingId);
                    }
                }
            }
        }

        toReturn.add("training", jsonArray);

        jsonArray = new JsonArray();
        ExerciseInTraining exerciseInTraining;
        list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (int exerciseInTrainingId : list){
            exerciseInTraining = exerciseInTrainingDAO.selectExerciseInTrainingById(exerciseInTrainingId);
            jsonArray.add(exerciseInTraining.json());
            if (!list2.contains(exerciseInTraining.getExerciseId())){
                list2.add(exerciseInTraining.getExerciseId());
            }
            for (int setIds : exerciseInTraining.getSetsIds()){
                if (!list1.contains(setIds)){
                    list1.add(setIds);
                }
            }
        }

        toReturn.add("exercisesInTraining", jsonArray);

        jsonArray = new JsonArray();
        Exercise exercise;

        for (int exerciseId : list2){
            exercise = exerciseDAO.selectExerciseById(exerciseId);
            jsonArray.add(exercise.json());
        }

        toReturn.add("exercise", jsonArray);

        Set set;
        jsonArray = new JsonArray();

        for (int setId : list1){
            set = setDAO.selectSetById(setId);
            jsonArray.add(set.json());
        }

        toReturn.add("set", jsonArray);

        return toReturn;
    }
}
