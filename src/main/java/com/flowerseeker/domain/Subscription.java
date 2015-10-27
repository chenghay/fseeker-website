package com.flowerseeker.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="subscription")
public class Subscription {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private Date start;
	
	@Column(nullable=false)
	private Date expiration;
	
	@Column(nullable=false)
	private Boolean pickup; //allow products to have local pickup option
	
	@Column(nullable=false)
	private Boolean tracking; //allow florists to input order status info
	
	@Column(nullable=false)
	private Boolean trial;
	
	@Column(nullable=false)
	private Boolean active;
	
	private String paypal; //unique tracking id for paypal
	
	@Column(nullable=false)
	private String status; //paid, unpaid, pending, etc
	
	@ManyToOne
	@JoinColumn(name="store_id", nullable=false)
	private Store store;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public Date getExpiration() {
		return expiration;
	}
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	public Boolean getTrial() {
		return trial;
	}
	public void setTrial(Boolean trial) {
		this.trial = trial;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Boolean getPickup() {
		return pickup;
	}
	public void setPickup(Boolean pickup) {
		this.pickup = pickup;
	}
	public Boolean getTracking() {
		return tracking;
	}
	public void setTracking(Boolean tracking) {
		this.tracking = tracking;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getPaypal() {
		return paypal;
	}
	public void setPaypal(String paypalId) {
		this.paypal = paypalId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
