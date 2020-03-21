package com.app.barber.controller;

import com.app.barber.model.Review;
import com.app.barber.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{id}/reviews")
    public Review add(@RequestBody String review, @RequestBody int star,@PathVariable  long id){
        return reviewService.add(review, star, id);
    }

    @GetMapping("/{id}/reviews")
    public List<Review> findAll(@PathVariable long id){
        return reviewService.findById(id);
    }

    @DeleteMapping("/{id}/reviews")
    public void delete(@PathVariable long id){
        reviewService.delete(id);
    }
}
