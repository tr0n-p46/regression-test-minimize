package com.webaction.test;

import java.util.List;

public class App implements GitEventListener, TestListener {

	private static final String DB_USERNAME = "souvik";
	private static final String DB_PASSWORD = "souvik";
	private static final String DB_NAME = "methodmap";
	private static final String JAVA_SOURCE_REGEX = "*.java";
	private static final String TEST_FILE = "chorus_tags.txt";

	private static DBSession session;

	public static void main(String[]args) {
		App app = new App();

		NotificationManager.getInstance().addEventListener(app);
		
		try {
			String sourceDir = args[0];
			session = new DBSession(DBManager.getInstance().getConnection(DB_NAME, DB_USERNAME, DB_PASSWORD));
		}
		catch(DBException e) {
			try {
				session = new DBSession(DBManager.getInstance().getConnection("", DB_USERNAME, DB_PASSWORD));
				session.createDB(DB_NAME);
				session = new DBSession(DBManager.getInstance().getConnection(DB_NAME, DB_USERNAME, DB_PASSWORD));
			}
			catch(DBException x) {
				e.printStackTrace();
				System.exit(0);
			}

		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

		List<Method> methodList = ScanSource.getMethodList(sourceDir, JAVA_SOURCE_REGEX);
		PersistanceManager.getInstance().persistDB(session, methodList);

		/*TestManager testManager = new TestManager(TEST_FILE);
		testManager.performTests();*/

		NotificationManager.getInstance().removeEventListener(app);
	}

	@Override
	public void onGitCommit(List<Method> methodList) {
		System.out.println("Commmit detected!");
	}	

	@Override
	public void onTestCompleted(List<Method> methodList) {
		
	}
}