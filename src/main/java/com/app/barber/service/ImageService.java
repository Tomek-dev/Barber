package com.app.barber.service;

import com.app.barber.dao.BarberDao;
import com.app.barber.dao.ImageDao;
import com.app.barber.model.Barber;
import com.app.barber.model.Image;
import com.app.barber.other.dto.ImageDto;
import com.app.barber.other.exception.BarberNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImageService {

    private ImageDao imageDao;

    @Autowired
    public ImageService(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    public List<ImageDto> getByBarber(Long id){
        return imageDao.findByBarberId(id).stream()
                .map(image -> new ImageDto(image.getUrl()))
                .collect(Collectors.toList());
    }

    public void create(String url, Barber barber){
        Image image = new Image.Builder()
                .barber(barber)
                .url(url)
                .build();
        imageDao.save(image);
    }
}
