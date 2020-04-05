package com.app.barber.controller;

import com.app.barber.other.dto.ImageDto;
import com.app.barber.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ImageController {

    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/image/{id}")
    public List<ImageDto> getByBarber(@PathVariable Long id){
        return imageService.getByBarber(id);
    }
}
