package com.samej.dao;

import java.util.Set;

import com.samej.pojo.ActionPojo;

public interface ActionDao {
	public void createAction(ActionPojo action) throws Exception;

	public void updateAction(ActionPojo action);

	public void deleteAction(ActionPojo action);

	public Set<ActionPojo> readAllActions();

	public ActionPojo readAction(ActionPojo action);
}
