package tech.nocountry.roadbites;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.nocountry.roadbites.controller.UserController;
import tech.nocountry.roadbites.controller.dto.RegisterUserDto;
import tech.nocountry.roadbites.controller.dto.UpdateUserDto;
import tech.nocountry.roadbites.domain.model.User;
import tech.nocountry.roadbites.domain.repository.UserRepository;

import java.util.Optional;

@SpringBootTest
public class UserControllerTests {

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    private User user1;
    private User user2;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
        user1 = userRepository.save(new User(
                "testuser1",
                "user",
                "1",
                "user1@test.com",
                "11111111",
                "123456"
        ));
        user2 = userRepository.save(new User(
                "testuser2",
                "user",
                "2",
                "user2@test.com",
                "22222222",
                "123456"
        ));

    }

    @Test
    void shouldGetAllUsers() {
        var users = userController.getAllUsers();
        Assertions.assertEquals(2, users.size());
    }

    @Test
    void shouldCreateNewUser() {
        var id = userController.createUser(new RegisterUserDto(
                "testuserX",
                "juan",
                "perez",
                "juanperez@test.com",
                "11111111",
                "123456"
        ));
        Assertions.assertNotNull(id);
    }

    @Test
    void shouldUpdateExistingUser() {
        UpdateUserDto updateUserDto = new UpdateUserDto(
                Optional.of("userUpdated"),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
        );

        User user = userController.updateUser(user1.getId(), updateUserDto).getBody();

        Assertions.assertNotNull(user);
        Assertions.assertEquals("userUpdated", user.getDisplayName());
        Assertions.assertNotNull(user.getLastUpdated());
    }

    @Test
    void shouldDeleteUser() {
        userController.deleteUser(user1.getId());
        var users = userController.getAllUsers();
        Assertions.assertEquals(1, users.size());
        Assertions.assertFalse(userRepository.existsById(user1.getId()));

    }


}
