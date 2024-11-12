package hans.startup.petfinderbackend.models.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
