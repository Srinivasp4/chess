package com.name.shop.model;

import java.io.Serializable;

public class CommentsModel implements Serializable {

	private static final long serialVersionUID = 1L;

	public CommentsModel() {
	}

	private Long id;
	private String title;
	private String content;
	private int rating;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}