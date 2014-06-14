package com.samej.pojo;

import java.util.Set;

public class UserPojo extends BasePojo {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String email;
	private String mobile;

	private Set<RolePojo> roles;
	private Set<String> roleSetRef;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Set<RolePojo> getRoles() {
		return roles;
	}

	public void setRoles(Set<RolePojo> roles) {
		this.roles = roles;
	}

	public Set<String> getRoleSetRef() {
		return roleSetRef;
	}

	public void setRoleSetRef(Set<String> roleSetRef) {
		this.roleSetRef = roleSetRef;
	}
}
