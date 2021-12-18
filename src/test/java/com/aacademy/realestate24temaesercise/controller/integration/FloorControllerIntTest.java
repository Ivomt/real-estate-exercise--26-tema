package com.aacademy.realestate24temaesercise.controller.integration;

import com.aacademy.realestate24temaesercise.dto.FloorDto;
import com.aacademy.realestate24temaesercise.model.Floor;
import com.aacademy.realestate24temaesercise.repository.FloorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)                  // integration test
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FloorControllerIntTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private FloorRepository floorRepository;

    @Test
    public void saveFloor() throws JsonProcessingException {

        FloorDto floorDto = FloorDto.builder().number(5).build();
        String jSonRequest = objectMapper.writeValueAsString(floorDto);    //това се вика, за да ни направи този рекуест toString

        given()
                .contentType(ContentType.APPLICATION_JSON.toString())
                .body(jSonRequest)
                .when()
                .post("http://localhost:8080/floors")
                .then()
                .statusCode(200)
                .body("id",equalTo(1))
                .body("number",equalTo(5));
    }
    @Test
    public void updateFloor () throws Exception{
        floorRepository.save(Floor.builder().number(1).build());
        FloorDto floorDto=FloorDto.builder().id(1L).number(5).build();
        String jsonRequest = objectMapper.writeValueAsString(floorDto);

        given()
                .contentType(ContentType.APPLICATION_JSON.toString())
                .body(jsonRequest)
                .when()
                .put("http://localhost:8080/floors/1")
                .then()
                .statusCode(200)
                .body("id",equalTo(1))
                .body("number",equalTo(5));
    }
    @Test
    public void findByNumber() throws Exception{
        floorRepository.save(Floor.builder().number(1).build());

        given()
                .contentType(ContentType.APPLICATION_JSON.toString())
                .when()
                .get("http://localhost:8080/floors/number/1")
                .then()
                .statusCode(200)
                .body("id",equalTo(1))
                .body("number",equalTo(1));
    }
}
