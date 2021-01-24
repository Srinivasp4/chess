package com.name.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "privilege_restresource")
public class PrivilegeToRestResource {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "privilege_restresource_seq")
    @SequenceGenerator(sequenceName = "privilege_restresource_seq", allocationSize = 1, name = "privilege_restresource_seq")
	private Long id;

	@Column(name = "priv_id")
	private Long privId;

	@Column(name = "resource_id")
	private Long resourceId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPrivId() {
		return privId;
	}

	public void setPrivId(Long privId) {
		this.privId = privId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	
}
