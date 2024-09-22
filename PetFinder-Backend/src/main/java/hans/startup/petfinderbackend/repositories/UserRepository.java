package hans.startup.petfinderbackend.repositories;

import hans.startup.petfinderbackend.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
}
