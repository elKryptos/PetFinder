package com.hans.petfinderv1.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthDto {
    @Email(message = "Accepted only standard email with '@'")
    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    private String response;
}
