package com.samej.common;

import com.samej.daoimpl.ActionDaoMongoImpl;
import com.samej.daoimpl.RoleDaoMongoImpl;
import com.samej.daoimpl.UserDaoMongoImpl;
import com.samej.manager.ActionManager;
import com.samej.manager.RoleManager;
import com.samej.manager.UserManager;

public class ObjectFactory {

	public static enum ObjectEnum {
		//@formatter:off
		// DAOs
		ACTION_DAO_MONGO(ActionDaoMongoImpl.class.getName()),
		ROLE_DAO_MONGO(RoleDaoMongoImpl.class.getName()),
		USER_DAO_MONGO(UserDaoMongoImpl.class.getName()),
		
		// Services
		ACTION_MANAGER(ActionManager.class.getName()),
		ROLE_MANAGER(RoleManager.class.getName()),
		USER_MANAGER(UserManager.class.getName());
		//@formatter:on

		private final String className;

		ObjectEnum(String className) {
			this.className = className;
		}

		@Override
		public String toString() {
			return className;
		}
	}

	public static Object getInstance(ObjectEnum ObjectEnum) {
		try {
			Class<?> clazz = Class.forName(ObjectEnum.toString());
			return clazz.newInstance();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return null;
	}
}
