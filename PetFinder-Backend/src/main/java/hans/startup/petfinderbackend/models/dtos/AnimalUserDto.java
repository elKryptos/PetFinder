package hans.startup.petfinderbackend.models.dtos;

import hans.startup.petfinderbackend.models.entities.Animal;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnimalUserDto {
    private Integer animalId;
    private String animalName;
    private String animalType;
    private String animalBreed;
    private String animalColor;
    private String description;
    private String imagePath;
    private LocalDateTime createdAt;
    private LocalDateTime lostDate;
    private Animal.Status status;

    private String firstname;
    private String lastname;
    private String email;

    //Da aggiungere valori AnimalReport una volta implementato!

    public AnimalUserDto(Animal animal) {
        this.animalId = animal.getAnimalId();
        this.animalName = animal.getAnimalName();
        this.animalType = animal.getAnimalType();
        this.animalBreed = animal.getAnimalBreed();
        this.animalColor = animal.getAnimalColor();
        this.description = animal.getDescription();
        this.imagePath = animal.getImagePath();
        this.createdAt = animal.getCreatedAt();
        this.lostDate = animal.getLostDate();
        this.status = animal.getStatus();
        this.firstname = animal.getUser().getFirstname();
        this.lastname = animal.getUser().getLastname();
        this.email = animal.getUser().getEmail();

        // AnimalReport da fare!
    }
}
