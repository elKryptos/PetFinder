package hans.startup.petfinderbackend.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "animal")
@Data
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

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @OneToMany(mappedBy = "animal")
    private List<AnimalReport> animalReport;

}
