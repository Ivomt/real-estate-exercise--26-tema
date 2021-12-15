package com.aacademy.realestate24temaesercise.service.impl.func;

import com.aacademy.realestate24temaesercise.model.Floor;
import com.aacademy.realestate24temaesercise.repository.FloorRepository;
import com.aacademy.realestate24temaesercise.service.FloorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


@SpringBootTest                         // functional test
@RunWith(SpringRunner.class)
public class FloorServiceFuncTest {
    @Autowired
    private FloorService floorService;

    @Autowired
    private FloorRepository floorRepository;

    @Test
    public void verifyUpdate(){

        Floor floor = Floor.builder().number(1).build();
        Floor savedFloor = floorRepository.save(floor);
        Floor expected = Floor.builder()
                .id(savedFloor.getId())
                .number(2)
                .build();

        Floor actual = floorService.update(savedFloor, savedFloor.getId());

        assertThat(expected.getId(), is(actual.getId()));
        assertThat(expected.getNumber(), is(actual.getNumber()));
    }
}

