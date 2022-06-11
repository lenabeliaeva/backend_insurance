package com.example.demo.repository.userdata;

import com.example.demo.entity.userdata.IncomeLevel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends CrudRepository<IncomeLevel, Long> {
}
