package hans.startup.petfinderbackend.repositories;

import hans.startup.petfinderbackend.models.entities.AnimalReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalReportRepository extends JpaRepository<AnimalReport, Integer> {
}
