package com.ga.hive.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbManager {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DbManager.class);
	private static Connection connection;
	// private static String driveName = "org.apache.hive.jdbc.HiveDriver";

	static {
		try {
			Class.forName("org.apache.hive.jdbc.HiveDriver");
			System.out.println("class loaded !!!");
		} catch (ClassNotFoundException exception) {
			LOGGER.error(exception.getMessage());
		}
	}

	public static Connection getConnection() throws SQLException {
	    LOGGER.info("inside ");
        connection = DriverManager.getConnection(
                "jdbc:hive2://localhost:10000/default", "", "");
        System.out.println("GOT CONNECTION!!!");
		return connection;
	}

	public static void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
				if (connection != null) {
					connection.close();
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
