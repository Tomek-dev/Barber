package com.app.barber.other.dto;

import com.app.barber.other.enums.Star;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewOutputDto {

    private String review;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMMM yyyy", locale = "English")
    private LocalDateTime date;
    private Integer star;
    private String workerName;
    private String serviceName;
    private String ownerName;
    private String ownerImageUrl;
    private Long id;

    public void setStar(Star star) {
        this.star = star.getNumber();
    }
}
