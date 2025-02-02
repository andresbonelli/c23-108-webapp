package tech.nocountry.roadbites;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTests {

    @ServiceConnection
    static final MySQLContainer mySqlContainer = new MySQLContainer("mysql:8.3.0")
            .withDatabaseName("roadbites")
            .withUsername("user")
            .withPassword("password");

    static {
        mySqlContainer.start();
    }

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void shouldCreateOrder() {
        String requestBody = """
                {
                    "userName": "test user",
                    "orderMenus": [
                            {
                                "menuId": 1,
                                "quantity": 1
                            }
                        ]
                }
                """;
        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/order")
                .then()
                .statusCode(201)
                .body("userName", Matchers.equalTo("test user"))
                .body("orderMenus[0].menuId", Matchers.equalTo(1))
                .body("orderMenus[0].quantity", Matchers.equalTo(1));
    }

    @AfterAll
    static void afterAll() {
        mySqlContainer.stop();
    }
}
