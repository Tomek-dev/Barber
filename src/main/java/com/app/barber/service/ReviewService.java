package com.app.barber.service;

import com.app.barber.dao.BarberDao;
import com.app.barber.dao.ReviewDao;
import com.app.barber.model.Barber;
import com.app.barber.model.Review;
import com.app.barber.other.builder.ReviewBuilder;
import com.app.barber.other.enums.Star;
import com.app.barber.other.exception.BarberNotFoundException;
import com.app.barber.other.exception.StarNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private ReviewDao reviewDao;
    private BarberDao barberDao;

    @Autowired
    public ReviewService(ReviewDao reviewDao, BarberDao barberDao) {
        this.reviewDao = reviewDao;
        this.barberDao = barberDao;
    }

    public Review add(String review, int star, long id){
        Optional<Barber> barberOptional = barberDao.findById(id);
        Barber foundBarber = barberOptional.orElseThrow(BarberNotFoundException::new);
        Optional<Star> starOptional = Star.fromNumber(star);
        Star foundStar = starOptional.orElseThrow(StarNotFoundException::new);
        Review newReview = ReviewBuilder.builder()
                .review(review)
                .star(foundStar)
                .barber(foundBarber)
                .build();
        return reviewDao.save(newReview);
    }

    public void delete(Long id){
        reviewDao.deleteById(id);
    }

    public List<Review> findById(long id){
        return reviewDao.findByIdOrderByDateDesc(id);
    }
}
