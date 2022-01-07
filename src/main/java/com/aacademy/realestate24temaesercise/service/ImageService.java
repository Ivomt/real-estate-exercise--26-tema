package com.aacademy.realestate24temaesercise.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void createImage(MultipartFile file,Long estateId);
}
