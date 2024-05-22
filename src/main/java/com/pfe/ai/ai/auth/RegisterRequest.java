package com.pfe.ai.ai.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private Long apg;

    @NotBlank(message = "First name cannot be empty or null !")
    private String firstname;

    @NotBlank(message = "Last name cannot be empty or null !")
    private String lastname;

    @Pattern(regexp = "^(\\d{10}|[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$"
            , message = "Username format invalid !")
    private String username;

    @NotBlank(message = "Password cannot be empty or null !")
    @Size(min = 4, message = "Password must contain at least 4 characters")
    private String password;
    @NotBlank(message = "user role cannot be empty or null !")
    private String role;
}
