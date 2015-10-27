package com.flowerseeker.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flowerseeker.controllers.forms.CustomerRegistrationForm;
import com.flowerseeker.dao.UserRepository;
import com.flowerseeker.services.CustomerUserService;

@Controller
@RequestMapping("/user")
public class CustomerController {

	@Autowired
	private CustomerUserService customerUserService;
	
	@Autowired
	private UserRepository ur;
	
	@RequestMapping(value="/ajax/{username}/available", method=RequestMethod.GET)
	public @ResponseBody String available(@PathVariable String username) {
		if (ur.findOne(username) == null)
			return "true";
		return "false";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String signup(Model model) {
		CustomerRegistrationForm form= new CustomerRegistrationForm();
		model.addAttribute("customerRegistrationForm", form);
		return "user/register";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String signup(@Valid CustomerRegistrationForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("customerRegistrationForm", form);
			return "user/register";
		}
		if (ur.exists(form.getCustomerUsername())) {
			model.addAttribute("customerRegistrationForm", form);
			model.addAttribute("errorMsg", "Username is already taken!");
			return "user/register";
		}
		customerUserService.createNewCustomer(form);
		return "redirect:/login";
	}
}
