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

import com.flowerseeker.controllers.forms.FloristRegistrationForm;
import com.flowerseeker.dao.UserRepository;
import com.flowerseeker.services.FloristService;

@Controller
@RequestMapping("/florist")
public class FloristController {

	@Autowired
	private FloristService floristService;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/ajax/{username}/available", method=RequestMethod.GET)
	public @ResponseBody String available(@PathVariable String username) {
		if (userRepository.findOne(username) == null)
			return "true";
		return "false";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String signup(Model model) {
		FloristRegistrationForm form = new FloristRegistrationForm();
		model.addAttribute("floristRegistrationForm", form);
		return "florist/register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(@Valid FloristRegistrationForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("floristRegistrationForm", form);
			return "florist/register";
		} 
		if (userRepository.exists(form.getUsername())) {
			model.addAttribute("floristRegistrationForm", form);
			model.addAttribute("errorMsg", "Username is already taken!");
			return "florist/register";
		}
		floristService.createNewFlorist(form);
		return "redirect:/login";
	}
	
	
}
