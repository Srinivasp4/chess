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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class Users implements Serializable {

	public Users() {}

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(sequenceName = "users_seq", allocationSize = 1, name = "users_seq")
	private Long userId;

	@Column(name = "user_name")
	private String userName;
	
//	@ManyToMany(mappedBy = "users", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//	private Set<Role> roles;
//	
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "users_role", 
//				joinColumns = { @JoinColumn(name = "user_id") }, 
//				inverseJoinColumns = { @JoinColumn(name = "role_id") })
//	private Set<Role> roles;


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
//	public Set<Role> getRoles() {
//	    if (roles == null) {
//	        roles = new HashSet<Role>();
//	    }
//	    return roles;
//	}
//
//	public void setRoles(Set<Role> roles) {
//	    this.roles = roles;
//	}

}
