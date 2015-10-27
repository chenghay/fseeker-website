package com.flowerseeker.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.flowerseeker.controllers.forms.AddReviewForm;
import com.flowerseeker.controllers.forms.SendEmailForm;
import com.flowerseeker.dao.OrderRepository;
import com.flowerseeker.dao.ProductRepository;
import com.flowerseeker.dao.StoreRepository;
import com.flowerseeker.domain.Order;
import com.flowerseeker.domain.Product;
import com.flowerseeker.domain.Store;
import com.flowerseeker.domain.User;
import com.flowerseeker.services.AdminService;
import com.flowerseeker.services.MailService;
 

@Controller
@RequestMapping("/admin")
public class AdministratorController {
	
	@Autowired
	private AdminService adminitratorService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private MailService mailService;
	
	@ModelAttribute("admin")
	public User getStore(Principal c) {
		return adminitratorService.getCustomerByUsername(c.getName());
	}
	
	@RequestMapping(value = "/profile", method= RequestMethod.GET)
	public String profile(Principal auth, Model model) {
		return "admin/profile";
	}
	
	@RequestMapping(value="/stores", method=RequestMethod.GET)
	public String getCustomers(Model model, 
			@PageableDefaults(pageNumber=0, value=20, sortDir=Sort.Direction.ASC, sort={"storename"}) Pageable pageable) {
		Page<Store> stores = storeRepository.findAll(pageable);
		model.addAttribute("store", stores);
		model.addAttribute("pageSize", pageable.getPageSize());
		model.addAttribute("pageSortDir", pageable.getSort().iterator().next().getDirection());
		model.addAttribute("pageSort", pageable.getSort().iterator().next().getProperty());
	
		return "admin/floristsListAll";
	}
	
	@RequestMapping(value = "/stores/{storeId}", method= RequestMethod.GET)
	public String profile(Model model, @PathVariable Long storeId) {
		Store store=storeRepository.findOne(storeId);		
		model.addAttribute("store", store);
		return "admin/storeInfo";
	}
	
	@RequestMapping("/stores/{storeId}/products")
	public String getCreateProductFromStore(Model model, @PathVariable Long storeId, @PageableDefaults(pageNumber=0, value=10, sortDir=Sort.Direction.ASC, sort={"added"}) Pageable pageable) {
		Store store=storeRepository.findOne(storeId);
		Page<Product> products= productRepository.findByStoreId(storeId, pageable);
		model.addAttribute("store", store);
		model.addAttribute("productsStore", products);
		
		model.addAttribute("pageSize", pageable.getPageSize());
		model.addAttribute("pageSortDir", pageable.getSort().iterator().next().getDirection());
		model.addAttribute("pageSort", pageable.getSort().iterator().next().getProperty());
		
		return "admin/storeProducts";
	}
	
	@RequestMapping("/stores/{storeId}/orders")
	public String getOrderPerStore(Model model, @PathVariable Long storeId, @PageableDefaults(pageNumber=0, value=10, sortDir=Sort.Direction.ASC, sort={"date"}) Pageable pageable) {
		Store store=storeRepository.findOne(storeId);
		Page<Order> order = orderRepository.findByStoreId(storeId, pageable);
		
		model.addAttribute("store", store);
		model.addAttribute("order", order);
		//pagination
		model.addAttribute("pageSize", pageable.getPageSize());
		model.addAttribute("pageSortDir", pageable.getSort().iterator().next().getDirection());
		model.addAttribute("pageSort", pageable.getSort().iterator().next().getProperty());
		return "admin/storeOrderInfo";
	}
	
	
	@RequestMapping(value="/contactCustomer/{storeId}", method=RequestMethod.GET)
	public String contactCustomer(@ModelAttribute("admin") User user, Model model, @PathVariable Long storeId) {
		 
		Store store= storeRepository.findOne(storeId);
		SendEmailForm orform= new SendEmailForm();
		orform.setTo(store.getEmail());
		model.addAttribute("sendEmailForm", orform);
		model.addAttribute("emailTo", store.getEmail());
		return "admin/sendContactMail";
	}
	
	@RequestMapping(value="/contactCustomer/{storeId}", method=RequestMethod.POST)
	public String sendEmailCustomer(@Valid SendEmailForm sef, BindingResult result,  Model model, @ModelAttribute("admin") User user,@PathVariable Long storeId) {
		
		if (result.hasErrors()) {
			
			Store store= storeRepository.findOne(storeId);
			sef.setTo(store.getEmail());
			model.addAttribute("sendEmailForm", sef);
			model.addAttribute("emailTo", store.getEmail());
			 
			return "admin/sendContactMail";
			
		}
		// add mail database in database ???
		
		// send mail
		mailService.sendEmail(sef);
		 
		return "redirect:/admin/profile";
	}
}
