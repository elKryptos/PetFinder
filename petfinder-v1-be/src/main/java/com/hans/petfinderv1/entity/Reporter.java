package com.hans.petfinderv1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "reporter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reporter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reporterId;
    private String reporterName;
    private String reporterEmail;
    private String reporterPhone;

    @OneToMany(mappedBy = "reporter")
    private List<AnimalReport> animalReportList;
}
