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

import static hans.startup.petfinderbackend.services.UserService.revokedTokens;

@AllArgsConstructor
@Service
public class AnimalService {

    AnimalRepository animalRepository;

    public ResponseEntity<BackendResponse> addAnimal(String auth, AnimalDto animalDto) {
        String token = auth.substring(7);
        Jws<Claims> claims = JwtToken.verifyToken(token);
        if (claims == null || token.isEmpty() || revokedTokens.contains(token)) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new BackendResponse("Authentication required, login please"));
        }

        Animal animal = new Animal();
        animal.setAnimalName(animalDto.getAnimalName());
        animal.setAnimalType(animalDto.getAnimalType());
        animal.setAnimalBreed(animalDto.getAnimalBreed());
        animal.setAnimalColor(animalDto.getAnimalColor());
        animal.setDescription(animalDto.getDescription());
        animal.setLostDate(animalDto.getLostDate());
        animal.setStatus(Animal.Status.Lost);
        // In work method for upload a file, is this case it will be an image.
        // Add after finishing FileSystemStorageService
        animalRepository.save(animal);

        return null;
    }

}
