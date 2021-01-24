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
@Table(name = "product_comments")
public class ProductCommentsEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public ProductCommentsEntity() {
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_comments_seq")
	@SequenceGenerator(sequenceName = "product_comments_seq", allocationSize = 1, name = "product_comments_seq")
	private Long id;

	@Column(name = "product_id")
	private Long productid;

	@Column(name = "comments_id")
	private Long commentsId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductid() {
		return productid;
	}

	public void setProductid(Long productid) {
		this.productid = productid;
	}

	public Long getCommentsId() {
		return commentsId;
	}

	public void setCommentsId(Long commentsId) {
		this.commentsId = commentsId;
	}

}
