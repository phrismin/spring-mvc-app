package com.rudoy.app.dao;

import com.rudoy.app.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
//@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private final List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Tom"));
        people.add(new Person(++PEOPLE_COUNT, "Bob"));
        people.add(new Person(++PEOPLE_COUNT, "Nike"));
        people.add(new Person(++PEOPLE_COUNT, "Mira"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        Person person = people.stream()
                .filter(pers -> pers.getId() == id).findFirst().orElse(null);
        return person;
    }
}
