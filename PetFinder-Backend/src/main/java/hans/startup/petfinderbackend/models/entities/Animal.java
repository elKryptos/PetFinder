package hans.startup.petfinderbackend.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String imagePath;
    private LocalDateTime createdAt;
    private LocalDateTime lostDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status{
        Lost,
        Found
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnimalReport> animalReport;

}
