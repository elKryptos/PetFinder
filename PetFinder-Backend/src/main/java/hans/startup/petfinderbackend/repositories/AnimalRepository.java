package hans.startup.petfinderbackend.repositories;

import hans.startup.petfinderbackend.models.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
}
