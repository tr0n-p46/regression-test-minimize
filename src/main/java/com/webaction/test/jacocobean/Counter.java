package com.webaction.test.jacocobean;

import javax.xml.bind.annotation.*;

@XmlType(propOrder = {"type", "missed", "covered"})
public class Counter {

	private String type;
	private long missed;
	private long covered;

	@XmlAttribute(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlAttribute(name = "missed")
	public long getMissed() {
		return missed;
	}

	public void setMissed(long missed) {
		this.missed = missed;
	}

	@XmlAttribute(name = "covered") 
	public long getCovered() {
		return covered;
	}

	public void setCovered(long covered) {
		this.covered = covered;
	}
}