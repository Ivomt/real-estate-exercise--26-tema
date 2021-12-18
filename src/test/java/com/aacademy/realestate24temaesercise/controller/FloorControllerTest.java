package com.aacademy.realestate24temaesercise.controller;

import com.aacademy.realestate24temaesercise.converter.FloorConverter;
import com.aacademy.realestate24temaesercise.dto.FloorDto;
import com.aacademy.realestate24temaesercise.model.Floor;
import com.aacademy.realestate24temaesercise.service.FloorService;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;


import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = FloorController.class)
// за да разбере Spring, че това е unit test и пишем кой контролер ще тества
public class FloorControllerTest extends BaseControllerTest {

    @MockBean
    private FloorService floorService;

    @MockBean
    private FloorConverter floorConverter;

    @Test
    void save() throws Exception {          //юнит тест за контролер-задължително искат throws Exception

        FloorDto floorDto = FloorDto.builder().number(1).build();    //създава се Дто
        String reqJson = objectMapper.writeValueAsString(floorDto);  //objectMapper служи да го направи като jSon

        when(floorConverter.toFloor(any(FloorDto.class))).thenReturn(Floor.builder().build());
        when(floorService.save(any(Floor.class))).thenReturn(Floor.builder().build());
        when(floorConverter.toFloorDto(any(Floor.class))).thenReturn(FloorDto.builder().id(1L).number(1).build());

        mockMvc.perform(post("/floors")    //mockMvc - мокнат външен клиент
                        .content(reqJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())     //проверява дали ендпоинта ще върне статус 200
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //проверявадали самия контент json format
                .andExpect(jsonPath("$.id", is(1))) //проверява дали id-то е същото, което сме муказали - 1L
                .andExpect(jsonPath("$.number", is(1)));
    }

    @Test
    public void findById() throws Exception {

        when(floorService.findById(any(Long.class))).thenReturn(Floor.builder().build());
        when(floorConverter.toFloorDto(any(Floor.class))).thenReturn(FloorDto.builder().id(1L).number(5).build());

        mockMvc.perform(get("floors/id/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.number", is(5)));
    }

    @Test
    public void deleteHappy() throws Exception {
        mockMvc.perform(delete("/floors/1"))
                .andExpect(status().isOk());

    }

    @Test
    public void updateHappy() throws Exception {
        FloorDto floorDto = FloorDto.builder().id(1L).number(2).build();
        String reqJson = objectMapper.writeValueAsString(floorDto);

        when(floorConverter.toFloorDto(any(Floor.class))).thenReturn(floorDto);
                mockMvc.perform(put("/floors/1")
                        .content(reqJson)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.id",is(1)))
                        .andExpect(jsonPath("$.number",is(2)));

    }
}