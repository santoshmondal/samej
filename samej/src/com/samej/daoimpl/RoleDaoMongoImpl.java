package com.samej.daoimpl;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.util.JSON;
import com.samej.common.Constants;
import com.samej.common.JsonUtil;
import com.samej.common.MongoUtil;
import com.samej.dao.RoleDao;
import com.samej.pojo.ActionPojo;
import com.samej.pojo.RolePojo;

public class RoleDaoMongoImpl implements RoleDao {
	private static final Logger log = Logger.getLogger(ActionDaoMongoImpl.class);
	private static final String actionset = "actions";
	private static final String actionsetRef = "actionSetRef";

	@Override
	public void createRole(RolePojo role) throws Exception {
		try {
			DB db = MongoUtil.getDB();
			String json = JsonUtil.objectToJson(role);
			DBObject dbobject = (DBObject) JSON.parse(json);
			if (!dbobject.containsField(Constants.MONGO_ROW_KEY)) {
				dbobject.put(Constants.MONGO_ROW_KEY, role.getRoleId());
				role.set_id(role.getRoleId());
			}

			storeReferenceOnly(db, role, dbobject);

			DBCollection collection = db.getCollection(Constants.MONGO_COLL_ROLES);
			collection.insert(dbobject);

		} catch (Exception e) {
			log.error(e);
			throw e;
		}
	}

	@Override
	public void updateRole(RolePojo role) throws Exception {
		try {
			DB db = MongoUtil.getDB();

			String json = JsonUtil.objectToJson(role);
			DBObject dbobject = (DBObject) JSON.parse(json);

			if (dbobject.containsField(Constants.MONGO_ROW_KEY)) {
				dbobject.removeField(Constants.MONGO_ROW_KEY);
			}

			// storing only reference
			storeReferenceOnly(db, role, dbobject);

			DBObject query = new BasicDBObject(Constants.MONGO_ROW_KEY, role.getRoleId());
			DBObject updateDocument = new BasicDBObject(Constants.MONGO_OPERATOR_SET, dbobject);

			DBCollection collection = db.getCollection(Constants.MONGO_COLL_ROLES);
			collection.update(query, updateDocument);

		} catch (Exception e) {
			log.error(e);
			throw e;
		}

	}

	@Override
	public void deleteRole(RolePojo role) throws Exception {
		try {
			role.setDeleted(true);
			updateRole(role);
		} catch (Exception e) {
			log.error(e);
			throw e;
		}
	}

	@Override
	public Set<RolePojo> readAllRoles() throws Exception {
		try {
			DB db = MongoUtil.getDB();

			DBCollection collection = db.getCollection(Constants.MONGO_COLL_ROLES);
			DBCursor dbcursor = collection.find();

			Set<RolePojo> rolepojoset = readFromCursor(dbcursor);

			return rolepojoset;
		} catch (Exception e) {
			log.error(e);
			throw e;
		}
	}

	@Override
	public Set<RolePojo> readRole(RolePojo role) throws Exception {
		try {
			DB db = MongoUtil.getDB();

			DBObject query = new BasicDBObject(Constants.MONGO_ROW_KEY, role.getRoleId());
			DBCollection collection = db.getCollection(Constants.MONGO_COLL_ROLES);
			DBCursor dbcursor = collection.find(query);

			Set<RolePojo> rolepojoset = readFromCursor(dbcursor);

			return rolepojoset;
		} catch (Exception e) {
			log.error(e);
			throw e;
		}
	}

	private void storeReferenceOnly(DB db, RolePojo role, DBObject dbobject) {
		// Storing reference only
		if (dbobject.containsField(actionset)) {
			Set<DBRef> actionRefSet = new HashSet<DBRef>();
			for (ActionPojo aAction : role.getActions()) {
				actionRefSet.add(new DBRef(db, Constants.MONGO_COLL_ACTIONS, aAction.getActionId()));
			}

			dbobject.removeField(actionset);
			dbobject.put(actionsetRef, actionRefSet);
		}

	}

	private Set<RolePojo> readFromCursor(DBCursor dbcursor) {
		Set<RolePojo> rolepojoset = new HashSet<RolePojo>();
		while (dbcursor.hasNext()) {
			DBObject dbObject = (DBObject) dbcursor.next();

			fetchReferenceOnly(dbObject);

			String serializeJson = JSON.serialize(dbObject);
			RolePojo rolepojo = (RolePojo) JsonUtil.jsonToObject(serializeJson, RolePojo.class.getName());

			rolepojoset.add(rolepojo);
		}

		return rolepojoset;
	}

	public void fetchReferenceOnly(DBObject dbobject) {
		// fetching reference only
		if (dbobject.containsField(actionsetRef)) {
			Set<DBObject> refObject = new HashSet<DBObject>();

			BasicDBList dbRefSet = (BasicDBList) dbobject.get(actionsetRef);
			for (Object dbref : dbRefSet) {
				DBObject fobject = ((DBRef) dbref).fetch();
				refObject.add(fobject);
			}

			dbobject.removeField(actionsetRef);
			dbobject.put(actionset, refObject);
		}
	}
}
