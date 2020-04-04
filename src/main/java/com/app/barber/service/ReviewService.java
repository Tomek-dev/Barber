package com.app.barber.service;

import com.app.barber.dao.BarberDao;
import com.app.barber.dao.ReviewDao;
import com.app.barber.model.Barber;
import com.app.barber.model.Review;
import com.app.barber.other.builder.ReviewBuilder;
import com.app.barber.other.dto.ReviewInputDto;
import com.app.barber.other.dto.ReviewOutputDto;
import com.app.barber.other.enums.Star;
import com.app.barber.other.exception.BarberNotFoundException;
import com.app.barber.other.exception.StarNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ModelMapper mapper = new ModelMapper();

    private ReviewDao reviewDao;
    private BarberDao barberDao;

    @Autowired
    public ReviewService(ReviewDao reviewDao, BarberDao barberDao) {
        this.reviewDao = reviewDao;
        this.barberDao = barberDao;
    }

    public void add(ReviewInputDto review, long id){
        Optional<Barber> barberOptional = barberDao.findById(id);
        Barber foundBarber = barberOptional.orElseThrow(BarberNotFoundException::new);
        Optional<Star> starOptional = Star.fromNumber(review.getStar());
        Star foundStar = starOptional.orElseThrow(StarNotFoundException::new);
        Review newReview = ReviewBuilder.builder()
                .review(review.getReview())
                .star(foundStar)
                .barber(foundBarber)
                .build();
        reviewDao.save(newReview);
    }

    public void delete(Long id){
        reviewDao.deleteById(id);
    }

    public List<ReviewOutputDto> findById(long id){
        List<Review> reviews = reviewDao.findByBarberId(id);
        return reviews.stream()
                .map(review -> mapper.map(review, ReviewOutputDto.class))
                .collect(Collectors.toList());
    }

    public Double averageByBarber(Long id){
        List<Review> reviews = reviewDao.findByBarberId(id);
        long sum = 0L;
        for (Review review : reviews) {
            sum += review.getStar().getNumber();
        }
        return  (double) sum / reviews.size();
    }
}
