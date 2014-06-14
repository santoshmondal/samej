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
import com.samej.dao.UserDao;
import com.samej.pojo.UserPojo;

public class UserDaoMongoImpl implements UserDao {
	private static final Logger log = Logger.getLogger(UserDaoMongoImpl.class);

	@Override
	public void createUser(UserPojo user) throws Exception {
		try {
			DB db = MongoUtil.getDB();
			String json = JsonUtil.objectToJson(user);
			DBObject dbobject = (DBObject) JSON.parse(json);
			if (!dbobject.containsField(Constants.MONGO_ROW_KEY)) {
				dbobject.put(Constants.MONGO_ROW_KEY, user.getEmail());
				user.set_id(user.getEmail());
			}

			DBCollection collection = db.getCollection(Constants.MONGO_COLL_USERS);
			collection.insert(dbobject);

		} catch (Exception e) {
			log.error(e);
			throw e;
		}

	}

	@Override
	public void updateUser(UserPojo user) throws Exception {
		try {
			DB db = MongoUtil.getDB();

			String json = JsonUtil.objectToJson(user);
			DBObject dbobject = (DBObject) JSON.parse(json);

			if (dbobject.containsField(Constants.MONGO_ROW_KEY)) {
				dbobject.removeField(Constants.MONGO_ROW_KEY);
			}

			DBObject query = new BasicDBObject(Constants.MONGO_ROW_KEY, user.getEmail());
			DBObject updateDocument = new BasicDBObject(Constants.MONGO_OPERATOR_SET, dbobject);

			DBCollection collection = db.getCollection(Constants.MONGO_COLL_USERS);
			collection.update(query, updateDocument);

		} catch (Exception e) {
			log.error(e);
			throw e;
		}

	}

	@Override
	public void deleteUser(UserPojo user) throws Exception {

		try {
			DB db = MongoUtil.getDB();
			DBObject query = new BasicDBObject(Constants.MONGO_ROW_KEY, user.getEmail());
			DBCollection collection = db.getCollection(Constants.MONGO_COLL_ACTIONS);
			collection.remove(query);
		} catch (Exception e) {
			log.error(e);
			throw e;
		}
	}

	@Override
	public Set<UserPojo> readAllUsers() throws Exception {
		try {
			DB db = MongoUtil.getDB();
			Set<UserPojo> userpojoset = new HashSet<UserPojo>();

			DBCollection collection = db.getCollection(Constants.MONGO_COLL_USERS);
			DBCursor dbcursor = collection.find();

			while (dbcursor.hasNext()) {
				DBObject dbObject = (DBObject) dbcursor.next();
				UserPojo userpojo = new UserPojo();
				userpojo = (UserPojo) JsonUtil.jsonToObject(dbObject.toString(), UserPojo.class.getName());
				userpojoset.add(userpojo);
			}
			return userpojoset;
		} catch (Exception e) {
			log.error(e);
			throw e;
		}

	}

	@Override
	public Set<UserPojo> readUser(UserPojo user) throws Exception {
		try {
			DB db = MongoUtil.getDB();
			Set<UserPojo> userpojoset = new HashSet<UserPojo>();

			DBObject query = new BasicDBObject(Constants.MONGO_ROW_KEY, user.getEmail());
			DBCollection collection = db.getCollection(Constants.MONGO_COLL_USERS);
			DBCursor dbcursor = collection.find(query);

			while (dbcursor.hasNext()) {
				DBObject dbObject = (DBObject) dbcursor.next();
				UserPojo userpojo = new UserPojo();
				userpojo = (UserPojo) JsonUtil.jsonToObject(dbObject.toString(), UserPojo.class.getName());
				userpojoset.add(userpojo);
			}
			return userpojoset;
		} catch (Exception e) {
			log.error(e);
			throw e;
		}

	}

}
