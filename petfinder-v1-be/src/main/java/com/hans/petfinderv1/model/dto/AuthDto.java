package com.hans.petfinderv1.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthDto {
    @Email(message = "Accepted only standard email with '@'")
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private String response;
}
