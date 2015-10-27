package com.flowerseeker.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flowerseeker.cart.ShoppingCart;
import com.flowerseeker.controllers.forms.AddOrderForm;
import com.flowerseeker.domain.Location;
import com.flowerseeker.domain.Order;
import com.flowerseeker.domain.OrderEntry;
import com.flowerseeker.domain.Product;
import com.flowerseeker.domain.Store;
import com.flowerseeker.domain.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.flowerseeker.dao.LocationRepository;
import com.flowerseeker.dao.OrderEntryRepository;
import com.flowerseeker.dao.OrderRepository;
import com.flowerseeker.dao.ProductRepository;
import com.flowerseeker.dao.StoreRepository;
import com.flowerseeker.dao.UserRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderEntryRepository orderEntryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LocationRepository locationRepository;

	@Transactional
	public String placeOrder(AddOrderForm aof, ShoppingCart cart, User customer) {
		String hashcodeForOrder= RandomStringUtils.randomAlphanumeric(12).toUpperCase();
		Location loc = new Location();
		loc.setAddress1(aof.getAddress1());
		loc.setAddress2(aof.getAddress2());
		loc.setCity(aof.getCity());
		loc.setState(aof.getUsState());
		loc.setZip(aof.getZipCode());
		loc = locationRepository.save(loc);
		User user = userRepository.findOne(customer.getUsername());
		float total = 0;
		Date date = new Date();
		for (Store s: cart.getStores().values()) {
			Store store = storeRepository.findOne(s.getId());
			Order o = new Order();
			float storetotal = 0;
			o.setGroupId(hashcodeForOrder);
			o.setStore(store);
			o.setStatus("unpaid");
			o.setCustomer(user);
			o.setDate(date);
			o.setDestination(loc);
			o.setInstruction(aof.getInstructions());
			o.setPickup(false);
			o.setTotal((float) 0.0);
			o = orderRepository.save(o);
			for (Long product: cart.getStore2products().get(s.getId())) {
				OrderEntry oe = cart.getProducts().get(product);
				Product p = productRepository.findOne(product);
				oe.setOrder(o);
				oe.setProduct(p);
				oe.setPrice(p.getPrice());
				oe.setPickup(false);
				orderEntryRepository.save(oe);
				storetotal += p.getPrice()*oe.getQuantity();
			}
			o.setTotal(storetotal);
			orderRepository.save(o);
			total += storetotal;
		}
		//interface with paypal here and return paykey so caller can redirect to it
		//need to know how to distribute fees - if each florist will pay the paypal transaction fee or flowerseeker pays the whole thing
		//if flowerseeker pays, will need to be chained payment, if each florist pays, would be parallel payment
		//taxes??
		return hashcodeForOrder;
	}
	
	public Order getOrder(Long id) {
		return orderRepository.findOne(id);
	}
	
	public List<Order> getOrderByUser(String id){
		return orderRepository.findByCustomerUsername(id);
	}
	
	public List<Order> getOrders(Long id){		
		return  orderRepository.findByStoreId(id);
	}
	
}
