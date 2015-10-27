package com.flowerseeker.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

 

import com.flowerseeker.domain.OrderEntry;
import com.flowerseeker.domain.Product;
import com.flowerseeker.domain.Store;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
/*
 * this shopping cart depends on the browser session and is not tied to the user logins
 * people will see different carts if they switch their browsers or their session expires
 * the cart will persist from not logged in to logged in, but will disappear when logged out 
 *   (or remove invalidate-session="true" in the xml security config logout tag)
 */
public class ShoppingCart implements Serializable {

	private Map<Long, OrderEntry> items= new HashMap<Long, OrderEntry>();
	private Map<Long, Set<Long>> store2products = new HashMap<Long, Set<Long>>();
	private Map<Long, Store> stores = new HashMap<Long, Store>();
	
	public List<OrderEntry> getItems() {
		return new ArrayList<OrderEntry>(items.values());
	}
	
	public Map<Long, OrderEntry> getProducts() {
		return items;
	}
	
	public Map<Long, Set<Long>> getStore2products() {
		return store2products;
	}

	public Map<Long, Store> getStores() {
		return stores;
	}

	public void addItem(Product product) {
		if (!items.containsKey(product.getId())) {
			OrderEntry oe = new OrderEntry();
			oe.setProduct(product);
			oe.setQuantity(1);
			oe.setPrice(product.getPrice());
			items.put(product.getId(), oe);
			Store s = product.getStore();
			if (!store2products.containsKey(s.getId())) {
				store2products.put(s.getId(), new HashSet<Long>());
				stores.put(s.getId(), s);
			}
			store2products.get(s.getId()).add(product.getId());
		} else {
			OrderEntry entry = items.get(product.getId());
			entry.setQuantity(entry.getQuantity()+1);
		}
	}
	
	public void removeItem(Product product) {
		if (items.containsKey(product.getId())) {
			OrderEntry entry = items.get(product.getId());
			if (entry!= null) {
				if (entry.getQuantity() == 1) {
					items.remove(product.getId());
					store2products.get(product.getStore().getId()).remove(product.getId());
					if (store2products.get(product.getStore().getId()).isEmpty())
						stores.remove(product.getStore().getId());
				}
				else {
					entry.setQuantity(entry.getQuantity()-1);
				}
			}
		}
	}
	
	public float getTotalOrderEntry(){
		float total = 0;
		for (OrderEntry entry : items.values()) {
			total += entry.getProduct().getPrice()*entry.getQuantity();
		}
		return total;
	}
	
	public void clearCart(){
		items.clear();
		store2products.clear();
		stores.clear();
	}
	
}
