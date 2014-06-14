package com.samej.common;

public class Constants {

	// Session Related Constants
	public static final String SESSION_KEY = "SESSION_KEY";

	// Mongo Related Constants
	public static final String MONGO_URL = "localhost";
	public static final int MONGO_PORT = 27017;
	public static final String MONGO_DB = "samej";

	// Mongo Collections
	public static final String MONGO_COLL_ACTIONS = "s_actions";
	public static final String MONGO_COLL_ROLES = "s_roles";
	public static final String MONGO_COLL_USERS = "s_users";

	// Monog Id Check
	public static final String MONGO_ROW_KEY = "_id";

	// MONGO OPERATORS
	public static final String MONGO_OPERATOR_SET = "$set";
}
