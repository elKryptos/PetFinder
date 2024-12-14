package com.hans.petfinderv1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDto {
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
}
