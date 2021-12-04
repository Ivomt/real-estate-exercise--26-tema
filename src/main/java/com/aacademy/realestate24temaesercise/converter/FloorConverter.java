package com.aacademy.realestate24temaesercise.converter;

import com.aacademy.realestate24temaesercise.dto.FloorDto;
import com.aacademy.realestate24temaesercise.model.Floor;
import org.springframework.stereotype.Component;

@Component
public class FloorConverter {

    public FloorDto toFloorDto(Floor floor){  //от модела Floor искаме да върнем FloorDto,
        return FloorDto.builder()               //конвертираме от един клас в друг
                .number(floor.getNumber())
                .build();
    }

    public Floor toFloor(FloorDto floorDto){ // обратно на горния метод
        return Floor.builder()
                .number(floorDto.getNumber())
                .build();
    }
}
