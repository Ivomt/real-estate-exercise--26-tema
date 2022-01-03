package com.aacademy.realestate24temaesercise.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable   // в модела Estate се слага Embeded анотация и се създават в таблица Estate 2 допълнителни колони - builtUpArea и pureArea
public class Quadrature {

    @Column(precision = 5,scale = 2,nullable = false)
    private BigDecimal builtUpArea;

    @Column(precision = 5,scale = 2,nullable = false)
    private BigDecimal pureArea;

}
