package com.flowerseeker.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="store")
public class Store {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String storename;
	
	@Column(nullable=false)
	private String phone;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	private String paypalemail;
	
	@OneToMany(mappedBy="store")
	private Set<Order> orders;
	
	@OneToMany(mappedBy="store")
	private Set<Hour> hours;
	
	@Column(nullable=false)
	private Boolean subscribed;
	
	@OneToMany(mappedBy="store", fetch=FetchType.LAZY)
	private Set<Subscription> subscriptions;
	
	@OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private User florist;
	
	@OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinColumn(name="location_id", nullable=false)
	private Location location;
	
	@OneToMany(mappedBy="store")
	private Set<Product> products;
	
	@ManyToMany
	@JoinTable(name="store_zipcode")
	private Set<Zipcode> deliversto;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String name) {
		this.storename = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getSubscribed() {
		return subscribed;
	}

	public void setSubscribed(Boolean subscribed) {
		this.subscribed = subscribed;
	}

	public User getFlorist() {
		return florist;
	}

	public void setFlorist(User florist) {
		this.florist = florist;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public Set<Hour> getHours() {
		return hours;
	}
	
	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public Set<Zipcode> getDeliversto() {
		return deliversto;
	}

	public void setDeliversto(Set<Zipcode> deliversto) {
		this.deliversto = deliversto;
	}

	public String getPaypalemail() {
		return paypalemail;
	}

	public void setPaypalemail(String paypal) {
		this.paypalemail = paypal;
	}

	
}
