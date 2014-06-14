package com.samej.daoimpl;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.samej.common.Constants;
import com.samej.common.JsonUtil;
import com.samej.common.MongoUtil;
import com.samej.dao.ActionDao;
import com.samej.pojo.ActionPojo;

public class ActionDaoMongoImpl implements ActionDao {
	private static final Logger log = Logger.getLogger(ActionDaoMongoImpl.class);

	@Override
	public void createAction(ActionPojo action) throws Exception {
		try {
			DB db = MongoUtil.getDB();
			String json = JsonUtil.objectToJson(action);
			DBObject dbobject = (DBObject) JSON.parse(json);
			if (!dbobject.containsField(Constants.MONGO_ROW_KEY)) {
				dbobject.put(Constants.MONGO_ROW_KEY, action.getActionId());
				action.set_id(action.getActionId());
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
		try {
			DB db = MongoUtil.getDB();

			String json = JsonUtil.objectToJson(action);
			DBObject dbobject = (DBObject) JSON.parse(json);

			if (dbobject.containsField(Constants.MONGO_ROW_KEY)) {
				dbobject.removeField(Constants.MONGO_ROW_KEY);
			}

			DBObject query = new BasicDBObject(Constants.MONGO_ROW_KEY, action.getActionId());
			DBObject updateDocument = new BasicDBObject(Constants.MONGO_OPERATOR_SET, dbobject);

			DBCollection collection = db.getCollection(Constants.MONGO_COLL_ACTIONS);
			collection.update(query, updateDocument);

		} catch (Exception e) {
			log.error(e);
			throw e;
		}
	}

	@Override
	public void deleteAction(ActionPojo action) throws Exception {
		try {
			action.setDeleted(true);
			updateAction(action);
		} catch (Exception e) {
			log.error(e);
			throw e;
		}
	}

	@Override
	public Set<ActionPojo> readAllActions() throws Exception {
		try {
			DB db = MongoUtil.getDB();

			DBCollection collection = db.getCollection(Constants.MONGO_COLL_ACTIONS);
			DBCursor dbcursor = collection.find();

			Set<ActionPojo> actionpojoset = readFromCursor(dbcursor);
			return actionpojoset;
		} catch (Exception e) {
			log.error(e);
			throw e;
		}
	}

	@Override
	public Set<ActionPojo> readAction(ActionPojo action) throws Exception {

		try {
			DB db = MongoUtil.getDB();

			DBObject query = new BasicDBObject(Constants.MONGO_ROW_KEY, action.getActionId());
			DBCollection collection = db.getCollection(Constants.MONGO_COLL_ACTIONS);

			DBCursor dbcursor = collection.find(query);
			Set<ActionPojo> actionpojoset = readFromCursor(dbcursor);

			return actionpojoset;
		} catch (Exception e) {
			log.error(e);
			throw e;
		}
	}

	private Set<ActionPojo> readFromCursor(DBCursor dbcursor) {
		Set<ActionPojo> actionpojoset = new HashSet<ActionPojo>();
		while (dbcursor.hasNext()) {
			DBObject dbObject = (DBObject) dbcursor.next();
			ActionPojo actionpojo = new ActionPojo();
			actionpojo = (ActionPojo) JsonUtil.jsonToObject(dbObject.toString(), ActionPojo.class.getName());
			actionpojoset.add(actionpojo);
		}

		return actionpojoset;
	}
}
