package com.example.demo.repository.userdata;

import com.example.demo.entity.userdata.Gender;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends CrudRepository<Gender, Long> {
}
