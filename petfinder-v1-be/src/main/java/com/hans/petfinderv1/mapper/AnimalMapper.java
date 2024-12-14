package com.hans.petfinderv1.mapper;

import com.hans.petfinderv1.dto.AnimalDto;
import com.hans.petfinderv1.entity.Animal;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnimalMapper {
    AnimalDto toDto(Animal animal);
    Animal toEntity(AnimalDto animalDto);
    List<AnimalDto> toDtoList(List<Animal> animalList);
    List<Animal> toEntityList(List<AnimalDto> animalDtoList);
}
