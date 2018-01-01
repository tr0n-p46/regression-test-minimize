package com.webaction.test.jacocobean;

import java.util.*;
import javax.xml.bind.annotation.*;

public class Class {
	
	private String name;
	private List<Method> methodList;
	private List<Counter> counterList;

	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "method") 
	public List<Method> getMethodList() {
		return methodList;
	}

	public void setMethodList(List<Method> methodList) {
		this.methodList = methodList;
	}

	@XmlElement(name = "counter") 
	public List<Counter> getCounterList() {
		return counterList;
	}

	public void setCounterList(List<Counter> counterList) {
		this.counterList = counterList;
	}
}