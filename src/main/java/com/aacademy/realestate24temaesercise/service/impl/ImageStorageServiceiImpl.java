package com.aacademy.realestate24temaesercise.service.impl;

import com.aacademy.realestate24temaesercise.exception.StorageException;
import com.aacademy.realestate24temaesercise.service.StorageService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ImageStorageServiceiImpl implements StorageService {

    private final List<String> SUPPORTED_FILE_EXTENSIONS = Arrays.asList("jpg", "png");

    private final ResourceLoader resourceLoader;   // върши работа за файла - да го записва в нашата система

    @Override
    public String store(MultipartFile file) {
        Objects.requireNonNull(file);  //статичен метод от клас Objects, който проверява дали file e null хвърля
        String newFileName = generateFileName(file.getOriginalFilename());

        try {
            if (file.isEmpty() || ImageIO.read(file.getInputStream()) == null || !FilenameUtils.isExtension(file.getOriginalFilename(), SUPPORTED_FILE_EXTENSIONS)) {
                throw new StorageException();
            }

            File destination = FileUtils.getFile("D:\\Java проекти\\Pictures In Project Real Estate\\", newFileName);
            file.transferTo(destination);   // това ще запише файла в показаната по-горе дестинация

        } catch (IOException ioException) {
            throw new StorageException();
        }


        return null;
    }

    private String generateFileName(String originalFileName) {
        String uuid = UUID.randomUUID().toString();   // генерира рандом юник идентифайър и той винаги е уникален
        String fileExtension = FilenameUtils.getExtension(originalFileName);

        return String.format("%s.%s", uuid, fileExtension);
    }

}
