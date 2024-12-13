package com.hans.petfinderv1.repository;

import com.hans.petfinderv1.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
