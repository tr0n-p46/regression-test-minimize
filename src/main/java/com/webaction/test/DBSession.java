package com.webaction.test;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBSession {

	private Connection connection;

	public DBSession() {

	}
	
	public DBSession(Connection connection) {
		this.connection = connection;
	} 

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void createTable() {
		
	}

	public void deleteTable() {

	}

	public void insertIntoTable() {

	}

	public void listData() throws DBException {
		String sql = "SELECT * FROM Method;";
		try{
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getInt(1) + ", " + rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4) + ", " + rs.getString(5) + ", " + rs.getString(6) + ", " + rs.getString(7));
			}
			rs.close();
			statement.close();
		}
		catch(SQLException e) {
			throw new DBException("Could not query table.");
		}
	}

}