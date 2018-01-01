package com.webaction.test;

import java.io.*;
import java.util.*;

public class CSVWriter {

	public void write(Map<String, String> map, String fileName) throws IOException {
		FileWriter fileWriter = new FileWriter(fileName);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		for(Map.Entry<String, String> entry : map.entrySet()) {
			bufferedWriter.write(entry.getKey() + "," + entry.getValue() + "\n");
		}
		bufferedWriter.close();
		fileWriter.close();
	}
}