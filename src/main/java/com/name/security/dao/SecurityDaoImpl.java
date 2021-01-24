package com.name.security.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.name.security.model.Duty;
import com.name.security.model.DutyToPrivilege;
import com.name.security.model.NAVResource;
import com.name.security.model.Privilege;
import com.name.security.model.PrivilegeToRestResource;
import com.name.security.model.RESTResource;
import com.name.security.model.Resource;
import com.name.security.model.Role;
import com.name.security.model.RoleToDuty;
import com.name.security.model.UIResource;
import com.name.security.model.UserToRole;
import com.name.security.model.Users;

@Repository
public class SecurityDaoImpl implements SecurityDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	// ::::::::::::::: Users :::::::::::::::::::::::::::::
	public List<Users> getAllUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Users").list();
	}

	public void addUser(Users payload) {
		Session session = this.sessionFactory.getCurrentSession();
		Users user = new Users();
		user.setUserName(payload.getUserName());
		session.saveOrUpdate(user);
	}

	public Users getUserById(Long userId) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(Users.class, userId);
	}
	
	public Users getUserByName(String userName) {
		Session session = this.sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("select user_id, user_name from users where user_name='" + userName+"'");
		query.addEntity(Users.class);
		return (Users) query.uniqueResult();
	}

	// ::::::::::::::: Roles :::::::::::::::::::::::::::::
	public List<Role> getAllRole() {
		Session session = this.sessionFactory.getCurrentSession();
		// List<Role> roleList = session.createQuery("from Role").list();
		List<Role> roleList = session.createQuery("from Role").list();
		return roleList;
	}

	public void addRole(Role payload) {
		Session session = this.sessionFactory.getCurrentSession();
		Role role = new Role();
		role.setRoleName(payload.getRoleName());
		session.saveOrUpdate(role);
	}
	
	public void updateRole(Role payload) {
		Session session = this.sessionFactory.getCurrentSession();
		Role role = new Role();
		role.setRoleId(payload.getRoleId());
		role.setRoleName(payload.getRoleName());
		session.update(role);
	}
	
	public void deleteRole(Role payload) {
		Session session = this.sessionFactory.getCurrentSession();
		Role role = new Role();
		role.setRoleId(payload.getRoleId());
		// role.setRoleName(payload.getRoleName());
		session.delete(role);
	}

	public Role getRoleById(Long roleId) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(Role.class, roleId);
	}

	public List<Role> getGrantedRolesByUserId(Long userId){
		Session session = this.sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("select \r\n" 
				+ "distinct r.role_id, r.role_name \r\n"
				+ "from users u\r\n" 
				+ "inner join users_role u_r on u_r.user_id = u.user_id\r\n"
				+ "inner join role r on r.role_id = u_r.role_id\r\n" + "where u.user_id=" + userId);
		
		query.addEntity(Role.class);
		return query.list();
	}
	
	public List<Role> getNotAssignRolesByUserId(Long userId){
		Session session = this.sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("select distinct r.role_id, r.role_name from role r where r.role_id not in (select distinct r.role_id from users u inner join users_role u_r on u_r.user_id = u.user_id inner join role r on r.role_id=u_r.role_id where u.user_id = " + userId+ ")");
		query.addEntity(Role.class);
		return query.list();
	}
	
	// ::::::::::::::: Users && Roles :::::::::::::::::::::::::::::
	public void assignUserToRole(UserToRole payload) {
		Session session = this.sessionFactory.getCurrentSession();
		UserToRole u_r = new UserToRole();
		u_r.setUserId(payload.getUserId());
		u_r.setRoleId(payload.getRoleId());
		session.saveOrUpdate(u_r);
	}
	
	
	public void deleteUserToRole(UserToRole payload) {
		Session session = this.sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(
				"delete from users_role where user_id=" + payload.getUserId() + " and role_id=" + payload.getRoleId());
		query.executeUpdate();
	}

	// ::::::::::::::: Duty ::::::::::::::::::::::::::::::::::::::::
	public List<Duty> getAllDuties() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Duty").list();

