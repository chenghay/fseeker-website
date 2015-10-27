package com.flowerseeker.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.flowerseeker.controllers.forms.AddDeliveryAreaForm;
import com.flowerseeker.controllers.forms.AddHourForm;
import com.flowerseeker.controllers.forms.FloristRegistrationForm;
import com.flowerseeker.controllers.forms.ModifyProductForm;
import com.flowerseeker.controllers.forms.ModifyStoreForm;
import com.flowerseeker.dao.HourRepository;
import com.flowerseeker.dao.LocationRepository;
import com.flowerseeker.dao.ProductRepository;
import com.flowerseeker.dao.StoreRepository;
import com.flowerseeker.dao.SubscriptionRepository;
import com.flowerseeker.dao.UserRepository;
import com.flowerseeker.dao.ZipcodeRepository;
import com.flowerseeker.domain.Hour;
import com.flowerseeker.domain.Location;
import com.flowerseeker.domain.Occasion;
import com.flowerseeker.domain.Product;
import com.flowerseeker.domain.Store;
import com.flowerseeker.domain.Subscription;
import com.flowerseeker.domain.User;
import com.flowerseeker.domain.Zipcode;
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

@Service
public class FloristService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private StoreRepository storeRepository;
	
	@Autowired 
	private ShaPasswordEncoder passwordEncoder;
	
	@Autowired
	private ZipcodeRepository zipRepository;
	
	@Autowired
	private HourRepository hourRepository;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	public void createNewFlorist(FloristRegistrationForm frf) {
		Location address = new Location();
		User florist = new User();
		Store store = new Store();
		
		address.setAddress1(frf.getAddress1());
		address.setAddress2(frf.getAddress2());
		address.setCity(frf.getCity());
		address.setState(frf.getState());
		address.setZip(frf.getZipcode());
		
		florist.setEmail(frf.getEmail());
		florist.setEnabled(true);
		florist.setPassword(passwordEncoder.encodePassword(frf.getPassword(), frf.getUsername()));
		florist.setJoined(new Date());
		florist.setUsername(frf.getUsername());
		florist.setHasStore(true);
		florist.setIsAdmin(false);
		florist.setFirstname(frf.getFirstname());
		florist.setLastname(frf.getLastname());
		florist.setPhone(frf.getUserphone());
		
		store.setLocation(address);
		store.setStorename(frf.getStorename());
		store.setPhone(frf.getPhone());
		store.setEmail(frf.getStoreEmail());
		store.setSubscribed(false);
		store.setPaypalemail(frf.getPaypal());
		store.setFlorist(florist);
		
		Set<Zipcode> zips = new HashSet<Zipcode>();
		List<Zipcode> delivers = zipRepository.findByCityAndZip(frf.getCity().toUpperCase(), frf.getZipcode());
		if (delivers.isEmpty()) {
			Zipcode deliver = new Zipcode();
			deliver.setZip(frf.getZipcode());
			deliver.setState(frf.getState());
			deliver.setCity(frf.getCity().toUpperCase());
			zipRepository.save(deliver);
			zips.add(deliver);
		} else {
			zips.add(delivers.get(0));
		}
		store.setDeliversto(zips);
		storeRepository.save(store);
	}
	
	@Transactional(readOnly=true)
	public void populateModifyStoreForm(ModifyStoreForm msf, Long id) {
		Store s = storeRepository.findOne(id);
		Location l = s.getLocation();
		msf.setId(id);
		msf.setAddress1(l.getAddress1());
		msf.setAddress2(l.getAddress2());
		msf.setCity(l.getCity());
		msf.setState(l.getState());
		msf.setZipcode(l.getZip());
		msf.setPhone(s.getPhone());
		msf.setStoreEmail(s.getEmail());
		msf.setStorename(s.getStorename());
		msf.setPaypal(s.getPaypalemail());
	}
	
	@Transactional
	public Store modifyStore(ModifyStoreForm msf) {
		Store s = storeRepository.findOne(msf.getId());
		Location l = s.getLocation();
		s.setStorename(msf.getStorename());
		s.setPhone(msf.getPhone());
		s.setEmail(msf.getStoreEmail());
		s.setPaypalemail(msf.getPaypal());
		l.setAddress1(msf.getAddress1());
		l.setAddress2(msf.getAddress2());
		l.setCity(msf.getCity());
		l.setState(msf.getState());
		l.setZip(msf.getZipcode());
		
		return s;
	}
	
	@Transactional
	public void addStoreHour(AddHourForm ahf, Store store) {
		Hour h = new Hour();
		h.setDay(ahf.getDay());
		h.setFromtime(ahf.getFrom() + " " + ahf.getFromhalf());
		h.setStore(storeRepository.findOne(store.getId()));
		h.setTotime(ahf.getTo() + " " + ahf.getTohalf());
		hourRepository.save(h);
	}
	
	@Transactional
	public void deleteStoreHour(Long hourId, Store store) {
		hourRepository.delete(hourId);
	}
	
	@Transactional
	public void addStoreDeliveryLocation(AddDeliveryAreaForm form, Store store) {
		List<Zipcode> zips = zipRepository.findByCityAndZip(form.getCity().toUpperCase(), form.getZipcode());
		Store s = storeRepository.findOne(store.getId());
		Set<Zipcode> deliversto = s.getDeliversto();
		if (zips.isEmpty()) {
			Zipcode zip = new Zipcode();
			zip.setCity(form.getCity().toUpperCase());
			zip.setState(form.getState());
			zip.setZip(form.getZipcode());
			zipRepository.save(zip);
			deliversto.add(zip);
		} else {
			deliversto.add(zips.get(0));
		}
		s.setDeliversto(deliversto);
	}
	
	@Transactional
	public void removeStoreDeliveryArea(Long id, Store store) {
		Store s = storeRepository.findOne(store.getId());
		s.getDeliversto().remove(zipRepository.findOne(id));
	}
	
	@Transactional
	public String addSubscription(Store store, String trial) {
		Subscription sub = new Subscription();
		Store s = storeRepository.findOne(store.getId());
		if (trial.equals("true")) {
			sub.setTrial(true);
			sub.setActive(true);
			s.setSubscribed(true);
			productRepository.setAllPickupForStore(true, store.getId());
			productRepository.setAllTrackingForStore(true, store.getId());
		} else {
			sub.setTrial(false);
			sub.setActive(false);
		}
		Calendar c = Calendar.getInstance();
		if (sub.getTrial())
			c.add(Calendar.MONTH, 3);
		else
			c.add(Calendar.MONTH, 12);
		Date expi = c.getTime();
		sub.setStart(new Date());
		sub.setExpiration(expi);
		sub.setStore(s);
		sub.setPickup(true);
		sub.setTracking(true);
		String paypal = UUID.randomUUID().toString();
		sub.setStatus("unpaid");
		sub.setPaypal(paypal);
		subscriptionRepository.save(sub);
		return setupPaypal(paypal);
		//return paypal; //this skips going to paypal
	}
	
	@Transactional
	public void removeSubscription(Store store) {
		Store s = storeRepository.findOne(store.getId());
		s.setSubscribed(false);
		Set<Subscription> subs = s.getSubscriptions();
		for (Subscription sub: subs) {
			if (sub.getActive()) {
				sub.setExpiration(new Date());
				sub.setActive(false);
			}
		}
		productRepository.setAllPickupForStore(false, store.getId());
		productRepository.setAllTrackingForStore(false, store.getId());
	}
	
	@Transactional
	public void cancelSubscriptionPayment(String paypal) {
		Subscription s = subscriptionRepository.findByPaypal(paypal);
		if (s != null) {
			subscriptionRepository.delete(s);
		}
	}
	
	@Transactional
	public void approveSubscriptionPayment(String paypal) {
		Subscription s = subscriptionRepository.findByPaypal(paypal);
		if (s != null) {
			s.setStatus("paid");
			s.setActive(true);
			s.getStore().setSubscribed(true);
			productRepository.setAllPickupForStore(true, s.getStore().getId());
			productRepository.setAllTrackingForStore(true, s.getStore().getId());
		}
	}
	
	public User getFloristByUsername(String u) {
		return userRepository.findOne(u);
	}
	
	public Store getStoreByUsername(String u) {
		return storeRepository.findByFloristUsername(u);
	}
	
	public Store getStoreById(Long id) {
		return storeRepository.findOne(id);
	}
	
	public String setupPaypal(String paypal) {
		RequestEnvelope requestEnvelope = new RequestEnvelope("en_US");
		PayRequest req = new PayRequest();
		List<Receiver> receiver = new ArrayList<Receiver>();
		Receiver rec = new Receiver();
		rec.setAmount(150.0);
		rec.setEmail("flowerseeker.dev-facilitator@gmail.com");
		receiver.add(rec);
		ReceiverList receiverlst = new ReceiverList(receiver);
		req.setReceiverList(receiverlst);
		req.setRequestEnvelope(requestEnvelope);
		//ClientDetailsType clientDetails = new ClientDetailsType();
		//clientDetails.setApplicationId("APP-80W284485P519543T");
		//req.setClientDetails(clientDetails);
		req.setFeesPayer("EACHRECEIVER");
		
		req.setTrackingId(paypal);
		req.setActionType("PAY");
		//use env var to determine which url here
		req.setCancelUrl("https://localhost:8443/flowerseeker/florist/subscription?status=cancel&paypal=" + paypal);
		req.setCurrencyCode("USD");
		req.setReturnUrl("https://localhost:8443/flowerseeker/florist/subscription?status=paid&paypal=" + paypal);
		AdaptivePaymentsService service = null;
		
		try {
			service = new AdaptivePaymentsService(this.getClass().getResourceAsStream("/paypal_config.properties"));
			PayResponse resp = service.pay(req);
			if (resp != null) {
				if (resp.getResponseEnvelope().getAck().toString().equalsIgnoreCase("SUCCESS")) {	
					String url = "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_ap-payment&paykey=" + resp.getPayKey();	
					return url;
				} else {
					return null;
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
		return null;
	}
}
