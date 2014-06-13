package com.samej.pojo;

import java.io.Serializable;
import java.util.Set;

public class RolePojo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String roleId;
	private String roleName;
	private int rolePriority;
	private Set<ActionPojo> actions;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getRolePriority() {
		return rolePriority;
	}

	public void setRolePriority(int rolePriority) {
		this.rolePriority = rolePriority;
	}

	public Set<ActionPojo> getActions() {
		return actions;
	}

	public void setActions(Set<ActionPojo> actions) {
		this.actions = actions;
	}


}
