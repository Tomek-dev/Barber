package com.app.barber.controller;

import com.app.barber.model.OAuthUser;
import com.app.barber.model.Review;
import com.app.barber.other.dto.ReviewInfoDto;
import com.app.barber.other.dto.ReviewInputDto;
import com.app.barber.other.dto.ReviewOutputDto;
import com.app.barber.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @PreAuthorize("isAuthenticated && hasRole('ROLE_OAUTH')")
    @PostMapping("/oauth/reviews/add/{id}")
    public void add(@Valid @RequestBody ReviewInputDto review, @PathVariable  long id, @AuthenticationPrincipal OAuthUser user){
        reviewService.add(review, id, user);
    }

    @GetMapping("/reviews/{id}")
    public List<ReviewOutputDto> findAll(@PathVariable long id){
        return reviewService.findById(id);
    }

    @GetMapping("/review/info/{id}")
    public ReviewInfoDto getInfo(@PathVariable Long id){
        return reviewService.reviewInfo(id);
    }

    @PreAuthorize("hasRole('ROLE_OAUTH') && @webSecurity.reviewOwner(id, authentication)")
    @DeleteMapping("/oauth/reviews/{id}")
    public void delete(@PathVariable long id){
        reviewService.delete(id);
    }
}
