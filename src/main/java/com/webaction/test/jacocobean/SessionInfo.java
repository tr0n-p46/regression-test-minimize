package com.webaction.test.jacocobean;

import javax.xml.bind.annotation.*;

public class SessionInfo {

	private String id;
	private long start;
	private long dump;

	@XmlAttribute(name = "id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlAttribute(name = "start")
	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	} 

	@XmlAttribute(name = "dump")
	public long getDump() {
		return dump;
	}

	public void setDump(long dump) {
		this.dump = dump;
	}
}