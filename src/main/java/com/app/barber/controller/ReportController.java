package com.app.barber.controller;

import com.app.barber.other.dto.ReportDto;
import com.app.barber.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReportController {

    private ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/report/{id}")
    public void report(@PathVariable Long id, ReportDto dto){
        reportService.add(id, dto);
    }
}
