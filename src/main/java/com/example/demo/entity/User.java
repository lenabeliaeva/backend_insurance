package com.example.demo.entity;

import com.example.demo.enums.ActivitySphere;
import com.example.demo.enums.SexEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Table
@Entity
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private String secondName;

    @Column
    private String surname;

    @Column
    private String passport;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String city;

    @Column
    private int age;

    @Column
    private SexEnum sex;

    @Column
    private int income;

    @Column
    private ActivitySphere activitySphere;

    @OneToMany
    private List<Police> policies;
}
