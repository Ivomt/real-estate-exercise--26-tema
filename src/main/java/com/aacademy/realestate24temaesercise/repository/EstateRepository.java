package com.aacademy.realestate24temaesercise.repository;

import com.aacademy.realestate24temaesercise.model.Estate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstateRepository extends JpaRepository <Estate, Long>{
}
