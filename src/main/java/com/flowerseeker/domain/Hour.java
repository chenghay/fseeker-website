package com.flowerseeker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="hour")
public class Hour {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="store_id", nullable=false)
	private Store store;
	
	@Column(nullable=false)
	private String day;
	
	@Column(nullable=false)
	private String fromtime;
	
	@Column(nullable=false)
	private String totime;

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

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getFromtime() {
		return fromtime;
	}

	public void setFromtime(String from) {
		this.fromtime = from;
	}

	public String getTotime() {
		return totime;
	}

	public void setTotime(String to) {
		this.totime = to;
	}

	
}
