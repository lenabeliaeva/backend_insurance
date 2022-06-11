package com.example.demo.repository.userdata;

import com.example.demo.entity.userdata.EducationLevel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends CrudRepository<EducationLevel, Long> {
}
