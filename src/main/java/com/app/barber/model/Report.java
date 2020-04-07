package com.app.barber.model;

import com.app.barber.other.enums.Reason;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Review review;

    private LocalDateTime date;

    private Reason reason;

    private Report() {
    }

    public static class Builder{
        Report report = new Report();

        public Builder review(Review review){
            report.review = review;
            return this;
        }

        public Builder reason(Reason reason){
            report.reason = reason;
            return this;
        }

        public Report build(){
            report.date = LocalDateTime.now();
            return report;
        }
    }
}
