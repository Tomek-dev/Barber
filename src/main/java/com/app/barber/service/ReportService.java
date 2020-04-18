package com.app.barber.service;

import com.app.barber.dao.ReportDao;
import com.app.barber.dao.ReviewDao;
import com.app.barber.model.Report;
import com.app.barber.model.Review;
import com.app.barber.other.dto.ReportDto;
import com.app.barber.other.enums.Reason;
import com.app.barber.other.exception.EnumNotExistException;
import com.app.barber.other.exception.ReviewNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportService {

    private ReportDao reportDao;
    private ReviewDao reviewDao;

    @Autowired
    public ReportService(ReportDao reportDao, ReviewDao reviewDao) {
        this.reportDao = reportDao;
        this.reviewDao = reviewDao;
    }

    public void add(Long id, ReportDto reportDto){
        Optional<Review> reviewOptional = reviewDao.findById(id);
        Review review = reviewOptional.orElseThrow(ReviewNotFoundException::new);
        Optional<Reason> reasonOptional = Reason.fromValue(reportDto.getReason());
        Reason reason = reasonOptional.orElseThrow(EnumNotExistException::new);
        Report report = new Report.Builder()
                .reason(reason)
                .review(review)
                .build();
        reportDao.save(report);
    }
}
