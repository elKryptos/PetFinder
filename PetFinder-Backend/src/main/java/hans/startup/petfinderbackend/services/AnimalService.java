package hans.startup.petfinderbackend.services;

import hans.startup.petfinderbackend.models.dtos.AnimalDto;
import hans.startup.petfinderbackend.models.dtos.AnimalUserDto;
import hans.startup.petfinderbackend.models.entities.Animal;
import hans.startup.petfinderbackend.models.entities.User;
import hans.startup.petfinderbackend.repositories.AnimalRepository;
import hans.startup.petfinderbackend.repositories.UserRepository;
import hans.startup.petfinderbackend.responses.AnimalResponse;
import hans.startup.petfinderbackend.utils.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static hans.startup.petfinderbackend.services.UserService.revokedTokens;

@AllArgsConstructor
@Service
public class AnimalService {

    UserRepository userRepository;
    AnimalRepository animalRepository;
    StorageService storageService;

    public ResponseEntity<AnimalResponse> addAnimal(@RequestHeader ("Authorization") String auth, AnimalDto animalDto) {
        String token = auth.substring(7);
        Jws<Claims> claims = JwtToken.verifyToken(token);
        if (claims == null || token.isEmpty() || revokedTokens.contains(token)) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new AnimalResponse("Authentication required, login please"));
        }
        String email = claims.getBody().get("email", String.class);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new AnimalResponse("User not found"));
        }
        if(animalDto.getImageFile() == null || animalDto.getImageFile().isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new AnimalResponse("Image file is required"));
            }

        Animal animal = new Animal();
        animal.setAnimalName(animalDto.getAnimalName());
        animal.setAnimalType(animalDto.getAnimalType());
        animal.setAnimalBreed(animalDto.getAnimalBreed());
        animal.setAnimalColor(animalDto.getAnimalColor());
        animal.setDescription(animalDto.getDescription());
        animal.setCreatedAt(LocalDateTime.now());
        animal.setLostDate(animalDto.getLostDate());
        animal.setStatus(Animal.Status.Lost);
        String imagePath = storageService.store(animalDto.getImageFile());
        animal.setImagePath(imagePath);
        animal.setUser(user);
        animalRepository.save(animal);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new AnimalResponse("Animal announcement created successfully", animal));
    }

    public ResponseEntity<AnimalResponse> allAnimals() {
        List<Animal> animals = animalRepository.findAll();
        return ResponseEntity.status(200).body(new AnimalResponse("Animals found", animals));
    }

    public ResponseEntity<AnimalResponse> getAnimalById(int id) {
        Optional<Animal> animalOptional = animalRepository.findById(id);
        if (!animalOptional.isPresent() || animalOptional.get().getUser() == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new AnimalResponse("Animal not found"));
        }
        Animal animal = animalOptional.get();
        AnimalUserDto animalUserDto = new AnimalUserDto(animal);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new AnimalResponse("Animal found", animalUserDto));
    }

}
