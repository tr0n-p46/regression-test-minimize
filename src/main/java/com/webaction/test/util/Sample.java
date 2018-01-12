/*package com.webaction.test;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.webaction.test.jacocobean.*;

class Item {

    String package_name;
    String class_name;
    String method_name;
    String method_signature;
    String modified;
    String checksum;
    String test_cases;
}

public class Sample {

	private static final String COMMAND = "/Users/souvik/Product/IntegrationTests/runTests.sh -c souvik -p souvik -u admin -a admin -t ";
	private static final String FINAL_REPORT = "/Users/souvik/sample/regression-test-minimize/Final_Report.csv";
    private static final String DB_NAME = "db";
    private static final String TABLE_NAME = "methods";
    private static Connection connection;
    private static PreparedStatement preparedStatement;

    static {
        try {
            java.lang.Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + DB_NAME, "souvik", "souvik");

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void pushToDb(Item item) throws Exception {
        String sql = "INSERT INTO " + TABLE_NAME + "(package_name, class_name, method_name, method_signature, modified, checksum, test_cases) VALUES(?, ?, ?, ?, ?, ?, ?)";
        System.out.println(item.package_name + ", " + item.class_name + ", " + item.method_name + ", " + item.method_signature + ", " + item.modified + ", " + item.checksum + ", " + item.test_cases);
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, item.package_name);
        preparedStatement.setString(2, item.class_name);
        preparedStatement.setString(3, item.method_name);
        preparedStatement.setString(4, item.method_signature);
        preparedStatement.setString(5, item.modified);
        preparedStatement.setString(6, item.checksum);
        preparedStatement.setString(7, item.test_cases);
        preparedStatement.execute();
    }

	public static void main(String[]args) throws Exception {
		FileReader file = new FileReader("/Users/souvik/sample/regression-test-minimize/Chorus_Testcases.csv");
		BufferedReader br = new BufferedReader(file);
		Map<String, List<String>> map = new HashMap<String, List<String>>();

        String testCase = "";

		while(true) {
			File reportFile = new File("/Users/souvik/Product/report_tcp.xml");
			if(reportFile.delete()) {
				System.out.println("Deleted old report!");
			}
			else {
				System.out.println("Could not delete old report!");
			}
			testCase = br.readLine();
            if(testCase == null) break;

			Runtime runtime = Runtime.getRuntime();
            System.out.println("\n\n********************Restarting server****************************");
            Process process = runtime.exec("/Users/souvik/Product/resetDerby.sh -A hard");
            process.waitFor();

            process = runtime.exec("/Users/souvik/Product/bin/startServer.sh -c souvik -p souvik -u admin -N striim  -a admin -m localhost:1527");
            //process.waitFor();
            System.out.println("\n\n********************Server restarted Successfully****************************");
            Thread.sleep(20000);
			System.out.println("\n\n********************Executing TestCase " + testCase + "*********************************\n\n");
        	process = runtime.exec(COMMAND + testCase);
        	process.waitFor();

        	InputStream inputStream = process.getInputStream();
        	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        	String s = null;
        	while ((s = bufferedReader.readLine()) != null) {
            	System.out.println(s);
        	}
        	inputStream.close();
        	System.out.println("\n\n********************Finished TestCase " + testCase + "*********************************\n\n");
        	System.out.println("Generating new XML report for test case " + testCase);
        	process = runtime.exec("ant -buildfile /Users/souvik/Product/build.xml");
        	process.waitFor();
        	System.out.println("\n\n********************Successfully generated new XML report!********************\n\n");

        	System.out.println("\n\n********************Parsing XML report********************\n\n");
        	XMLParser parser = new XMLParser();
        	Report report = parser.getReport();
        	System.out.println("\n\n********************Successfully Parsed XML and got report********************\n\n");

            System.out.println("\n\n********************Pushing to DB********************\n\n");
            Item item = new Item();

            List<com.webaction.test.jacocobean.Package> packageList = report.getPackageList();
            for(com.webaction.test.jacocobean.Package packageE : packageList) {
                String packageName = packageE.getName().replaceAll("/", ".");
                item.package_name = packageName;

                List<com.webaction.test.jacocobean.Class> classList = packageE.getClassList();
                for(com.webaction.test.jacocobean.Class classS : classList) {
                    String className = classS.getName().replaceAll("$", ".");
                    item.class_name = className;

                    List<Method> methodList = classS.getMethodList();
                    for(Method method : methodList) {
                        String methodName = method.getName();
                        item.method_name = methodName;
                        item.method_signature = method.getDesc();
                        item.modified = "no";
                        item.checksum = "ksjbfjks";

                        List<Counter> counterList = method.getCounterList();
                        for(Counter counter : counterList) {
                            if(counter.getType().equals("METHOD") && counter.getCovered() != 0) {
                                //System.out.println(packageName + ", " + className.substring(className.lastIndexOf('/') + 1, className.length()) + ", " + methodName);
                                pushToDb(item);
                            }
                        }
                    }
                }
            }


        	System.out.println();
        	//reportFile.close();
        	bufferedReader.close();
        	inputStream.close();
        }

        /*System.out.println("Writing results...");
        CSVWriter csvWriter = new CSVWriter(FINAL_REPORT);
        csvWriter.writeCSV(map);
        System.out.println("Successfully written results to Final_Report.csv");*/

        /*br.close();
        file.close();
	}
}*/