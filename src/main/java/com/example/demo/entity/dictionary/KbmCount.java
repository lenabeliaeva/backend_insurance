package com.example.demo.entity.dictionary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "kbm_count")
public class KbmCount {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private int caseCount;

    @Column
    private int newCbmClass;

    @ManyToOne
    @JoinColumn(name = "kbm_id")
    private KBM kbm;
}
