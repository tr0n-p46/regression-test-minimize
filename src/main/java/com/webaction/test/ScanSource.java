package com.webaction.test;

import java.util.List;

public class ScanSource {

	public static String computeChecksum(String data) {
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

	public static List<Method> getMethodsFromSource(String sourceFile) throws IOException {
		String contents = new String(Files.readAllBytes(Paths.get(sourceFile)));		
		List<Method> methodList = new ArrayList<Method>();

		Pattern packageRegex = Pattern.compile("package +(a-zA-Z0-1.)+;");
		Matcher packageMatcher = packageRegex.matcher(contents);

		String packageName = "package";
		if(packageMatcher.matches()) {
			packageName = contents.substring(packageMatcher.start(0), packageMatcher.end(0));
			packageName = packageName.substring(packageName.lastIndexOf(' ') + 1, packageName.lastIndexOf(';'));
		}
		
		Pattern classRegex = Pattern.compile("class +(a-zA-Z0-9_)+");
		Matcher classMatcher = classRegex.matcher(contents);

		String className = "class";
		if(classMatcher.matches()) {
			className = contents.substring(classMatcher.start(0), classMatcher.end(0));
			className = className.substring(className.lastIndexOf(' ') + 1, className.length());
		}

		Pattern methodRegex = Pattern.compile("(public|private|static|protected) +([a-zA-Z0-9<>._?, ]+) +([a-zA-Z0-9_]+) *\\([a-zA-Z0-9<>\\[\\]._?, \n]*\\) *([a-zA-Z0-9_ ,\n]*) *\\{");
		Matcher methodMatcher = methodRegex.matcher(contents);	
		while (methodMatcher.find()) {
        	int start = methodMatcher.start(0);
       		int end = methodMatcher.end(0);
    
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
        		if(contents.charAt(i) == '{') 
        			stack.push('{');
        		else if(contents.charAt(i) == '}') 
        			stack.pop();
        		else 
        			checksum += contents.charAt(i);
        	}
        	checksum = computeChecksum(checksum);

        	Method method = new Method(0, packageName, className, methodName, methodDesc, checksum, "");
        	methodList.add(method);
        }
      	return methodList;
	}

	public static List<Method> getMethodList(String sourceDir, String sourceRegex) {
		ListAllFiles listAllFiles = new ListAllFiles();
		List<String> sourceFileList =  listAllFiles.getFileList(sourceDir, sourceRegex);

		List<Method> methodList = new ArrayList<Method>();
		for(String sourceFileName : sourceFileList) {
			methodList.putAll(getMethodsFromSource(sourceFileName));
		}	

		return methodList;
	}
}