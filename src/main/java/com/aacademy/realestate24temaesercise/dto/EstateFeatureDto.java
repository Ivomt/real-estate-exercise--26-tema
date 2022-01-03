package com.aacademy.realestate24temaesercise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class EstateFeatureDto {

    private Long id;

    private String feature;
}
