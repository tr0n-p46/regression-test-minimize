package com.webaction.test.util;

import java.io.*;
import java.util.*;
import java.security.*;

public class FilesCompare {

	private static final String CSV_FILE = "/Users/souvik/sample/regression-test-minimize/file_checksum.csv";
	private static List<String> filesAdded, filesRemoved, filesEdited;

	public FilesCompare() {
		filesAdded = new ArrayList<String>();
		filesRemoved = new ArrayList<String>();
		filesEdited = new ArrayList<String>();
	}

	public static void main(String[]args) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		FilesCompare filesCompare = new FilesCompare();
		ListAllFiles listAllFiles = new ListAllFiles();
		List<String> fileList = listAllFiles.getFileList("/Users/souvik/sample/weather");
		GenerateChecksum generateChecksum = new GenerateChecksum();
		CSVReader csvReader = new CSVReader();
		Map<String, String> map = csvReader.read(CSV_FILE);
		for(String fileName : fileList) {
			if(!map.containsKey(fileName)) {
				filesAdded.add(fileName);
				continue;
			}
			String newChecksum = generateChecksum.getChecksum(fileName);
			String oldChecksum = map.get(fileName);
			if(!newChecksum.equals(oldChecksum)) {
				filesEdited.add(fileName);
			}
			map.remove(fileName);
		}
		for(Map.Entry<String, String> entry : map.entrySet()) {
			filesRemoved.add(entry.getKey());
		}
		System.out.println("(+) Files Added : " + filesAdded.size());
		for(String fileName : filesAdded) {
			System.out.println("\t+ " + fileName);
		}
		System.out.println("(-) Files Removed : " + filesRemoved.size());
		for(String fileName : filesRemoved) {
			System.out.println("\t- " + fileName);
		}
		System.out.println("(*) Files Modified : " + filesEdited.size());
		for(String fileName : filesEdited) {
			System.out.println("\t* " + fileName);
		}
		WriteFileChecksum.main(args);
	}
}