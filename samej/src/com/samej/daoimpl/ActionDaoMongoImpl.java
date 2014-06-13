package com.samej.daoimpl;

import java.util.Set;

import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import com.samej.common.Constants;
import com.samej.common.JsonUtil;
import com.samej.dao.ActionDao;
import com.samej.pojo.ActionPojo;

public class ActionDaoMongoImpl implements ActionDao {
	private static final Logger log = Logger.getLogger(ActionDaoMongoImpl.class);

	private DB db;

	public ActionDaoMongoImpl() {
		try {
			MongoClient mongoClient = new MongoClient();
			this.db = mongoClient.getDB(Constants.MONGO_DB);
		} catch (Exception e) {
			log.error(e);
		}
	}

	@Override
	public void createAction(ActionPojo action) throws Exception {
		try {
			String json = JsonUtil.objectToJson(action);
			DBObject dbobject = (DBObject) JSON.parse(json);
			if (!dbobject.containsField(Constants.MONGO_ROW_KEY)) {
				dbobject.put(Constants.MONGO_ROW_KEY, action.getActionId());
			}

			DBCollection collection = db.getCollection(Constants.MONGO_COLL_ACTIONS);
			collection.insert(dbobject);

		} catch (Exception e) {
			log.error(e);
			throw e;
		}
	}

	@Override
	public void updateAction(ActionPojo action) {

	}

	@Override
	public void deleteAction(ActionPojo action) {
		updateAction(action);
	}

	@Override
	public Set<ActionPojo> readAllActions() {
		return null;
	}

	@Override
	public ActionPojo readAction(ActionPojo action) {
		return null;
	}

}
