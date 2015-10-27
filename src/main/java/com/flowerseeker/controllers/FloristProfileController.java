package com.flowerseeker.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.flowerseeker.controllers.forms.AddDeliveryAreaForm;
import com.flowerseeker.controllers.forms.AddHourForm;
import com.flowerseeker.controllers.forms.AddProductForm;
import com.flowerseeker.controllers.forms.ModifyProductForm;
import com.flowerseeker.controllers.forms.ModifyStoreForm;
import com.flowerseeker.dao.OccasionRepository;
import com.flowerseeker.dao.OrderRepository;
import com.flowerseeker.dao.ProductRepository;
import com.flowerseeker.dao.ReviewRepository;
import com.flowerseeker.dao.StoreRepository;
import com.flowerseeker.dao.SubscriptionRepository;
import com.flowerseeker.dao.UserRepository;
import com.flowerseeker.dao.ZipcodeRepository;
import com.flowerseeker.domain.Order;
import com.flowerseeker.domain.Product;
import com.flowerseeker.domain.Store;
import com.flowerseeker.domain.User;
import com.flowerseeker.exceptions.ForbiddenException;
import com.flowerseeker.exceptions.ResourceNotFoundException;
import com.flowerseeker.services.FloristService;
import com.flowerseeker.services.ProductService;

@Controller
@RequestMapping("/florist")
public class FloristProfileController {

	@Autowired
	private FloristService floristService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired 
	private OccasionRepository ocassionRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ZipcodeRepository zipRepository;
	
	@ModelAttribute("store")
	public Store getStore(Principal f) {
		return floristService.getStoreByUsername(f.getName());
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public String getProfile(@ModelAttribute("store") Store store, Model model,
			@PageableDefaults(pageNumber=0, value=5, sortDir=Sort.Direction.DESC, sort={"date"}) @Qualifier("review") Pageable reviews,
			@PageableDefaults(pageNumber=0, value=5, sortDir=Sort.Direction.DESC, sort={"date"}) @Qualifier("order") Pageable orders) {
		model.addAttribute("reviews", reviewRepository.findByProductStoreId(store.getId(), reviews));
		model.addAttribute("orders", orderRepository.findByStoreId(store.getId(), orders));
		return "florist/profile";
	}
	
	@RequestMapping(value="/profile/modify", method=RequestMethod.GET)
	public String updateProfileForm(@ModelAttribute("store") Store store, Model model) {
		ModifyStoreForm msf = new ModifyStoreForm();
		floristService.populateModifyStoreForm(msf, store.getId());
		model.addAttribute("modifyStoreForm", msf);
		return "florist/updateProfile";
	}
	
	@RequestMapping(value="/profile/modify", method=RequestMethod.POST)
	public String updateProfile(@Valid ModifyStoreForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("modifyStoreForm", form);
			return "florist/updateProfile";
		}
		Store store = floristService.modifyStore(form);
		model.addAttribute("store", store);
		return "redirect:/florist/profile";
	}
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public String getProducts(@ModelAttribute("store") Store store, Model model,
			@PageableDefaults(pageNumber=0, value=20, sortDir=Sort.Direction.ASC, sort={"name"}) Pageable pageable) {
		model.addAttribute("products", productRepository.findByStoreId(store.getId(), pageable));
		model.addAttribute("pageSize", pageable.getPageSize());
		model.addAttribute("pageSortDir", pageable.getSort().iterator().next().getDirection());
		model.addAttribute("pageSort", pageable.getSort().iterator().next().getProperty());
		return "florist/products";
	}
	
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String batchModifyProducts(@ModelAttribute("store") Store store, Model model, 
			@RequestParam(value="tracking", required=false) Boolean tracking, @RequestParam(value="pickup", required=false) Boolean pickup) {
		if (tracking != null) {
			productRepository.setAllTrackingForStore(tracking, store.getId());
		}
		if (pickup != null) {
			productRepository.setAllPickupForStore(pickup, store.getId());
		}
		return "redirect:/florist/products";
	}
	
	@RequestMapping(value="/products/add", method=RequestMethod.GET)
	public String addProductForm(Model model) {
		AddProductForm form = new AddProductForm();
		model.addAttribute("addProductForm", form);
		model.addAttribute("occasions", ocassionRepository.findAll());
		return "florist/addProduct";
	}
	
