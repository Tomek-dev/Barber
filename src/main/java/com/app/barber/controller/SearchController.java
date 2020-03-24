package com.app.barber.controller;

import com.app.barber.other.dto.BarberOutputDto;
import com.app.barber.other.specification.SearchSpecification;
import com.app.barber.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public List<BarberOutputDto> search(SearchSpecification specification){
        return searchService.search(specification);
    }
}
