package com.samej.manager;

import java.util.Set;

import com.samej.common.ObjectFactory;
import com.samej.common.ObjectFactory.ObjectEnum;
import com.samej.daoimpl.RoleDaoMongoImpl;
import com.samej.pojo.RolePojo;

public class RoleManager {
	RoleDaoMongoImpl rRef = null;

	public RoleManager() {
		rRef = (RoleDaoMongoImpl) ObjectFactory.getInstance(ObjectEnum.ROLE_DAO_MONGO);
	}

	public void createRole(RolePojo role) throws Exception {
		rRef.createRole(role);
	}

	public void updateRole(RolePojo role) throws Exception {
		rRef.updateRole(role);
	}

	public void deleteRole(RolePojo role) throws Exception {
		rRef.deleteRole(role);
	}

	public Set<RolePojo> readAllRoles() throws Exception {
		return rRef.readAllRoles();
	}

	public Set<RolePojo> readRole(RolePojo role) throws Exception {
		return rRef.readRole(role);
	}
}
