package com.aacademy.realestate24temaesercise.service;

import com.aacademy.realestate24temaesercise.model.Neighborhood;
import jdk.dynalink.linker.LinkerServices;

import java.util.Set;

public interface NeighborhoodService {
    Neighborhood findByName(String name);
    Neighborhood findById(Long id);

    Set<Neighborhood> findAll();

    Neighborhood save (Neighborhood neighborhood);
}
