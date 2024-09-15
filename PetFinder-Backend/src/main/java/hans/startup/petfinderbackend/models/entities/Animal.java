package hans.startup.petfinderbackend.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer animalId;
    private String animalName;
    private String animalType;
    private String breed;
    private String color;
    private String description;

    private LocalDateTime lostDate;
    private Status status;

    public enum Status{
        LOST,
        FOUND
    }

    public Animal() {}

}
