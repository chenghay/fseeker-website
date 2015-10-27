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
@Table(name="emailmessage")
public class EmailMessage {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="store_id", nullable=false)
	private Store store;
	
	@Column(nullable=false)
	private String message;
	
	@Column(nullable=false)
	private String messageSender;
	
	@Column(nullable=false)
	private String messageReciever;
	
	@Column(nullable=false)
	private String messageSubject;

}
