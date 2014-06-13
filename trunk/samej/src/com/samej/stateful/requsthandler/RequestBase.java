package com.samej.stateful.requsthandler;

import java.io.Serializable;

import com.samej.pojo.UserPojo;

public class RequestBase implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String sampleParam;
	private UserPojo user;
	

	public UserPojo getUser() {
		return user;
	}

	public void setUser(UserPojo user) {
		this.user = user;
	}

	public String getSampleParam() {
		return sampleParam;
	}

	public void setSampleParam(String sampleParam) {
		this.sampleParam = sampleParam;
	}
}
