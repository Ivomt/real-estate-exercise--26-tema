package com.aacademy.realestate24temaesercise.converter;

import com.aacademy.realestate24temaesercise.dto.FloorDto;
import com.aacademy.realestate24temaesercise.dto.NeighborhoodDto;
import com.aacademy.realestate24temaesercise.model.Floor;
import com.aacademy.realestate24temaesercise.model.Neighborhood;
import org.springframework.stereotype.Component;

@Component
public class NeighborhoodConverter {
    public NeighborhoodDto toNeighborhoodDto(Neighborhood neighborhood){
        return NeighborhoodDto.builder()    //билдваме Neighborhood обект
                .id(neighborhood.getId())
                .name(neighborhood.getName())
                .build();
    }

    public Neighborhood toNeighborhood(NeighborhoodDto neighborhoodDto){ // обратно на горния метод
        return Neighborhood.builder()
                .id(neighborhoodDto.getId())
                .name(neighborhoodDto.getName())
                .build();
    }
}
