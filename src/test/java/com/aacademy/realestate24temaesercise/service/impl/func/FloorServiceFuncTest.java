package com.aacademy.realestate24temaesercise.service.impl.func;

import com.aacademy.realestate24temaesercise.exception.DuplicateRecordException;
import com.aacademy.realestate24temaesercise.exception.ResourceNotFoundException;
import com.aacademy.realestate24temaesercise.model.Floor;
import com.aacademy.realestate24temaesercise.repository.FloorRepository;
import com.aacademy.realestate24temaesercise.service.FloorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


@SpringBootTest                         // functional test
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FloorServiceFuncTest {
    @Autowired
    private FloorService floorService;

    @Autowired
    private FloorRepository floorRepository;

    @Test
    public void verifyUpdate() {

        Floor floor = Floor.builder().number(1).build();
        Floor savedFloor = floorRepository.save(floor);
        Floor expected = Floor.builder()
                .id(savedFloor.getId())
                .number(1)
                .build();

        Floor actual = floorService.update(savedFloor, savedFloor.getId());

        assertThat(expected.getId(), is(actual.getId()));
        assertThat(expected.getNumber(), is(actual.getNumber()));
    }

    @Test
    public void verifyFindById() {
        Floor floor = Floor.builder().number(1).build();
        Floor expected = floorRepository.save(floor);
        Floor actual = floorService.findById(expected.getId());

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getNumber(), actual.getNumber());
    }

    @Test
    public void verifyFindAll() {
        floorRepository.saveAll(Arrays.asList(Floor.builder().number(1).build(),
                Floor.builder().number(2).build()));
        Set<Floor> actual = floorService.findAll();
        assertThat(actual.size(), is(2));
    }

    @Test
    public void verifySave() {
        Floor savedFloor = floorService.save(Floor.builder().number(1).build());
        Optional<Floor> actualFloor = floorRepository.findById(savedFloor.getId());
        assertTrue(actualFloor.isPresent());
    }

    @Test
    public void verifyDeleteById() {
        Floor savedFloor = floorRepository.save(Floor.builder().number(1).build());
        floorService.delete(savedFloor.getId());
        List<Floor> actual = floorRepository.findAll();

        assertThat(actual.size(), is(0));
    }

    @Test
    public void verifyFindByNumber() {
        Floor savedFloor = floorRepository.save(Floor.builder().number(1).build());
        Floor actual = floorService.findByNumber(1);
        assertEquals(actual.getNumber(), (savedFloor.getNumber()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void verifyFindByNumberException() {
        floorService.findByNumber(1);
    }

    @Test(expected = DuplicateRecordException.class)
    public void verifySaveDuplicateException() {

        floorService.save(Floor.builder().number(1).build());
        floorService.save(Floor.builder().number(1).build());
    }
}

