package com.webaction.test.util;

import java.io.*;
import java.util.*;
import java.security.*;

public class WriteFileChecksum {

	private static final String CSV_FILE = "/Users/souvik/sample/regression-test-minimize/file_checksum.csv";

	public static void main(String[]args) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		ListAllFiles listAllFiles = new ListAllFiles();
		List<String> fileList = listAllFiles.getFileList("/Users/souvik/sample/weather");
		CSVWriter csvWriter = new CSVWriter("");
		GenerateChecksum generateChecksum = new GenerateChecksum();
		Map<String, String> map = new HashMap<String, String>();

		for(String fileName : fileList) {
			String result = generateChecksum.getChecksum(fileName);
			map.put(fileName, result);
		}	
		csvWriter.write(map, CSV_FILE);
	}
}