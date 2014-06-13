package com.samej.manager;

import java.util.Set;

import com.samej.dao.ActionDao;
import com.samej.daoimpl.ActionDaoMongoImpl;
import com.samej.pojo.ActionPojo;

public class ActionManager {
	public void createAction(ActionPojo action) throws Exception {
		ActionDao actionDao = new ActionDaoMongoImpl();
		actionDao.createAction(action);
	}

	public void updateAction(ActionPojo action) {

	}

	public void deleteAction(ActionPojo action) {

	}

	public Set<ActionPojo> readAllActions() {
		return null;
	}

	public void readAction(ActionPojo action) {
	}
}
