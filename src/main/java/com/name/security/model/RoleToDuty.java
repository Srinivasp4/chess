package com.name.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "role_duty")
public class RoleToDuty {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_duty_seq")
	@SequenceGenerator(sequenceName = "role_duty_seq", allocationSize = 1, name = "role_duty_seq")
	private Long id;

	@Column(name = "role_id")
	private Long roleId;

	@Column(name = "duty_id")
	private Long dutyId;

	public RoleToDuty() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getDutyId() {
		return dutyId;
	}

	public void setDutyId(Long dutyId) {
		this.dutyId = dutyId;
	}

}
