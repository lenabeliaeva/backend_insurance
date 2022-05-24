package com.example.demo.entity;

import com.example.demo.entity.dictionary.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "car")
public class Car {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ts_type")
    private TsType tsType;

    @ManyToOne
    @JoinColumn(name = "marka")
    private Marka marka;

    @ManyToOne
    @JoinColumn(name = "model")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "power")
    private Power power;

    @ManyToOne
    @JoinColumn(name = "year")
    private Year year;

    @Column
    private String regNumber;

    @Column
    private int insurCaseCount;

    @ManyToOne
    @JoinColumn(name = "kbm")
    private KBM kbm;

    @Column
    private double insuranceCost;
}
