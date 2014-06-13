package com.samej.stateful.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.samej.stateful.requsthandler.RequestBase;

public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	protected String result;
	protected RequestBase req;

	public RequestBase getReq() {
		return req;
	}

	public void setReq(RequestBase req) {
		this.req = req;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
