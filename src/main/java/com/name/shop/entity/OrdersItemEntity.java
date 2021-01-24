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
@Table(name = "orders_item")
public class OrdersItemEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public OrdersItemEntity() {
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_item_seq")
	@SequenceGenerator(sequenceName = "orders_item_seq", allocationSize = 1, name = "orders_item_seq")
	private Long id;

	@Column(name = "orders_id")
	private Long ordersId;

	@Column(name = "product_id")
	private Long prodId;

	@Column(name = "qty")
	private Long qty;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodid) {
		this.prodId = prodid;
	}

	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

}
