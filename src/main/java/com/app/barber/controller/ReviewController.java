package com.app.barber.controller;

import com.app.barber.model.Review;
import com.app.barber.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/reviews/{id}")
    public Review add(@RequestBody String review, @RequestBody int star,@PathVariable  long id){
        return reviewService.add(review, star, id);
    }

    @GetMapping("/reviews/{id}")
    public List<Review> findAll(@PathVariable long id){
        return reviewService.findById(id);
    }

    @DeleteMapping("/reviews/{id}")
    public void delete(@PathVariable long id){
        reviewService.delete(id);
    }
}
