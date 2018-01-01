package com.webaction.test;

import java.io.*;
import java.security.*;
import java.util.*;

public class ListAllFiles {

    private List<String> fileList;
    private GenerateChecksum generateChecksum;
    
    public ListAllFiles() {
        fileList = new ArrayList<String>();
        generateChecksum = new GenerateChecksum();
    }

	public void iterateFiles(String path) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
        File root = new File(path);
        File[] list = root.listFiles();
        if (list == null) return;
        for (File file : list) {
            if (file.isDirectory()) 
                getFileList(file.getAbsolutePath());
            else {
                if(file.getName().charAt(0)!='.')
                    fileList.add(file.getAbsolutePath());
            }
        }
    }

    public List<String> getFileList(String path) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
        iterateFiles(path);
        return fileList;
    }

	public static void main(String[]args) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		ListAllFiles listAllFiles = new ListAllFiles();
		listAllFiles.getFileList("./");
	}
}