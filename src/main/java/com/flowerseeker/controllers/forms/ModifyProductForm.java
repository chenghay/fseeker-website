package com.flowerseeker.controllers.forms;

import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class ModifyProductForm {

	@NotNull
	@NotEmpty(message="Product name cannot be empty")
	private String name;
	
	@NotNull
	private Long id;

	@NotNull
	@Pattern(regexp="\\d+(\\.\\d{1,2})?", message="Price must be of the form x.xx")
	private String price;
	
	private MultipartFile file;
	
	@NotNull
	@NotEmpty(message="Product description cannot be empty")
	private String description;
	
	@NotNull
	private Boolean available;
	
	@NotNull(message="At least 1 occasion must be selected")
	private Set<String> occasions;
	
	private Boolean pickup;
	
	private Boolean tracking;
	
	public Set<String> getOccasions() {
		return occasions;
	}

	public void setOccasions(Set<String> occasions) {
		this.occasions = occasions;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
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
