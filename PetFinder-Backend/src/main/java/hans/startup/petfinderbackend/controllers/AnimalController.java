package hans.startup.petfinderbackend.controllers;

import hans.startup.petfinderbackend.models.dtos.AnimalDto;
import hans.startup.petfinderbackend.responses.Response;
import hans.startup.petfinderbackend.services.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("v1/animal")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping("/all")
    public ResponseEntity<Response<List<AnimalDto>>> getAllAnimals() {
        Response<List<AnimalDto>> response = animalService.allAnimals();
        if(response == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<AnimalDto>> getAnimalById(@PathVariable int id) {
        Response<AnimalDto> response = animalService.getAnimalById(id);
        if(response == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<Response<AnimalDto>> addAnimal(
            @RequestHeader ("Authorization") String auth,
            @ModelAttribute AnimalDto animalDto) {
        Response<AnimalDto> response = animalService.addAnimal(auth, animalDto);
        if(response == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(response);
    }
}
