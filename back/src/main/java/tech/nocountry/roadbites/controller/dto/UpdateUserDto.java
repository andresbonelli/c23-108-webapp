package tech.nocountry.roadbites.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import tech.nocountry.roadbites.domain.model.User;

public record UpdateUserDto(
        @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
        String username,

        String firstName,

        String lastName,

        @Email(message = "Email should be valid")
        String email,

        String phone,

        String passwordHash,

        User.Status status,

        User.Role role
) {}
