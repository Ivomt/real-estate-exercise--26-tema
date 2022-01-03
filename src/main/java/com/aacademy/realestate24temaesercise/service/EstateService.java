package com.aacademy.realestate24temaesercise.service;

import com.aacademy.realestate24temaesercise.model.Estate;

import java.util.Set;

public interface EstateService {
    Estate save(Estate estate);

    Set<Estate>findAll();
}
