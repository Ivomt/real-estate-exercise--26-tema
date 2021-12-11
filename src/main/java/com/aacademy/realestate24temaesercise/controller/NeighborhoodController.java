package com.aacademy.realestate24temaesercise.controller;

import com.aacademy.realestate24temaesercise.converter.NeighborhoodConverter;
import com.aacademy.realestate24temaesercise.dto.NeighborhoodDto;
import com.aacademy.realestate24temaesercise.model.Neighborhood;
import com.aacademy.realestate24temaesercise.service.NeighborhoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/neighborhoods")          //анотация за добрите практики
public class NeighborhoodController {

    private final NeighborhoodService neighborhoodService;
    private final NeighborhoodConverter neighborhoodConverter;

    public NeighborhoodController(NeighborhoodService neighborhoodService, NeighborhoodConverter neighborhoodConverter) {
        this.neighborhoodService = neighborhoodService;
        this.neighborhoodConverter = neighborhoodConverter;
    }
    @GetMapping
    public ResponseEntity<Set<NeighborhoodDto>> findAll() {  // ResponseEntity.ок - дава достъп до това какво ще връщаме като статус - 200,401
                                                          // Set<NeighborhoodDto> - това е другият вариант, но не контролираме статуса

        return ResponseEntity.ok(neighborhoodService
                    .findAll()
                      .stream()
                      .map(neighborhoodConverter::toNeighborhoodDto)
                     .collect(Collectors.toSet()));
    }
    @GetMapping(value = "/{name}")
    public ResponseEntity<NeighborhoodDto>findByName(@PathVariable String name){
        Neighborhood neighborhood = neighborhoodService.findByName(name);
        NeighborhoodDto neighborhoodDto = neighborhoodConverter.toNeighborhoodDto(neighborhood);
        return ResponseEntity.ok(neighborhoodDto);
    }
    @PostMapping
    public ResponseEntity<NeighborhoodDto>save(@RequestBody NeighborhoodDto neighborhoodDto){
        return ResponseEntity.ok(neighborhoodConverter.toNeighborhoodDto
                (neighborhoodService.save(neighborhoodConverter.toNeighborhood(neighborhoodDto))));
    }
}
