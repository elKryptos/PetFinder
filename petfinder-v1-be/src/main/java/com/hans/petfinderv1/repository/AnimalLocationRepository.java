package com.hans.petfinderv1.repository;

import com.hans.petfinderv1.entity.AnimalLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalLocationRepository extends JpaRepository<AnimalLocation, Long> {
}