	@RequestMapping(value="/products/add", method=RequestMethod.POST)
	public String addProduct(@Valid AddProductForm form, BindingResult result, Model model, @ModelAttribute("store") Store store) {
		if (result.hasErrors()) {
			model.addAttribute("addProductForm", form);
			model.addAttribute("occasions", ocassionRepository.findAll());
			return "florist/addProduct";
		}
		productService.addProduct(form, store);
		return "redirect:/florist/products";
	}
	
	@RequestMapping(value="/products/{id}", method=RequestMethod.GET)
	public String productDetail(@PathVariable Long id, Model model, @ModelAttribute("store") Store store,
			@PageableDefaults(pageNumber=0, value=10, sortDir=Sort.Direction.DESC, sort={"date"}) @Qualifier("order") Pageable orderpage,
			@PageableDefaults(pageNumber=0, value=10, sortDir=Sort.Direction.DESC, sort={"date"}) @Qualifier("review") Pageable reviewpage) {
		Product product = productService.getProduct(id);
		if (product == null)
			throw new ResourceNotFoundException();
		if (!product.getStore().getId().equals(store.getId()))
			throw new ForbiddenException();
		model.addAttribute("product", product);
		model.addAttribute("occasions", product.getOccasions());
		model.addAttribute("orders", orderRepository.findByOrderEntriesProductId(id, orderpage));
		model.addAttribute("reviews", reviewRepository.findByProductId(id, reviewpage));
		return "florist/productDetail";
	}
	
	@RequestMapping(value="/products/{id}/modify", method=RequestMethod.GET)
	public String modifyProductForm(@PathVariable Long id, Model model, @ModelAttribute("store") Store store) {
		ModifyProductForm form = new ModifyProductForm();
		Product product = productService.getProduct(id);
		if (product == null)
			throw new ResourceNotFoundException();
		if (!product.getStore().getId().equals(store.getId()))
			throw new ForbiddenException();
		productService.populateModifyProductForm(form, id);
		model.addAttribute("product", productRepository.findOne(id));
		model.addAttribute("modifyProductForm", form);
		model.addAttribute("occasions", ocassionRepository.findAll());
		return "florist/modifyProduct";
	}
	
	@RequestMapping(value="/products/{id}/modify", method=RequestMethod.POST)
	public String modifyProduct(@Valid ModifyProductForm form, BindingResult result, @PathVariable Long id, Model model, @ModelAttribute("store") Store store) {
		Product product = productService.getProduct(id);
		if (product == null)
			throw new ResourceNotFoundException();
		if (!product.getStore().getId().equals(store.getId()))
			throw new ForbiddenException();
		if (result.hasErrors()) {
			model.addAttribute("product", productRepository.findOne(id));
			model.addAttribute("modifyProductForm", form);
			model.addAttribute("occasions", ocassionRepository.findAll());
			return "florist/modifyProduct";
		}
		productService.modifyProduct(form);
		return "redirect:/florist/products/" + id;
	}
	
	@RequestMapping(value="/orders", method=RequestMethod.GET)
	public String getOrders(@ModelAttribute("store") Store store, Model model,
			@PageableDefaults(pageNumber=0, value=20, sortDir=Sort.Direction.DESC, sort={"date"}) Pageable pageable) {
		model.addAttribute("orders", orderRepository.findByStoreId(store.getId(), pageable));
		model.addAttribute("pageSize", pageable.getPageSize());
		model.addAttribute("pageSortDir", pageable.getSort().iterator().next().getDirection());
		model.addAttribute("pageSort", pageable.getSort().iterator().next().getProperty());
		return "florist/orders";
	}
	
	@RequestMapping(value="/orders/{id}", method=RequestMethod.GET)
	public String getOrderDetail(@PathVariable Long id, Model model, @ModelAttribute("store") Store store) {
		Order order = orderRepository.findOne(id);
		if (order == null)
			throw new ResourceNotFoundException();
		if (!order.getStore().getId().equals(store.getId()))
			throw new ForbiddenException();
		model.addAttribute("order", orderRepository.findOne(id));
		return "florist/orderDetail";
	}
	
