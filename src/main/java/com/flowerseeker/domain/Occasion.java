package com.flowerseeker.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="occasion")
public class Occasion {

	@Id
	private String name;
	
	@ManyToMany(mappedBy="occasions")
	private Set<Product> products;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Product> getProducts() {
		return products;
	}
}
