package com.buba.gymApp.backend.dao;

import com.buba.gymApp.backend.model.person.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add((new Person(id, person.getName(), surname, cellphone, birtday, standardUser, email, indemnitys, entries, password, trainingSchedules)));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> person = selectPersonById(id);
        if (person.isEmpty()) {
            return 0;
        } else {
            DB.remove(person.get());
            return 1;
        }
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return selectPersonById(id).map(p -> {
            int indexOfP = DB.indexOf(p);
            if (indexOfP >= 0) {
                DB.set(indexOfP, new Person(id, person.getName(), surname, cellphone, birtday, standardUser, email, indemnitys, entries, password, trainingSchedules));
                return 1;
            } else {
                return 0;
            }
        }).orElse(0);
    }
}
