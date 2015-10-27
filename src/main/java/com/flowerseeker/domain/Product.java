package com.flowerseeker.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private Float price;
	
	@Column(nullable=false)
	private Boolean available;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="store_id", nullable=false)
	private Store store;
	
	@Column(nullable=false)
	private String description;
	
	private String image;
	
	@OneToMany(mappedBy="product")
	private Set<OrderEntry> orderEntries;
	
	private Date added;
	
	public Float getAverageRank() {
		Float sum = 0F;
		for(Review review: reviews)
			sum += review.getRank();
		return sum/reviews.size();
	}
	
	@OneToMany(mappedBy="product")
	private Set<Review> reviews;
	
	@ManyToMany
	@JoinTable(name="product_occasion")
	private Set<Occasion> occasions;
	
	private Boolean pickup;
	
	private Boolean tracking;
	
	public Set<Occasion> getOccasions() {
		return occasions;
	}
	public void setOccasions(Set<Occasion> occasions) {
		this.occasions = occasions;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	public Set<OrderEntry> getOrderEntries() {
		return orderEntries;
	}
	public Date getAdded() {
		return added;
	}
	public void setAdded(Date added) {
		this.added = added;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public Set<Review> getReviews() {
		return reviews;
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
}
