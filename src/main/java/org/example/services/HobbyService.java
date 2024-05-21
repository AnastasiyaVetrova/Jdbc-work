package org.example.services;

import org.example.config.AppConnection;
import org.example.entity.HobbyEnt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class HobbyService {
    private final AppConnection connection;

    @Autowired
    public HobbyService(AppConnection connection) {
        this.connection = connection;
    }

    public List<HobbyEnt> get(Integer studentId) throws SQLException {
        List<HobbyEnt> list = new ArrayList<>();
        String sql = "SELECT * FROM hobby WHERE student_id=" + studentId;
        Statement statement = connection.get().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            list.add(new HobbyEnt(resultSet.getInt("id"),
                    resultSet.getString("title")));
        }
        return list;
    }

    public void create(String title, int idStudent) {
        try (Statement statement = connection.get().createStatement()) {
            statement.executeUpdate(
                    "INSERT INTO hobby (title, student_id) VALUES ('"
                            + title + "', " + idStudent + ")");

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + " HobbyService");
        }
    }
}
