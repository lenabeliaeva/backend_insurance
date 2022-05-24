package com.example.demo.entity.dictionary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "year")
public class Year {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private int year;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
}
