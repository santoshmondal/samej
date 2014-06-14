package com.samej.dao;

import java.util.Set;

import com.samej.pojo.ActionPojo;

public interface ActionDao {
	public void createAction(ActionPojo action) throws Exception;

	public void updateAction(ActionPojo action) throws Exception;

	public void deleteAction(ActionPojo action) throws Exception;

	public Set<ActionPojo> readAllActions() throws Exception;

	public Set<ActionPojo> readAction(ActionPojo action) throws Exception;
}
