package com.app.barber.service;

import com.app.barber.dao.BarberDao;
import com.app.barber.dao.ReviewDao;
import com.app.barber.dao.VisitDao;
import com.app.barber.model.*;
import com.app.barber.other.builder.*;
import com.app.barber.other.dto.ReviewInfoDto;
import com.app.barber.other.dto.ReviewInputDto;
import com.app.barber.other.dto.ReviewOutputDto;
import com.app.barber.other.enums.Star;
import com.app.barber.other.exception.EnumNotExistException;
import com.app.barber.other.exception.VisitNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

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
    private VisitDao visitDao;

    @Mock
    private BarberDao barberDao;

    @InjectMocks
    private ReviewService reviewService;

    private Visit visit;
    private Service service;
    private Worker worker;
    private OAuthUser user;

    @Before
    public void init(){
        service = ServiceBuilder.builder()
                .name("name")
                .build();
        worker = WorkerBuilder.builder()
                .name("name")
                .build();
        visit = VisitBuilder.builder()
                .service(service)
                .worker(worker)
                .barber(new Barber())
                .customer(new OAuthUser())
                .build();
        user = new OAuthUser();
    }

    @Test
    public void shouldThrowVisitNotFoundException(){
        //given
        given(visitDao.findById(Mockito.any())).willReturn(Optional.empty());

        //then
        assertThrows(VisitNotFoundException.class, () -> reviewService.add(new ReviewInputDto(), 4L, user));
    }

    @Test
    public void shouldAdd(){
        //given
        ReviewInputDto review = new ReviewInputDto();
        review.setStar(5);
        review.setReview("review");
        given(visitDao.findById(Mockito.any())).willReturn(Optional.of(visit));

        //when
        reviewService.add(review, 4L, user);

        //then
        verify(reviewDao).save(any());
    }

    @Test
    public void shouldThrowStarNotFoundException(){
        //given
        ReviewInputDto review = new ReviewInputDto();
        review.setStar(10);
        review.setReview("review");
        given(visitDao.findById(Mockito.any())).willReturn(Optional.of(visit));

        //then
        assertThrows(EnumNotExistException.class, () -> reviewService.add(review, 4L, user));
    }

    @Test
    public void shouldReturnListOfReviewOutputDto(){
        //given
        LocalDateTime date = LocalDateTime.now();
        OAuthUser owner = OAuthUserBuilder.builder()
                .imageUrl("url")
                .name("name")
                .build();
        Review review = ReviewBuilder.builder()
                .date(date)
                .star(Star.FOUR)
                .review("review")
                .worker(worker)
                .service(service)
                .owner(owner)
                .build();
        given(reviewDao.findByBarberId(Mockito.any(), Mockito.any())).willReturn(new PageImpl<>(Collections.singletonList(review)));

        //when
        List<ReviewOutputDto> reviews = reviewService.findById(4L, PageRequest.of(0, 10));

        //then
        assertEquals("review", reviews.get(0).getReview());
        assertEquals("name", reviews.get(0).getWorkerName());
        assertEquals("name", reviews.get(0).getServiceName());
        assertEquals("name", reviews.get(0).getOwnerName());
        assertEquals("url", reviews.get(0).getOwnerImageUrl());
        assertEquals(date, reviews.get(0).getDate());
        assertEquals(4, reviews.get(0).getStar());
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

        ///when
        ReviewInfoDto dto = reviewService.reviewInfo(4L);

        //then
        assertEquals(4.5, dto.getAverage());
        assertEquals(2, dto.getCount());
    }
}