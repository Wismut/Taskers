package com.tasker.database;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;


public class DataBase {

    private final String URL = "jdbc:mysql://localhost/scheduled_tasks?characterEncoding=utf8";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    public static final String INSERT_NEW = "INSERT INTO programs_data(program_path, start_date) VALUES(?, ?)";


    public void createDB() {
        final String createDatabaseQyery = "CREATE DATABASE scheduled_tasks CHARACTER SET utf8 COLLATE utf8_general_ci";

        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String URL = "jdbc:mysql://localhost/mysql";

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();

            statement.executeUpdate(createDatabaseQyery);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void createTables() {
        final String createTableQuery = "CREATE TABLE `programs_data` (" +
                "  `id` int(10) NOT NULL auto_increment," +
                "  `program_path` varchar(200) default NULL," +
                "  `start_date` TIMESTAMP," +
                "  PRIMARY KEY  (`id`)" +
                ") ENGINE = InnoDB DEFAULT CHARSET = utf8;";

        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            statement.executeUpdate(createTableQuery);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void InsertData(String path, String date) {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW);
            Timestamp timestamp_date = Timestamp.valueOf(date);
            preparedStatement.setString(1, path);
            preparedStatement.setTimestamp(2, timestamp_date);
            preparedStatement.execute();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
