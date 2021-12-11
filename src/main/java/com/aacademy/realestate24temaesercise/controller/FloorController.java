package com.aacademy.realestate24temaesercise.controller;

import com.aacademy.realestate24temaesercise.converter.FloorConverter;
import com.aacademy.realestate24temaesercise.dto.FloorDto;
import com.aacademy.realestate24temaesercise.model.Floor;
import com.aacademy.realestate24temaesercise.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/floors")
// означава, че всеки един мапинг, който направим, ще започва с /floors, без значение дали е get, post или update - в postman se мапва
public class FloorController {

    private final FloorService floorService;
    private final FloorConverter floorConverter;

    @Autowired
    public FloorController(FloorService floorService, FloorConverter floorConverter) {
        this.floorService = floorService;
        this.floorConverter = floorConverter;
    }

    @GetMapping
    public ResponseEntity<Set<FloorDto>> findAll() {
        Set<FloorDto> floorDtos = new HashSet<>();
        Set<Floor> floors = floorService.findAll();   // колекция, която се връща от сървиса

        for (Floor floor : floors) {
            FloorDto floorDto = floorConverter.toFloorDto(floor);
            floorDtos.add(floorDto);
        }

        // или същото нещо се пише по следния начин:
        //    return ResponseEntity.ok(floorService
        //    .findAll()
        //      .stream()
        //      .map(floorConverter::toFloorDto)
        //     .collect(Collectors.toSet())); - не е необходим долния ретърн

        return ResponseEntity.ok(floorDtos);
    }
    @GetMapping(value = "id/{id}")
    public ResponseEntity <FloorDto>findById(@PathVariable Long id){
        return ResponseEntity.ok(floorConverter.toFloorDto(floorService.findById(id)));
    }
    @GetMapping(value = "number/{number}")
    public ResponseEntity<FloorDto>findByNumber(@PathVariable Integer number){
        return ResponseEntity.ok(floorConverter.toFloorDto(floorService.findByNumber(number)));
    }
    @PutMapping(value = "/{id}")                      //ъпдейт
    public ResponseEntity<FloorDto>update(@RequestBody @Valid FloorDto floorDto,@PathVariable Long id){
        Floor floor = floorConverter.toFloor(floorDto);
        Floor updatedFloor = floorService.update(floor,id);
        return ResponseEntity.ok(floorConverter.toFloorDto(updatedFloor));
    }

    @PostMapping
    public ResponseEntity<FloorDto> save(@RequestBody @Valid FloorDto floorDto) {

        Floor floor = floorConverter.toFloor(floorDto);
        Floor savedFloor = floorService.save(floor);
        return ResponseEntity.ok(floorConverter.toFloorDto(savedFloor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        floorService.delete(id);
        return ResponseEntity.ok().build();

    }

}
