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
    private String animalBreed;
    private String animalColor;
    private String description;
    private LocalDateTime lostDate;
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status{
        Lost,
        Found
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnimalReport> animalReport;

}
