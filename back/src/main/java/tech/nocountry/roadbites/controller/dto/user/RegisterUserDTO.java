package tech.nocountry.roadbites.controller.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserDTO(
        @Schema(description = "Username", example = "juanperez123")
        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
        String username,

        @Schema(description = "First name", example = "Juan")
        @NotBlank(message = "First name is required")
        String firstName,

        @Schema(description = "Last name", example = "Perez")
        @NotBlank(message = "Last name is required")
        String lastName,

        @Schema(description = "Email", example = "juanperez123@gmail.com")
        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        String email,

        @Schema(description = "Phone number", example = "541122223333")
        @NotBlank(message = "Phone number is required")
        String phone,

        @Schema(description = "Plain password", example = "1234abcd")
        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String plainPassword // Plain password for registration
) {}
