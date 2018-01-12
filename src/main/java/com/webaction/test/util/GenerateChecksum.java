package com.webaction.test.util;

import java.io.*;
import java.security.*;

public class GenerateChecksum {

	public byte[] generateChecksum(InputStream inputStream) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		if(inputStream == null) return null;
		byte buffer[] = new byte[1024];
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		int numRead;
		do {
			numRead = inputStream.read(buffer);
			if(numRead > 0) {
				messageDigest.update(buffer, 0, numRead);
			}
		}
		while(numRead != -1);
		inputStream.close();
		return messageDigest.digest();
	}

	public String getChecksum(String fileName) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		InputStream inputStream = new FileInputStream(fileName);
		byte checkSum[] = generateChecksum(inputStream);
		String result = "";
		for(int i = 0;i < checkSum.length;i++) {
			result += Integer.toString((checkSum[i] & 0xFF) + 0x100, 16).substring(1);
		}
		return result;
	}

	public static void main(String[]args) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		GenerateChecksum generateChecksum = new GenerateChecksum();
		String result = generateChecksum.getChecksum("Sample.java");
		System.out.println("MD5 : " + result);
	}
}