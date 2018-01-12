package com.webaction.test;

public class Method {

	private int id;
	private String packageName;
	private String className;
	private String methodName;
	private String methodDesc;
	private String checksum;
	private String testCases;

	public Method(int id, String packageName, String className, String methodName, String methodDesc, String checksum, String testCases) {
		this.id = id;
		this.packageName = packageName;
		this.className = className;
		this.methodName = methodName;
		this.methodDesc = methodDesc;
		this.checksum = checksum;
		this.testCases = testCases;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodDesc() {
		return methodDesc;
	}

	public void setMethodDesc(String methodDesc) {
		this.methodDesc = methodDesc;
	}	

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public String getTestcases() {
		return testCases;
	}

	public void setTestcases(String testCases) {
		this.testCases = testCases;
	}
}