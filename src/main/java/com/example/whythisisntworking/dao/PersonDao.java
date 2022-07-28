package com.example.whythisisntworking.dao;

import com.example.whythisisntworking.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    private static Long PEOPLE_COUNT = 0L;

    public List<Person> getAll() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getById(Long id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void update(Person person, Long id) {
        jdbcTemplate.update("UPDATE person SET name=?, age=?, email=? WHERE id=?", person.getName(),
                person.getAge(),
                person.getEmail(), id);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO person(name, age, email) VALUES(?, ?, ?)", person.getName(), person.getAge(), person.getEmail());
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }
}
