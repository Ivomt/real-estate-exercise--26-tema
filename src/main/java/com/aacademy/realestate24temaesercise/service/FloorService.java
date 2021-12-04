package com.aacademy.realestate24temaesercise.service;

import com.aacademy.realestate24temaesercise.model.Floor;

import java.util.List;
import java.util.Set;

public interface FloorService {  // тук ще има crud (create,read, update, delete) операции

    Floor save(Floor floor);

    Floor findByNumber (Integer number);

    Floor findById (Long id);

    Floor update (Floor floor, Long id);

    void delete (Long id);

    Set<Floor> findAll();

}
