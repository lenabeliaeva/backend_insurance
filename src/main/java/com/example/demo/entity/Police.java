package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "police")
public class Police {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private int number;

    @Column
    private double cost;

    @Column
    private int objectType;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "car_id")
//    private Car car;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
