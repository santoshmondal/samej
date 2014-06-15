package com.samej.manager;

import java.util.Set;

import com.samej.common.ObjectFactory;
import com.samej.common.ObjectFactory.ObjectEnum;
import com.samej.daoimpl.UserDaoMongoImpl;
import com.samej.pojo.UserPojo;

public class UserManager {
	UserDaoMongoImpl uRef = null;

	public UserManager() {
		uRef = (UserDaoMongoImpl) ObjectFactory.getInstance(ObjectEnum.USER_DAO_MONGO);
	}

	public void createUser(UserPojo user) throws Exception {
		uRef.createUser(user);
	}

	public void updateUser(UserPojo user) throws Exception {
		uRef.updateUser(user);
	}

	public void deleteUser(UserPojo user) throws Exception {
		uRef.deleteUser(user);
	}

	public Set<UserPojo> readAllUsers() throws Exception {
		return uRef.readAllUsers();
	}

	public Set<UserPojo> readUser(UserPojo user) throws Exception {
		return uRef.readUser(user);
	}

	public UserPojo authenticateUser(UserPojo user) throws Exception {
		return uRef.authenticateUser(user);
	}

}
