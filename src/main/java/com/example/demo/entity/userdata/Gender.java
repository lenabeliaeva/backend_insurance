package com.example.demo.entity.userdata;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
public class Gender {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;
}
