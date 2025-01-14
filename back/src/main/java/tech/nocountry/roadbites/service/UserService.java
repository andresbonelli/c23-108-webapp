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
                userDetails.getUsername(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getEmail(),
                userDetails.getPhone(),
                userDetails.getPlainPassword() // TODO: Encriptar la contraseña
        );
        newUser.setCreated(LocalDateTime.now());
        newUser.setStatus(User.Status.ACTIVE); // TODO: Crear logica de activacion de usuarios
        return userRepository.save(newUser);
    }

    public User updateUser(Long id, UpdateUserDto userDetails) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (userDetails.getUsername() != null) {
                user.setUsername(userDetails.getUsername());
            }
            if (userDetails.getFirstName() != null) {
                user.setFirstName(userDetails.getFirstName());
            }
            if (userDetails.getLastName() != null) {
                user.setLastName(userDetails.getLastName());
            }
            if (userDetails.getEmail() != null) {
                user.setEmail(userDetails.getEmail());
            }
            if (userDetails.getPhone() != null) {
                user.setPhone(userDetails.getPhone());
            }
            if (userDetails.getPasswordHash() != null) {
                user.setPasswordHash(userDetails.getPasswordHash());
            }
            if (userDetails.getStatus() != null) {
                user.setStatus(userDetails.getStatus());
            }
            if (userDetails.getRole() != null) {
                user.setRole(userDetails.getRole());
            }
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
