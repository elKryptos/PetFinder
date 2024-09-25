package hans.startup.petfinderbackend.models.dtos;

import hans.startup.petfinderbackend.models.entities.Animal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class AnimalDto {
    @NotBlank
    private String animalName;
    @NotBlank
    private String animalType;
    @NotBlank
    private String animalBreed;
    @NotBlank
    private String animalColor;
    @NotBlank
    private String description;
    @NotBlank
    private LocalDateTime lostDate;
    @NotNull
    private Animal.Status status;
    private MultipartFile imageFile;
}
