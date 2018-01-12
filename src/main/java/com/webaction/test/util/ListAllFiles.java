package com.webaction.test.util;

import java.util.List;

public class ListAllFiles {

    private List<String> fileList;
    private Pattern pattern;
    private Matcher matcher;

    public ListAllFiles() {
        fileList = new ArrayList<String>();
    }

	public void walkDir(String path) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
        File root = new File(path);
        File[] list = root.listFiles();
        if (list == null) return;
        for (File file : list) {
            if (file.isDirectory()) 
                walkDir(file.getAbsolutePath());
            else {
                matcher = pattern.matcher(file.getName());
                if(matcher.matches())
                    fileList.add(file.getAbsolutePath());
            }
        }
    }

    public List<String> getFileList(String path) throws FileNotFoundException {
        return getFileList(path, "*");
    }

    public List<String> getFileList(String path, String regex) throws FileNotFoundException {
        pattern = Pattern.compile(regex);
        walkDir(path);
        return fileList;
    }
}