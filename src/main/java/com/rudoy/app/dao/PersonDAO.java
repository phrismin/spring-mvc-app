package com.rudoy.app.dao;

import com.rudoy.app.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDAO {


    private static final String GET_ALL_PEOPLE = "SELECT * FROM person";
    private static final String INSERT_NEW_PEOPLE = "INSERT INTO person VALUES(1, ?, ?, ?)";
    private static final String GET_PEOPLE_BY_ID = "SELECT * FROM person WHERE id = ?";
    private static final String UPDATE_PERSON = "UPDATE person SET name = ?, age = ?, email = ? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM person WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query(GET_ALL_PEOPLE, new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query(GET_PEOPLE_BY_ID,
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class))
                    .stream()
                    .findAny()
                    .orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update(INSERT_NEW_PEOPLE,
                person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person updatePerson) {
        jdbcTemplate.update(UPDATE_PERSON, updatePerson.getName(),
                updatePerson.getAge(), updatePerson.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update(DELETE_BY_ID, id);
    }
}