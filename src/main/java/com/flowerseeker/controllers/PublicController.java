package com.flowerseeker.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
 
 
import org.springframework.validation.BindingResult;
 
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.flowerseeker.cart.ShoppingCart;
import com.flowerseeker.controllers.forms.AddOrderForm;
import com.flowerseeker.dao.OccasionRepository;
import com.flowerseeker.dao.ProductRepository;
 
 
import com.flowerseeker.dao.ReviewRepository;
 
 
 
 
import com.flowerseeker.domain.Occasion;
import com.flowerseeker.domain.Product;
import com.flowerseeker.services.ProductService;

@Controller
public class PublicController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired 
	private OccasionRepository ocassionRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private ShoppingCart cart;
	
	@ModelAttribute("occasions")
	public List<Occasion> getOccasions() {
		return ocassionRepository.findAll();
	}
	
	@RequestMapping("/search")
	public String search(@RequestParam(value="max", required=false) float maxPrice, @RequestParam(value="min", required=false) float minPrice, Model model)
	{
		model.addAttribute("products", productRepository.findByPriceGreaterThanAndPriceLessThan(minPrice, maxPrice));
		return "search";
	}
	
	@RequestMapping("/searchPriceMax")
	public String searchByPriceMax(@RequestParam(value="max", required=false) float maxPrice, Model model)
	{
		model.addAttribute("products", productRepository.findByPriceLessThan(maxPrice));
		return "search";
	}
	
	@RequestMapping("/searchPriceMin")
	public String searchByPriceMin(@RequestParam(value="min", required=false) float minPrice, Model model)
	{
		model.addAttribute("products", productRepository.findByPriceGreaterThan(minPrice));
		return "search";
	}
	
	@RequestMapping("/searchLocation")
	public String searchByLocation(@RequestParam("zip")String zipCode, Model model)
	{
		model.addAttribute("products", productRepository.findByStoreDeliverstoZip(zipCode));
		return "search";
	}
	
	@RequestMapping("/searchReviews")
	public String searchByProductReviewRank(@RequestParam("rank")Integer rank, Model model)
	{
		model.addAttribute("products", productRepository.findByReviewsRankGreaterThan(rank-1));
		return "search";
	}
	
	@RequestMapping("/searchOccassions")
	public String searchByOccasions(@RequestParam("occasion")String occasion, Model model)
	{
		model.addAttribute("products", productRepository.findByOccasionsName(occasion));
		return "search";
	}
	
	@RequestMapping(value="/products/{id}", method=RequestMethod.GET)
	public String productDetail(@PathVariable Long id, Model model) {
		Product product = productService.getProduct(id);
		model.addAttribute("product", product);
		model.addAttribute("reviews", reviewRepository.findByProductIdOrderByDateDesc(id));
		return "productDetail";
	}
 
 
}
