package com.aacademy.realestate24temaesercise.repository;

import com.aacademy.realestate24temaesercise.model.EstateFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // служи като документация, не е задължителна, ако се хвърлят ексепшъни - по-добре ги описва
public interface EstateFeatureRepository extends JpaRepository <EstateFeature,Long>{

    Optional<EstateFeature>findByFeature(String feature);
}
