package hans.startup.petfinderbackend.controllers;

import hans.startup.petfinderbackend.models.dtos.AnimalDto;
import hans.startup.petfinderbackend.responses.AnimalResponse;
import hans.startup.petfinderbackend.services.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("v1/animal")
@CrossOrigin
public class AnimalController {
    AnimalService animalService;

    @GetMapping("/all")
    public ResponseEntity<AnimalResponse> getAllAnimals() {
        return animalService.allAnimals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponse> getAnimalById(@PathVariable int id) {
        return animalService.getAnimalById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<AnimalResponse> addAnimal(
            @RequestHeader ("Authorization") String auth,
            @ModelAttribute AnimalDto animalDto) {
        return animalService.addAnimal(auth, animalDto);
    }
}
