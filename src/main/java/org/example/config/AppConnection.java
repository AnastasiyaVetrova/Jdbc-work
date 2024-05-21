package org.example.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "spring")
public class AppConnection {

    private Datasource datasource;
    Connection connection;

    @Bean
    public Connection get() {
        try {
            connection = DriverManager.getConnection(datasource.getUrl(), datasource.getUsername(), datasource.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
