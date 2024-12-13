package com.hans.petfinderv1.repository;

import com.hans.petfinderv1.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
