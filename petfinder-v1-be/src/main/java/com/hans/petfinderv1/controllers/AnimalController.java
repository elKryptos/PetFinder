package com.hans.petfinderv1.controllers;

import com.hans.petfinderv1.model.dto.AnimalDto;
import com.hans.petfinderv1.services.AnimalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/animal")
@CrossOrigin()
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping()
    public ResponseEntity<List<AnimalDto>> getAllAnimals() {
        List<AnimalDto> animals = animalService.getAllAnimals();
        return ResponseEntity.status(HttpStatus.OK).body(animals);
    }

    @PostMapping()
    public ResponseEntity<AnimalDto> createAnimal(@Valid @RequestBody AnimalDto animalDto) {
        AnimalDto newAnimal = animalService.createAnimal(animalDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAnimal);
    }

    @PutMapping("/{animalId}")
    public ResponseEntity<AnimalDto> updateAnimal(@PathVariable Long animalId, @Valid @RequestBody AnimalDto animalDto) {
        AnimalDto updatedAnimal = animalService.updateAnimal(animalId, animalDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedAnimal);
    }

    @PostMapping("/{animalId}")
    public ResponseEntity<String> deleteAnimal(@PathVariable Long animalId) {
        String deletedAnimal = animalService.deleteAnimal(animalId);
        return ResponseEntity.status(HttpStatus.OK).body(deletedAnimal);
    }

    @GetMapping("/id/{animalId}")
    public ResponseEntity<AnimalDto> getAnimalById(@PathVariable Long animalId) {
        AnimalDto animalDto = animalService.getAnimalById(animalId);
        return ResponseEntity.status(HttpStatus.OK).body(animalDto);
    }

}
