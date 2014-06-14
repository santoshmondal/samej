package com.samej.moduletest;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import com.samej.common.ObjectFactory;
import com.samej.common.ObjectFactory.ObjectEnum;
import com.samej.manager.ActionManager;
import com.samej.pojo.ActionPojo;

public class ActionDaoMongoImplTest extends BaseTestCase {

	@Ignore
	public void createActionTaskTest() {
		try {
			ActionManager manager = (ActionManager) ObjectFactory.getInstance(ObjectEnum.ACTION_MANAGER);

			ActionPojo action = new ActionPojo();
			action.setActionId("12346");
			action.setActionName("Samej");

			manager.createAction(action);

			assertEquals("12346", action.get_id());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void updateActionTaskTest() {
		try {
			ActionManager manager = (ActionManager) ObjectFactory.getInstance(ObjectEnum.ACTION_MANAGER);

			ActionPojo action = new ActionPojo();
			action.setActionId("12346");
			action.setActionName("Samej 12345");

			manager.updateAction(action);

			assertEquals("Samej 12345", action.getActionName());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
