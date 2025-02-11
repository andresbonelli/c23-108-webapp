package tech.nocountry.roadbites.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tech.nocountry.roadbites.controller.dto.user.RegisterUserDTO;
import tech.nocountry.roadbites.controller.dto.user.UpdateUserDTO;
import tech.nocountry.roadbites.domain.model.AccountStatus;
import tech.nocountry.roadbites.domain.model.Role;
import tech.nocountry.roadbites.domain.model.User;
import tech.nocountry.roadbites.domain.repository.UserRepository;
import tech.nocountry.roadbites.service.exceptions.UserNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User createUser(RegisterUserDTO userDetails) {
        User newUser = buildUserFromDto(userDetails);
        newUser.setDisplayName(newUser.getFirstName()+" "+newUser.getLastName());
        newUser.setStatus(AccountStatus.ACTIVE); // TODO: Crear logica de activacion de usuarios
        newUser.setRole(Role.CUSTOMER);
        newUser.setCreated(LocalDateTime.now());
        newUser.setLastUpdated(LocalDateTime.now());
        return userRepository.save(newUser);
    }

    public User updateUser(Long id, UpdateUserDTO userDetails) {
        if (userDetails.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No fields to update.");
        }
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            if  (userDetails.displayName().isPresent()) {
                existingUser.setDisplayName(userDetails.displayName().get());
            }
            if (userDetails.firstName().isPresent()) {
                existingUser.setFirstName(userDetails.firstName().get());
            }
            if (userDetails.lastName().isPresent()) {
                existingUser.setLastName(userDetails.lastName().get());
            }
            if (userDetails.phone().isPresent()) {
                existingUser.setPhone(userDetails.phone().get());
            }
            if (userDetails.passwordHash().isPresent()) {
                existingUser.setPasswordHash(userDetails.passwordHash().get());
            }
            if (userDetails.status().isPresent()) {
                existingUser.setStatus(userDetails.status().get());
            }
            if (userDetails.role().isPresent()) {
                existingUser.setRole(userDetails.role().get());
            }
            existingUser.setLastUpdated(LocalDateTime.now());
            return userRepository.save(existingUser);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private User buildUserFromDto(RegisterUserDTO userDetails) {
        return User.builder()
                .username(userDetails.username())
                .firstName(userDetails.firstName())
                .lastName(userDetails.lastName())
                .email(userDetails.email())
                .phone(userDetails.phone())
                .passwordHash(userDetails.plainPassword())
                .build();
    }
}
