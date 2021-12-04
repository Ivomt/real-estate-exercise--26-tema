package com.aacademy.realestate24temaesercise.repository;

import com.aacademy.realestate24temaesercise.model.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {

    Optional<Floor> findByNumber (Integer number);  //метод за търсене на етажи по номер
}
