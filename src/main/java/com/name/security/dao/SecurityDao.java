package com.name.security.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.RequestBody;

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

public interface SecurityDao {

	public List<Users> getAllUsers();
	public void addUser(Users payload);
	public Users getUserById(Long userId);
	public Users getUserByName(String userName);
	
	public List<Role> getAllRole();
	public void addRole(Role role);
	public void updateRole(Role role);
	public void deleteRole(Role role);
	public Role getRoleById(Long roleId);
	public List<Role> getGrantedRolesByUserId(Long userId);
	public List<Role> getNotAssignRolesByUserId(Long userId);
	public void assignUserToRole(UserToRole payload);
	public void deleteUserToRole(UserToRole payload);
	
	public List<Duty> getAllDuties();
	public void addDuty(Duty duty);
	public Duty getDutyById(Long dutyId);
	public List<Duty> getAssignDutiesByRoleId(Long roleId);
	public List<Duty> getNotAssignDutiesByRoleId(Long roleId);
	
	public void assignRoleToDuty(RoleToDuty payload);
	public void deleteRoleToDuty(RoleToDuty payload);
	
	public List<Privilege> getAllPrivileges();
	public List<Privilege> getAssignPrivilegesByDutyId(Long dutyId);
	public List<Privilege> getNotAssignPrivilegesByDutyId(Long dutyId);
	public void addPrivilege(Privilege priv);
	public Privilege getPrivilegeById(Long privId);
	
	public void assignDutyToPrivilege(DutyToPrivilege payload);
	public void deleteDutyPrivilege(DutyToPrivilege payload);
	
	public void addRESTResource(Resource payload);
	public void addNAVResource(Resource payload);
	public void addUITResource(Resource payload);	
	public void assignPrivilegeToRESTResource(PrivilegeToRestResource payload);
	public void deletePrivilegeToRESTResource(PrivilegeToRestResource payload);
	public List<RESTResource> getAssignResourcesByPrivId(Long privId);
	public List<RESTResource> getNotAssignResourcesByPrivId(Long privId);
	public List<Resource> getAllResources();
	
	public List<Resource> getResourcesById(Long userId);
	
	
	List<String> getPrivileges(Long userId);
	
	public List<String> getRESTResources(String restName,String userName);
	
	
	
	
	
	public void assignResourceToPrivilege(PrivilegeToRestResource payload);
	
}
