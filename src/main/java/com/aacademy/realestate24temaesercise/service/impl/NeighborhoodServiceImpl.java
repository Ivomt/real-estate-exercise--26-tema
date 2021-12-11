package com.aacademy.realestate24temaesercise.service.impl;

import com.aacademy.realestate24temaesercise.exception.ResourceNutFoundException;
import com.aacademy.realestate24temaesercise.model.Neighborhood;
import com.aacademy.realestate24temaesercise.repository.NeighborhoodRepository;
import com.aacademy.realestate24temaesercise.service.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class NeighborhoodServiceImpl implements NeighborhoodService {

    private final NeighborhoodRepository neighborhoodRepository;

    @Autowired
    public NeighborhoodServiceImpl(NeighborhoodRepository neighborhoodRepository) {
        this.neighborhoodRepository = neighborhoodRepository;
    }


    @Override
    public Neighborhood findByName(String name) {
        return neighborhoodRepository.findByName(name)
                .orElseThrow(()->new ResourceNutFoundException(String.format("Neighborhood with name: %s does not exists", name)));
    }

    @Override
    public Neighborhood findById(Long id) {
        return neighborhoodRepository.findById(id)
                .orElseThrow(()->new ResourceNutFoundException(String.format("Neighborhood with id: %s does not exists", id)));
    }

    @Override
    public Set<Neighborhood> findAll() {
        return new HashSet<>(neighborhoodRepository.findAll()); //иска да е се сложи в HashSet, защото findAll връща лист
    }

    @Override
    public Neighborhood save(Neighborhood neighborhood) {
        return neighborhoodRepository.save(neighborhood);
    }


}
