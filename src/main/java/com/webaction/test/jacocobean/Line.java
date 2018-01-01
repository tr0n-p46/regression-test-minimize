package com.webaction.test.jacocobean;

import javax.xml.bind.annotation.*;

public class Line {

	private long nr;
	private long mi;
	private long ci;
	private long mb;
	private long cb;

	@XmlAttribute(name = "nr")
	public long getNr() {
		return nr;
	}

	public void setNr(long nr) {
		this.nr = nr;
	}


	@XmlAttribute(name = "mi")
	public long getMi() {
		return mi;
	}

	public void setMi(long mi) {
		this.mi = mi;
	}


	@XmlAttribute(name = "ci")
	public long getCi() {
		return ci;
	}

	public void setCi(long ci) {
		this.ci = ci;
	}


	@XmlAttribute(name = "mb")
	public long getMb() {
		return mb;
	}

	public void setMb(long mb) {
		this.mb = mb;
	}


	@XmlAttribute(name = "cb")
	public long getCb() {
		return cb;
	}

	public void setCb(long cb) {
		this.cb = cb;
	}
	
}