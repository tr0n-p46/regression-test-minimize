package com.webaction.test;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.security.*;

public class ExtractTestNGTestCases {

	private static final String TEST_PATH = "/Users/souvik/Product/IntegrationTests/src/test/java";
	private static final String TESTNG_TESTCASES_FILE = "/Users/souvik/sample/regression-test-minimize/TestNG_Testcases.csv";

	public Set<String> getTestCases() throws NoSuchAlgorithmException, IOException {
		ListAllFiles listAllFiles = new ListAllFiles();
		List<String> fileList = listAllFiles.getFileList(TEST_PATH);
		Set<String> testCases = new HashSet<String>();
		int count = 0;

		for(String fileName : fileList) {
			Pattern pattern = Pattern.compile("groups");
    		BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
    		String line;
    		while((line = bufferedReader.readLine()) != null) {
   				Matcher matcher = pattern.matcher(line);
   				    while (matcher.find()) {
        				int start = matcher.start(0);
        				int end = matcher.end(0);
        				String testNGGroup = line.substring(start, end);
        				System.out.println(testNGGroup);
        				++count;
        				testCases.add(testNGGroup);
      				}
    		} 
		}
		System.out.println(count);
		return testCases;
	}

	public void writeTestCases(Set<String> testNGTestCases, String fileName) throws IOException {
		FileWriter fileWriter = new FileWriter(fileName);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		for(String testCase : testNGTestCases) {
    		bufferedWriter.write(testCase + "\n");
    	}
    	bufferedWriter.close();
		fileWriter.close();
	}

	public static void main(String[]args) throws NoSuchAlgorithmException, IOException {
		ExtractTestNGTestCases extractTestNGTestCases = new ExtractTestNGTestCases();
    	Set<String> testNGTestCases = extractTestNGTestCases.getTestCases();
    	extractTestNGTestCases.writeTestCases(testNGTestCases, TESTNG_TESTCASES_FILE);
	}
}