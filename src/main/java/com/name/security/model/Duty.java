package com.name.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "duty")
public class Duty {

	@Id
	@Column(name = "duty_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "duty_seq")
    @SequenceGenerator(sequenceName = "duty_seq", allocationSize = 1, name = "duty_seq")
	private Long dutyId;

	@Column(name = "duty_name")
	private String dutyName;

	public Duty() {
	}

	public Long getDutyId() {
		return dutyId;
	}

	public void setDutyId(Long dutyId) {
		this.dutyId = dutyId;
	}

	public String getDutyName() {
		return dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

}
