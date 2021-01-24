package com.name.shop.mode;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	public Product() {}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(sequenceName = "product_seq", allocationSize = 1, name = "product_seq")
	private Long id;

	@Column(name = "title")
	private String title;
	
	@Column(name = "price")
	private Long price;
	
	@Column(name = "size")
	private Long size;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "color")
	private String color;
	    
//	@Column(name = "metaTitle")
//	private String metaTitle;
//	    
//	@Column(name = "slug")
//	private String slug;
	
//	@Column(name = "image_url")
//	private String imageUrl;
	    
//	@Column(name = "type")
//	private String type;
//	    
//	@Column(name = "sku")
//	private String sku;
//	    
//	@Column(name = "price")
//	private String price;
//	    
	@Column(name = "discount")
	private String discount;
//	    
//	@Column(name = "quantity")
//	private String quantity;
//	    
//	@Column(name = "shop")
//	private String shop;
//	    
//	@Column(name = "createdAt")
//	private String createdAt;
//	    
//	@Column(name = "updatedAt")
//	private String updatedAt;
//	    
//	@Column(name = "publishedAt")
//	private String publishedAt;
//	    
//	@Column(name = "startsAt")
//	private String startsAt;
//	    
//	@Column(name = "endsAt")
//	private String endsAt;
//	    
//	@Column(name = "content")
//	private String content;


	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

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

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
//	public String getMetaTitle() {
//		return metaTitle;
//	}
//
//	public void setMetaTitle(String metaTitle) {
//		this.metaTitle = metaTitle;
//	}
//
//	public String getSlug() {
//		return slug;
//	}
//
//	public void setSlug(String slug) {
//		this.slug = slug;
//	}

//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//	public String getSku() {
//		return sku;
//	}
//
//	public void setSku(String sku) {
//		this.sku = sku;
//	}
//
//	public String getPrice() {
//		return price;
//	}
//
//	public void setPrice(String price) {
//		this.price = price;
//	}
//
//	public String getDiscount() {
//		return discount;
//	}
//
//	public void setDiscount(String discount) {
//		this.discount = discount;
//	}
//
//	public String getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(String quantity) {
//		this.quantity = quantity;
//	}
//
//	public String getShop() {
//		return shop;
//	}
//
//	public void setShop(String shop) {
//		this.shop = shop;
//	}
//
//	public String getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(String createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public String getUpdatedAt() {
//		return updatedAt;
//	}
//
//	public void setUpdatedAt(String updatedAt) {
//		this.updatedAt = updatedAt;
//	}
//
//	public String getPublishedAt() {
//		return publishedAt;
//	}
//
//	public void setPublishedAt(String publishedAt) {
//		this.publishedAt = publishedAt;
//	}
//
//	public String getStartsAt() {
//		return startsAt;
//	}
//
//	public void setStartsAt(String startsAt) {
//		this.startsAt = startsAt;
//	}
//
//	public String getEndsAt() {
//		return endsAt;
//	}
//
//	public void setEndsAt(String endsAt) {
//		this.endsAt = endsAt;
//	}
//
//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
	
}
