package com.samej.manager;

import java.util.Set;

import com.samej.common.ObjectFactory;
import com.samej.common.ObjectFactory.ObjectEnum;
import com.samej.daoimpl.ActionDaoMongoImpl;
import com.samej.pojo.ActionPojo;

public class ActionManager {

	ActionDaoMongoImpl aRef = null;

	public ActionManager() {
		aRef = (ActionDaoMongoImpl) ObjectFactory.getInstance(ObjectEnum.ACTION_DAO_MONGO);
	}

	public void createAction(ActionPojo action) throws Exception {
		aRef.createAction(action);
	}

	public void updateAction(ActionPojo action) {
		aRef.updateAction(action);
	}

	public void deleteAction(ActionPojo action) {
		aRef.deleteAction(action);
	}

	public Set<ActionPojo> readAllActions() {
		return aRef.readAllActions();
	}

	public void readAction(ActionPojo action) {
		aRef.readAction(action);
	}

	public static void main(String[] args) {
		ActionManager aManager = (ActionManager) ObjectFactory.getInstance(ObjectEnum.ACTION_MANAGER);
		System.out.println(aManager);
	}
}
