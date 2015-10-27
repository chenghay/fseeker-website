package com.flowerseeker.services;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flowerseeker.controllers.forms.AddProductForm;
import com.flowerseeker.controllers.forms.ModifyProductForm;
import com.flowerseeker.dao.OccasionRepository;
import com.flowerseeker.dao.ProductRepository;
import com.flowerseeker.dao.StoreRepository;
import com.flowerseeker.domain.Occasion;
import com.flowerseeker.domain.Product;
import com.flowerseeker.domain.Store;

@Service 
public class ProductService {
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OccasionRepository ocassionRepository;
	
	@Autowired 
	ServletContext sc;
	
	@Transactional(readOnly=true)
	public void populateModifyProductForm(ModifyProductForm mpf, Long id) {
		Product p = productRepository.findOne(id);
		mpf.setName(p.getName());
		mpf.setDescription(p.getDescription());
		mpf.setPrice(p.getPrice().toString());
		mpf.setId(id);
		mpf.setAvailable(p.getAvailable());
		mpf.setPickup(p.getPickup());
		mpf.setTracking(p.getTracking());
		Set<String> currentOccasions = new HashSet<String>();
		for (Occasion occasion: p.getOccasions()) {
			currentOccasions.add(occasion.getName());
		}
		mpf.setOccasions(currentOccasions);
	}
	
	public Product getProduct(Long id) {
		return productRepository.findOne(id);
	}
	
	public List<Product> getProducts(Store store) {		
		return productRepository.findByStoreIdOrderByNameAsc(store.getId());
	}
	
	@Transactional
	public void addProduct(AddProductForm adf, Store store) {
		Product p = new Product();
		Store s = storeRepository.findOne(store.getId());
		p.setAdded(new Date());
		p.setName(adf.getName());
		p.setPrice(Float.parseFloat(adf.getPrice()));
		p.setAvailable(true);
		p.setStore(s);
		p.setImage(adf.getFile().getOriginalFilename());
		p.setDescription(adf.getDescription());
		p.setTracking(adf.getTracking());
		p.setPickup(adf.getPickup());
		Set<Occasion> occasions = new HashSet<Occasion>();
		if (adf.getOccasions() != null) {
			for (String occasion: adf.getOccasions()) {
				occasions.add(ocassionRepository.findOne(occasion));
			}
		}
		p.setOccasions(occasions);
		p = productRepository.save(p);
		
		File dir = new File(sc.getRealPath("static/uploaded/product/" + p.getId())); //currently saves to webapp root's folders...
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File imagefile = new File(dir, adf.getFile().getOriginalFilename());
		try {
			FileUtils.writeByteArrayToFile(imagefile, adf.getFile().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void modifyProduct(ModifyProductForm adf) {
		Long id = adf.getId();
		Product p = productRepository.findOne(id);
		p.setName(adf.getName());
		p.setPrice(Float.parseFloat(adf.getPrice()));
		p.setAvailable(adf.getAvailable());
		p.setDescription(adf.getDescription());
		p.setTracking(adf.getTracking());
		p.setPickup(adf.getPickup());
		Set<Occasion> occasions = new HashSet<Occasion>();
		for (String occasion: adf.getOccasions()) {
			occasions.add(ocassionRepository.findOne(occasion));
		}
		p.setOccasions(occasions);
		if (adf.getFile() != null && !adf.getFile().isEmpty()) {
			File dir = new File(sc.getRealPath("static/uploaded/product/" + p.getId())); //currently saves to webapp root's folders...
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File imagefile = new File(dir, adf.getFile().getOriginalFilename());
			p.setImage(adf.getFile().getOriginalFilename());
			try {
				FileUtils.writeByteArrayToFile(imagefile, adf.getFile().getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
