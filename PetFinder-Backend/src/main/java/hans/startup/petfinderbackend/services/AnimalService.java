package hans.startup.petfinderbackend.services;

import hans.startup.petfinderbackend.models.dtos.AnimalDto;
import hans.startup.petfinderbackend.models.dtos.AnimalUserDto;
import hans.startup.petfinderbackend.models.entities.Animal;
import hans.startup.petfinderbackend.models.entities.User;
import hans.startup.petfinderbackend.models.mappers.AnimalMapper;
import hans.startup.petfinderbackend.repositories.AnimalRepository;
import hans.startup.petfinderbackend.repositories.UserRepository;
import hans.startup.petfinderbackend.responses.AnimalResponse;
import hans.startup.petfinderbackend.responses.Response;
import hans.startup.petfinderbackend.utils.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static hans.startup.petfinderbackend.services.UserService.revokedTokens;

@AllArgsConstructor
@Service
public class AnimalService {

    UserRepository userRepository;
    AnimalRepository animalRepository;
    StorageService storageService;
    private final AnimalMapper animalMapper;

    public Response<List<AnimalDto>> allAnimals() {
        List<Animal> animalList = animalRepository.findAll();
        List<AnimalDto> animalDto = animalMapper.toDtoList(animalList);
        return new Response("Animals found", animalDto);
    }

    public Response<AnimalDto> getAnimalById(int id) {
        Optional<Animal> animal = animalRepository.findById(id);
        if (animal.isPresent()) {
            AnimalDto animalDto = animalMapper.toDto(animal.get());
            return new Response("Animal found", animalDto);
        }
        return new Response("Animal not found");
    }

    public Response<AnimalDto> addAnimal(String auth, AnimalDto animalDto) {
        String token = auth.substring(7);
        Jws<Claims> claims = JwtToken.verifyToken(token);
        if (claims == null || token.isEmpty() || revokedTokens.contains(token)) {
            return new Response("Authentication required, login please");
        }
        String email = claims.getBody().get("email", String.class);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return new Response("User not found");
        }
        if(animalDto.getImageFile() == null || animalDto.getImageFile().isEmpty()){
            return new Response("Image file is required");
            }
        Animal animal = animalMapper.toEntity(animalDto);
        String imagePath = storageService.store(animalDto.getImageFile());
        animal.setImagePath(imagePath);
        animal.setCreatedAt(LocalDateTime.now());
        animal.setUser(user);
        animalRepository.save(animal);

        AnimalDto responseDto = animalMapper.toDto(animal);
        
        if (animalDto.getImageFile() != null) {
            try (InputStream inputStream = animalDto.getImageFile().getInputStream()) {
                byte[] imageBytes = inputStream.readAllBytes(); // Legge tutti i byte
                responseDto.setImageFileBase64(Base64.getEncoder().encodeToString(imageBytes));
            } catch (IOException e) {
                // Gestisci l'eccezione, ad esempio loggandola o impostando un messaggio di errore
                System.err.println("Error reading image file: " + e.getMessage());
                responseDto.setImageFileBase64(null); // Imposta a null o gestisci diversamente
            }
        }
        return new Response<>("Animal announcement created successfully", responseDto);
    }
}
