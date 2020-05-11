package com.app.barber.controller;

import com.app.barber.model.User;
import com.app.barber.other.dto.ImageDto;
import com.app.barber.other.exception.UploadException;
import com.app.barber.service.ImageService;
import com.app.barber.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ImageController {

    private ImageService imageService;
    private UploadService uploadService;

    @Autowired
    public ImageController(ImageService imageService, UploadService uploadService) {
        this.imageService = imageService;
        this.uploadService = uploadService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/image")
    public void upload(@RequestParam MultipartFile file, @AuthenticationPrincipal User user){
        Map uploadResult = null;
        try {
            uploadResult = uploadService.upload(file);
        } catch (IOException e) {
            throw new UploadException();
        }
        imageService.create(uploadResult.get("url").toString(), user.getBarber());
    }

    @GetMapping("/image/{id}")
    public List<ImageDto> getByBarber(@PathVariable Long id){
        return imageService.getByBarber(id);
    }
}
