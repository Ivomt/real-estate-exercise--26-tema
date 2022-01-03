package com.aacademy.realestate24temaesercise.service.impl;

import com.aacademy.realestate24temaesercise.exception.ResourceNotFoundException;
import com.aacademy.realestate24temaesercise.model.City;
import com.aacademy.realestate24temaesercise.model.Neighborhood;
import com.aacademy.realestate24temaesercise.repository.CityRepository;
import com.aacademy.realestate24temaesercise.service.CityService;
import com.aacademy.realestate24temaesercise.service.NeighborhoodService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final NeighborhoodService neighborhoodService;

    public CityServiceImpl(CityRepository cityRepository, NeighborhoodService neighborhoodService) {
        this.cityRepository = cityRepository;
        this.neighborhoodService = neighborhoodService;
    }

    @Override
    public City findById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(String.format("City with id %d does not exists.",id)));
    }

    @Override
    public City save(City city) {
        Set<Neighborhood> neighborhoods = new HashSet<>();
        for (Neighborhood neighborhood : city.getNeighborhoods()) {
            Neighborhood foundNeighborhood = neighborhoodService.findById(neighborhood.getId());
            neighborhoods.add(foundNeighborhood);
        }
        return cityRepository.save(City.builder()
                .name(city.getName())
                .neighborhoods(neighborhoods)
                .build());

    }

    @Override
    public Set<City> findAll() {
        return null;
    }

    @Override
    public City update(City city, Long id) {
        City foundCity = findById(id);     //това е записа от базата
        City cityToUpdate=City.builder()
                .id(foundCity.getId())
                .neighborhoods(city.getNeighborhoods())
                .build();
         return cityRepository.save(cityToUpdate);

    }

    @Override
    public void detachCityNeighborhood(Long cityId, Set<Long> neighborhoodIds) {
        City foundCity = findById(cityId);
        foundCity.getNeighborhoods()
                .removeIf(neighborhood -> neighborhoodIds.contains(neighborhood.getId()));
        cityRepository.save(foundCity);

    }

}
