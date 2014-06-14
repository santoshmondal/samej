package com.samej.dao;

import java.util.Set;

import com.samej.pojo.RolePojo;

public interface RoleDao {
	public void createRole(RolePojo role) throws Exception;

	public void updateRole(RolePojo role) throws Exception;

	public void deleteRole(RolePojo role) throws Exception;

	public Set<RolePojo> readAllRoles() throws Exception;

	public Set<RolePojo> readRole(RolePojo role) throws Exception;

}
