package com.webaction.test.jacocobean;

import java.util.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "report")
public class Report {

	private String name;
	private SessionInfo sessionInfo;
	private List<Package> packageList;
	private List<Counter> counterList;

	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "sessioninfo")
	public SessionInfo getSessionInfo() {
		return sessionInfo;
	}

	public void setSessionInfo(SessionInfo sessionInfo) {
		this.sessionInfo = sessionInfo;
	}

	@XmlElement(name = "package")
	public List<Package> getPackageList() {
		return packageList;
	}

	public void setPackageList(List<Package> packageList) {
		this.packageList = packageList;
	}

	@XmlElement(name = "counter")
	public List<Counter> getCounterList() {
		return counterList;
	}

	public void setCounterList(List<Counter> counterList) {
		this.counterList = counterList;
	}
}