package com.example.demo.repository;

import com.example.demo.entity.dictionary.KBM;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KbmRepository extends CrudRepository<KBM, Long> {
}
