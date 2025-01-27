package com.hans.petfinderv1.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Long userId;
    @NotBlank(message = "Firstname cannot be empty")
    private String firstname;
    @NotBlank(message = "Lastname cannot be empty")
    private String lastname;
    @Email(message = "Accepted only standard email with '@'")
    @NotBlank
    private String email;
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Minimun characters accepted '8'")
    private String password;
    private String resetToken;
}
