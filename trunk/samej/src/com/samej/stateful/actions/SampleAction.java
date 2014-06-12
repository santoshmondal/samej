package com.samej.stateful.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.samej.stateful.requsthandler.RequestBase;

public class SampleAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private RequestBase requestBase;

	public String sampleAction() {
		return SUCCESS;
	}
	
	
	public RequestBase getRequestBase() {
		return requestBase;
	}
	public void setRequestBase(RequestBase requestBase) {
		this.requestBase = requestBase;
	}
	
}
