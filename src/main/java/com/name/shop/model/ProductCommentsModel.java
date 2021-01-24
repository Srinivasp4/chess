
package com.name.shop.model;

import java.io.Serializable;

public class ProductCommentsModel implements Serializable {

	private static final long serialVersionUID = 1L;

	public ProductCommentsModel() {
	}

	private Long id;
	private Long productid;
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