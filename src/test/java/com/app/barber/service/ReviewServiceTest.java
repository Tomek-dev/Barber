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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReviewServiceTest {

    @Mock
    private ReviewDao reviewDao;

    @Mock
    private BarberDao barberDao;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    public void shouldThrowBarberNotFoundException(){
        //given
        given(barberDao.findById(Mockito.any())).willReturn(Optional.empty());

        //then
        assertThrows(BarberNotFoundException.class, () -> reviewService.add(new ReviewInputDto(), 4L));
    }

    @Test
    public void shouldAdd(){
        //given
        Barber barber = new Barber();
        ReviewInputDto review = new ReviewInputDto();
        review.setStar(5);
        review.setReview("review");
        given(barberDao.findById(Mockito.any())).willReturn(Optional.of(barber));

        //when
        reviewService.add(review, 4L);

        //then
        verify(reviewDao).save(any());
    }

    @Test
    public void shouldThrowStarNotFoundException(){
        //given
        Barber barber = new Barber();
        ReviewInputDto review = new ReviewInputDto();
        review.setStar(10);
        review.setReview("review");
        given(barberDao.findById(Mockito.any())).willReturn(Optional.of(barber));

        //then
        assertThrows(StarNotFoundException.class, () -> reviewService.add(review, 4L));
    }

    @Test
    public void shouldReturnListOfReviewOutputDto(){
        //given
        LocalDateTime date = LocalDateTime.now();
        Review review = ReviewBuilder.builder()
                .date(date)
                .star(Star.FOUR)
                .review("review")
                .build();
        given(reviewDao.findByBarberId(Mockito.any())).willReturn(Collections.singletonList(review));

        //when
        List<ReviewOutputDto> reviews = reviewService.findById(4L);

        //then
        assertEquals("review", reviews.get(0).getReview());
        assertEquals(date, reviews.get(0).getDate());
        assertEquals(Star.FOUR, reviews.get(0).getStar());
    }

    @Test
    public void shouldDelete(){
        //when
        reviewService.delete(4L);

        //then
        verify(reviewDao).deleteById(4L);
    }

    @Test
    public void shouldReturnAverage(){
        //given
        List<Review> reviews = new LinkedList<>();
        Review review1 = ReviewBuilder.builder()
                .star(Star.FOUR)
                .build();
        reviews.add(review1);
        Review review2 = ReviewBuilder.builder()
                .star(Star.FIVE)
                .build();
        reviews.add(review2);
        given(reviewDao.findByBarberId(Mockito.any())).willReturn(reviews);

        //then
        assertEquals(4.5, reviewService.averageByBarber(4L));
    }
}