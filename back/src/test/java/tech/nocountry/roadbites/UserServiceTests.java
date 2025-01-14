package tech.nocountry.roadbites;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.nocountry.roadbites.controller.UserController;
import tech.nocountry.roadbites.controller.dto.RegisterUserDto;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserController userController;

    @Test
    void shouldCreateNewUser() {
        var id = userController.createUser(new RegisterUserDto(
                "testuser1",
                "juan",
                "perez",
                "juanperez@test.com",
                "11111111",
                "123456"
        ));
        Assertions.assertNotNull(id);
    }


}
