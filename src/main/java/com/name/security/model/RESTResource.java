package com.name.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "restresource")
public class RESTResource {

	@Id
	@Column(name = "resource_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restresource_seq")
	@SequenceGenerator(sequenceName = "restresource_seq", allocationSize = 1, name = "restresource_seq")
	private Long resourceId;

	@Column(name = "resource_name")
	private String resourceName;

	@Column(name = "type")
	private String type;

	@Column(name = "actions")
	private String actions;

	public RESTResource() {
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

}
