package com.webaction.test.jacocobean;

import java.util.*;
import javax.xml.bind.annotation.*;

public class Method {
	
	private String name;
	private String desc;
	private long line;
	private List<Counter> counterList;

	@XmlAttribute(name = "name") 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(name = "desc")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@XmlAttribute(name = "line") 
	public long getLine() {
		return line;
	}

	public void setLine(long line) {
		this.line = line;
	}

	@XmlElement(name = "counter")
	public List<Counter> getCounterList() {
		return counterList;
	}

	public void setCounterList(List<Counter> counterList) {
		this.counterList = counterList;
	}
}