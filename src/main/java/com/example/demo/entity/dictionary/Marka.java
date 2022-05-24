package com.example.demo.entity.dictionary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "marks")
public class Marka {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "ts_type_id")
    private TsType tsType;
}
