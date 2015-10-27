package com.flowerseeker.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.flowerseeker.cart.ShoppingCart;
import com.flowerseeker.controllers.forms.AddOrderForm;
import com.flowerseeker.controllers.forms.AddProductForm;
import com.flowerseeker.controllers.forms.AddReviewForm;
import com.flowerseeker.controllers.forms.CustomerModifyRegistrationForm;
import com.flowerseeker.controllers.forms.CustomerRegistrationForm;
import com.flowerseeker.controllers.forms.ModifyStoreForm;
import com.flowerseeker.domain.Location;
import com.flowerseeker.domain.Order;
import com.flowerseeker.domain.OrderEntry;
import com.flowerseeker.domain.Product;
import com.flowerseeker.domain.Store;
import com.flowerseeker.domain.User;
import com.flowerseeker.services.CustomerUserService;
import com.flowerseeker.services.FloristService;
import com.flowerseeker.services.OrderService;
import com.flowerseeker.services.ProductService;
import com.flowerseeker.services.ReviewService;
import com.sun.org.apache.bcel.internal.generic.LSTORE;

@Controller
@RequestMapping("/user")
@SessionAttributes("customer")
public class CustomerProfileManager {
	
	@Autowired
	private OrderService oorderService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private CustomerUserService customerUsersService;
	
	@Autowired
	private ProductService productService;
	
 	
	@Autowired
	private ShoppingCart cart;
	
	@ModelAttribute("customer")
	public User getStore(Principal c) {
		return customerUsersService.getCustomerByUsername(c.getName());
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public String profile() {
		return "user/profile";
	}
	
	@RequestMapping(value="/orders", method=RequestMethod.GET)
	public String getOrders(@ModelAttribute("customer") User user, Model model) {
		model.addAttribute("orders", oorderService.getOrderByUser(user.getUsername()));
		return "user/orders";	
	}
	
	@RequestMapping(value="/orders/{id}", method=RequestMethod.GET)
	public String getOrderDetail(@PathVariable Long id, Model model) {
		model.addAttribute("order", oorderService.getOrder(id));
		return "user/orderDetail";
	}
	
	@RequestMapping(value="/orders/{id}/products/{pid}/review", method=RequestMethod.GET)
	public String productDetail(@PathVariable Long id, @PathVariable Long pid, Model model, @ModelAttribute("customer") User user) {
		AddReviewForm orform = new AddReviewForm();
		orform.setUsername(user.getUsername());
		model.addAttribute("addReviewForm", orform);
		model.addAttribute("product", productService.getProduct(pid));
		model.addAttribute("orderid", id);
		model.addAttribute("review", reviewService.existingReview(pid, user.getUsername()));
		return "user/reviewProduct";
	}
	
	@RequestMapping(value="/orders/{id}/products/{pid}/review", method=RequestMethod.POST)
	public String productDetail(@Valid AddReviewForm orform, BindingResult result, @PathVariable Long id, @PathVariable Long pid, Model model, @ModelAttribute("customer") User user) {
		if (result.hasErrors()) {
			model.addAttribute("addReviewForm", orform);
			model.addAttribute("product", productService.getProduct(pid));
			model.addAttribute("orderid", id);
			return "user/reviewProduct";
		}		
		reviewService.addReview(orform, pid);
		return "redirect:/user/orders/" + id;
	}
	
	@RequestMapping(value="/profile/modify",method=RequestMethod.GET)
	public String modifyProfileForm(@ModelAttribute("customer") User user, Model model) {
		CustomerModifyRegistrationForm cmf= new CustomerModifyRegistrationForm();
		customerUsersService.populateModifyUserForm(cmf, user.getUsername());
		model.addAttribute("customerModifyRegistrationForm", cmf);
		return "user/updateProfile";
		
	}
	
	@RequestMapping(value="/profile/modify",method=RequestMethod.POST)
	public String modifyProfile(@Valid CustomerModifyRegistrationForm cmf, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("customerModifyRegistrationForm", cmf);
			model.addAttribute("errorMsg", "There are errors in your form");
			return "user/updateProfile";
		}
		customerUsersService.modifyUser(cmf);
		return "redirect:/user/profile";
	}
	
	@RequestMapping(value="/cart/PlaceOrder", method= RequestMethod.GET)
	public String fillPlaceOrder(Model model) {
		AddOrderForm form= new AddOrderForm();
		model.addAttribute("addOrderForm", form);
		model.addAttribute("productCart", cart.getItems());
		model.addAttribute("total", cart.getTotalOrderEntry());
		return "user/orderCheckout";		
	}
	
	@RequestMapping(value="/cart/PlaceOrder", method= RequestMethod.POST)
	public String fillPlaceCheckOrder( @Valid AddOrderForm aof, BindingResult bindingresult, Model model, @ModelAttribute("customer") User u) {
		if (bindingresult.hasErrors()) {
			model.addAttribute("addOrderForm", aof);
			model.addAttribute("errorMsg", "There are errors in your shipping information");
			model.addAttribute("productCart", cart.getItems());
			model.addAttribute("total", cart.getTotalOrderEntry());
			return "user/orderCheckout";
		}
		oorderService.placeOrder(aof, cart, u);
		//if paypal is integrated, should redirect to paypal instead and clear cart after payment successful
		cart.clearCart();
		return "redirect:/user/orders";		
	}
	
	
 
}
