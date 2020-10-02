package com.buba.gymApp.backend.dao;

import com.buba.gymApp.backend.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("fakeUserDAO")
public class FakeUserDAO implements UserDAO {

    private static List<User> DB = new ArrayList<>();

    @Override
    public int insertUser(User user) {
        DB.add(user);
        return 1;
    }

    @Override
    public List<User> selectAllUsers() {
        return DB;
    }

    @Override
    public int deleteUserById(int id) {
        Optional<User> userOptional = selectUserById(id);
        if (userOptional.isEmpty())
            return 0;
        else
            DB.remove(userOptional.get());
        return 1;
    }

    @Override
    public int updateUserById(int id, User user) {
        return selectUserById(id).map(u -> {
            int indexOfUserToUpdate = DB.indexOf(u);
            if (indexOfUserToUpdate >= 0) {
                DB.set(indexOfUserToUpdate, user);
                return 1;
            }
            return 0;
        }).orElse(0);
    }

    @Override
    public Optional<User> selectUserById(int id) {
        return DB.stream().filter(user -> user.getId() == id).findFirst();
    }
}
