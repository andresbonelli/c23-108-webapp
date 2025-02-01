package tech.nocountry.roadbites;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import tech.nocountry.roadbites.controller.UserController;
import tech.nocountry.roadbites.controller.dto.RegisterUserDto;
import tech.nocountry.roadbites.controller.dto.UpdateUserDto;
import tech.nocountry.roadbites.domain.model.User;
import tech.nocountry.roadbites.domain.repository.UserRepository;

import java.util.Optional;

import static org.aspectj.bridge.MessageUtil.fail;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTests {

    @ServiceConnection
    static final MySQLContainer mySqlContainer = new MySQLContainer("mysql:8.0.26")
            .withDatabaseName("roadbites")
            .withUsername("user")
            .withPassword("password");

    static {
        mySqlContainer.start();
    }

    @LocalServerPort
    private Integer port;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        String requestBody = """
                {
                    "username": "testuser1",
                    "firstName": "Juan",
                    "lastName": "Perez",
                    "email": "testuser1@example.com",
                    "phone": "11111111",
                    "password": "123456"
                """;
        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/users")
                .then()
                .extract()
                .response();
    }

    @AfterAll
    static void afterAll() {
        mySqlContainer.stop();
    }

    @Test
    void shouldGetAllUsers() {
        RestAssured.given()
                .contentType("application/json")
                .when()
                .get("/api/users")
                .then()
                .statusCode(200);
    }

    @Test
    void shouldCreateNewUser() {
        RestAssured.given()
                .contentType("application/json")
                .body("""
                {
                    "username": "testuser2",
                    "firstName": "Juan",
                    "lastName": "Perez",
                    "email": "testuser2@example.com",
                    "phone": "11111111",
                    "password": "123456"
                }
                """)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201);
    }
}
