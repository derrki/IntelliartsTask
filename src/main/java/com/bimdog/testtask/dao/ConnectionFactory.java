package com.bimdog.testtask.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {

    private static ConnectionFactory instance = null;

    private static String db_url;
    private static String login;
    private static String password;
    private static String driver;

    public ConnectionFactory(){
        getProperties();
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(db_url, login, password);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
            e.printStackTrace();
        }
        return connection;
    }

    public static ConnectionFactory getInstance(){
        if (instance == null){
            instance = new ConnectionFactory();
        }
        return instance;
    }


    //одержуємо параметри конекту до БД з файлу config.properties
    private void getProperties(){
        ResourceBundle resource = ResourceBundle.getBundle("config");
        db_url = resource.getString("DB_URL");
        login = resource.getString("LOGIN");
        password = resource.getString("PASSWORD");
        driver = resource.getString("DRIVER");
    }

}
