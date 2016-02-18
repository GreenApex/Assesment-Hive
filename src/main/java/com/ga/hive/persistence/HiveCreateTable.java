package com.ga.hive.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.ga.hive.persistence.entity.User;

public class HiveCreateTable {
    private static final Logger LOGGER = Logger.getLogger(HiveCreateTable.class);

    // private static String driverName =
    // "org.apache.hadoop.hive.jdbc.HiveDriver";

    public void createTable(String tablename) throws ClassNotFoundException, SQLException {

        // System.out.println("method callled");
        // Register driver and create driver instance
        Class.forName("org.apache.hive.jdbc.HiveDriver");
        System.out.println("class loaded !!!");
        // get connection
        Connection connection = DriverManager.getConnection("jdbc:hive2://localhost:10000/default", "", "");
        System.out.println("connection !!!!!!");
        // create statement
        Statement stmt = connection.createStatement();

        // execute statement
        String sql = "create table users (userID string,name string,email string,password string) ";
        stmt.execute(sql);

        System.out.println("Table " + tablename + " created");
        connection.close();

    }

    public void insertTable(String tablename) throws ClassNotFoundException, SQLException {

        // System.out.println("method callled");
        // Register driver and create driver instance
        Class.forName("org.apache.hive.jdbc.HiveDriver");
        System.out.println("class loaded !!!");
        // get connection
        Connection connection = DriverManager.getConnection("jdbc:hive2://localhost:10000/default", "", "");
        System.out.println("connection !!!!!!");
        // create statement
        String query = "select * from users where email='bits@gmail.com'";
        LOGGER.info(": " + query);
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        LOGGER.info("After Execution");
        while (rs.next()) {
            String userID = rs.getString("userid");
            String name = rs.getString("name");
            String emailID = rs.getString("email");
            String password = rs.getString("password");

            User user = new User();
            user.setName(name);
            user.setUserID(userID);
            user.setEmail(emailID);
            user.setPassword(password);
            DbManager.closeConnection(connection);
            LOGGER.info("result: " + userID);
        }

        connection.close();

    }

    public Connection getConn() throws ClassNotFoundException, SQLException {

        Class.forName("org.apache.hive.jdbc.HiveDriver");
        System.out.println("class loaded !!!");
        // get connection
        Connection connection = DriverManager.getConnection("jdbc:hive2://localhost:10000/default", "", "");
        System.out.println("connection !!!!!!");

        return connection;

    }

}
