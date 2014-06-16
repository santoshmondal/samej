package com.samej.stateful.actions;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.samej.common.Constants;
import com.samej.pojo.UserPojo;

public class AuthAction extends BaseAction implements SessionAware {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AuthAction.class);

	private Map<String, Object> session;

	public String login() {
		result = LOGIN;

		UserPojo user = (UserPojo) session.get(Constants.SESSION_KEY);

		if (user != null) {
			result = SUCCESS;
		} else if (req != null && req.getUser() != null) {
			session.clear();
			try {

				// Authenticate user/ call DAO layer;
				UserPojo authUser = userManager.authenticateUser(req.getUser());

				// Set session and roles/menu. Call DAO layer;
				if (authUser.isAuthSuccess()) {
					result = SUCCESS;
					session.put(Constants.SESSION_KEY, authUser);
				}
			} catch (Exception e) {
				log.error(e);
			}

		} else {
			result = LOGIN;
		}

		return result;
	}

	public String logout() {
		result = SUCCESS;
		if (session != null) {
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
