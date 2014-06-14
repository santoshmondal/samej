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
import com.samej.dao.RoleDao;
import com.samej.pojo.RolePojo;

public class RoleDaoMongoImpl implements RoleDao {
	private static final Logger log = Logger.getLogger(ActionDaoMongoImpl.class);

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
			DB db = MongoUtil.getDB();
			DBObject query = new BasicDBObject(Constants.MONGO_ROW_KEY, role.getRoleId());
			DBCollection collection = db.getCollection(Constants.MONGO_COLL_ROLES);
			collection.remove(query);
		} catch (Exception e) {
			log.error(e);
			throw e;
		}

	}

	@Override
	public Set<RolePojo> readAllRoles() throws Exception {
		try {
			DB db = MongoUtil.getDB();
			Set<RolePojo> rolepojoset = new HashSet<RolePojo>();

			DBCollection collection = db.getCollection(Constants.MONGO_COLL_ROLES);
			DBCursor dbcursor = collection.find();

			while (dbcursor.hasNext()) {
				DBObject dbObject = (DBObject) dbcursor.next();
				RolePojo rolepojo = new RolePojo();
				rolepojo = (RolePojo) JsonUtil.jsonToObject(dbObject.toString(), RolePojo.class.getName());
				rolepojoset.add(rolepojo);
			}
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
			Set<RolePojo> rolepojoset = new HashSet<RolePojo>();

			DBObject query = new BasicDBObject(Constants.MONGO_ROW_KEY, role.getRoleId());
			DBCollection collection = db.getCollection(Constants.MONGO_COLL_ROLES);
			DBCursor dbcursor = collection.find(query);

			while (dbcursor.hasNext()) {
				DBObject dbObject = (DBObject) dbcursor.next();
				RolePojo rolepojo = new RolePojo();
				rolepojo = (RolePojo) JsonUtil.jsonToObject(dbObject.toString(), RolePojo.class.getName());
				rolepojoset.add(rolepojo);
			}
			return rolepojoset;
		} catch (Exception e) {
			log.error(e);
			throw e;
		}
	}

}
