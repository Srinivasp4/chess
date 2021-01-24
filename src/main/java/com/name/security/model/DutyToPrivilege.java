package com.name.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "duty_privilege")
public class DutyToPrivilege {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "duty_privilege_seq")
	@SequenceGenerator(sequenceName = "duty_privilege_seq", allocationSize = 1, name = "duty_privilege_seq")
	private Long id;

	@Column(name = "duty_id")
	private Long dutyId;

	@Column(name = "priv_id")
	private Long privId;

	public DutyToPrivilege() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDutyId() {
		return dutyId;
	}

	public void setDutyId(Long dutyId) {
		this.dutyId = dutyId;
	}

	public Long getPrivId() {
		return privId;
	}

	public void setPrivId(Long privId) {
		this.privId = privId;
	}

}
