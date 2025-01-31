package tech.nocountry.roadbites.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import tech.nocountry.roadbites.domain.model.Role;
import tech.nocountry.roadbites.domain.model.Status;

import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdateUserDto(
        Optional<String> displayName,
        Optional<String> firstName,
        Optional<String> lastName,
        Optional<String> phone,
        Optional<String> passwordHash,
        Optional<Status> status,
        Optional<Role> role
) {
    public boolean isEmpty() {
        return displayName.isEmpty() &&
                firstName.isEmpty() &&
                lastName.isEmpty() &&
                phone.isEmpty() &&
                passwordHash.isEmpty() &&
                status.isEmpty() &&
                role.isEmpty();
    }
}
