package com.example.demo.entity.dictionary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "ts_type")
public class TsType {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private int coefficient;
}
