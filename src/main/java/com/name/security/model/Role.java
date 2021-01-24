package com.name.security.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQ")
    @SequenceGenerator(sequenceName = "role_seq", allocationSize = 1, name = "ROLE_SEQ")
	@Column(name = "role_id")
	private Long roleId;

	@Column(name = "role_name")
	private String roleName;

	/*
	 * This typically happens when you are returning an object via @Responsebody (or
	 * in your case response body by way of @RestController) and an object is being
	 * serialized but has children in a LAZY collection that have not been
	 * referenced. By the time you are in your controller there is no longer a
	 * transaction active that will facilitate them being fetched (like the one your
	 * started in your @Service). You can either make your fetch strategy EAGER,
	 * bring in your collections by reference while still in your transaction or
	 * make your LAZY collections JSON Transient.
	 */
//	@JsonIgnore
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "users_role",
//	        joinColumns = @JoinColumn(name = "fk_role"),
//	        inverseJoinColumns = @JoinColumn(name = "fk_users")
//	        //uniqueConstraints = {@UniqueConstraint(columnNames = {"role_id", "user_id"})}
//	)
//	private Set<Users> users;
	
//	@ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
//	private Set<Users> users;

	public Role() {

	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
//	public Set<Users> getUsers() {
//	    if (users == null) {
//	        users = new HashSet<Users>();
//	    }
//	    return users;
//	}
//
//	public void setUsers(Set<Users> users) {
//	    this.users = users;
//	}

}
