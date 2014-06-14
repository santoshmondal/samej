package com.samej.stateful.actions;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.samej.common.ObjectFactory;
import com.samej.common.ObjectFactory.ObjectEnum;
import com.samej.manager.UserManager;
import com.samej.pojo.ActionPojo;
import com.samej.pojo.RolePojo;
import com.samej.pojo.UserPojo;
import com.samej.stateful.requsthandler.RequestBase;

/**
 * SuperAdmin with CreateAction, CreateRole, CreateUser
 * The above actionElement will be inserted into the ActionCollections. 
 * 
 * SuperRole Role will be created. 
 * 
 * Finally SuperAdmin User will be created. 
 * @author santoshm
 *
 */
public class SuperAdminAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SuperAdminAction.class);

	public String superAdmin() {
		result = SUCCESS;
		try {

			Set<UserPojo> userExists = userManager.readUser(defaultUser());
			if (userExists.size() == 0) {
				Set<ActionPojo> defaultActionList = defaultActionList();
				for (ActionPojo aTemp : defaultActionList) {
					aManager.createAction(aTemp);
				}

				RolePojo role = defaultRole();
				roleManager.createRole(role);

				userManager.createUser(defaultUser());
				log.info("Superadmin created successfully.");
			} else {
				if (req == null) {
					req = new RequestBase();
				}
				for (UserPojo sUser : userExists) {
					req.setUser(sUser);
				}
				log.info("Superadmin Already created.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public Set<ActionPojo> defaultActionList() {
		Set<ActionPojo> defaultActionList = new HashSet<ActionPojo>();

		ActionPojo createUser = new ActionPojo();
		createUser.setActionId("createUser");
		createUser.setActionName("CREATE USER");
		createUser.setDisplayName("CREATE USER");
		createUser.setVisible(true);

		ActionPojo createRole = new ActionPojo();
		createRole.setActionId("createRole");
		createRole.setActionName("CREATE ACTION");
		createRole.setDisplayName("CREATE ACTION");
		createRole.setVisible(true);

		ActionPojo createAction = new ActionPojo();
		createAction.setActionId("createAction");
		createAction.setActionName("CREATE ACTION");
		createAction.setDisplayName("CREATE ACTION");
		createAction.setVisible(true);

		defaultActionList.add(createUser);
		defaultActionList.add(createAction);
		defaultActionList.add(createRole);

		return defaultActionList;
	}

	public RolePojo defaultRole() {
		RolePojo defaultRole = new RolePojo();

		defaultRole.setRoleId("superrole");
		defaultRole.setRoleName("SUPER ROLE");
		defaultRole.setDisplayName("SUPER ROLE");

		defaultRole.setActions(defaultActionList());

		return defaultRole;
	}

	public UserPojo defaultUser() {
		UserPojo defaultUser = new UserPojo();
		defaultUser.setEmail("superadmin");
		defaultUser.setUsername("superadmin");
		defaultUser.setPassword("superadmin");
		defaultUser.setDisplayName("SUPER ADMIN");

		Set<RolePojo> roles = Collections.singleton(defaultRole());
		defaultUser.setRoles(roles);

		return defaultUser;
	}

	public static void main(String[] args) throws Exception {

		/*Set<ActionPojo> defaultActionList = saAction.defaultActionList();
		ActionManager aManager = (ActionManager) ObjectFactory.getInstance(ObjectEnum.ACTION_MANAGER);
		for (ActionPojo aTemp : defaultActionList) {
			aManager.createAction(aTemp);
		}*/

		/*RoleManager roleManager = (RoleManager) ObjectFactory.getInstance(ObjectEnum.ROLE_MANAGER);
		RolePojo role = saAction.defaultRole();
		roleManager.createRole(role);*/

		UserManager userManager = (UserManager) ObjectFactory.getInstance(ObjectEnum.USER_MANAGER);
		// userManager.createUser(saAction.defaultUser());

		Set<UserPojo> users = userManager.readAllUsers();
		for (UserPojo uPojo : users) {
			System.out.println(uPojo);
		}

	}
}
