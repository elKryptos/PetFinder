package com.hans.petfinderv1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "animal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long animalId;
    private String animalName;
    private String animalType;
    private String animalBreed;
    private String animalColor;
    private String description;
    private String imagePath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lostDate;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "statusId")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @OneToMany(mappedBy = "animal")
    private List<AnimalReport> animalReportList;

    @OneToMany(mappedBy = "animal")
    private List<AnimalLocation> animalLocationList;

    @OneToMany(mappedBy = "animal")
    private List<AnimalPhoto> animalPhotoList;
}
