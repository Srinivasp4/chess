package com.name.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "privilege")
public class Privilege {

	@Id
	@Column(name = "priv_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "privilege_seq")
    @SequenceGenerator(sequenceName = "privilege_seq", allocationSize = 1, name = "privilege_seq")
	private Long privId;

	@Column(name = "priv_name")
	private String privName;

	public Privilege() {
	}

	public Long getPrivId() {
		return privId;
	}

	public void setPrivId(Long privId) {
		this.privId = privId;
	}

	public String getPrivName() {
		return privName;
	}

	public void setPrivName(String privName) {
		this.privName = privName;
	}

}
