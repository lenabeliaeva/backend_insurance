package com.example.demo.entity.dictionary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "model")
public class Model {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "marks_id")
    private Marka marka;
}
