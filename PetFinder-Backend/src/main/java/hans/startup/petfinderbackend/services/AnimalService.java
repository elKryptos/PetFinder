package hans.startup.petfinderbackend.services;

import hans.startup.petfinderbackend.models.dtos.AnimalDto;
import hans.startup.petfinderbackend.models.entities.Animal;
import hans.startup.petfinderbackend.repositories.AnimalRepository;
import hans.startup.petfinderbackend.responses.BackendResponse;
import hans.startup.petfinderbackend.utils.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.AllArgsConstructor;
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
            return ResponseEntity.status()
        }

        Animal animal = new Animal();

        return null;
    }
}