	@RequestMapping(value="/reviews", method=RequestMethod.GET)
	public String getReviews(@ModelAttribute("store") Store store, Model model,
			@PageableDefaults(pageNumber=0, value=20, sortDir=Sort.Direction.DESC, sort={"date"}) Pageable pageable) {
		model.addAttribute("reviews", reviewRepository.findByProductStoreId(store.getId(), pageable));
		model.addAttribute("pageSize", pageable.getPageSize());
		model.addAttribute("pageSortDir", pageable.getSort().iterator().next().getDirection());
		model.addAttribute("pageSort", pageable.getSort().iterator().next().getProperty());
		return "florist/reviews";
	}
	
	@RequestMapping(value="/customers", method=RequestMethod.GET)
	public String getCustomers(@ModelAttribute("store") Store store, Model model,
			@PageableDefaults(pageNumber=0, value=20, sortDir=Sort.Direction.ASC, sort={"username"}) Pageable pageable) {
		Page<User> customers = userRepository.findByOrdersStoreId(store.getId(), pageable);
		Map<String, Long> orders = new HashMap<String, Long>();
		Map<String, Long> reviews = new HashMap<String, Long>();
		for (User customer: customers) {
			String username = customer.getUsername();
			orders.put(username, orderRepository.findByCustomerUsernameAndStoreId(customer.getUsername(), store.getId(), new PageRequest(0,1)).getTotalElements());
			reviews.put(username, reviewRepository.findByCustomerUsernameAndProductStoreId(username, store.getId(), new PageRequest(0,1)).getTotalElements());
		}
		model.addAttribute("customers", customers);
		model.addAttribute("orders", orders);
		model.addAttribute("reviews", reviews);
		model.addAttribute("pageSize", pageable.getPageSize());
		model.addAttribute("pageSortDir", pageable.getSort().iterator().next().getDirection());
		model.addAttribute("pageSort", pageable.getSort().iterator().next().getProperty());
		return "florist/customers";
	}
	
	@RequestMapping(value="/customers/{name}", method=RequestMethod.GET)
	public String getCustomerDetail(@ModelAttribute("store") Store store, @PathVariable String name, Model model,
			@PageableDefaults(pageNumber=0, value=10, sortDir=Sort.Direction.DESC, sort={"date"}) @Qualifier("order") Pageable orderspage,
			@PageableDefaults(pageNumber=0, value=10, sortDir=Sort.Direction.DESC, sort={"date"}) @Qualifier("review") Pageable reviewspage) {
		model.addAttribute("orders", orderRepository.findByCustomerUsernameAndStoreId(name, store.getId(), orderspage));
		model.addAttribute("reviews", reviewRepository.findByCustomerUsernameAndProductStoreId(name, store.getId(), reviewspage));
		model.addAttribute("customer", userRepository.findOne(name));
		return "florist/customerDetail";
	}
	
	@RequestMapping(value="/hours", method=RequestMethod.GET)
	public String getStoreHours(@ModelAttribute("store") Store store, Model model) {
		model.addAttribute("addHourForm", new AddHourForm());
		return "florist/hours";
	}
	
	@RequestMapping(value="/hours/add", method=RequestMethod.POST)
	public String addStoreHour(@Valid AddHourForm form, BindingResult result, Model model, @ModelAttribute("store") Store store) {
		if (result.hasErrors()) {
			model.addAttribute("addHourForm", form);
			model.addAttribute("store", storeRepository.findOne(store.getId()));
			return "florist/hours";
		}
		floristService.addStoreHour(form, store);
		return "redirect:/florist/hours";
	}
	
	@RequestMapping(value="/hours/delete", method=RequestMethod.POST)
	public String deleteStoreHour(@RequestParam("hourId") Long id, @ModelAttribute("store") Store store, Model model) {
		floristService.deleteStoreHour(id, store);
		return "redirect:/florist/hours";
	}
	
	@RequestMapping(value="/delivery", method=RequestMethod.GET)
	public String getStoreDelivers(@ModelAttribute("store") Store store, Model model) {
		AddDeliveryAreaForm adaf = new AddDeliveryAreaForm();
		adaf.setState(store.getLocation().getState());
		model.addAttribute("addDeliveryAreaForm", adaf);
		model.addAttribute("areas", zipRepository.findByStoresIdOrderByZipAsc(store.getId()));
		return "florist/delivery";
	}
	
