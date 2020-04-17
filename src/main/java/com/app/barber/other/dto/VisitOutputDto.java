package com.app.barber.other.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VisitOutputDto {

    private Long id;

    private String name;

    private String workerName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMMM yyyy hh:mm")
    private LocalDateTime beginning;

    private String serviceName;

    private Double servicePrice;
}
