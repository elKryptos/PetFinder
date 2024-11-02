package hans.startup.petfinderbackend.models.mappers;

import hans.startup.petfinderbackend.models.dtos.AnimalDto;
import hans.startup.petfinderbackend.models.entities.Animal;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnimalMapper {
    AnimalDto toDto(Animal animal);
    Animal toEntity(AnimalDto animalDto);
    List<AnimalDto> toDtoList(List<Animal> animalList);
    List<Animal> toEntityList(List<AnimalDto> animalDtoList);
}