	@RequestMapping(value="/delivery/add", method=RequestMethod.POST)
	public String addStoreDelivers(@Valid AddDeliveryAreaForm form, BindingResult result, Model model, @ModelAttribute("store") Store store) {
		if (result.hasErrors()) {
			model.addAttribute("addDeliveryAreaForm", form);
			return "florist/delivery";
		}
		floristService.addStoreDeliveryLocation(form, store);
		return "redirect:/florist/delivery";
	}
	
	@RequestMapping(value="/delivery/delete", method=RequestMethod.POST)
	public String deleteStoreDelivers(@RequestParam("deliveryId") Long id, @ModelAttribute("store") Store store, Model model) {
		floristService.removeStoreDeliveryArea(id, store);
		return "redirect:/florist/delivery";
	}
	
	@RequestMapping(value="/subscription", method=RequestMethod.GET)
	public String subscription(Model model, @ModelAttribute("store") Store store,
			@RequestParam(value="paypal", required=false) String paypalId, 
			@RequestParam(value="status", required=false) String payStatus) {
		if (paypalId != null && payStatus != null) {
			if (payStatus.equals("cancel"))
				floristService.cancelSubscriptionPayment(paypalId);
			else
				floristService.approveSubscriptionPayment(paypalId);
		}
		if (store.getSubscriptions().isEmpty())
			model.addAttribute("trialOk", true);
		else
			model.addAttribute("trialOk", false);
		model.addAttribute("store", storeRepository.findOne(store.getId()));
		model.addAttribute("subscriptions", subscriptionRepository.findByStoreIdOrderByExpirationDesc(store.getId()));
		return "florist/subscription";
	}
	
	@RequestMapping(value="/subscription", method=RequestMethod.POST)
	public String modifySubscription(Model model, @RequestParam("trial") String trial, @RequestParam("action") String action, @ModelAttribute("store") Store store) {
		if (action.equals("add")) {
			if (trial.equals("true")) {
				floristService.addSubscription(store, trial);
			} else {
				String paypal = floristService.addSubscription(store, trial);
				if (paypal != null)
					return "redirect:" + paypal;
				//return "redirect:/florist/subscription?status=paid&paypal=" + paypal; //use this if skipping paypal, coordinate with addSubscription method!
			}
		} else {
			floristService.removeSubscription(store);
		}
		return "redirect:/florist/subscription";
	}
	
	@RequestMapping(value="/customers/{name}/reviews", method=RequestMethod.GET)
	public String getReviewsForCustomer(@ModelAttribute("store") Store store, @PathVariable String name, Model model,
			@PageableDefaults(pageNumber=0, value=10, sortDir=Sort.Direction.DESC, sort={"date"}) Pageable pageable) {
		model.addAttribute("reviews", reviewRepository.findByCustomerUsernameAndProductStoreId(name, store.getId(), pageable));
		return "florist/snippets/reviewsForCustomer";
	}
	
	@RequestMapping(value="/customers/{name}/orders", method=RequestMethod.GET)
	public String getOrdersForCustomer(@ModelAttribute("store") Store store, @PathVariable String name, Model model,
			@PageableDefaults(pageNumber=0, value=10, sortDir=Sort.Direction.DESC, sort={"date"}) Pageable pageable) {
		model.addAttribute("orders", orderRepository.findByCustomerUsernameAndStoreId(name, store.getId(), pageable));
		return "florist/snippets/ordersForCustomer";
	}
	
	@RequestMapping(value="/products/{id}/reviews", method=RequestMethod.GET)
	public String getReviewsForProduct(@PathVariable Long id, Model model,
			@PageableDefaults(pageNumber=0, value=10, sortDir=Sort.Direction.DESC, sort={"date"}) Pageable pageable) {		
		model.addAttribute("reviews", reviewRepository.findByProductId(id, pageable));
		return "florist/snippets/reviewsForProduct";
	}
	
	@RequestMapping(value="/products/{id}/orders", method=RequestMethod.GET)
	public String productDetail(@PathVariable Long id, Model model,
			@PageableDefaults(pageNumber=0, value=10, sortDir=Sort.Direction.DESC, sort={"date"}) Pageable pageable) {
		model.addAttribute("orders", orderRepository.findByOrderEntriesProductId(id, pageable));
		return "florist/snippets/ordersForProduct";
	}
}
