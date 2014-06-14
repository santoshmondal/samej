package com.samej.manager;

import java.util.Set;

import com.samej.common.ObjectFactory;
import com.samej.common.ObjectFactory.ObjectEnum;
import com.samej.daoimpl.UserDaoMongoImpl;
import com.samej.pojo.UserPojo;

public class UserManager {
	UserDaoMongoImpl aRef = null;

	public UserManager() {
		aRef = (UserDaoMongoImpl) ObjectFactory.getInstance(ObjectEnum.USER_DAO_MONGO);
	}

	public void createUser(UserPojo user) throws Exception {
		aRef.createUser(user);
	}

	public void updateUser(UserPojo user) throws Exception {
		aRef.updateUser(user);
	}

	public void deleteUser(UserPojo user) throws Exception {
		aRef.deleteUser(user);
	}

	public Set<UserPojo> readAllUsers() throws Exception {
		return aRef.readAllUsers();
	}

	public Set<UserPojo> readUser(UserPojo user) throws Exception {
		return aRef.readUser(user);
	}
}
