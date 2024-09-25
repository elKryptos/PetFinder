package hans.startup.petfinderbackend.controllers;

import hans.startup.petfinderbackend.models.dtos.AnimalDto;
import hans.startup.petfinderbackend.responses.BackendResponse;
import hans.startup.petfinderbackend.services.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@CrossOrigin
public class AnimalController {

    private final AnimalService animalService;

    @PostMapping("/add")
    public ResponseEntity<BackendResponse> addAnimal(
            @RequestHeader ("Authorization") String auth,
            @ModelAttribute AnimalDto animalDto) {
        return animalService.addAnimal(auth, animalDto);
    }
}
