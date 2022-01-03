package com.aacademy.realestate24temaesercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc      //за да ни го конфигрира Spring, a не ние
public abstract class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvc;   //protected e, защото ще се използва в други класове (абстракт е този), Floor ще наследи този BaseController
                                // и ще може да си прави заявки към endpoint (postmaping, getmaping...)

    @Autowired
    protected ObjectMapper objectMapper;
}
