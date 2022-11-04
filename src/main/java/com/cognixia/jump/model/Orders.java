package com.cognixia.jump.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Orders implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // order id auto increment
	private Long order_id;

	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime order_date;

	@Column(nullable = false)
	private String order_status;
	@ManyToOne
	@JoinColumn(name = "id", referencedColumnName = "id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="product_id", referencedColumnName = "product_id")
	private Products product;

	public Orders() {

	}

	public Orders(Long order_id, LocalDateTime order_date, String order_status, User user, Products product) {
		super();
		this.order_id = order_id;
		this.order_date = order_date;
		this.order_status = order_status;
		this.user = user;
		this.product = product;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public LocalDateTime getOrder_date() {
		return order_date;
	}

	public void setOrder_date(LocalDateTime order_date) {
		this.order_date = order_date;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}



	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

	@Override
	public String toString() {
		return "Orders [order_id=" + order_id + ", order_date=" + order_date + ", order_status=" + order_status
				+ ", user=" + user + ", product=" + product + "]";
	}

}
