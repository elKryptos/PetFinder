package hans.startup.petfinderbackend.responses;

import hans.startup.petfinderbackend.models.dtos.AnimalUserDto;
import hans.startup.petfinderbackend.models.entities.Animal;
import lombok.Data;

import java.util.List;

@Data
public class AnimalResponse {
    String msg;
    Animal animal;
    AnimalUserDto animalUserDto;
    List<Animal> animals;

    public AnimalResponse(String msg) {
        this.msg = msg;
    }

    public AnimalResponse(String msg, Animal animal) {
        this.msg = msg;
        this.animal = animal;
    }

    public AnimalResponse(String msg, AnimalUserDto animalUserDto) {
        this.msg = msg;
        this.animalUserDto = animalUserDto;
    }

    public AnimalResponse(String msg, List<Animal> animals) {
        this.msg = msg;
        this.animals = animals;
    }
}
