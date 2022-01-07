package com.aacademy.realestate24temaesercise.service.impl;

import com.aacademy.realestate24temaesercise.exception.ResourceNotFoundException;
import com.aacademy.realestate24temaesercise.repository.EstateRepository;
import com.aacademy.realestate24temaesercise.repository.ImageRepository;
import com.aacademy.realestate24temaesercise.service.ImageService;
import com.aacademy.realestate24temaesercise.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.module.ResolutionException;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final StorageService storageService;
    private final ImageRepository imageRepository;
    private final EstateRepository estateRepository;

    @Override
    public void createImage(MultipartFile file, Long estateId) {
        estateRepository.findById(estateId).orElseThrow(()->new ResourceNotFoundException("Estate does not exists with id"+ " git"));

    }
}
