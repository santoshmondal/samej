package com.samej.stateful.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.samej.common.Constants;
import com.samej.pojo.UserPojo;

public class AuthAction extends BaseAction implements SessionAware {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	
	public String login() {
		result = LOGIN;
		
		UserPojo user = (UserPojo)session.get(Constants.SESSION_KEY);
		
		if(user != null) {
			result = SUCCESS;
		} else if(req != null && req.getUser() != null) {
			session.clear();
			
			String username = req.getUser().getUsername();
			String password = req.getUser().getPassword();
			
			// Authenticate user/ call DAO layer;
			boolean authentication = false;
			if("admin".equalsIgnoreCase(username) && "admin".equalsIgnoreCase(password)) {
				result = SUCCESS;
				authentication = true;
			}
			
			// Set session and roles/menu. Call DAO layer;
			if(authentication) {
				session.put(Constants.SESSION_KEY, req.getUser());
			}
			
		} else {
			result = LOGIN;
		}
		
		return result;
	}
	
	public String logout() {
		result = SUCCESS;
		if(session != null) {
			session.clear();
		}
		return result;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	

	public Map<String, Object> getSession() {
		return session;
	} 
}
