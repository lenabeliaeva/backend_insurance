package com.example.demo.entity.userdata;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table
@Entity
public class ActivitySphere {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;
}
