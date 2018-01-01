package com.webaction.test;

import java.io.*;
import java.util.*;

public class CSVReader {

	private static final String DELIMITER = ",";

	public Map<String, String> read(String fileName) throws FileNotFoundException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = "";
		while((line = bufferedReader.readLine()) != null) {
			String data[] = line.split(DELIMITER);
			map.put(data[0], data[1]);
		}
		return map;
	}

	public static void main(String[]args) throws FileNotFoundException, IOException {
		CSVReader csvReader = new CSVReader();
		csvReader.read("file_checksum.csv");
	}
}