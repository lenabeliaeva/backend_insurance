package com.example.demo.repository.userdata;

import com.example.demo.entity.userdata.ActivitySphere;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivitySphereRepository extends CrudRepository<ActivitySphere, Long> {
}
