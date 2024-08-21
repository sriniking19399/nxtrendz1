/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.nxttrendz1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

import com.example.nxttrendz1.repository.*;
import com.example.nxttrendz1.model.*;

@Service
public class ReviewJpaService implements ReviewRepository {

    @Autowired
    private ReviewJpaRepository reviewRepository;

    @Autowired
    private ProductJpaRepository productRepository;

    @Override
    public ArrayList<Review> getAllReview() {
        List<Review> reviewList = reviewRepository.findAll();
        ArrayList<Review> reviews = new ArrayList<>(reviewList);
        return reviews;
    }

    @Override
    public Review getReviewById(int reviewId) {
        try {
            Review review = reviewRepository.findById(reviewId).get();
            return review;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
    }

    @Override
    public Review addReview(Review review) {
        Product product = review.getProduct();
        int productId = product.getProductId();
        try {
            Product com_product = productRepository.findById(productId).get();
            review.setProduct(com_product);
            reviewRepository.save(review);
            return review;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }

    }

    @Override
    public Review updateReview(int reviewId, Review review) {
        try {
            Review newReview = reviewRepository.findById(reviewId).get();
            if (review.getReviewContent() != null) {
                newReview.setReviewContent(review.getReviewContent());
            }
            if (review.getRating() != 0) {
                newReview.setRating(review.getRating());
            }
            if (review.getProduct() != null) {
                Product product = review.getProduct();
                int productId = product.getProductId();
                Product newProduct = productRepository.findById(productId).get();
                newReview.setProduct(newProduct);
            }
            reviewRepository.save(newReview);
            return newReview;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteReview(int reviewId) {
        try {
            reviewRepository.deleteById(reviewId);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public Product getProductByRewiewId(int reviewId) {
        try {
            Review review = reviewRepository.findById(reviewId).get();
            return review.getProduct();

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }

    }
}