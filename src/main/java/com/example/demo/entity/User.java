package com.example.demo.entity;

import com.example.demo.entity.userdata.ActivitySphere;
import com.example.demo.entity.userdata.EducationLevel;
import com.example.demo.entity.userdata.Gender;
import com.example.demo.entity.userdata.IncomeLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Table(name = "users")
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

    @Column(name = "login")
    private String email;

    @Column
    private String password;

    @Column
    private String city;

    @Column
    private int age;

    @ManyToOne
    @JoinColumn(name = "education_id")
    private EducationLevel education;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "income_level_id")
    private IncomeLevel income;

    @ManyToOne
    @JoinColumn(name = "activity_sphere_id")
    private ActivitySphere activitySphere;

    @OneToMany(mappedBy = "user")
    private List<Police> policies;
}
