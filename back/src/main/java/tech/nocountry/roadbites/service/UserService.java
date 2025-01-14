package tech.nocountry.roadbites.service;

import org.springframework.stereotype.Service;
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
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (userDetails.username() != null) {
                user.setUsername(userDetails.username());
            }
            if (userDetails.firstName() != null) {
                user.setFirstName(userDetails.firstName());
            }
            if (userDetails.lastName() != null) {
                user.setLastName(userDetails.lastName());
            }
            if (userDetails.email() != null) {
                user.setEmail(userDetails.email());
            }
            if (userDetails.phone() != null) {
                user.setPhone(userDetails.phone());
            }
            if (userDetails.passwordHash() != null) {
                user.setPasswordHash(userDetails.passwordHash());
            }
            if (userDetails.status() != null) {
                user.setStatus(userDetails.status());
            }
            if (userDetails.role() != null) {
                user.setRole(userDetails.role());
            }
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
