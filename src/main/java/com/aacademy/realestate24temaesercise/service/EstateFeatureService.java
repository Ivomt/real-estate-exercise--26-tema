package com.aacademy.realestate24temaesercise.service;

import com.aacademy.realestate24temaesercise.model.EstateFeature;

public interface EstateFeatureService {
    EstateFeature save(EstateFeature estateFeature);

    EstateFeature findByFeature(String feature);

    EstateFeature findById(Long Id);
}
