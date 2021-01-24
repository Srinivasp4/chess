package com.name.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
import com.name.security.service.SecurityService;

@RestController
@RequestMapping("/api/v1")
public class SecurityController {

	@Autowired
	SecurityService securityService;

	// Users
	@RequestMapping(value = "/secUsers", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Users> getAllUsers() {
		return securityService.getAllUsers();
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, headers = "Accept=application/json")
	public void addUser(@RequestBody Users payload) {
		securityService.addUser(payload);
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Users getUserById(@PathVariable("userId") Long userId) {
		return securityService.getUserById(userId);
	}

	// Role
	@RequestMapping(value = "/roles", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Role> getAllRoles() {
		return securityService.getAllRoles();
	}

	@RequestMapping(value = "/role", method = RequestMethod.POST, headers = "Accept=application/json")
	public void addRole(@RequestBody Role payload) {
		securityService.addRole(payload);
	}
	
	@RequestMapping(value = "/role", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteRole(@RequestBody Role payload) {
		securityService.deleteRole(payload);
	}

	@RequestMapping(value = "/role", method = RequestMethod.PUT, headers = "Accept=application/json")
	public void updateRole(@RequestBody Role payload) {
		securityService.updateRole(payload);
	}
	
	@RequestMapping(value = "/roleByRoleId/{roleId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Role getRoleById(@PathVariable("roleId") Long roleId) {
		return securityService.getRoleById(roleId);
	}

	@RequestMapping(value = "/getAssignRolesByUserId/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Role> getAllRoleByUserId(@PathVariable("userId") Long userId) {
		return securityService.getGrantedRolesByUserId(userId);
	}

	@RequestMapping(value = "/getNotAssignRolesByUserId/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Role> getNotAssignRolesByUserId(@PathVariable("userId") Long userId) {
		return securityService.getNotAssignRolesByUserId(userId);
	}

	// User && Role
	@RequestMapping(value = "/user/role", method = RequestMethod.POST, headers = "Accept=application/json")
	public void assignUserToRole(@RequestBody UserToRole palyload) {
		securityService.assignUserToRole(palyload);
	}
	
	@RequestMapping(value = "/user/role", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteUserToRole(@RequestBody UserToRole palyload) {
		securityService.deleteUserToRole(palyload);
	}

	// Duty
	@RequestMapping(value = "/duties", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Duty> getAllDuties() {
		return securityService.getAllDuties();
	}

	@RequestMapping(value = "/duty", method = RequestMethod.POST, headers = "Accept=application/json")
	public void addDuty(@RequestBody Duty payload) {
		securityService.addDuty(payload);
	}

	@RequestMapping(value = "/duty/{dutyId}", method = RequestMethod.POST, headers = "Accept=application/json")
	public Duty getDutyById(@PathVariable("dutyId") Long dutyId) {
		return securityService.getDutyById(dutyId);
	}

	@RequestMapping(value = "/getAssignDutiesByRoleId/{roleId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Duty> getGrantedDutiesByRoleId(@PathVariable("roleId") Long roleId) {
		return securityService.getAssignDutiesByRoleId(roleId);
	}

	@RequestMapping(value = "/getNotAssignDutiesByRoleId/{roleId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Duty> getNotAssignDutiesByRoleId(@PathVariable("roleId") Long roleId) {
		return securityService.getNotAssignDutiesByRoleId(roleId);
	}

	// Role && Duty
	@RequestMapping(value = "/role/duty", method = RequestMethod.POST, headers = "Accept=application/json")
	public void assignRoleToDuty(@RequestBody RoleToDuty palyload) {
		securityService.assignRoleToDuty(palyload);
	}
	
	@RequestMapping(value = "/role/duty", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteUserToRole(@RequestBody RoleToDuty palyload) {
		securityService.deleteRoleToDuty(palyload);
	}

	// Privilege
	@RequestMapping(value = "/privileges", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Privilege> getAllPrivileges() {
		return securityService.getAllPrivileges();
	}

	@RequestMapping(value = "/getAssignPrivilegesByDutyId/{dutyId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Privilege> getAssignPrivilegesByDutyId(@PathVariable("dutyId") Long dutyId) {
		return securityService.getAssignPrivilegesByDutyId(dutyId);
	}

	@RequestMapping(value = "/getNotAssignPrivilegesByDutyId/{dutyId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Privilege> getNotAssignPrivilegesByDutyId(@PathVariable("dutyId") Long dutyId) {
		return securityService.getNotAssignPrivilegesByDutyId(dutyId);
	}

	@RequestMapping(value = "/privilege", method = RequestMethod.POST, headers = "Accept=application/json")
	public void addPrivilege(@RequestBody Privilege payload) {
		securityService.addPrivilege(payload);
	}

	@RequestMapping(value = "/privilege/{privId}", method = RequestMethod.POST, headers = "Accept=application/json")
	public Privilege getPrivilegeById(@PathVariable("privId") Long privId) {
		return securityService.getPrivilegeById(privId);
	}

	// Duty && Privilege
	@RequestMapping(value = "/duty/privilege", method = RequestMethod.POST, headers = "Accept=application/json")
	public void assignDutyToPrivilege(@RequestBody DutyToPrivilege palyload) {
		securityService.assignDutyToPrivilege(palyload);
	}
	
	@RequestMapping(value = "/duty/privilege", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteDutyPrivilege(@RequestBody DutyToPrivilege palyload) {
		securityService.deleteDutyPrivilege(palyload);
	}

	// Add resource (it can be, REST or UI or Navigation resource)
	@RequestMapping(value = "/resource", method = RequestMethod.POST, headers = "Accept=application/json")
	public void addResource(@RequestBody Resource payload) {
		if ("REST".equalsIgnoreCase(payload.getType())) {
			securityService.addRESTResource(payload);
		} else if ("NAV".equalsIgnoreCase(payload.getType())) {
			securityService.addNAVResource(payload);
		} else if ("UI".equalsIgnoreCase(payload.getType())) {
			securityService.addUITResource(payload);
		}
	}

	// Resource
	@RequestMapping(value = "/privilege/resource", method = RequestMethod.POST, headers = "Accept=application/json")
	public void assignPrivilegeToRESTResource(@RequestBody PrivilegeToRestResource palyload) {
		securityService.assignPrivilegeToRESTResource(palyload);
	}

	@RequestMapping(value = "/privilege/resource", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deletePrivilegeToRESTResource(@RequestBody PrivilegeToRestResource palyload) {
		securityService.deletePrivilegeToRESTResource(palyload);
	}
	
	@RequestMapping(value = "/getAssignResourcesByPrivId/{privId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<RESTResource> getAssignResourcesByPrivId(@PathVariable("privId") Long privId) {
		return securityService.getAssignResourcesByPrivId(privId);
	}

	@RequestMapping(value = "/getNotAssignResourcesByPrivId/{privId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<RESTResource> getNotAssignResourcesByPrivId(@PathVariable("privId") Long privId) {
		return securityService.getNotAssignResourcesByPrivId(privId);
	}

	@RequestMapping(value = "/getAllResources", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Resource> getAllResources() {
		return securityService.getAllResources();
	}

	// etc..
	@RequestMapping(value = "/privileges/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<String> getGrantedPrivileges(@PathVariable("userId") Long userId) {
		return securityService.getPrivileges(userId);
	}

	@RequestMapping(value = "/getResourcesByUserId/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Resource> getResourcesByUserId(@PathVariable("userId") Long userId) {
		return securityService.getResourcesById(userId);
	}

}
