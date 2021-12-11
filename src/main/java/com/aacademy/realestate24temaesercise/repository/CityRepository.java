package com.aacademy.realestate24temaesercise.repository;

import com.aacademy.realestate24temaesercise.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
