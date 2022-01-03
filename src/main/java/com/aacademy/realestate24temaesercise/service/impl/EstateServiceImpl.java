package com.aacademy.realestate24temaesercise.service.impl;

import com.aacademy.realestate24temaesercise.model.City;
import com.aacademy.realestate24temaesercise.model.Estate;
import com.aacademy.realestate24temaesercise.model.EstateFeature;
import com.aacademy.realestate24temaesercise.model.Floor;
import com.aacademy.realestate24temaesercise.repository.EstateRepository;
import com.aacademy.realestate24temaesercise.service.CityService;
import com.aacademy.realestate24temaesercise.service.EstateService;
import com.aacademy.realestate24temaesercise.service.FloorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class EstateServiceImpl implements EstateService {

    private final EstateRepository estateRepository;
    private final EstateFeatureServiceImpl estateFeatureService;
    private final CityService cityService;
    private final FloorService floorService;

    @Override
    public Estate save(Estate estate) {

        Set<EstateFeature> estateFeatures = new HashSet<>();
        for (EstateFeature estateFeature : estate.getEstateFutures()) {
            EstateFeature foundEstateFeature = estateFeatureService.findById(estateFeature.getId());
            estateFeatures.add(foundEstateFeature);

        }
        City city = cityService.findById(estate.getCity().getId());
        Floor floor = floorService.findById(estate.getFloor().getId());

        return estateRepository.save(Estate.builder()
                .id(estate.getId())
                .quadrature(estate.getQuadrature())
                .description(estate.getDescription())
                .floor(floor)
                .city(city)
                .estateFutures(estateFeatures)
                .build());
    }

    @Override
    public Set<Estate> findAll() {
        return new HashSet<>(estateRepository.findAll());
    }
}
