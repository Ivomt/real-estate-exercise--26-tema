package com.aacademy.realestate24temaesercise.service.impl;

import com.aacademy.realestate24temaesercise.exception.DuplicateRecordException;
import com.aacademy.realestate24temaesercise.exception.ResourceNutFoundException;
import com.aacademy.realestate24temaesercise.model.Floor;
import com.aacademy.realestate24temaesercise.repository.FloorRepository;
import com.aacademy.realestate24temaesercise.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FloorServiceImpl implements FloorService {

    private final FloorRepository floorRepository;

    @Autowired
    public FloorServiceImpl(FloorRepository floorRepository) {

        this.floorRepository = floorRepository;
    }

    @Override
    public Floor save(Floor floor) {
        try {
            return floorRepository.save(floor);
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateRecordException(String.format("Floor with number %d already exists", floor.getNumber()));  //хвърляме наша си грешка
        }

    }

    @Override
    public Floor findByNumber(Integer number) {
        return floorRepository.findByNumber(number)
                .orElseThrow(() -> new ResourceNutFoundException(String.format("Floor %d does not exists", number)));
    }

    @Override
    public Floor findById(Long id) {
        return floorRepository.findById(id)     // проверява дали го има в базата - id-то
                .orElseThrow(() -> new ResourceNutFoundException(String.format("Floor with id %d does not exists", id)));  // ако го няма - хвърля грвшка
    }

    @Override
    public Floor update(Floor floor, Long id) {

       Floor foundFloor=findById(id);          //намираш етажа от базата данни, който ти идва
       Floor updatedFloor = Floor.builder()   //създаваш нов обект
               .id(foundFloor.getId())        //слагаш неговото id
               .number(floor.getNumber())     // слагаш номера, който искаш да ъпдейтнеш
               .build();

       return save(updatedFloor);           // сейфаш
    }

    @Override
    public void delete(Long id) {
        floorRepository.deleteById(id);
    }

    @Override
    public Set<Floor> findAll() {

        return new HashSet<>(floorRepository.findAll());        //за да вземем всички  етажи
    }
}
