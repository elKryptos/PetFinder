package com.hans.petfinderv1.repository;

import com.hans.petfinderv1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
