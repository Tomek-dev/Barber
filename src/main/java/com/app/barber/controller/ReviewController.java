package com.app.barber.controller;

import com.app.barber.model.Review;
import com.app.barber.other.dto.ReviewInputDto;
import com.app.barber.other.dto.ReviewOutputDto;
import com.app.barber.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //only openId user
    @PostMapping("/reviews/add/{id}")
    public void add(@Valid @RequestBody ReviewInputDto review, @PathVariable  long id){
        reviewService.add(review, id);
    }

    @GetMapping("/reviews/{id}")
    public List<ReviewOutputDto> findAll(@PathVariable long id){
        return reviewService.findById(id);
    }

    //only owner of this (openID)
    @DeleteMapping("/reviews/{id}")
    public void delete(@PathVariable long id){
        reviewService.delete(id);
    }
}
