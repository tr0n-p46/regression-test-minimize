package com.webaction.test;

public class App implements GitCommitListener, TestListener {

	private static final String DB_USERNAME = "souvik";
	private static final String DB_PASSWORD = "souvik";
	private static final String DB_NAME = "MethodsMap";
	private static final String JAVA_SOURCE_REGEX = "*.java";
	private static final String TEST_FILE = "chorus_tags.txt";

	private static DBSession session;

	public static void main(String[]args) {

		NotificationManager.getInstance().addEventListener(this);

		try {
			String sourceDir = args[0];
			session = new DBSession(DBBManager.getSession(DB_NAME, DB_USERNAME, DB_PASSWORD));
		}
		catch(DBNotFoundException e) {

		}
		catch(DBException e) {
			e.printStackTrace();
			System.exit(0);
		}
		finally {
			try{
				List<Method> methodList = ScanSource.getMethodList(sourceDir, JAVA_SOURCE_REGEX);
				PersistanceManager.getInstance().persistDB(session, methodList);

				TestManager testManager = new TestManager(TEST_FILE);
				testManager.performTests();
			}
			catch() {

			}
		}

		NotificationManager.getInstance().removeEventListener(this);
	}

	@Override
	public void onGitCommit(List<Method> methodList) {

	}

	@Override
	public void onTestCompleted(List<Method> methodList) {
		PersistanceManager.getInstance().persistDB(session, methodList);
	}
}