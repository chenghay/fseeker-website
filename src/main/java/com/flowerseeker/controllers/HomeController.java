package com.flowerseeker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flowerseeker.dao.OccasionRepository;
import com.flowerseeker.dao.ProductRepository;
import com.flowerseeker.domain.Occasion;
import com.flowerseeker.domain.Product;


@Controller
public class HomeController {

	
	@Autowired
	private OccasionRepository ocassionRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@ModelAttribute("occasions")
	public List<Occasion> getOccasions() {
		return ocassionRepository.findAll();
	}
	
	@RequestMapping("/about")
	public String simple() {
		return "about";
	}
	
	@RequestMapping("/")
	public String home(Model model,@PageableDefaults(pageNumber=0, value=4, sortDir=Sort.Direction.DESC, sort={"added"}) Pageable pageable) {
		Page<Product> product= productRepository.findAll(pageable);
		model.addAttribute("productPage", product);
		return "home";
	}
	
	@RequestMapping("/login")
	public String login(Model model, @RequestParam(value="loginError", required=false) Boolean error ) {
		model.addAttribute("loginError", error);
		return "login";
	}
	
	@RequestMapping("/404")
	public String notFound() {
		return "404";
	}
	
	@RequestMapping("/403")
	public String forbidden() {
		return "403";
	}
	
}
