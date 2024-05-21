package org.example.services;

import org.example.config.AppConnection;
import org.example.entity.AddressEnt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class AddressService {

    private static final Logger log = LoggerFactory.getLogger(AddressService.class);
    private final AppConnection connection;

    @Autowired
    public AddressService(AppConnection connection) {
        this.connection = connection;
    }

    public AddressEnt get(Integer studentId) throws SQLException {
        AddressEnt addressEnt = new AddressEnt();
        String sql = "SELECT * FROM address WHERE student_id=" + studentId;
        Statement statement = connection.get().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            addressEnt.setId(resultSet.getInt("id"));
            addressEnt.setAddress(resultSet.getString("address"));
            addressEnt.setPhone(resultSet.getString("phone"));
            break;
        }
        resultSet.close();
        return addressEnt;
    }

    public void create(String nameAddress, String phone, int studentId) {
        try (Statement statement = connection.get().createStatement()) {
            statement.executeUpdate("INSERT INTO address (address, phone, student_id) VALUES ('"
                    + nameAddress + "','"
                    + phone + "',"
                    + studentId + ")");
        } catch (SQLException e) {
            log.info(e.getMessage());
        }
    }
}
