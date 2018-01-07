package com.webaction.test;


import java.util.regex.*;
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
/*
1. Read file into string
2. Match pattern instances
3. Compute checksum from m.end(0) to last } 
4. Obtain package name, method name and class name
5. Save in CSV file
6. Write MethodCompare
*/

public class MatchMethod {

	private static final String CSV_FILE = "/Users/souvik/sample/method_checksum.csv";
	Pattern pattern = Pattern.compile("(public|private|static|protected) +([a-zA-Z0-9<>._?, ]+) +([a-zA-Z0-9_]+) *\\([a-zA-Z0-9<>\\[\\]._?, \n]*\\) *([a-zA-Z0-9_ ,\n]*) *\\{");

	public String computeChecksum(String data) {
		String result = "";
	    try {
	        MessageDigest digest = MessageDigest.getInstance("MD5");
	        byte[] hash = digest.digest(data.getBytes("UTF-8"));
			for(int i = 0;i < hash.length;i++) {
				result += Integer.toString((hash[i] & 0xFF) + 0x100, 16).substring(1);
			}
	    }
	    catch(Exception ex) {
	            ex.printStackTrace();
	    }
	    return result;
	}

	public Map<String, String> getMethodChecksumMap(String sourceFile) throws IOException {
		Map<String, String> map = new HashMap<String, String>();

		String contents = new String(Files.readAllBytes(Paths.get(sourceFile)));
		Matcher matcher = pattern.matcher(contents);
		
		while (matcher.find()) {
        	int start = matcher.start(0);
       		int end = matcher.end(0);
    
        	
        	String temp = contents.substring(start, end);
        	
        	Stack<Character> stack = new Stack<Character>();
        	String methodName = "", checksum = "";
        	Pattern p = Pattern.compile("([a-zA-Z0-9_]+) *\\(");
        	Matcher m = p.matcher(temp);	
        	int s = 0, e = 0;
        	while(m.find()) {
        		s = m.start(0);
        		e = m.end(0) - 1;
        	}
        	
        	methodName = temp.substring(s, e);
        	
        stack.push('{');
        for(int i = end + 1;!stack.empty();i++) {
        	if(contents.charAt(i) == '{') stack.push('{');
        	else if(contents.charAt(i) == '}') stack.pop();
        	else checksum += contents.charAt(i);
        }
        checksum = computeChecksum(checksum);
        map.put(methodName, checksum);
      }
      return map;
	}

  public static void main(String[] args) throws IOException {
    
  	MatchMethod matchMethod = new MatchMethod();
  	Map<String, String> map = matchMethod.getMethodChecksumMap("/Users/souvik/sample/test_method.java");
  	CSVWriter csvWriter = new CSVWriter("");
  	csvWriter.write(map, CSV_FILE);

  }
}