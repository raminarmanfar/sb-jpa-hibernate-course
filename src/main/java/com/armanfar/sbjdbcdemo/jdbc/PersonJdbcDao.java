package com.armanfar.sbjdbcdemo.jdbc;

import com.armanfar.sbjdbcdemo.jdbc.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class PersonJdbcDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class PersonRowMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {
            Person person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setLocation(resultSet.getString("location"));
            person.setBirthDate(resultSet.getTimestamp("birth_date"));
            return person;
        }
    }

    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonRowMapper());
    }

    public Person findById(int id) {
        return (Person) jdbcTemplate.queryForObject(
                "SELECT * FROM person WHERE id=?",
                new Object[]{id},
                new PersonRowMapper());
    }

    public int deleteAll() {
        return jdbcTemplate.update("DELETE FROM person");
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM person WHERE id=?", new Object[]{ id });
    }

    public int insert(Person person) {
        return jdbcTemplate.update(
                "INSERT INTO person (id, name, location, birth_date) VALUES(?, ?, ?, ?)",
                new Object[]{ person.getId(), person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime()) }
                );
    }

    public int update(Person person) {
        return jdbcTemplate.update(
                "UPDATE person SET name=?, location=?, birth_date=? WHERE id=?",
                new Object[]{ person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime()), person.getId() }
        );
    }
}
