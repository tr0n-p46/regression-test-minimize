package com.webaction.test;

import java.io.*;
import java.util.*;

public class CSVWriter {

	private String csvPath;

	public CSVWriter(String csvPath) {
		this.csvPath = csvPath;
	}

	public void write(Map<String, String> map, String fileName) throws IOException {
		FileWriter fileWriter = new FileWriter(fileName);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		for(Map.Entry<String, String> entry : map.entrySet()) {
			bufferedWriter.write(entry.getKey() + "," + entry.getValue() + "\n");
		}
		bufferedWriter.close();
		fileWriter.close();
	}

	public void writeCSV(Map<String, List<String>> map) throws IOException {
		FileWriter fileWriter = new FileWriter(csvPath);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		for(Map.Entry<String, List<String>> entry : map.entrySet()) {
			//bufferedWriter.write(entry.getKey() + ",");
			List<String> list = entry.getValue();
			for(String testCase : list) {
				bufferedWriter.write(testCase + ",");
			}
			bufferedWriter.write("\n");
		}
		bufferedWriter.close();
		fileWriter.close();
	}
}