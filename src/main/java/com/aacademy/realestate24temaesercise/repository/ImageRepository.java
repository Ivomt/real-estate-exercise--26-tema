package com.aacademy.realestate24temaesercise.repository;

import com.aacademy.realestate24temaesercise.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
}
