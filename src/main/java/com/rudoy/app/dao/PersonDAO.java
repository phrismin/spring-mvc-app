package com.rudoy.app.dao;

import com.rudoy.app.model.Person;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonDAO {
    private static final String GET_ALL_PEOPLE = "SELECT * FROM person";
    private static final String INSERT_NEW_PEOPLE = "INSERT INTO person VALUES(1, ?, ?, ?)";
    private static final String GET_PEOPLE_BY_ID = "SELECT * FROM person WHERE id = ?";
    private static final String UPDATE_PERSON = "UPDATE person SET name = ?, age = ?, email = ? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM person WHERE id = ?";

    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_PEOPLE)) {

            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setAge(resultSet.getInt("age"));

                people.add(person);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return people;
    }

    public Person show(int id) {
        Person person = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_PEOPLE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            person = new Person();

            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return person;
    }

    public void save(Person person) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_NEW_PEOPLE)) {

            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setString(3, person.getEmail());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Person updatePerson) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_PERSON)) {
            statement.setString(1, updatePerson.getName());
            statement.setInt(2, updatePerson.getAge());
            statement.setString(3, updatePerson.getEmail());
            statement.setInt(4, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
}