//		SQLQuery query = session.createSQLQuery("select \r\n" + "distinct d.duty_id, d.duty_name \r\n"
//				+ "from users u\r\n" + "inner join users_role u_r on u_r.user_id = u.user_id\r\n"
//				+ "inner join role r on r.role_id = u_r.role_id\r\n"
//				+ "inner join role_duty r_d on r_d.role_id = r.role_id\r\n"
//				+ "inner join duty d on d.duty_id = r_d.duty_id\r\n"
//				+ "inner join duty_privilege d_p on d_p.duty_id = d.duty_id\r\n" + "where r.role_id=" + roleId);
//
//		query.addEntity(Duty.class);
//		return query.list();
	}

	public void addDuty(Duty payload) {
		Session session = this.sessionFactory.getCurrentSession();
		Duty duty = new Duty();
		duty.setDutyName(payload.getDutyName());
		session.saveOrUpdate(duty);
	}

	public Duty getDutyById(Long dutyId) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(Duty.class, dutyId);
	}
	
	public List<Duty> getAssignDutiesByRoleId(Long roleId) {
		Session session = this.sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("select \r\n" + "distinct d.duty_id, d.duty_name \r\n"
				+ "from users u\r\n" + "inner join users_role u_r on u_r.user_id = u.user_id\r\n"
				+ "inner join role r on r.role_id = u_r.role_id\r\n"
				+ "inner join role_duty r_d on r_d.role_id = r.role_id\r\n"
				+ "inner join duty d on d.duty_id = r_d.duty_id\r\n" + "where r.role_id=" + roleId);

		query.addEntity(Duty.class);
		return query.list();
	}

	public List<Duty> getNotAssignDutiesByRoleId(Long roleId) {
		Session session = this.sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("select \r\n" + 
				"distinct d.duty_id,\r\n" + 
				"d.duty_name \r\n" + 
				"from duty d\r\n" + 
				"where d.duty_id not in (\r\n" + 
			"    select distinct d.duty_id \r\n" + 
				"    from users u \r\n" + 
				"    inner join users_role u_r on u_r.user_id = u.user_id \r\n" + 
				"    inner join role r on r.role_id=u_r.role_id \r\n" + 
				"    inner join role_duty r_d on r_d.role_id = r.role_id\r\n" + 
				"    inner join duty d on d.duty_id = r_d.duty_id\r\n" + 
				"    where r.role_id = "+roleId+"\r\n" + 
				")");

		query.addEntity(Duty.class);
		return query.list();
	}
	
	
	// ::::::::::::::: Role && Duty ::::::::::::::::::::::::::::::::::::::::
	public void assignRoleToDuty(RoleToDuty payload) {
		Session session = this.sessionFactory.getCurrentSession();
		RoleToDuty r_d = new RoleToDuty();
		r_d.setRoleId(payload.getRoleId());
		r_d.setDutyId(payload.getDutyId());
		session.saveOrUpdate(r_d);
	}
	
	public void deleteRoleToDuty(RoleToDuty payload) {
		Session session = this.sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(
				"delete from role_duty where role_id=" + payload.getRoleId()+ " and duty_id=" + payload.getDutyId());
		query.executeUpdate();
	}

	// ::::::::::::::: Privilege ::::::::::::::::::::::::::::::::::::::::
	public List<Privilege> getAllPrivileges() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Privilege").list();
	}
	
	public List<Privilege> getAssignPrivilegesByDutyId(Long dutyId) {
		Session session = this.sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("select \r\n" + "distinct p.priv_id, p.priv_name\r\n"
				+ "from users u\r\n" + "inner join users_role u_r on u_r.user_id = u.user_id\r\n"
				+ "inner join role r on r.role_id = u_r.role_id\r\n"
				+ "inner join role_duty r_d on r_d.role_id = r.role_id\r\n"
				+ "inner join duty d on d.duty_id = r_d.duty_id\r\n"
				+ "inner join duty_privilege d_p on d_p.duty_id = d.duty_id\r\n"
				+ "inner join privilege p on p.priv_id = d_p.priv_id\r\n" + "where d.duty_id=" + dutyId);

		query.addEntity(Privilege.class);
		return query.list();
	}
	
	public List<Privilege> getNotAssignPrivilegesByDutyId(Long dutyId) {
		Session session = this.sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("\r\n" + 
				"select distinct p.priv_id, p.priv_name\r\n" + 
				"from privilege p\r\n" + 
				"where p.priv_id not in (\r\n" + 
				"select \r\n" + 
				"distinct p.priv_id\r\n" + 
				"from users u\r\n" + 
				"inner join users_role u_r on u_r.user_id = u.user_id\r\n" + 
				"inner join role r on r.role_id = u_r.role_id\r\n" + 
				"inner join role_duty r_d on r_d.role_id = r.role_id\r\n" + 
				"inner join duty d on d.duty_id = r_d.duty_id\r\n" + 
				"inner join duty_privilege d_p on d_p.duty_id = d.duty_id\r\n" + 
				"inner join privilege p on p.priv_id = d_p.priv_id\r\n" + 
				"where d.duty_id = "+dutyId+"\r\n" + 
				")");

		query.addEntity(Privilege.class);
		return query.list();
	}

	public void addPrivilege(Privilege payload) {
		Session session = this.sessionFactory.getCurrentSession();
		Privilege priv = new Privilege();
		priv.setPrivName(payload.getPrivName());
		session.saveOrUpdate(priv);
	}

	public Privilege getPrivilegeById(Long privId) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(Privilege.class, privId);
	}

	// ::::::::::::::: Duty && Privilege ::::::::::::::::::::::::::::::::::::::::
	public void assignDutyToPrivilege(DutyToPrivilege payload) {
		Session session = this.sessionFactory.getCurrentSession();
		DutyToPrivilege d_p = new DutyToPrivilege();
		d_p.setDutyId(payload.getDutyId());
		d_p.setPrivId(payload.getPrivId());
		session.saveOrUpdate(d_p);
	}
	
	public void deleteDutyPrivilege(DutyToPrivilege payload) {
		Session session = this.sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(
				"delete from duty_privilege where duty_id=" + payload.getDutyId()+ " and priv_id=" + payload.getPrivId());
		query.executeUpdate();
	}

	// ::::::::::::::: REST Resource ::::::::::::::::::::::::::::::::::::::::
	
	public void addRESTResource(Resource payload) {
		Session session = this.sessionFactory.getCurrentSession();
		RESTResource res = new RESTResource();
		res.setResourceName(payload.getResourceName());
		res.setType(payload.getType());
		res.setActions(payload.getActions());
		session.saveOrUpdate(res);
	}
	
	public void addNAVResource(Resource payload) {
		Session session = this.sessionFactory.getCurrentSession();
		NAVResource nav = new NAVResource();
		nav.setResourceName(payload.getResourceName());
		nav.setType(payload.getType());
		nav.setActions(payload.getActions());
		session.saveOrUpdate(nav);
	}
	
	public void addUITResource(Resource payload) {
		Session session = this.sessionFactory.getCurrentSession();
		UIResource ui = new UIResource();
		ui.setResourceName(payload.getResourceName());
		ui.setType(payload.getType());
		ui.setActions(payload.getActions());
		session.saveOrUpdate(ui);
	}
	
	public void assignPrivilegeToRESTResource(PrivilegeToRestResource payload) {
		Session session = this.sessionFactory.getCurrentSession();
		PrivilegeToRestResource p_r = new PrivilegeToRestResource();
		p_r.setPrivId(payload.getPrivId());
		p_r.setResourceId(payload.getResourceId());
		session.saveOrUpdate(p_r);
	}
	
	public void deletePrivilegeToRESTResource(PrivilegeToRestResource payload) {
		Session session = this.sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(
				"delete from privilege_restresource where priv_id=" + payload.getPrivId()+" and resource_id="+payload.getResourceId());
		query.executeUpdate();
	}
	
	public List<Resource> getAllResources() {
		Session session = this.sessionFactory.getCurrentSession();
		List<RESTResource> restResources = session.createQuery("from RESTResource").list();
		List<Resource> result = new ArrayList();
		for (RESTResource rest : restResources) {
			Resource r = new Resource();
			r.setResourceId(rest.getResourceId());
			r.setResourceName(rest.getResourceName());
			r.setActions(rest.getActions());
			r.setType(rest.getType());
			result.add(r);
		}

		return result;
	}
	
	public List<RESTResource> getAssignResourcesByPrivId(Long privId) {
		Session session = this.sessionFactory.getCurrentSession();

		List<RESTResource> restResources = session
				.createSQLQuery("select \r\n" + "distinct res.resource_id, res.resource_name, res.actions, res.type\r\n" + "from users u\r\n"
						+ "inner join users_role u_r on u_r.user_id = u.user_id\r\n"
						+ "inner join role r on r.role_id = u_r.role_id\r\n"
						+ "inner join role_duty r_d on r_d.role_id = r.role_id\r\n"
						+ "inner join duty d on d.duty_id = r_d.duty_id\r\n"
						+ "inner join duty_privilege d_p on d_p.duty_id = d.duty_id\r\n"
						+ "inner join privilege p on p.priv_id = d_p.priv_id\r\n"
						+ "inner join privilege_restresource p_r on p_r.priv_id = p.priv_id\r\n"
						+ "inner join restresource res on res.resource_id = p_r.resource_id\r\n" + "where p.priv_id ="
						+ privId + "\r\n")
				.addEntity(RESTResource.class).list();

		return restResources;
	}
	
	
	public List<RESTResource> getNotAssignResourcesByPrivId(Long privId) {
		Session session = this.sessionFactory.getCurrentSession();

		List<RESTResource> restResources = session
				.createSQLQuery("select \r\n" + "distinct res.resource_id, res.resource_name, res.actions, res.type\r\n" + "from users u\r\n"
						+ "inner join users_role u_r on u_r.user_id = u.user_id\r\n"
						+ "inner join role r on r.role_id = u_r.role_id\r\n"
						+ "inner join role_duty r_d on r_d.role_id = r.role_id\r\n"
						+ "inner join duty d on d.duty_id = r_d.duty_id\r\n"
						+ "inner join duty_privilege d_p on d_p.duty_id = d.duty_id\r\n"
						+ "inner join privilege p on p.priv_id = d_p.priv_id\r\n"
						+ "inner join privilege_restresource p_r on p_r.priv_id = p.priv_id\r\n"
						+ "inner join restresource res on res.resource_id = p_r.resource_id\r\n" + "where p.priv_id <>"
						+ privId + "\r\n")
				.addEntity(RESTResource.class).list();

		return restResources;
	}
