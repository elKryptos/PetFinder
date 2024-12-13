package com.hans.petfinderv1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    private String password;
    private String resetToken;
    private LocalDateTime resetTokenExpiration;
    private LocalDateTime registrationDate;
    private LocalDateTime lastLogin;

    @OneToMany(mappedBy = "user")
    private List<Animal> animalList;
}
