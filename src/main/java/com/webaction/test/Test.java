package com.webaction.test;

import java.sql.Connection;
import com.webaction.test.DBException;

public class Test {

	private static final String dbName = "db";
	private static final String dbUsername = "souvik";
	private static final String dbPassword = "souvik";

	public static void main(String[]args) {
		try {
			DBSession session = new DBSession(DBManager.getInstance().getConnection(dbName, dbUsername, dbPassword));
			session.listData();
		}
		catch(DBException e) {
			e.printStackTrace();
		}
	}
}