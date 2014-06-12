package com.samej.stateful.requsthandler;

import java.io.Serializable;

public class RequestBase implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String sampleParam;

	public String getSampleParam() {
		return sampleParam;
	}

	public void setSampleParam(String sampleParam) {
		this.sampleParam = sampleParam;
	}
}
