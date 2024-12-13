package com.hans.petfinderv1.repository;

import com.hans.petfinderv1.entity.AnimalPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalPhotoRepository extends JpaRepository<AnimalPhoto, Long> {
}
