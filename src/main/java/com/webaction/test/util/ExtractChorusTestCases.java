package com.webaction.util;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.security.*;

public class ExtractChorusTestCases {

	private static final String FEATURE_PATH = "/Users/souvik/Product/IntegrationTests/src/test/resources/features";
	private static final String CHORUS_TESTCASES_FILE = "/Users/souvik/sample/regression-test-minimize/Chorus_Testcases.csv";

	public Set<String> getTestCases() throws NoSuchAlgorithmException, IOException {
		ListAllFiles listAllFiles = new ListAllFiles();
		List<String> fileList = listAllFiles.getFileList(FEATURE_PATH);
		Set<String> testCases = new HashSet<String>();
		for(String fileName : fileList) {
			if(fileName.contains(".feature")) {
				Pattern pattern = Pattern.compile("@[a-zA-Z0-9_]+");
    			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

    			String line;
    			while((line = bufferedReader.readLine()) != null) {
      				Matcher matcher = pattern.matcher(line);
      				while (matcher.find()) {
        				int start = matcher.start(0);
        				int end = matcher.end(0);
        				String chorusTag = line.substring(start, end);
        				testCases.add(chorusTag);
      				}
    			} 
    			
			}
		}
		return testCases;
	}

	public void writeTestCases(Set<String> chorusTestCases, String fileName) throws IOException {
		FileWriter fileWriter = new FileWriter(fileName);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		for(String testCase : chorusTestCases) {
    		bufferedWriter.write(testCase + "\n");
    	}
    	bufferedWriter.close();
		fileWriter.close();
	}

	public static void main(String[]args) throws NoSuchAlgorithmException, IOException {
		ExtractChorusTestCases extractChorusTestCases = new ExtractChorusTestCases();
    	Set<String> chorusTestCases = extractChorusTestCases.getTestCases();
    	extractChorusTestCases.writeTestCases(chorusTestCases, CHORUS_TESTCASES_FILE);
	}
}