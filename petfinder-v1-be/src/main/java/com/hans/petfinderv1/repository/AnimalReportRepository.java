package com.hans.petfinderv1.repository;

import com.hans.petfinderv1.entity.AnimalReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalReportRepository extends JpaRepository<AnimalReport, Long> {
}
