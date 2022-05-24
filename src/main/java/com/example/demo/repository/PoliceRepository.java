package com.example.demo.repository;

import com.example.demo.entity.Police;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliceRepository extends CrudRepository<Police, Long> {
}
