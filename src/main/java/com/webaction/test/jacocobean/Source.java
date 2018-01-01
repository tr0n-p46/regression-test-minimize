package com.webaction.test.jacocobean;

import java.util.*;
import javax.xml.bind.annotation.*;

public class Source {
	
	private String name;
	private List<Counter> counterList;
	private List<Line> lineList;

	@XmlAttribute(name = "name") 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "line") 
	public List<Line> getLineList() {
		return lineList;
	}

	public void setLineList(List<Line> lineList) {
		this.lineList = lineList;
	}

	@XmlElement(name = "counter")
	public List<Counter> getCounterList() {
		return counterList;
	}

	public void setCounterList(List<Counter> counterList) {
		this.counterList = counterList;
	}
}