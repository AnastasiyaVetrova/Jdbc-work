package org.example.services;

import org.example.config.AppConnection;
import org.example.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final AppConnection connection;

    @Autowired
    public StudentService(AppConnection connection) {
        this.connection = connection;
    }

    public StudentEnt get(Integer id) throws Exception {
        StudentEnt studentEnt = new StudentEnt();
        String sql = "SELECT * FROM student WHERE id=" + id;
        Statement statement = connection.get().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            studentEnt.setId(resultSet.getInt("id"));
            studentEnt.setName(resultSet.getString("name"));
            studentEnt.setFacultyId(resultMapToFaculty(resultSet.getInt("faculty_id")).get(0).getName());
        }
        return studentEnt;
    }

    public void create(String name, int facultyId) {
        try (Statement statement = connection.get().createStatement()) {
            String sql = "INSERT INTO student (name, faculty_id) VALUES ('"
                    + name + "'," + facultyId + ")";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + " StudentCRUDService");
        }
    }

    public void updateAnyTable(String table, int id, String param, String newName) {
        try (Statement statement = connection.get().createStatement()) {
            String sql = "UPDATE " + table + " SET " + param + "='" + newName + "' WHERE id=" + id;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + " update-CRUDService");
        }
    }

    public void deleteAnyTable(String table, int id) throws SQLException {
        try(Statement statement = connection.get().createStatement()) {
            String sql = "DELETE FROM " + table + " WHERE id=" + id;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<FacultyEnt> resultMapToFaculty(int id) throws SQLException {
        List<FacultyEnt> list = new ArrayList<>();
        String sql = "SELECT * FROM faculty WHERE id=" + id;
        Statement statement = connection.get().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            list.add(new FacultyEnt(resultSet.getInt("id"),
                    resultSet.getString("name")));
        }
        return list;
    }
}

