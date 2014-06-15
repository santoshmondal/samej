package com.samej.daoimpl;

import java.util.HashSet;
import java.util.Iterator;
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
import com.samej.common.PasswordUtil;
import com.samej.dao.UserDao;
import com.samej.pojo.RolePojo;
import com.samej.pojo.UserPojo;

public class UserDaoMongoImpl implements UserDao {
	private static final Logger log = Logger.getLogger(UserDaoMongoImpl.class);
	private static final String roleset = "roles";
	private static final String rolesetRef = "roleSetRef";

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

			storeReferenceOnly(db, user, dbobject);

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

			storeReferenceOnly(db, user, dbobject);

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
			user.setDeleted(true);
			updateUser(user);
		} catch (Exception e) {
			log.error(e);
			throw e;
		}
	}

	@Override
	public Set<UserPojo> readAllUsers() throws Exception {
		try {
			DB db = MongoUtil.getDB();

			DBCollection collection = db.getCollection(Constants.MONGO_COLL_USERS);
			DBCursor dbcursor = collection.find();

			Set<UserPojo> userset = readFromCursor(dbcursor);
			return userset;
		} catch (Exception e) {
			log.error(e);
			throw e;
		}

	}

	@Override
	public Set<UserPojo> readUser(UserPojo user) throws Exception {
		try {
			DB db = MongoUtil.getDB();

			DBObject query = new BasicDBObject(Constants.MONGO_ROW_KEY, user.getEmail());
			if (user.getPassword() != null) {
				query.put(Constants.MONGO_USER_PASSWORD, user.getPassword());
			}

			DBCollection collection = db.getCollection(Constants.MONGO_COLL_USERS);
			DBCursor dbcursor = collection.find(query);

			Set<UserPojo> userset = readFromCursor(dbcursor);
			return userset;
		} catch (Exception e) {
			log.error(e);
			throw e;
		}
	}

	private Set<UserPojo> readFromCursor(DBCursor dbcursor) {
		Set<UserPojo> userpojoset = new HashSet<UserPojo>();
		while (dbcursor.hasNext()) {
			DBObject dbObject = (DBObject) dbcursor.next();

			fetchReferenceOnly(dbObject);

			String serializeJson = JSON.serialize(dbObject);
			UserPojo rolepojo = (UserPojo) JsonUtil.jsonToObject(serializeJson, UserPojo.class.getName());

			userpojoset.add(rolepojo);
		}

		return userpojoset;
	}

	private void fetchReferenceOnly(DBObject dbobject) {
		// fetching reference only
		if (dbobject.containsField(rolesetRef)) {
			Set<DBObject> refObject = new HashSet<DBObject>();

			BasicDBList dbRefSet = (BasicDBList) dbobject.get(rolesetRef);
			RoleDaoMongoImpl ref = new RoleDaoMongoImpl();
			for (Object dbref : dbRefSet) {
				DBObject roleObject = ((DBRef) dbref).fetch();

				ref.fetchReferenceOnly(roleObject);
				refObject.add(roleObject);
			}

			dbobject.removeField(rolesetRef);
			dbobject.put(roleset, refObject);
		}
	}

	private void storeReferenceOnly(DB db, UserPojo user, DBObject dbobject) {
		// Storing reference only
		if (dbobject.containsField(roleset)) {
			Set<DBRef> roleRefSet = new HashSet<DBRef>();
			for (RolePojo rolePojo : user.getRoles()) {
				roleRefSet.add(new DBRef(db, Constants.MONGO_COLL_ROLES, rolePojo.getRoleId()));
			}

			dbobject.removeField(roleset);
			dbobject.put(rolesetRef, roleRefSet);
		}
	}

	@Override
	public UserPojo authenticateUser(UserPojo user) throws Exception {
		user.setPassword(PasswordUtil.encrypt(user.getPassword()));
		try {
			Set<UserPojo> readUser = readUser(user);
			if (!readUser.isEmpty()) {
				Iterator<UserPojo> iterator = readUser.iterator();
				user = iterator.next();
				user.setAuthSuccess(true);
			} else {
				user.setAuthSuccess(false);
			}
		} catch (Exception e) {
			log.error(e);
			throw e;
		}
		return user;
	}
}