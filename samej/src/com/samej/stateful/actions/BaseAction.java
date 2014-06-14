package com.samej.stateful.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.samej.common.ObjectFactory;
import com.samej.common.ObjectFactory.ObjectEnum;
import com.samej.manager.ActionManager;
import com.samej.manager.RoleManager;
import com.samej.manager.UserManager;
import com.samej.stateful.requsthandler.RequestBase;

public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	protected UserManager userManager = (UserManager) ObjectFactory.getInstance(ObjectEnum.USER_MANAGER);
	protected ActionManager aManager = (ActionManager) ObjectFactory.getInstance(ObjectEnum.ACTION_MANAGER);
	protected RoleManager roleManager = (RoleManager) ObjectFactory.getInstance(ObjectEnum.ROLE_MANAGER);

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
