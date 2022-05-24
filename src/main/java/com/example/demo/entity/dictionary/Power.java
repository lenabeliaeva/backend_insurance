package com.example.demo.entity.dictionary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "power")
public class Power {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private int power;

    @Column
    private double coefficient;

    @ManyToOne
    @JoinColumn(name = "year_id")
    private Year year;
}
