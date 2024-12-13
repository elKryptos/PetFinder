package com.hans.petfinderv1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "animal_report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long animalReportId;
    private LocalDateTime reportedDate;
    private String note;
    private String locationReported;

    @ManyToOne
    @JoinColumn(name = "reporter_id", referencedColumnName = "reporterId")
    private Reporter reporter;

    @ManyToOne
    @JoinColumn(name = "animal_id", referencedColumnName = "animalId")
    private Animal animal;
}
