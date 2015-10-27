package com.flowerseeker.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	private String username;

	@Column(nullable=true)
	private String firstname;
	
	@Column(nullable=true)
	private String lastname;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	private String phone;
	
	@Column(nullable=false)
	private Date joined;
	
	@Column(nullable=false)
	private Boolean enabled;
	
	@Column(nullable=false)
	private Boolean isAdmin;
	
	@Column(nullable=false)
	private Boolean hasStore;
	
	@OneToOne(mappedBy="florist", fetch=FetchType.LAZY)
	private Store store;
	
	@OneToMany(mappedBy="customer")
	private Set<Order> orders;
	
	@OneToMany(mappedBy="customer")
	private Set<Review> reviews;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String userName) {
		this.username = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getJoined() {
		return joined;
	}
	public void setJoined(Date joined) {
		this.joined = joined;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Boolean getHasStore() {
		return hasStore;
	}
	public void setHasStore(Boolean hasStore) {
		this.hasStore = hasStore;
	}
	public Store getStore() {
		return store;
	}
	public Set<Order> getOrders() {
		return orders;
	}
	public Set<Review> getReviews() {
		return reviews;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
