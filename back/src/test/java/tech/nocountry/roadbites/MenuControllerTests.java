package tech.nocountry.roadbites;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;
import org.hamcrest.Matchers;
import tech.nocountry.roadbites.config.TestConfig;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestConfig.class)
public class MenuControllerTests {

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

    @AfterAll
    static void afterAll() {
        mySqlContainer.stop();
    }

    @Test
    void shouldGetAllMenus() {
        RestAssured.given()
                .when()
                .get("/api/menu")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()", Matchers.greaterThan(0))
                .body("[0].id", Matchers.equalTo(1))
                .body("[0].name", Matchers.equalTo("Arroz con Pollo"));
    }

    @Test
    void shouldGetMenusByCategory() {
        RestAssured.given()
                .when()
                .get("/api/menu/category/bebidas")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()", Matchers.greaterThan(0))
                .body("[0].category", Matchers.equalTo("bebidas"));

        RestAssured.given()
                .when()
                .get("/api/menu/category/snacks")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()", Matchers.greaterThan(0))
                .body("[0].category", Matchers.equalTo("snacks"));
    }
}
