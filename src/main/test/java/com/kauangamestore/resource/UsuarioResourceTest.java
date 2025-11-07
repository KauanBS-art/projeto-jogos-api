package com.kauangamestore.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class UsuarioResourceTest {

    @Test
    void deveListarUsuarios() {
        given()
            .when().get("/usuarios")
            .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    void deveCriarEDeletarUsuario() {
        // Cria novo usuário
        String location = given()
            .contentType(ContentType.JSON)
            .body("{"
                + "\"nome\":\"João Tester\","
                + "\"email\":\"joao@test.com\","
                + "\"senha\":\"123456\""
                + "}")
        .when()
            .post("/usuarios")
        .then()
            .statusCode(201)
            .extract().header("Location");

        String id = location.substring(location.lastIndexOf('/') + 1);

        // Exclui o usuário criado
        given()
            .when().delete("/usuarios/" + id)
        .then()
            .statusCode(204);
    }
}