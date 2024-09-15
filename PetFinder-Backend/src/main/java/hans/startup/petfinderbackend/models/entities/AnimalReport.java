package hans.startup.petfinderbackend.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "animal_report")
public class AnimalReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer animalReportId;
    private String reporterName;
    private String reporterEmail;
    private LocalDateTime reportDate;
    private String note;

    @ManyToOne
    @JoinColumn(name = "animal_id", referencedColumnName = "animalId")
    private Animal animal;

}
