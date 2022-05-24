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
@Table(name = "kbm")
public class KBM {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private double coeff;

    @Column(name = "class")
    private int kbmClass;
}
