package com.aacademy.realestate24temaesercise.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String store(MultipartFile multipartFile);  //интерфейс от Спринг, който върши работа за файлове - качване, изтриване  и т.н
}
