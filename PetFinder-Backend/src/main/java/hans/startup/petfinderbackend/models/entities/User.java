package hans.startup.petfinderbackend.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String firstname;
    private String lastname;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String resetToken;

    private LocalDateTime registrationDate;
    private LocalDateTime lastLogin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Animal> animals;

}
