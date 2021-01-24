package com.name.security.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.name.security.dao.SecurityDao;
import com.name.security.model.Duty;
import com.name.security.model.DutyToPrivilege;
import com.name.security.model.Privilege;
import com.name.security.model.PrivilegeToRestResource;
import com.name.security.model.RESTResource;
import com.name.security.model.Resource;
import com.name.security.model.Role;
import com.name.security.model.RoleToDuty;
import com.name.security.model.UserToRole;
import com.name.security.model.Users;

@Service("securityService")
public class SecurityService {

	@Autowired
	SecurityDao securityDao;

	// Users
	@Transactional
	public List<Users> getAllUsers() {
		return securityDao.getAllUsers();
	}

	@Transactional
	public void addUser(Users payload) {
		securityDao.addUser(payload);
	}

	@Transactional
	public Users getUserById(Long userId) {
		return securityDao.getUserById(userId);
	}
	
	@Transactional
	public Users getUserByName(String userName) {
		return securityDao.getUserByName(userName);
	}

	// Role
	@Transactional
	public List<Role> getAllRoles() {
		return securityDao.getAllRole();
	}

	@Transactional
	public void addRole(Role role) {
		securityDao.addRole(role);
	}
	
	@Transactional
	public void updateRole(Role role) {
		securityDao.updateRole(role);
	}

	@Transactional
	public void deleteRole(Role role) {
		securityDao.deleteRole(role);
	}

	@Transactional
	public Role getRoleById(Long roleId) {
		return securityDao.getRoleById(roleId);
	}

	@Transactional
	public List<Role> getGrantedRolesByUserId(Long userId) {
		return securityDao.getGrantedRolesByUserId(userId);
	}

	@Transactional
	public List<Role> getNotAssignRolesByUserId(Long userId) {
		return securityDao.getNotAssignRolesByUserId(userId);
	}

	// Users to Role
	@Transactional
	public void assignUserToRole(UserToRole payload) {
		securityDao.assignUserToRole(payload);
	}
	
	@Transactional
	public void deleteUserToRole(UserToRole payload) {
		securityDao.deleteUserToRole(payload);
	}

	// Duty
	@Transactional
	public List<Duty> getAllDuties() {
		return securityDao.getAllDuties();
	}

	@Transactional
	public void addDuty(Duty duty) {
		securityDao.addDuty(duty);
	}

	@Transactional
	public Duty getDutyById(Long dutyId) {
		return securityDao.getDutyById(dutyId);
	}

	@Transactional
	public List<Duty> getAssignDutiesByRoleId(Long roleId) {
		return securityDao.getAssignDutiesByRoleId(roleId);
	}

	@Transactional
	public List<Duty> getNotAssignDutiesByRoleId(Long roleId) {
		return securityDao.getNotAssignDutiesByRoleId(roleId);
	}

	// Role to Duty
	@Transactional
	public void assignRoleToDuty(RoleToDuty payload) {
		securityDao.assignRoleToDuty(payload);
	}
	
	@Transactional
	public void deleteRoleToDuty(RoleToDuty payload) {
		securityDao.deleteRoleToDuty(payload);
	}

	// Privilege
	@Transactional
	public List<Privilege> getAllPrivileges() {
		return securityDao.getAllPrivileges();
	}

	@Transactional
	public List<Privilege> getAssignPrivilegesByDutyId(Long dutyId) {
		return securityDao.getAssignPrivilegesByDutyId(dutyId);
	}

	@Transactional
	public List<Privilege> getNotAssignPrivilegesByDutyId(Long dutyId) {
		return securityDao.getNotAssignPrivilegesByDutyId(dutyId);
	}

	@Transactional
	public void addPrivilege(Privilege priv) {
		securityDao.addPrivilege(priv);
	}

	@Transactional
	public Privilege getPrivilegeById(Long privId) {
		return securityDao.getPrivilegeById(privId);
	}

	// Duty to Privilege
	@Transactional
	public void assignDutyToPrivilege(DutyToPrivilege payload) {
		securityDao.assignDutyToPrivilege(payload);
	}
	
	@Transactional
	public void deleteDutyPrivilege(DutyToPrivilege payload) {
		securityDao.deleteDutyPrivilege(payload);
	}

	// Rest resource
	@Transactional
	public void addRESTResource(Resource payload) {
		securityDao.addRESTResource(payload);
	}

	@Transactional
	public void addNAVResource(Resource payload) {
		securityDao.addNAVResource(payload);
	}

	@Transactional
	public void addUITResource(Resource payload) {
		securityDao.addUITResource(payload);
	}
	
	@Transactional
	public void assignPrivilegeToRESTResource(PrivilegeToRestResource payload) {
		securityDao.assignPrivilegeToRESTResource(payload);
	}

	@Transactional
	public void deletePrivilegeToRESTResource(PrivilegeToRestResource payload) {
		securityDao.deletePrivilegeToRESTResource(payload);
	}

	@Transactional
	public List<RESTResource> getAssignResourcesByPrivId(Long privId) {
		return securityDao.getAssignResourcesByPrivId(privId);
	}
	
	@Transactional
	public List<RESTResource> getNotAssignResourcesByPrivId(Long privId) {
		return securityDao.getNotAssignResourcesByPrivId(privId);
	}

	@Transactional
	public void assignResourceToPrivilege(PrivilegeToRestResource palyload) {
		securityDao.assignResourceToPrivilege(palyload);
	}
	
	@Transactional
	public List<Resource> getAllResources() {
		return securityDao.getAllResources();
	}
	
	
	@Transactional
	public List<String> getPrivileges(Long userId) {
		return securityDao.getPrivileges(userId);
	}

	@Transactional
	public List<String> checkRESTAccess(String restName, String userName) {
		return securityDao.getRESTResources(restName, userName);
	}

	@Transactional
	public List<Resource> getResourcesById(Long userId) {
		return securityDao.getResourcesById(userId);
	}

}
