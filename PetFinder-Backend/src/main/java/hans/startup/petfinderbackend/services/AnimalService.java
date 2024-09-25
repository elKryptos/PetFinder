package hans.startup.petfinderbackend.services;

import hans.startup.petfinderbackend.models.dtos.AnimalDto;
import hans.startup.petfinderbackend.models.entities.Animal;
import hans.startup.petfinderbackend.repositories.AnimalRepository;
import hans.startup.petfinderbackend.responses.BackendResponse;
import hans.startup.petfinderbackend.utils.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import static hans.startup.petfinderbackend.services.UserService.revokedTokens;

@AllArgsConstructor
@Service
public class AnimalService {

    AnimalRepository animalRepository;
    StorageService storageService;

    public ResponseEntity<BackendResponse> addAnimal(@RequestHeader ("Authorization") String auth, AnimalDto animalDto) {
        String token = auth.substring(7);
        Jws<Claims> claims = JwtToken.verifyToken(token);
        if (claims == null || token.isEmpty() || revokedTokens.contains(token)) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new BackendResponse("Authentication required, login please"));
        }

        if(animalDto.getImageFile() == null || animalDto.getImageFile().isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new BackendResponse("Image file is required"));
            }

        Animal animal = new Animal();
        animal.setAnimalName(animalDto.getAnimalName());
        animal.setAnimalType(animalDto.getAnimalType());
        animal.setAnimalBreed(animalDto.getAnimalBreed());
        animal.setAnimalColor(animalDto.getAnimalColor());
        animal.setDescription(animalDto.getDescription());
        animal.setLostDate(animalDto.getLostDate());
        animal.setStatus(Animal.Status.Lost);
        String imagePath = storageService.store(animalDto.getImageFile());
        animal.setImagePath(imagePath);
        animalRepository.save(animal);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new BackendResponse("Animal announcement created successfully"));
    }

    public ResponseEntity<>

}
