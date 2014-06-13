package com.samej.moduletest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.samej.manager.ActionManager;
import com.samej.pojo.ActionPojo;

public class ActionDaoMongoImplTest extends BaseTestCase {

	@Test
	public void createActionTaskTest() {
		try {
			ActionManager manager = new ActionManager();

			ActionPojo action = new ActionPojo();
			action.setActionId("12346");
			action.setActionName("Samej");

			manager.createAction(action);

			assertEquals("12346", action.get_id());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
