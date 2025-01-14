package tech.nocountry.roadbites.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import tech.nocountry.roadbites.domain.model.User;

@Getter
@Setter
public class UpdateUserDto {
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;
    private String firstName;
    private String lastName;
    @Email(message = "Email should be valid")
    private String email;
    private String phone;
    private String passwordHash;
    private User.Status status;
    private User.Role role;
}
