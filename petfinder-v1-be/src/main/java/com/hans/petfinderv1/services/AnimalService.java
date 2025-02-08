package com.hans.petfinderv1.services;

import com.hans.petfinderv1.Constants;
import com.hans.petfinderv1.exception.NotFoundException;
import com.hans.petfinderv1.model.dto.AnimalDto;
import com.hans.petfinderv1.model.entity.Animal;
import com.hans.petfinderv1.model.mapper.AnimalMapper;
import com.hans.petfinderv1.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;

    public List<AnimalDto> getAllAnimals() {
        List<Animal> animalList = animalRepository.findAll();
        return animalMapper.toDtoList(animalList);
    }

    public AnimalDto createAnimal(AnimalDto animalDto) {
        Animal animal = animalMapper.toEntity(animalDto);
        animalRepository.save(animal);
        return animalMapper.toDto(animal);
    }

    public AnimalDto updateAnimal(Long animalId, AnimalDto animalDto) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new NotFoundException(Constants.ANIMAL_NOT_FOUND.getMessage()));
        animalMapper.animalUpdate(animal, animalDto);
        Animal animalUpdated = animalRepository.save(animal);
        return animalMapper.toDto(animalUpdated);
    }

    public String deleteAnimal(Long animalId) {
        Animal animal  = animalRepository.findById(animalId)
                .orElseThrow(() -> new NotFoundException(Constants.ANIMAL_NOT_FOUND.getMessage()));
        animalRepository.delete(animal);
        return Constants.ANIMAL_DELETED.getMessage();
    }

    public AnimalDto getAnimalById(Long animalId) {
        Optional<Animal> animal = Optional.ofNullable(animalRepository.findById(animalId)
                .orElseThrow(() -> new NotFoundException(Constants.ANIMAL_NOT_FOUND.getMessage())));
        return animalMapper.toDto(animal.get());
    }
}