//	
//	public List<Resource> getNotAssignResourcesByPrivId(Long privId) {
//		Session session = this.sessionFactory.getCurrentSession();
//
//		List<Resource> resources = new ArrayList<Resource>();
//		// rest resources
//		List<Resource> restResources = session
//				.createSQLQuery("select \r\n" + "distinct res.resource_id, res.resource_name\r\n" + "from users u\r\n"
//						+ "inner join users_role u_r on u_r.user_id = u.user_id\r\n"
//						+ "inner join role r on r.role_id = u_r.role_id\r\n"
//						+ "inner join role_duty r_d on r_d.role_id = r.role_id\r\n"
//						+ "inner join duty d on d.duty_id = r_d.duty_id\r\n"
//						+ "inner join duty_privilege d_p on d_p.duty_id = d.duty_id\r\n"
//						+ "inner join privilege p on p.priv_id = d_p.priv_id\r\n"
//						+ "inner join privilege_restresource p_r on p_r.priv_id = p.priv_id\r\n"
//						+ "inner join restresource res on res.resource_id = p_r.resource_id\r\n" + "where p.priv_id <> "
//						+ privId + "\r\n")
//				.addEntity(Resource.class).list();
//
//		// UI resources
//		List<Resource> uiResources = session
//				.createSQLQuery("select \r\n" + "distinct res.resource_id, res.resource_name\r\n" + "from users u\r\n"
//						+ "inner join users_role u_r on u_r.user_id = u.user_id\r\n"
//						+ "inner join role r on r.role_id = u_r.role_id\r\n"
//						+ "inner join role_duty r_d on r_d.role_id = r.role_id\r\n"
//						+ "inner join duty d on d.duty_id = r_d.duty_id\r\n"
//						+ "inner join duty_privilege d_p on d_p.duty_id = d.duty_id\r\n"
//						+ "inner join privilege p on p.priv_id = d_p.priv_id\r\n"
//						+ "inner join privilege_uiresource p_r on p_r.priv_id = p.priv_id\r\n"
//						+ "inner join uiresource res on res.resource_name = p_r.resource_name\r\n"
//						+ "where p.priv_id <> " + privId + "\r\n")
//				.addEntity(Resource.class).list();
//
//		// Navigation resources
//		List<Resource> navResources = session
//				.createSQLQuery("select \r\n" + "distinct res.resource_id, res.resource_name\r\n" + "from users u\r\n"
//						+ "inner join users_role u_r on u_r.user_id = u.user_id\r\n"
//						+ "inner join role r on r.role_id = u_r.role_id\r\n"
//						+ "inner join role_duty r_d on r_d.role_id = r.role_id\r\n"
//						+ "inner join duty d on d.duty_id = r_d.duty_id\r\n"
//						+ "inner join duty_privilege d_p on d_p.duty_id = d.duty_id\r\n"
//						+ "inner join privilege p on p.priv_id = d_p.priv_id\r\n"
//						+ "inner join privilege_navresource p_r on p_r.priv_id = p.priv_id\r\n"
//						+ "inner join navresource res on res.resource_id = p_r.resource_id\r\n" + "where p.priv_id <> "
//						+ privId + "\r\n")
//				.addEntity(Resource.class).list();
//
//		resources.addAll(restResources);
//		resources.addAll(uiResources);
//		resources.addAll(navResources);
//		return resources;
//	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getPrivileges(Long userId) {
		Session session = this.sessionFactory.getCurrentSession();

		List<String> privilegeList = new ArrayList<String>();
		// rest resources
		List<String> restResources = session.createSQLQuery("select \r\n" + "distinct p.priv_name\r\n"
				+ "from users u\r\n" + "inner join users_role u_r on u_r.user_id = u.user_id\r\n"
				+ "inner join role r on r.role_id = u_r.role_id\r\n"
				+ "inner join role_duty r_d on r_d.role_id = r.role_id\r\n"
				+ "inner join duty d on d.duty_id = r_d.duty_id\r\n"
				+ "inner join duty_privilege d_p on d_p.duty_id = d.duty_id\r\n"
				+ "inner join privilege p on p.priv_id = d_p.priv_id\r\n"
				+ "inner join privilege_restresource p_r on p_r.priv_id = p.priv_id\r\n"
				+ "inner join restresource res on res.resource_id = p_r.resource_id\r\n"
				+ "where u.user_name = "+userId+"\r\n").list();

		// UI resources
		List<String> uiResources = session.createSQLQuery("select \r\n" + "distinct p.priv_name\r\n"
				+ "from users u\r\n" + "inner join users_role u_r on u_r.user_id = u.user_id\r\n"
				+ "inner join role r on r.role_id = u_r.role_id\r\n"
				+ "inner join role_duty r_d on r_d.role_id = r.role_id\r\n"
				+ "inner join duty d on d.duty_id = r_d.duty_id\r\n"
				+ "inner join duty_privilege d_p on d_p.duty_id = d.duty_id\r\n"
				+ "inner join privilege p on p.priv_id = d_p.priv_id\r\n"
				+ "inner join privilege_uiresource p_r on p_r.priv_id = p.priv_id\r\n"
				+ "inner join uiresource res on res.resource_name = p_r.resource_name\r\n"
				+ "where u.user_name = "+userId+"\r\n").list();

		// Navigation resources
		List<String> navResources = session.createSQLQuery("select \r\n" + "distinct p.priv_name\r\n"
				+ "from users u\r\n" + "inner join users_role u_r on u_r.user_id = u.user_id\r\n"
				+ "inner join role r on r.role_id = u_r.role_id\r\n"
				+ "inner join role_duty r_d on r_d.role_id = r.role_id\r\n"
				+ "inner join duty d on d.duty_id = r_d.duty_id\r\n"
				+ "inner join duty_privilege d_p on d_p.duty_id = d.duty_id\r\n"
				+ "inner join privilege p on p.priv_id = d_p.priv_id\r\n"
				+ "inner join privilege_navresource p_r on p_r.priv_id = p.priv_id\r\n"
				+ "inner join navresource res on res.resource_id = p_r.resource_id\r\n"
				+ "where u.user_name = "+userId+"\r\n").list();

		privilegeList.addAll(restResources);
		privilegeList.addAll(uiResources);
		privilegeList.addAll(navResources);
		return privilegeList;
	}

	public List<String> getRESTResources(String restName, String userName) {

		Session session = this.sessionFactory.getCurrentSession();
		List<String> restResources = session.createSQLQuery("select \r\n" + "distinct res.resource_name \r\n"
				+ "from users u\r\n" + "inner join users_role u_r on u_r.user_id = u.user_id\r\n"
				+ "inner join role r on r.role_id = u_r.role_id\r\n"
				+ "inner join role_duty r_d on r_d.role_id = r.role_id\r\n"
				+ "inner join duty d on d.duty_id = r_d.duty_id\r\n"
				+ "inner join duty_privilege d_p on d_p.duty_id = d.duty_id\r\n"
				+ "inner join privilege p on p.priv_id = d_p.priv_id\r\n"
				+ "inner join privilege_restresource p_r on p_r.priv_id = p.priv_id\r\n"
				+ "inner join restresource res on res.resource_id = p_r.resource_id\r\n"
				+ "where u.user_name = '"+userName+"'\r\n").list();

		return restResources;

	}

	public List<Resource> getResourcesById(Long privId) {
		Session session = this.sessionFactory.getCurrentSession();

		List<Object[]> resources = new ArrayList<Object[]>();
		// rest resources
		List<Object[]> restResources = session
				.createSQLQuery("select \r\n" + "distinct res.resource_id, res.resource_name\r\n" + "from users u\r\n"
						+ "inner join users_role u_r on u_r.user_id = u.user_id\r\n"
						+ "inner join role r on r.role_id = u_r.role_id\r\n"
						+ "inner join role_duty r_d on r_d.role_id = r.role_id\r\n"
						+ "inner join duty d on d.duty_id = r_d.duty_id\r\n"
						+ "inner join duty_privilege d_p on d_p.duty_id = d.duty_id\r\n"
						+ "inner join privilege p on p.priv_id = d_p.priv_id\r\n"
						+ "inner join privilege_restresource p_r on p_r.priv_id = p.priv_id\r\n"
						+ "inner join restresource res on res.resource_id = p_r.resource_id\r\n" + "where p.priv_id = "
						+ privId + "\r\n")
				.list();

		resources.addAll(restResources);

		// UI resources
		List<Object[]> uiResources = session
				.createSQLQuery("select \r\n" + "distinct res.resource_id, res.resource_name\r\n" + "from users u\r\n"
						+ "inner join users_role u_r on u_r.user_id = u.user_id\r\n"
						+ "inner join role r on r.role_id = u_r.role_id\r\n"
						+ "inner join role_duty r_d on r_d.role_id = r.role_id\r\n"
						+ "inner join duty d on d.duty_id = r_d.duty_id\r\n"
						+ "inner join duty_privilege d_p on d_p.duty_id = d.duty_id\r\n"
						+ "inner join privilege p on p.priv_id = d_p.priv_id\r\n"
						+ "inner join privilege_uiresource p_r on p_r.priv_id = p.priv_id\r\n"
						+ "inner join uiresource res on res.resource_id = p_r.resource_id\r\n"
						+ "where p.priv_id = " + privId + "\r\n")
				.list();

		resources.addAll(uiResources);

		// Navigation resources
		List<Object[]> navResources = session
				.createSQLQuery("select \r\n" + "distinct res.resource_id, res.resource_name\r\n" + "from users u\r\n"
						+ "inner join users_role u_r on u_r.user_id = u.user_id\r\n"
						+ "inner join role r on r.role_id = u_r.role_id\r\n"
						+ "inner join role_duty r_d on r_d.role_id = r.role_id\r\n"
						+ "inner join duty d on d.duty_id = r_d.duty_id\r\n"
						+ "inner join duty_privilege d_p on d_p.duty_id = d.duty_id\r\n"
						+ "inner join privilege p on p.priv_id = d_p.priv_id\r\n"
						+ "inner join privilege_navresource p_r on p_r.priv_id = p.priv_id\r\n"
						+ "inner join navresource res on res.resource_id = p_r.resource_id\r\n" + "where p.priv_id = "
						+ privId + "\r\n")
				.list();

		resources.addAll(navResources);
		List<Resource> resourceList = new ArrayList();
		for (Object[] row : resources) {
			Resource res = new Resource();
			res.setResourceId(Long.parseLong(row[0].toString()));
			res.setResourceName(row[1].toString());

			resourceList.add(res);
		}

		return resourceList;
	}


	public void assignResourceToPrivilege(PrivilegeToRestResource palyload) {

		Session session = this.sessionFactory.getCurrentSession();
		PrivilegeToRestResource privToRes = new PrivilegeToRestResource();
		// privToRes.setId(1L);
		privToRes.setPrivId(palyload.getPrivId());
		privToRes.setResourceId(palyload.getResourceId());
		session.saveOrUpdate(privToRes);

	}
	
}
