package com.aacademy.realestate24temaesercise.controller;

import com.aacademy.realestate24temaesercise.converter.EstateConverter;
import com.aacademy.realestate24temaesercise.dto.EstateDto;
import com.aacademy.realestate24temaesercise.dto.EstateResponse;
import com.aacademy.realestate24temaesercise.model.Estate;
import com.aacademy.realestate24temaesercise.service.EstateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/estates")
@AllArgsConstructor
public class EstateController {

    private final EstateService estateService;
    private final EstateConverter estateConverter;

    @PostMapping
    public ResponseEntity<EstateResponse> save(@RequestBody @Valid EstateDto estateDto){
        Estate estate = estateConverter.toEstate(estateDto);
        Estate savedEstate = estateService.save(estate);
        return ResponseEntity.ok(estateConverter.toEstateResponse(savedEstate));
    }
    @GetMapping
    public ResponseEntity<Set<EstateResponse>> findAll(){
        return ResponseEntity.ok(estateService.findAll().stream().map(estateConverter::toEstateResponse).collect(Collectors.toSet()));
    }
}