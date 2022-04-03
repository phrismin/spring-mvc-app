package com.rudoy.app.dao;

import com.rudoy.app.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
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

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatePerson) {
        Person person = show(id);
        person.setName(updatePerson.getName());
    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }
}
