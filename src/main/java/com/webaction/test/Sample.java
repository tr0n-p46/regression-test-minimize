package com.webaction.test;

import java.io.*;
import java.util.*;

public class Sample {

	private static String COMMAND = "/Users/souvik/Product/IntegrationTests/runTests.sh -c souvik -p souvik -u admin -a admin -t ";
	private static String FINAL_REPORT = "/Users/souvik/sample/regression-test-minimize/Final_Report.csv";

	public static void main(String[]args) throws IOException, InterruptedException {
		FileReader file = new FileReader("/Users/souvik/sample/regression-test-minimize/Chorus_Testcases.csv");
		BufferedReader br = new BufferedReader(file);
		Map<String, List<String>> map = new HashMap<String, List<String>>();

		for(int i = 0;i < 5;i++) {
			File reportFile = new File("/Users/souvik/Product/report_tcp.xml");
			if(reportFile.delete()) {
				System.out.println("Deleted old report!");
			}
			else {
				System.out.println("Could not delete old report!");
			}
			String testCase = br.readLine();
			Runtime runtime = Runtime.getRuntime();
			System.out.println("\n\n********************Executing TestCase " + testCase + "*********************************\n\n");
        	Process process = runtime.exec(COMMAND + testCase);
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
        	System.out.println("Successfully generated new XML report!");

        	System.out.println("Parsing XML report");
        	XMLParser parser = new XMLParser();
        	List<String> methodList = parser.getMethodList();
        	System.out.println("Successfully Parsed report and got method list!");

        	for(int j = 0;j < methodList.size();j++) {
        		if(map.containsKey(methodList.get(j)) && !map.get(methodList.get(j)).contains(testCase)) {
        			map.get(methodList.get(j)).add(testCase);

        		}
        		else {
        			List<String> list = new ArrayList<String>();
        			list.add(testCase);
        			map.put(methodList.get(j), list);
        		}
        	}


        	System.out.println();
        	//reportFile.close();
        	bufferedReader.close();
        	inputStream.close();
        }

        System.out.println("Writing results...");
        CSVWriter csvWriter = new CSVWriter(FINAL_REPORT);
        csvWriter.writeCSV(map);
        System.out.println("Successfully written results to Final_Report.csv");

        br.close();
        file.close();
	}
}