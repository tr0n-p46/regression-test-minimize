package com.webaction.test;

import java.io.*;
import java.util.*;

public class MethodCompare {

	private static final String CSV_FILE = "/Users/souvik/sample/method_checksum.csv";
	private static final String METHOD_FILE = "/Users/souvik/sample/test_method.java";

	public static void main(String[]args) throws Exception {

		CSVReader csvReader = new CSVReader();
		Map<String, String> prevChecksum = csvReader.read(CSV_FILE);
		MatchMethod matchMethod = new MatchMethod();
		Map<String, String> newChecksum = matchMethod.getMethodChecksumMap(METHOD_FILE);

		List<String> methodsModified = new ArrayList<String>();
		List<String> methodsAdded = new ArrayList<String>();
		List<String> methodsRemoved = new ArrayList<String>();

		for(Map.Entry<String, String> e : newChecksum.entrySet()) {
			if(prevChecksum.containsKey(e.getKey()) && !prevChecksum.get(e.getKey()).equals(e.getValue())) {
				methodsModified.add(e.getKey());	
				prevChecksum.remove(e.getKey());
			}
			else if(!prevChecksum.containsKey(e.getKey())) {
				methodsAdded.add(e.getKey());
			}
			else {
				prevChecksum.remove(e.getKey());
			}
		
		}

		for(Map.Entry<String, String> e : prevChecksum.entrySet()) {
			methodsRemoved.add(e.getKey());
		}

		


		System.out.println("(+) Methods Added : " + methodsAdded.size());
		for(String item : methodsAdded) {
			System.out.println("\t+ " + item);
		}

		System.out.println("(*) Methods Modified : " + methodsModified.size());
		for(String item : methodsModified) {
			System.out.println("\t* " + item);
		}

		System.out.println("(-) Methods Removed : " + methodsRemoved.size());
		for(String item : methodsRemoved) {
			System.out.println("\t- " + item);
		}

		matchMethod.main(args);
	}
}