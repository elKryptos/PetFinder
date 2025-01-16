package com.hans.petfinderv1.model.mapper;

import com.hans.petfinderv1.model.dto.AnimalDto;
import com.hans.petfinderv1.model.entity.Animal;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnimalMapper {
    AnimalDto toDto(Animal animal);
    Animal toEntity(AnimalDto animalDto);
    List<AnimalDto> toDtoList(List<Animal> animalList);
    List<Animal> toEntityList(List<AnimalDto> animalDtoList);
}
