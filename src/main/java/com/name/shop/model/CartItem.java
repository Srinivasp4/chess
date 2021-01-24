package com.name.shop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cart_item")
public class CartItem implements Serializable {

	private static final long serialVersionUID = 1L;

	public CartItem() {
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_item_seq")
	@SequenceGenerator(sequenceName = "cart_item_seq", allocationSize = 1, name = "cart_item_seq")
	private Long id;

	@Column(name = "cart_id")
	private Long cartId;

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

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
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
