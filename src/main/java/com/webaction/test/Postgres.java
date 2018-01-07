package com.webaction.test;

import java.sql.*;
import java.io.*;

public class Postgres {


	public static void restartServer() {

	}

	public static void runTestcase(String testCase) {

	}

	public static void generateReport() {

	}

	public static void parseReport() {

	}

	public static void pushToDb() {

	}

	public static void cleanup() {

	}

	public static void main(String[]args) throws Exception {
		
		String testCaseFile = args[0];
		FileReader  fileReader = new FileReader(testCaseFile);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		String line;
		while((line = bufferedReader.readLine()) != null) {
			String testCase = "-t " + line;
			System.out.println(testCase);
			//restartServer();
			//runTestcase(testCase);
			//generateReport();
			//parseReport();
			//pushToDb();
			//cleanup();
		}
		bufferedReader.close();
		fileReader.close();
	}
}