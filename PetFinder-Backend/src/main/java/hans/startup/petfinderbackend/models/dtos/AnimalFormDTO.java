package hans.startup.petfinderbackend.models.dtos;

import hans.startup.petfinderbackend.models.entities.Animal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnimalFormDTO {
    @NotBlank
    private String animalName;
    @NotBlank
    private String animalType;
    @NotBlank
    private String animalBreed;
    @NotBlank
    private String color;
    @NotBlank
    private String description;
    @NotBlank
    private LocalDateTime lostDate;
    @NotNull
    private Animal.Status status;
}
