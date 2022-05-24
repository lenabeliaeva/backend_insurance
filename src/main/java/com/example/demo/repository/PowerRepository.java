package com.example.demo.repository;

import com.example.demo.entity.dictionary.Power;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerRepository extends CrudRepository<Power, Long> {
}
