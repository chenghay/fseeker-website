package com.flowerseeker.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flowerseeker.controllers.forms.AddReviewForm;
import com.flowerseeker.dao.OrderEntryRepository;
import com.flowerseeker.dao.ProductRepository;
import com.flowerseeker.dao.ReviewRepository;
import com.flowerseeker.dao.UserRepository;
import com.flowerseeker.domain.Order;
import com.flowerseeker.domain.OrderEntry;
import com.flowerseeker.domain.Review;

@Service
public class ReviewService {

	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	ProductRepository prodcutRepository;
	
 	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public void addReview(AddReviewForm rf, Long pid) {
		Review review = new Review();
		review.setComment(rf.getComment());
		review.setCustomer(userRepository.findOne(rf.getUsername()));
		review.setDate(new Date());
		review.setProduct(prodcutRepository.findOne(pid));
		review.setRank(Integer.parseInt(rf.getRank()));
		reviewRepository.save(review);
	}
	
	public Review existingReview(Long productid, String user) {
		List<Review> exist = reviewRepository.findByProductIdAndCustomerUsername(productid, user);
		if (!exist.isEmpty()) {
			return exist.get(0);
		}
		return null;
	}
}
