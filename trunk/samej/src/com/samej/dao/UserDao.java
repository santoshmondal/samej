package com.samej.dao;

import java.util.Set;

import com.samej.pojo.UserPojo;

public interface UserDao {
	public void createUser(UserPojo user) throws Exception;

	public void updateUser(UserPojo user) throws Exception;

	public void deleteUser(UserPojo user) throws Exception;

	public Set<UserPojo> readAllUsers() throws Exception;

	public Set<UserPojo> readUser(UserPojo user) throws Exception;

}
