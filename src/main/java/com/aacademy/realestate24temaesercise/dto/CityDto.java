package com.aacademy.realestate24temaesercise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class    CityDto {

    private Long id;

    private String name;

    private Set<Long>neighborhoodIds;
}
