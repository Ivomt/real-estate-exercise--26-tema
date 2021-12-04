package com.aacademy.realestate24temaesercise.repository;

import com.aacademy.realestate24temaesercise.model.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NeghborhoodRepository extends JpaRepository <Neighborhood, Long>{

    Optional<Neighborhood> findByName (String name);  //метод за търсене на квартали по име
}
