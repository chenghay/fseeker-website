package com.flowerseeker.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.flowerseeker.cart.ShoppingCart;
import com.flowerseeker.dao.OccasionRepository;
import com.flowerseeker.domain.OrderEntry;
import com.flowerseeker.domain.Product;
import com.flowerseeker.services.ProductService;
import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import com.paypal.svcs.services.AdaptivePaymentsService;
import com.paypal.svcs.types.ap.PayRequest;
import com.paypal.svcs.types.ap.PayResponse;
import com.paypal.svcs.types.ap.Receiver;
import com.paypal.svcs.types.ap.ReceiverList;
import com.paypal.svcs.types.common.RequestEnvelope;

@Controller
public class CartController {

	@Autowired
	private ShoppingCart cart;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private OccasionRepository orderRepository;
	
	@RequestMapping(value="/cart/add/{productid}", method=RequestMethod.GET)
	public String addToCart(@PathVariable Long productid, Model model) {
		Product product=productService.getProduct(productid);
		cart.addItem(product);
		model.addAttribute("items", cart.getItems());
		return "cartContents";
	}
	
	@RequestMapping(value="/cart/delete/{productid}", method=RequestMethod.GET)
	public String deleteToCart(@PathVariable Long productid, Model model) {
		Product product=productService.getProduct(productid);
		cart.removeItem(product);
		model.addAttribute("items", cart.getItems());
		return "cartContents";
	}
 
	@RequestMapping("/cart") 
	public ModelAndView getCart() {
		final List<OrderEntry> list= cart.getItems();
		ModelAndView mav=new ModelAndView("cart");
		mav.addObject("items", list);
		mav.addObject("occasions", orderRepository.findAll());
		return mav;
	}
	
	@RequestMapping("/checkout/error")
	public String checkoutError(@RequestParam("tracking") String tracking, Model model) {
		model.addAttribute("tracking", tracking);
		return "checkout/error";
	}
	
	@RequestMapping("/checkout/success")
	public String checkoutSuccess(@RequestParam("tracking") String tracking, Model model) {
		model.addAttribute("tracking", tracking);
		return "checkout/success";
	}
	
	//test hardcoded payment process
	@RequestMapping("/cart/checkout")
	public String checkout(Model model) {
		RequestEnvelope requestEnvelope = new RequestEnvelope("en_US");
		PayRequest req = new PayRequest();
		List<Receiver> receiver = new ArrayList<Receiver>();
		Receiver rec = new Receiver();
		rec.setAmount(30.4);
		rec.setEmail("florist1@gmail.com");
		receiver.add(rec);
		ReceiverList receiverlst = new ReceiverList(receiver);
		req.setReceiverList(receiverlst);
		req.setRequestEnvelope(requestEnvelope);
		//ClientDetailsType clientDetails = new ClientDetailsType();
		//clientDetails.setApplicationId("APP-80W284485P519543T");
		//req.setClientDetails(clientDetails);
		req.setFeesPayer("EACHRECEIVER");
		UUID tracking = UUID.randomUUID();
		req.setTrackingId(tracking.toString());
		req.setActionType("PAY");
		req.setCancelUrl("https://localhost:8443/flowerseeker/checkout/error?tracking=" + tracking);
		req.setCurrencyCode("USD");
		req.setReturnUrl("https://localhost:8443/flowerseeker/checkout/success?tracking=" + tracking);
		AdaptivePaymentsService service = null;
		
		try {
			service = new AdaptivePaymentsService(this.getClass().getResourceAsStream("/paypal_config.properties"));
			PayResponse resp = service.pay(req);
			if (resp != null) {
				if (resp.getResponseEnvelope().getAck().toString().equalsIgnoreCase("SUCCESS")) {	
					String url = "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_ap-payment&paykey=" + resp.getPayKey();	
					return "redirect:" + url;
				} else {
					model.addAttribute("errors", resp.getError());
				}
			}
		} catch (SSLConfigurationException e) {
			e.printStackTrace();
		} catch (InvalidCredentialException e) {
			e.printStackTrace();
		} catch (HttpErrorException e) {
			e.printStackTrace();
		} catch (InvalidResponseDataException e) {
			e.printStackTrace();
		} catch (ClientActionRequiredException e) {
			e.printStackTrace();
		} catch (MissingCredentialException e) {
			e.printStackTrace();
		} catch (OAuthException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("tracking", tracking);
		return "checkout/error";
	}
}
