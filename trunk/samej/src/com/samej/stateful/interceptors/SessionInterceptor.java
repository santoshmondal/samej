package com.samej.stateful.interceptors;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.samej.common.Constants;
import com.samej.pojo.UserPojo;

public class SessionInterceptor implements Interceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		ActionContext invocationContext = actionInvocation.getInvocationContext();
		Map<String, Object> session = invocationContext.getSession();
		UserPojo userPojo =  (UserPojo)session.get(Constants.SESSION_KEY);

		if (userPojo == null) {
			return Action.LOGIN;
		} else {
			return actionInvocation.invoke();
		}
	}
	
	
	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

}
