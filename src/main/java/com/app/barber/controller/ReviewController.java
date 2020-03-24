package com.app.barber.controller;

import com.app.barber.model.Review;
import com.app.barber.other.dto.ReviewInputDto;
import com.app.barber.other.dto.ReviewOutputDto;
import com.app.barber.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add/{id}")
    public void add(@RequestBody ReviewInputDto review, @PathVariable  long id){
        reviewService.add(review, id);
    }

    @GetMapping("/{id}")
    public List<ReviewOutputDto> findAll(@PathVariable long id){
        return reviewService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        reviewService.delete(id);
    }
}
