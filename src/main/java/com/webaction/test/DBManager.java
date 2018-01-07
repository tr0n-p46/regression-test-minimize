package com.webaction.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {

	private static DBManager dbManagerInstance;
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_HOST = "localhost";
	private static final String DB_PORT = "5432";

	static {
		try {
			Class.forName(DB_DRIVER);
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	} 

	private DBManager() {
		
	}

	public static synchronized DBManager getInstance() {
		if(dbManagerInstance == null) 
			dbManagerInstance = new DBManager();
		return dbManagerInstance;
	}

	//Can return null
	public synchronized Connection getConnection(String dbName, String dbUsername, String dbPassword) throws DBException {
		return getConnection(DB_HOST, DB_PORT, dbName, dbUsername, dbPassword);
	}

	//Can return null
	public synchronized Connection getConnection(String dbHost, String dbPort, String dbName, String dbUsername, String dbPassword) throws DBException {
		Connection connection = null;
		try{
			String url = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;
			Properties properties = new Properties();
			properties.setProperty("user", dbUsername);
			properties.setProperty("password", dbPassword);

			connection = DriverManager.getConnection(url, properties);
		}
		catch(SQLException e) {
			throw new DBException("Cannot connect to database.");
		}

		return connection;
	}
}