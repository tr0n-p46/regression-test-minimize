package com.webaction.test;

import java.util.*;
import java.io.*;
import java.security.*;

public class ScanSource {

	private static final String METHOD_CHECKSUM_CSV = "$HOME/method_checksum_map.csv";

	public static void main(String[]args) throws NoSuchAlgorithmException, IOException {
		if(args.length != 1) {
			System.out.println("Source directory not specified.");
			System.exit(0);
		}
		String sourceDirectory = args[0];
		ListAllFiles listAllFiles = new ListAllFiles();
		List<String> sourceFileList =  listAllFiles.getFileList(sourceDirectory);

		Map<String, String> methodChecksumMap = new HashMap<String, String>();
		MatchMethod matchMethod = new MatchMethod();
		for(String sourceFileName : sourceFileList) {
			methodChecksumMap.putAll(matchMethod.getMethodChecksumMap(sourceFileName));
		}	
		CSVWriter csvWriter = new CSVWriter("");
		csvWriter.write(methodChecksumMap, METHOD_CHECKSUM_CSV);
	}
}