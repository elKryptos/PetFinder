package hans.startup.petfinderbackend.repositories;

import hans.startup.petfinderbackend.models.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    Optional<Animal> findById(int id);
}
