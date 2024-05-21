package org.example.services;

import org.example.config.AppConnection;
import org.example.entity.CourseEnt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    private final AppConnection connection;

    @Autowired
    public CourseService(AppConnection connection) {
        this.connection = connection;
    }

    public List<CourseEnt> get(Integer studentId) throws SQLException {
        List<CourseEnt> list = new ArrayList<>();
        String sql = "SELECT * FROM course \n" +
                "join student_course ON course.id=student_course.id_course\n" +
                "where id_student=" + studentId;
        Statement statement = connection.get().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            list.add(new CourseEnt(
                    resultSet.getString("name"),
                    resultSet.getString("teacher")));
        }
        return list;
    }

    public boolean create(String name, String teacher) {
        try (Statement statement = connection.get().createStatement()) {
            statement.executeUpdate(
                    "INSERT INTO course (name, teacher) VALUES ('"
                            + name + "', '" + teacher + "')");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
