package hans.startup.petfinderbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;


@SpringBootApplication
public class PetFinderBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetFinderBackendApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder(6, new SecureRandom());
    }

}
