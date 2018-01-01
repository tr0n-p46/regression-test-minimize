package com.webaction.test.jacocobean;

import java.util.*;
import javax.xml.bind.annotation.*;

public class Package {

	private String name;
	private List<Class> classList;
	private List<Source> sourceList;
	private List<Counter> counterList;

	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "class")
	public List<Class> getClassList() {
		return classList;
	}

	public void setClassList(List<Class> classList) {
		this.classList = classList;
	}

	@XmlElement(name = "sourcefile")
	public List<Source> getSourceList() {
		return sourceList;
	}

	public void setSourceList(List<Source> sourceList) {
		this.sourceList = sourceList;
	}

	@XmlElement(name = "counter")
	public List<Counter> getCounterList() {
		return counterList;
	}

	public void setCounterList(List<Counter> counterList) {
		this.counterList = counterList;
	}
}