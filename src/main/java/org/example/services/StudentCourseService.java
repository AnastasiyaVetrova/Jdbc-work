package org.example.services;

import org.example.config.AppConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Statement;

@Service
public class StudentCourseService {
    private final AppConnection connection;

    @Autowired
    public StudentCourseService(AppConnection connection) {
        this.connection = connection;
    }

    public void create(int studentId, int courseId) {
        try (Statement statement = connection.get().createStatement()) {
            statement.executeUpdate(
                    "INSERT INTO student_course (id_student, id_course) VALUES ('"
                            + studentId + "', " + courseId + ")");

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + " StudentCourseService");
        }
    }

    public void delete(int studentId, int courseId) {
        try (Statement statement = connection.get().createStatement()) {
            String sql = "DELETE FROM student_course WHERE id_student=" + studentId + " AND id_course=" + courseId;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
