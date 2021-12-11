package com.aacademy.realestate24temaesercise.service;

import com.aacademy.realestate24temaesercise.model.City;

import java.util.Set;

public interface CityService {
    City findById(Long id);
    City save (City city);
    Set<City>findAll();
    City update (City city, Long id);
    void detachCityNeighborhood(Long cityId,Set<Long>neighborhoodIds);
}
