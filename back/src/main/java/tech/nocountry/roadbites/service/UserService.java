package tech.nocountry.roadbites.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tech.nocountry.roadbites.controller.dto.RegisterUserDto;
import tech.nocountry.roadbites.controller.dto.UpdateUserDto;
import tech.nocountry.roadbites.domain.model.User;
import tech.nocountry.roadbites.domain.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(RegisterUserDto userDetails) {
        User newUser = new User(
                userDetails.username(),
                userDetails.firstName(),
                userDetails.lastName(),
                userDetails.email(),
                userDetails.phone(),
                userDetails.plainPassword() // TODO: Encriptar la contraseña
        );
        newUser.setCreated(LocalDateTime.now());
        newUser.setStatus(User.Status.ACTIVE); // TODO: Crear logica de activacion de usuarios
        return userRepository.save(newUser);
    }

    public User updateUser(Long id, UpdateUserDto userDetails) {
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
}
