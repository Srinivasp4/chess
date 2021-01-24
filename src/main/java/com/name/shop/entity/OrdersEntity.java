package com.name.shop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class OrdersEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public OrdersEntity() {
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
	@SequenceGenerator(sequenceName = "orders_seq", allocationSize = 1, name = "orders_seq")
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "total")
	private Long total;

	@Column(name = "discount")
	private Long discount;

	@Column(name = "grandTotal")
	private Long grandTotal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getDiscount() {
		return discount;
	}

	public void setDiscount(Long discount) {
		this.discount = discount;
	}

	public Long getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(Long grandTotal) {
		this.grandTotal = grandTotal;
	}

}
